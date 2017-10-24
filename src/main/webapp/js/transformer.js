//依赖Comm、bootstrap-select、jQuery。
var Transformer = Transformer ? Transformer : {};

Transformer.transformers = null;
Transformer.eles = []; 
Transformer.lastTransformer = null;
Transformer.currentTrasList = [];
Transformer.contextPath = Config.context; 
//生成变压器的下拉框并添加事件
Transformer.generateTransformerCombo = function() {
	Comm.getRemoteData({
		url : Transformer.contextPath + "/newPro/transformerInfo/getData.do",
		data : {
			selectField: "id as id, transformerName as transformerName, bdCoorsX as bdCoorsX, bdCoorsY as bdCoorsY, belongLine as belongLine ",
			sort : true,
			sortedBy : "id"
		}
		}, function(transformers) {
			//为下拉框设定第一个文本
			transformers.unshift({
				transformerName : "变压器"
			});
			Transformer.transformers = transformers;
			Comm.generateCombo("transformerComboContainer", "transformerCombo", transformers, "transformerName", function(selectTag) {
				selectTag.on("hidden.bs.select", function(e) {
					var index = $('#transformerCombo option:selected').val(),
						point;
					if(index != 0) {
						point = new BMap.Point(Transformer.transformers[index].bdCoorsX, Transformer.transformers[index].bdCoorsY);
						LineInfo.show10kVLine(Transformer.transformers[index].belongLine); 
						Transformer.select(point, Transformer.infoWindow(Transformer.transformers[index]));
					}
				});
			}) ;
	});
};

//生成一个变压器标注并移动到它的位置。
Transformer.select = function (point, infoWindow) {
	var marker = Transformer.generateMarker(point, "img/transformer.png");
	map.closeInfoWindow();	//先关闭信息窗口
	Transformer.clearCurrentTrans();
	map.panTo(point);
	map.addOverlay(marker);
	if(infoWindow) {	//有信息窗口时，打开信息窗口
		marker.openInfoWindow(infoWindow);
	}
	marker.addEventListener("click", function(e) {
		marker.openInfoWindow(infoWindow);
	});
	$.each(Transformer.events, function(eventName, eventHandler) {
		marker.addEventListener(eventName, function() {
			eventHandler.call(this,infoWindow.blAttr);
		});
	});
	Transformer.lastTransformer = marker;
};

//生成一个标注。
Transformer.generateMarker = function (point, imgUrl) {
	var icon, marker;
	if(imgUrl) {
		icon = new BMap.Icon(imgUrl , new BMap.Size(50,50));
		marker = new BMap.Marker(point,{icon:icon});  // 创建标注
	}else {
		marker = new BMap.Marker(point);
	}
	return marker;
};
/**
 * 根据变压器数据生成变压器
 */
Transformer.generateTrans = function (transDataArr) {
	$.each(transDataArr, function() {
		var marker = Comm.generateMarker(new BMap.Point(this.bdCoorsX, this.bdCoorsY), "img/transformer.png");
		marker.blAttr = this;
		marker.addEventListener("click", function() {
			this.openInfoWindow(Transformer.infoWindow(this.blAttr));
		});
		$.each(Transformer.events, function(eventName, eventHandler) {
			marker.addEventListener(eventName, function() {
				eventHandler.call(this, this.blAttr);
			});
		});
		map.addOverlay(marker);
		//保存当前显示的变压器
		Transformer.currentTrasList.push(marker);
	});
};
//根据客户号查询客户关联的变压器
Transformer.getTransformerByUser = function (customerNumber, callback) {
	Comm.getRemoteData({
		url : Transformer.contextPath + "/newPro/transformerInfo/getTransformer.do",
		data : {
			searchKey : customerNumber
		},
		dataType : "json"
		}, function(transformerArr) {
			map.closeInfoWindow();	//先关闭信息窗口
			Transformer.clearCurrentTrans();
			if(transformerArr && transformerArr.length > 0) {
				if(transformerArr.length == 1) {
					Transformer.select(new BMap.Point(transformerArr[0].bdCoorsX, transformerArr[0].bdCoorsY),  Transformer.infoWindow(transformerArr[0]));
				}else {
					Transformer.generateTrans(transformerArr);
					map.setViewport(BdMapUtil.extraCoordsArr(transformerArr, {
						lng : "bdCoorsX",
						lat : "bdCoorsY"
					}));
				}
			}
			callback && callback(transformerArr);
	});
};

//生成一个显示变压器数据的信息窗口
Transformer.infoWindow = function (transformerData) {
	var infoWindow = Comm.generateInfoWindow(transformerData, "变压器信息", 
		function(prop, dataObj) {
			if(prop == "transformerName") {
				return true;
			}
			return false;
		});
	infoWindow.blAttr = transformerData;
	return infoWindow;
};

//获得变压器关联的线路信息
Transformer.getRelatedLine = function(customerNumber) {
	Comm.getRemoteData({ 
		url : Transformer.contextPath + "/newPro/customerTransformerInfo/getByCostomerNumber.do",
		data : {
			costomerNumber : customerNumber
		}
		}, function(jsonData) {
			jsonData && LineInfo.show10kVLine(jsonData.lineName);
	});
};
//根据线路名显示变压器
Transformer.renderTransbyLineName = function(lineName) {
	if(!lineName) {
		throw new Error ("参数不能为空");
	}
	Comm.getRemoteData({ 
		url : Transformer.contextPath + "transformerInfo/getTransformerByLine.do",
		data : {
			lineName : lineName
		}
	}, function(jsonData) {	//返回的数据是个数组
		Transformer.clearCurrentTrans();
		jsonData.length && $.each(jsonData, function() {
			var marker = Comm.generateMarker(new BMap.Point(this.bdCoorsX, this.bdCoorsY), "img/transformer.png");
			marker.blAttr = this;
			marker.addEventListener("click", function() {
				this.openInfoWindow(Transformer.infoWindow(this.blAttr));
			});
			map.addOverlay(marker);
			//保存当前显示的变压器
			Transformer.currentTrasList.push(marker);
		});		 
	});
};

/**
 * 搜索并返回以指定点坐标为中心的附近变压器。
 */
Transformer.getByCenter = function(point, rad, callback) {
	var offSet = rad / 1000;
	Transformer.getByBound({
		minBdCoordsx : point.lng - offSet,
		minBdCoordsy : point.lat - offSet,
		maxBdCoordsx : point.lng + offSet,
		maxBdCoordsy : point.lat + offSet
	}, callback);
};
/**
 * 搜索并返回范围内的变压器,
 * 参数:{
 * 	minBdCoordsx ,
 * 	minBdCoordsy ,
 * 	maxBdCoordsx ,
 * 	maxBdCoordsy 
 * }
 */
Transformer.getByBound = function(bound, callback) {
	Comm.getRemoteData({ 
		url : Transformer.contextPath + "/newPro/transformerInfo/getDataWithJson.do",
		data : {
			minBdCoordsx : bound.minBdCoordsx,
			minBdCoordsy : bound.minBdCoordsy,
			maxBdCoordsx : bound.maxBdCoordsx,
			maxBdCoordsy : bound.maxBdCoordsy
		},
		dataType : "json"
		}, function(transformers) {
			map.closeInfoWindow();
			Transformer.clearCurrentTrans();
			if(transformers && transformers.length > 0) {
				if(transformers.length == 1) {
					Transformer.select(new BMap.Point( transformers[0].bdCoorsX, transformers[0].bdCoorsY),  Transformer.infoWindow(transformers[0]));
				}else {
					Transformer.generateTrans(transformers);
					map.setViewport(BdMapUtil.extraCoordsArr(transformers, {
						lng : "bdCoorsX",
						lat : "bdCoorsY"
					}));
				}
			}
			callback && callback(transformers);
			
	});
};

/**
 * 清空当前百度地图上的变压器
 */
Transformer.clearCurrentTrans = function() {
	if(Transformer.lastTransformer) {
		map.removeOverlay(Transformer.lastTransformer);
		Transformer.lastTransformer = null;
	};
	if(Transformer.currentTrasList.length) {
		$.each(Transformer.currentTrasList, function() {
			map.removeOverlay(this);
		});
		Transformer.currentTrasList = [];
	}
};
Transformer.events = {};
/**
 * 自定义变压器的事件
 */
Transformer.addEventListener = function(eventName, handler) {
	Transformer.events[eventName] = handler;
};
Transformer.removeEventListener = function(eventName) {
	delete Transformer.events[eventName];
};
