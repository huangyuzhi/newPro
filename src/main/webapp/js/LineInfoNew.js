/*依赖Common、jQuery、百度map对象。*/
//"use strict";
var LineInfo = LineInfo ? LineInfo : {};	//命名空间
+ function($, map, Comm, PersonPosition) {	
	var color10kv = "#0099CC", 
		color35kv = "#003399",
		colorDixian = "#222222",
		colorTrouble = "red",
		colorFocus = "red",
		//baseController = Config.context ?  Config.context + "lineInfo/" : "/newPro/lineInfo/",
		baseController = Config.context + "/lineInfo/",
		troubleController = Config.context + "/troubleLine/",
		lastSelectBranch ,
		lineManagerFor10kV,
		lineManagerFor35kV;
		/**
		 * 线路管理
		*/
		function LineManager(towers, lineType) {
			var branchs = {};
			if(lineType == "10kV") {
				$.each(towers, function(index, tower) {
					var branchPath = tower.belongLine + "/" + tower.belongBranch + "/" + tower.secondaryClass;
					if(branchs[branchPath]) {
						branchs[branchPath].push(tower);
					}else {
						branchs[branchPath] = [tower];
					}
				});
			}else if(lineType == "35kV") {
				$.each(towers, function(index, tower) {
					var belongLine = tower.belongLine;
					if(branchs[belongLine]) {
						branchs[belongLine].push(tower);
					}else {
						branchs[belongLine] = [tower];
					}
				});
			}
			this.branchs = branchs; 
			this.lineType = lineType;
		}
		
		/**
		 * 获得线路
		 */
		LineManager.prototype.getLine = function(lineName) {
			var resultLine = [],
				lineManager = this,
				lineType = this.lineType;
			$.each(this.branchs, function(path, branch) {
				var blAttr, belongLine,	belongBranch, secondaryClass;
				if(lineType == "10kV") {
					blAttr = path.split("/");
					belongLine = blAttr[0];
					belongBranch = blAttr[1];
					secondaryClass = blAttr[2];
				}else if(lineType == "35kV") {
					belongLine = path;
				}
				if(lineName == belongLine || lineName == "$") {
					//转换为branchLine对象后放入resultLine
					if($.type(branch) == "array") {
						branch= new BranchLine({
							towers : branch,
							belongLine : belongLine,
							branchLine : belongBranch,
							secondaryClass : secondaryClass,
							lineType : secondaryClass == "master_dixian" ? "dixian" : lineType	//入地线缆也属于10kV线路
						});
						lineManager.branchs[path] = branch;
						resultLine.push(branch);
					}else {
						resultLine.push(branch);
					}
				}
			});
			return resultLine;
		};
		
		/**
		 * 显示并返回指定线路
		 */
		LineManager.prototype.show = function(lineName) {
			var line = this.getLine(lineName);
			$.each(line, function(k, brahch) {
				brahch.show();
			});
			return line;
		};
		
		/**
		 * 显示故障线路
		 */
		LineManager.prototype.showTroubleLine = function() {
			var branchs = this.branchs;
			(function(){
				var that = arguments.callee;
				$.ajax({
					   type: "POST",
					   url: troubleController + "getAll.do",
					   cache : false,
					   data : {},
					   dataType : 'json',
					   success: function(troubleLines) {
						   $.each(branchs, function(k, branch) {
							   var isTrouble = false;
							   $.each(troubleLines, function(index, troubleLine) {
								   if(troubleLine.lineQuality == k) {
									   isTrouble = true;
								   }
							   });
							   if(isTrouble) {
								   branch.toTroubleState();
							   }else {
								   branch.revokeTrouble();
							   }
						   });
						   window.setTimeout(that, 10000);
					   }
				});
			})();
		};
		/**
		 * 聚焦到某一条线路
		 */
		LineManager.prototype.focuLine = function(lineName) {
			var linesBranchs = [],
				containMasterBranch = false;
			$.each(this.branchs, function(k, branch) {
				if(lineName == branch.belongLine) {
					branch.show();
					linesBranchs.push(branch);
					if(lineName == branch.branchLine) {	//如果支线路相等则选择该线路
						branch.panTo().focus();
						containMasterBranch = true;
					}
				}else {
					branch.hiden();
				}
			});
			if(!containMasterBranch) {
				//没有与主线路同名的支线路,则选择最后一个支路
				linesBranchs[linesBranchs.length - 1].panTo().focus();
			}
		};
		
		/**
		 * 删除某一条线路
		 */
		LineManager.prototype.delBranch = function(branchName) {
			var branchs = this.branchs,
				removedBranch;
			$.each(branchs, function(branchPath, branch){
				if(branchPath == branchName) {
					removedBranch = branch;
					delete branchs[branchPath];
				}
			});
			return removedBranch;
		};
		
		/**
		 * 对外暴露的接口,类似于门面设计模式。
		 */
		LineInfo.render10kVLine = function() {
			Comm.getRemoteData({ 
				url : baseController + "with10kvForJson.do",
				data : {
					selectField: "id as id, belongLine as belongLine, belongBranch as belongBranch, secondaryClass as secondaryClass, bdCoorsX as bdCoorsX, bdCoorsY as bdCoorsY, towerNumber as towerNumber",
					sort : true,
					sortedBy : "belongLine, belongBranch, secondaryClass, towerSort"
		//			,
		//			belongLine : "10kV炬大线"
				},
				dataType : "json",
				}, function(towers) {
					lineManagerFor10kV = new LineManager(towers, "10kV");
					lineManagerFor10kV.show("$");
					lineManagerFor10kV.showTroubleLine();
			});
		};
		
		LineInfo.show10kVLine = function(lineNmae) {
			lineManagerFor10kV.show(lineNmae);
		};
		LineInfo.focuLine10kV = function(lineNmae) {
			lineManagerFor10kV.focuLine(lineNmae);
		};
		
		LineInfo.render35kVLine = function() {
			Comm.getRemoteData({ 
				url : baseController + "with35kvForJson.do",
				data : {
					selectField: "id as id, belongLine as belongLine, bdCoorsX as bdCoorsX, bdCoorsY as bdCoorsY",
					sort : true,
					sortedBy : "id"
				},
				dataType : "json",
				}, function(towers) {
					lineManagerFor35kV = new LineManager(towers, "35kV");
					lineManagerFor35kV.show("$");
			});
		};
		LineInfo.show35kVLine = function(lineName) {
			lineManagerFor35kV.show(lineName);
		};
		LineInfo.focuLine35kV =  function(lineName) {
			$.each(lineManagerFor35kV.branchs, function(k, branch){
				if(lineName == k) {
					branch.focus();
				}
			});
		};
		
		/*
		 * poly:百度API中的折线对象
		 * addtionalAttr : 为这条线路附加的属性
		 * 
		 * 将地图上的折线转化为一个线路对象
		 * */
		LineInfo.convertToDixianFrom = function(poly, addtionalAttr) {
			var towers = [],
				dixianBranch,
				branchPath = [addtionalAttr.lineName, addtionalAttr.branchName, "master_dixian"].join("/"),
				branchs = lineManagerFor10kV.branchs;
			$.each(poly.getPath(), function(k, point) {
				var virtualTower = {};
				virtualTower.bdCoorsX = point.lng;
				virtualTower.bdCoorsY = point.lat;
				virtualTower.belongLine = addtionalAttr.lineName;
				virtualTower.belongBranch = addtionalAttr.branchName;
				virtualTower.secondaryClass = "master_dixian";
				towers.push(virtualTower);
			});
			dixianBranch = new BranchLine({
				towers : towers,
				belongLine : addtionalAttr.lineName,
				branchLine : addtionalAttr.branchName,
				secondaryClass : "master_dixian",
				lineType : "dixian"
			});
			branchs[branchPath] = dixianBranch;
			dixianBranch.show();
			return dixianBranch;
		};
		
		//表示支路,支路是杆塔的集合
		/*{
		 *	towers :
		 * 	belongLine : 
		 * 	branchLine : 
		 * 	secondaryClass :
		 * 	lineType :  10kV, 35kV, dixian
		 * }*/
		function BranchLine(params) {
			var poly, branch;
			this.belongLine = params.belongLine;
			this.branchLine = params.branchLine;
			this.secondaryClass = params.secondaryClass;
			this.towers =  params.towers;
			this.lineType = params.lineType;
			this.timeOutId = undefined;
			this.state = "normal"; // normal | trouble
			//百度地图组件
			this.poly = lineFactory(params.lineType, extractPoints(params.towers));
			this.polyWidth = this.poly.getStrokeWeight();
			this.contextMenu;
			this.towerMarkers = [];
			
			branch = this;
			poly = this.poly;
			
			poly.addEventListener("mouseover",function(){	//性能优化，在鼠标划入线路时才为线路添加右键菜单
				branch.selectedStyle();
				//添加右键菜单
				if(branch.lineType == "10kV" || branch.lineType == "dixian") {
					if(!branch.contextMenu) {
						branch.contextMenu = branch.genrMenu();
						poly.addContextMenu(branch.contextMenu);
					}
				}
			});
			poly.addEventListener("click",function(e){
				//弹出百度信息窗口，显示当前线路信息
				branch.focus(e.point);
			});
			poly.addEventListener("mouseout", function() {	//性能优化，在鼠标划出线路时移除右键菜单
				branch.unselectedStyle();
				/*if(branch.contextMenu) {
					poly.removeContextMenu(branch.contextMenu);
				}
				branch.contextMenu = undefined;*/
			});
		}

		/**
		 * 线路被选中时的样式
		 */
		BranchLine.prototype.selectedStyle = function () {
			this.poly.setStrokeWeight(5);
		};

		/**
		 * 线路未被选中时的样式
		 */
		BranchLine.prototype.unselectedStyle = function () {
			this.poly.setStrokeWeight(this.polyWidth);
		};

		/**
		 * 在地图上显示该线路
		 */
		BranchLine.prototype.show = function() {
			map.addOverlay(this.poly);
			return this;
		};
		
		/**
		 * 隐藏线路
		 */
		BranchLine.prototype.hiden = function() { 
			map.removeOverlay(this.poly);
		};
		
		/**
		 * 移动
		 */
		BranchLine.prototype.panTo = function() {
			map.panTo(this.polyCenter());
			return this;
		};
		
		/**
		 * 聚焦到某一条线路
		 */
		BranchLine.prototype.focus = function(point) {
			var infoWindow = this.genrtWindow(), 
				branch = this;
			this.unfocus();
			//改变颜色
			this.poly.setStrokeColor("red");
			infoWindow.addEventListener("close", function() {
				branch.unfocus();
			});
			map.openInfoWindow(infoWindow, point || this.polyCenter());
			lastSelectBranch = this;
			return this;
		};
		
		/**
		 * 获得线路的颜色
		 */
		BranchLine.prototype.getLineColor = function() {
			var lineColor;
			if(this.lineType == "10kV") {
				lineColor = color10kv;
			}else if(this.lineType == "35kV") {
				lineColor = color35kv;
			}else if(this.lineType == "dixian"){
				lineColor = colorDixian;
			}else {
				lineColor = undefined;
			}
			return lineColor;
		};
		
		/**
		 * 取消到某一条线路的聚焦
		 */
		BranchLine.prototype.unfocus = function() {
			this.poly.setStrokeColor(this.getLineColor());
			map.closeInfoWindow();
			lastSelectBranch = undefined;
		};
		
		/**
		 * 为线路生成信息窗口
		 */
		BranchLine.prototype.genrtWindow = function() {
			var dataObj = {};
			if(this.lineType == "10kV" || this.lineType == "dixian") {
				dataObj = {
						belongLine : this.belongLine,
						branchName : this.branchLine};
			}else if(this.lineType == "35kV") {
				dataObj = {belongLine : this.belongLine};
			}
			return Comm.generateInfoWindow(dataObj, "线路信息");
		};
		
		BranchLine.prototype.polyCenter = function() {
			var points = this.poly.getPath(),
				coords = points[Math.floor(points.length / 2)];
			return new BMap.Point(coords.lng,coords.lat);		
		};
		
		/**
		 * 将线路设置为trouble状态
		 */
		BranchLine.prototype.toTroubleState = function() {
			var branch = this,
				polyLine = this.poly;
			if(this.state === "normal") {	
				(function() {
					if(polyLine.getStrokeColor() == color10kv) {
						polyLine.setStrokeColor(colorTrouble);
					}else {
						polyLine.setStrokeColor(color10kv);
					}
					branch.timeOutId = window.setTimeout(arguments.callee, 800);
				})();
				this.state = "trouble";
			}else {
				//对于已经是trouble状态的branch不做操作
			}
		};
		/**
		 * 保存故障线路，要保存的线路必须是trouble状态。
		 */
		BranchLine.prototype.storeTrouble = function() {
			if(this.lineType === "10kV" ) {
				if(this.state === "trouble") {
					//存储故障线路信息
					$.ajax({
					   type: "POST",
					   url: troubleController + "add.do",
					   cache : false,
					   data : {lineQuality : this.getBranchIdentifier()},
					   dataType : 'json',
					   success: function(data){
						   //do nothing
					   }
					});
				}
			}else {
				throw new Error("only support LineType : dixian");
			}
		};
		
		/**
		 * 撤消故障线路的标注
		 */
		BranchLine.prototype.revokeTrouble = function() {
			if(this.lineType === "10kV") {
				if(this.state === "trouble") {
					window.clearTimeout(this.timeOutId);
					this.state = "normal";
					this.poly.setStrokeColor(color10kv);
					$.ajax({
					   type: "POST",
					   url: troubleController + "del.do",
					   cache : false,
					   data : {lineQuality : this.getBranchIdentifier()},
					   dataType : 'json',
					   success: function(data){
						   //do nothing
					   }
					});
				}
			}else {
				console.info(this);
				throw new Error("only support LineType : 10kV");
			}
		};
		
		BranchLine.prototype.showTowers = function() {
			var towerMarkers = this.towerMarkers;
			if(towerMarkers.length == 0) {
				$.each(this.towers, function(k,tower) {
					var marker = Comm.generateMarker(new BMap.Point(tower.bdCoorsX, tower.bdCoorsY));
					marker.addEventListener("click", function(){
						var towerWindow = Comm.generateInfoWindow(tower,"杆塔信息", function(prop, dataObj) {
							if(prop == "belongLine" || prop == "belongBranch" || prop == "towerNumber") {
								return true;
							}
							return false;
						});
						marker.openInfoWindow(towerWindow);
					});
					towerMarkers.push(marker);
					map.addOverlay(marker);
				});
			}
		};
		
		BranchLine.prototype.hideTowers = function() {
			var towerMarkers = this.towerMarkers;
			$.each(towerMarkers, function(k,towerMarker) {
				map.removeOverlay(towerMarker);
			});
			this.towerMarkers = [];
		};
		
		BranchLine.prototype.remove = function() {
			if(this.lineType == "dixian") {
				LineInfoController.removeLineInfo(this.belongLine, this.branchLine);
				map.removeOverlay(this.poly);
			}
		};
		
		/**
		 * 根据poly的改变同时调整towers的坐标信息
		 */
		BranchLine.prototype.updateTowers = function() {
			var newTowers = [],
				branch = this;
			if(branch.lineType == "dixian") {
				$.each(branch.poly.getPath(), function(k, point) {
					newTowers.push({
						bdCoorsX : point.lng,
						bdCoorsY : point.lat,
						belongLine : branch.belongLine,
						belongBranch : branch.branchLine,
						secondaryClass : branch.secondaryClass
					});
				});
				branch.towers = newTowers;
			}else {
				throw new Error("only support LineType : dixian");
			}
		};
		
		/**
		 * 获得branch的identifier，10kV和35kV线路的identifier不同。
		 */
		BranchLine.prototype.getBranchIdentifier = function() {
			var lineType = this.lineType,
				identifier;
			if(lineType === "10kV") {
				identifier = [this.belongLine, this.branchLine, this.secondaryClass].join("/");
			}else if(lineType === "35kV") {
				identifier = this.belongLine;
			}
			return identifier;
		};
		/**
		 * 生成线路的工厂
		 */
		function lineFactory(lineType, points) {
			if(lineType === "10kV") {
				return genStrategyBy10kV(points);
			}else if(lineType === "35kV") {
				return genStrategyBy35kV(points);
			}else if(lineType === "dixian") {
				return genStrategyByDixian(points);
			}else {
				throw new Error("不存在指定的线路");
			}
		}
		/**
		 * 10kV线路的生成策略
		 */
		function genStrategyBy10kV(points) {
			var polyLine = new BMap.Polyline(points, {
				strokeColor : color10kv,	//折线颜色	
				strokeWeight : 2.5,			//折线宽度，以像素为单位。 
				strokeOpacity :1,		//折线的透明度，取值范围0 - 1。
				strokeStyle:"solid"		//折线的样式，solid或dashed。
			});
			return polyLine;
		}
		
		/**
		 * 35kV线路的生成策略
		 */
		function genStrategyBy35kV(points) {
			var polyLine = new BMap.Polyline(points, {
				strokeColor : color35kv,	//折线颜色	
				strokeWeight : 2,			//折线宽度，以像素为单位。 
				strokeOpacity :1,		//折线的透明度，取值范围0 - 1。
				strokeStyle:"solid"		//折线的样式，solid或dashed。
			});
			return polyLine;
		}
		
		/**
		 * 入地线缆线路的生成策略
		 */
		function genStrategyByDixian(points) {
			var polyLine = new BMap.Polyline(points, {
				strokeColor : colorDixian,	//折线颜色	
				strokeWeight : 2.5,			//折线宽度，以像素为单位。 
				strokeOpacity :1,		//折线的透明度，取值范围0 - 1。
				strokeStyle:"solid"		//折线的样式，solid或dashed。
			});
			return polyLine;
		}
		
		/**
		 * 从towers杆塔集合中抽取出坐标集合,返回的是一个BMap.Point数组。
		 */
		function extractPoints(towers) {
			var points = [];
			$.each(towers, function(k, tower){
				points.push(new BMap.Point(tower.bdCoorsX, tower.bdCoorsY));
			});
			return points;
		}
		
		BranchLine.prototype.genrMenu = function() {
			var polyMenu = new BMap.ContextMenu(),
				branch = this,
				menuItems = [{
						text : "标注该线路",
						callback : function(point) {
							branch.toTroubleState();
							branch.storeTrouble();
						}
					}, 
					{
						text : "取消标注",
						callback : function(point) {
							branch.revokeTrouble();
						}
				}];
			if(branch.lineType == "10kV") {
				menuItems.push({
					text : "显示杆塔",
					callback : function() {
						branch.showTowers();
					}
				}, {
					text : "隐藏杆塔",
					callback : function() {
						branch.hideTowers();
					}
				},{
					text : "抢修派工", 
					callback : function(point) {
							/*$('#consumeNameGroup').hide();
							$('#consumePhoneGroup').hide();*/
							$('#wqtModel').modal('show');
							initMissionForm(point, {
								title : branch.belongLine + "/" + branch.branchLine,
								content : branch.belongLine + "/" + branch.branchLine,
								destinationDeviceType : "line",
								destinationBdcoordX : point.lng,
								destinationBdcoordY : point.lat,
								lineQuality : branch.getBranchIdentifier()
							});
					}
				});
			}
			if(branch.lineType == "dixian") {	//地线会新增三个菜单项
				menuItems.push({
					text : "编辑",
					callback : function() {
						branch.poly.enableEditing();
					}
				},{
					text : "编辑完成",
					callback : function() {
						branch.updateTowers();
						//LineInfoController._path = "http://127.0.0.1:8080/newPro/dwr/";
						LineInfoController.updateVirtualLine(branch.towers);
						branch.poly.disableEditing();
					}
				},{
					text : "删除",
					callback : function(point) {
						var removedBranch = lineManagerFor10kV.delBranch([branch.belongLine, branch.branchLine, branch.secondaryClass].join("/"));
						removedBranch.remove();
					}
				});
			}
			$.each(menuItems, function(k, v) {
				polyMenu.addItem(new BMap.MenuItem(menuItems[k].text,menuItems[k].callback));
				if(k < menuItems.length - 1) {
					polyMenu.addSeparator();
				}
			});
			return polyMenu;
		};
}(jQuery, map, Comm, PersonPosition);
