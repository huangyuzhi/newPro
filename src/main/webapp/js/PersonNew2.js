//依赖Comm、jQuery、map
var PersonPosition = PersonPosition ? PersonPosition : {};
/**
 * 初始化人员位置模块，该方法只执行一次。
 */
PersonPosition.renderPersons = function(option) {
	var setting = option || {},
		personInfos = {}, 
		servicemans,
		crontabId,
		interval = 10000,
		normalIcon = new BMap.Icon("img/user.png" , new BMap.Size(50,50)),
		workingIcon = new BMap.Icon("img/user_working.png" , new BMap.Size(50,50));
	
	Comm.getRemoteData({
		url : "PersonPosition/getServiceman.do",
		dataType : "json",
		
	}, function(data) {	//存储抢修人员数据
		servicemans = data;
	});
	/**
	 * 定时任务
	 */
	PersonPosition.timeC = function() {
		Comm.getRemoteData({
			url : "PersonPosition/getPersonPositionWithJson.do",
			data : {
				pageSize : 500
			},
			dataType : "json"
		}, function(positions) {
			if(positions && positions.result) {
				/*$.each(trimPositions(positions.result), function(prop, value){
					personInfos[prop] = new Person(value);
				});*/
				updatePersonInfos(trimPositions(positions.result));
			}
			crontabId = setTimeout("PersonPosition.timeC()", interval);
		});
	};
	PersonPosition.timeC();
	
	/**************外部接口****************/
	/**
	 * 停止定时任务
	 */
	PersonPosition.stopCrontAndClearMarker = function() {
		clearTimeout(crontabId);
		clearMarkerFromMap();
	};
	PersonPosition.getPersonInfos = function() {
		return personInfos;
	};
	
	/**
	 * 返回离给定位置最近的人
	 */
	PersonPosition.minDistPerson = function(point, persons) {
		var minDistance = Number.POSITIVE_INFINITY, //初始化最短距离为正无穷大
			minDistancePerson = undefined;
		$.each(persons ? persons : PersonPosition.getPersonInfos(),
			function(key, person) {
				var distance = Math.pow(point.lng - person.lng, 2)
						+ Math.pow(point.lat	- person.lat, 2);
				if (distance < minDistance) {
					minDistancePerson = person;
					minDistance = distance;
				} else {
					//do nothing
				}
			});
		return minDistancePerson;
	};
	
	/**
	 * 根据指定的属性查询人员
	 * 如根据电话号码查询：
	 *{
	 *	phone : xxxx
	 *} 
	 *根据外勤通的id查询:
	 *{
	 *	memberId : xxxxx
	 *}
	 */
	PersonPosition.getPersonByParam = function(param) {
		var resultPerson = [];
		$.each(PersonPosition.getPersonInfos(), function(key, person) {
			var isMatch = true;
			$.each(param, function(prop, propValue) {
				if(Comm.extractProp(person.attr, prop) != propValue) {
					isMatch = false;
				}
			});
			isMatch && resultPerson.push(person);
		});
		return resultPerson;
	};
	
	/**
	 * 返回负责人
	 */
	PersonPosition.getMasterServiceman = function(filter) {
		var result = [];
		filter = filter ? filter : function (index, serviceman) {
			if(serviceman.post === "m" || serviceman.post === "dm" || serviceman.deptName === "BY") {
				return true;
			}else {
				return false;
			}
		};
		$.each(servicemans, function(index, serviceman) {
			if(serviceman) {
				if(filter(index, serviceman)) {
					result.push(serviceman);
				}
			}
		});
		return result;
	};
	
	/**
	 * 返回一般巡检人员
	 */
	PersonPosition.getServiceman = function(deptName) {
		var resultPerson = [];
		if(deptName) {
			resultPerson = PersonPosition.getPersonByParam({
				"serviceman.post" : "g",
				"serviceman.deptName" : deptName
			});
			resultPerson.concat(PersonPosition.getPersonByParam({
				"serviceman.post" : "BY",
			}));
		}else {
			resultPerson = PersonPosition.getPersonByParam({
				"serviceman.post" : "g"
			});
		}
		return resultPerson;
	};
	
	PersonPosition.servicemanOptionModels = function() {
		return Comm.optionModel(PersonPosition.getServiceman(), {
			id : "attr.memberId",
			label : "attr.name"
		});
	}
	
	PersonPosition.mastermanOptionModels = function() {
		return Comm.optionModel(PersonPosition.getMasterServiceman(), {
			id : "wqtMemberId",
			label : "terminalName"
		});
	}
	/**
	 * 指派任务
	 */
	PersonPosition.assignMission = function(mission, afterHandler) {
		Comm.getRemoteData({
			url : "faultStatisticsDetail/assignMissionAndStatistics.do",
			data : mission,
			dataType : "json"
		}, function(response) {
			afterHandler && afterHandler(response);
		});
		
	};
	
	function Person(personInfo) {
		this.attr = personInfo;
		this.lng = personInfo.userLng;
		this.lat = personInfo.userLat;
		this.positionHistory = [];
		this.lastUpdateTime = new Date();
		this.showTrack = false;	//是否显示轨迹
		this.curveLine;
		this.maxStorePosition = personInfo.maxStorePosition || 500;	//最大存储位置信息的数量
		
		this.driving;	//导航对象
		this.drivingFun;
		this.marke;
		if(personInfo.wqtMission && personInfo.wqtMission.status == 2) {	//派工状态
			this.marke = Comm.generateMarkerByIcon(new BMap.Point(this.lng, this.lat),workingIcon);
		}else {	//完工或待机
			this.marke = Comm.generateMarkerByIcon(new BMap.Point(this.lng, this.lat),normalIcon);
		}
		var person = this,
			personWindow;
		if(personInfo.wqtMission && personInfo.wqtMission.status == 2) {
			personWindow = Comm.generateInfoWindow({name : person.attr.name, phone : person.attr.phone, 
				workState : "派工中"
				}, "人员信息");
		}else if(personInfo.wqtMission && personInfo.wqtMission.status == 3) {
			personWindow = Comm.generateInfoWindow(
					{
					 name : person.attr.name, 
					 phone : person.attr.phone, 
					 workState : "派工已完成",
					 replyContent : person.attr.wqtMission.replyContent
					}, "人员信息");
		}else {
			personWindow = Comm.generateInfoWindow(person.attr, "人员信息", function(prop, dataObj) {
				if(prop == "name" || prop == "phone") {
					return true;
				}
				return false;
			});
		}
		personWindow.addEventListener("open", function(e) {
			/*person.showTrack = true;
			person.drawTrack();*/
		});
		personWindow.addEventListener("close", function(e) {
			person.showTrack = false;
			person.removeLine();
		});
		person.marke.addEventListener("click", function() {
			this.openInfoWindow(personWindow);
		});
		map.addOverlay(this.marke);
	}
	
	Person.prototype.show = function() {
		map.addOverlay(this.marke);
	};
	
	Person.prototype.updateBlAttr = function(personInfo) {
		this.lng = personInfo.userLng;
		this.lat = personInfo.userLat;
		var person = this, 
			personWindow;
		map.removeOverlay(this.marke);
		if(personInfo.wqtMission && personInfo.wqtMission.status == 2) {	//派工状态
			this.marke = Comm.generateMarkerByIcon(new BMap.Point(this.lng, this.lat),workingIcon);
		}else {	//完工或待机
			this.marke = Comm.generateMarkerByIcon(new BMap.Point(this.lng, this.lat),normalIcon);
		}
		map.addOverlay(this.marke);
		if(personInfo.wqtMission && personInfo.wqtMission.status == 2) {
			personWindow = Comm.generateInfoWindow({name : person.attr.name, phone : person.attr.phone, workState : "派工中"}, "人员信息");
		}else if(personInfo.wqtMission && personInfo.wqtMission.status == 3) {
			personWindow = Comm.generateInfoWindow(
					{
					 name : person.attr.name, 
					 phone : person.attr.phone, 
					 workState : "派工已完成",
					 replyContent : personInfo.wqtMission.replyContent
					}, "人员信息");
		}else {
			personWindow = Comm.generateInfoWindow(person.attr, "人员信息", function(prop, dataObj) {
				if(prop == "name" || prop == "phone") {
					return true;
				}
				return false;
			});
		}
		person.marke.addEventListener("click", function() {
			this.openInfoWindow(personWindow);
		});
	};
	
	/**
	 * 转换到派工样式
	 */
	Person.prototype.toWorkingStyle = function() {
		this.marke.setIcon(workingIcon);
	};
	
	Person.prototype.removeLine = function() {
		map.removeOverlay(this.curveLine);
	};
	/**
	 * 更新人员的位置信息。
	 * 参数对象:
	 * {
	 * 	lng : 经度
	 *  lat : 纬度
	 * }
	 */
	Person.prototype.updatePosition = function(position) {
		//将当前的经纬度放入人员位置的历史记录里,历史记录最多放100条数据,具体条数可以设定。
		if(this.positionHistory.length > this.maxStorePosition) {
			this.positionHistory.shift();
		}
		this.positionHistory.push(new BMap.Point(this.lng, this.lat));
		this.lng = position.lng;
		this.lat = position.lat;
	};
	/**
	 * 将人员当前的位置显示在地图上。
	 */
	Person.prototype.showPositionInMap = function() {
		if(this.marke) {
			this.marke.setPosition(new BMap.Point(this.lng, this.lat));	
		}else {
			//do nothing
		}
	};
	/**
	 * 更新人员信息并显示在地图上。
	 */
	Person.prototype.updatePositionAndShow = function(position) {
		this.updatePosition(position);
		this.showPositionInMap();
		this.drawTrack();
		this.drivingFun && this.drivingFun();
	};
	
	/**
	 * 在地图上显示人员轨迹。
	 */
	Person.prototype.drawTrack = function() {
		var track = [],
			curveLine = this.curveLine;	
		if(!this.showTrack) {
			return;
		}
		track = this.positionHistory.slice(0, this.positionHistory.length);
		track.push(new BMap.Point(this.lng, this.lat));
		curveLine && map.removeOverlay(curveLine);
		this.curveLine = new BMap.Polyline(track, {strokeColor:"#FF6600", 
													strokeWeight:4,
													strokeStyle : "dashed"});
		map.addOverlay(this.curveLine); //添加到地图中
	};
	
	/**
	 * 销毁一个人员对象，包含它再地图上的marker和curveLine
	 */
	Person.prototype.destory = function() {
		clearDriveRoute();
		map.removeOverlay(this.curveLine);
		map.removeOverlay(this.marke);
	};
	
	/**
	 * 生成导航对象
	 */
	Person.prototype.genDrive = function() {
		var person = this;
		var	driving = new BMap.DrivingRoute(map, {renderOptions:{map: map, 
			autoViewport: false,
			enableDragging : false //起终点可进行拖拽
			},
			onMarkersSet :function(localResultPois) {
				var driveRoute = driving,
					endMarker = localResultPois[1].marker,
					menu;
				map.removeOverlay(localResultPois[0].marker);
				endMarker.addEventListener("mouseover", function() {
					var markerMenu = new BMap.ContextMenu(),
						menuItems = [{
							text : "取消导航",
							callback : function() {
								driveRoute.clearResults();
								person.clearDriveRoute();
							}
						}];
					$.each(menuItems, function(k, v) {
						markerMenu.addItem(new BMap.MenuItem(menuItems[k].text,menuItems[k].callback));
						if(k < menuItems.length - 1) {
							markerMenu.addSeparator();
						}
					});
					endMarker.addContextMenu(markerMenu);
					menu = markerMenu;
				});
			},
			onPolylinesSet : function(routes) {
				var poly = routes[0].getPolyline();
				poly.setStrokeColor("red");
			}
		});
		return driving;
	};
	
	/**
	 * 搜索导航路线
	 */
	Person.prototype.drivingSearch = function(destination) {
		if(!this.driving) {
			this.driving = this.genDrive();
			this.drivingFun = function() {
				this.driving.search(this.marke.getPosition(), destination);
			};
			this.drivingFun();
		}
	};
	/**
	 * 车辆导航,指定目标地点
	 */
	Person.prototype.clearDriveRoute = function() {
		if(this.driving) {
			this.driving.clearResults();
		}
		this.driving = undefined;
		this.drivingFun = undefined;
	};
	
	/**
	 * 根据新的位置信息维护personInfos
	 */
	function updatePersonInfos(newPositions) {
		var newPersonInfos = {},
			retainPersonPhones = [];
		$.each(newPositions, function(index, newPosition){
			var newPerson,
				oldPerson = personInfos[newPosition.phone];
			if(oldPerson) {	//更新新的位置信息
				retainPersonPhones.push(newPosition.phone);
				oldPerson.updateBlAttr(newPosition);
				if(newPosition.wqtMission && newPosition.wqtMission.missionId ) {
					if(newPosition.wqtMission.status == 3) {
						oldPerson.clearDriveRoute();
					}
				}else {
					oldPerson.clearDriveRoute();
				}
				oldPerson.updatePositionAndShow({lng : newPosition.userLng, lat : newPosition.userLat});
				newPersonInfos[newPosition.phone] = oldPerson;
			}else {	//新增的人员信息
				newPerson = new Person(newPosition);
				if(newPosition.wqtMission && newPosition.wqtMission.missionId && newPosition.wqtMission.status != 3) {
					newPerson.drivingSearch(new BMap.Point(newPosition.wqtMission.destinationBdcoordX, newPosition.wqtMission.destinationBdcoordY));
				}
				newPersonInfos[newPosition.phone] = newPerson;
			}
		});
		//删掉所有旧的person
		$.each(personInfos, function(identifier, person) {
			if($.inArray(person.attr.phone, retainPersonPhones) === -1) {
				person.destory();
			}
		});
		personInfos = newPersonInfos;
	}
	
	/**
	 * 去掉人员信息中的重复数据,保留最新的位置值，并返回一个map结构
	 */
	function trimPositions(positions) {
		var resultPosition = {};
		$.each(positions, function(index, person) {
			var sameKeyPerson = resultPosition[person.phone];
			if(sameKeyPerson) {
				//date值较大则更新
				if(sameKeyPerson.createTime < person.createTime) {
					rresultPosition[person.phone] = person;
				}
			}else {
				resultPosition[person.phone] = person;
			}
		});
		return resultPosition;
	}
};