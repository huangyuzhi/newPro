//依赖Comm、bootstrap-select、jQuery、LineInfo。
var Transformer = Transformer ? Transformer : {};
Transformer.contextPath = "";
Transformer.remoteDataType = "json";
+function($, map) {
	var transFormerComboDatas = [],
		events = {},
		currentTransformers = [],
		timeoutId,
		transformerIcon = new BMap.Icon("img/transformer.png" , new BMap.Size(50,50)),
		troubleTransformerIcon = new BMap.Icon("img/transformer_trouble.png" , new BMap.Size(50,50));
	
	/**
	 * 直接加载故障变压器
	 */
	(function(){
		var that = arguments.callee;
		Comm.getRemoteData({
			url : Transformer.contextPath + "transformerInfo/findFaultTransformer.do",
			data : {},
			dataType : Transformer.remoteDataType
			}, function(transformerdatas) {	//维护缓存的变压器对象
				var normalTransformer = [];
				$.each(currentTransformers,function(k, transformer){
					if(transformer.blAttr.isFault) {
						transformer.destory();
					}else{
						normalTransformer.push(transformer);
					}
				});  
				currentTransformers = normalTransformer;
				Transformer.generateTrans(transformerdatas);
				$.each(currentTransformers,function(k, transformer){
					transformer.show();
				});
				timeoutId = window.setTimeout(that, 10*1000);
		});
	})();

	
	Transformer.transformerCombo = function(options) {
		Comm.getRemoteData({
			url : Transformer.contextPath + "transformerInfo/getDataWithJson.do",
			data : {
				selectField: "id as id, transformerName as transformerName, bdCoorsX as bdCoorsX, bdCoorsY as bdCoorsY, belongLine as belongLine ",
				sort : true,
				sortedBy : "id"
			},
			dataType : Transformer.remoteDataType
			}, function(transformers) {
				//为下拉框设定第一个文本
				/*transformers.unshift({
					transformerName : "变压器",
					id : 0
				});*/
				transFormerComboDatas = transformers;
				options.datas = transformers;
				Comm.fillSelect(options);
				$(options.select).selectpicker("refresh");
		});
	};
	
	Transformer.getComboData = function(id) {
		var result = null;
		$.each(transFormerComboDatas, function(index, transData){
			if(transData.id == id) {
				result = transData;
				return;
			}
		});
		return result;
	};
	
	/**
	 * 根据客户号或客户姓名查询客户关联的变压器
	 */
	Transformer.searchTrans = function (searchKey, callback) {
		Comm.getRemoteData({
			url : Transformer.contextPath + "transformerInfo/getTransformer.do",
			data : {
				searchKey : searchKey
			},
			dataType : Transformer.remoteDataType 
			}, function(transformerArr) {
				handleTransData(transformerArr);
				callback && callback(transformerArr);
		});
	};
	
	
	/**
	 * 获得变压器关联的线路信息
	 */
	Transformer.getRelatedLine = function(customerNumber) {
		Comm.getRemoteData({ 
			url : Transformer.contextPath + "customerTransformerInfo/getByCostomerNumber.do",
			data : {
				costomerNumber : customerNumber
			}
			}, function(jsonData) {
				jsonData && LineInfo.show10kVLine(jsonData.lineName);
		});
	};
	
	Transformer.getRemoteTransformer = function(id, afterResponse) {
		Comm.getRemoteData({ 
			url : Transformer.contextPath + "transformerInfo/getDataWithJson.do",
			data : {
				id : id
			},
			dataType : Transformer.remoteDataType
			}, function(transformers) {
				afterResponse && afterResponse(transformers);
		});
	};
	
	/**
	 * 变压器对象
	 */
	function Transfor(transdata) {
		this.blAttr = transdata;
		this.marker = Comm.generateMarkerByIcon(new BMap.Point(transdata.bdCoorsX, transdata.bdCoorsY), transformerIcon);
		this.infoWindow = transformerInfoWindow(transdata);
		//this.timeoutId;
		var trans = this;
		this.marker.addEventListener("click", function() {
			this.openInfoWindow(trans.infoWindow);
		});
		$.each(events, function(eventName, eventHandler) {
			trans.marker.addEventListener(eventName, function() {
				eventHandler.call(trans);
			});
		});
		if(trans.blAttr.isFault) {
			trans.faultStyle();
		}
	};
	
	/**
	 * 故障样式。
	 */
	Transfor.prototype.faultStyle = function() {
		var trans = this;
		trans.marker.setIcon(troubleTransformerIcon);
	/*	var trans = this;
		if(trans.marker.getIcon() == troubleTransformerIcon) {
			trans.marker.setIcon(new BMap.Icon(transformerIcon));
		}else {
			trans.marker.setIcon(new BMap.Icon(troubleTransformerIcon));
		}
		trans.timeoutId = window.setTimeout(arguments.callee, 800);*/
	};
	
	/**
	 * 普通样式。
	 */
	Transfor.prototype.normalStyle = function(){
		var trans = this;
		trans.marker.setIcon(transformerIcon);
	};
	/**
	 * 设置变压器的故障状态,true：故障，false：正常。
	 */
	Transfor.prototype.changeFaultState = function(destinationState) {
		/*var trans = this;
		if(destinationState == null){throw new Error("参数不能为空")};
		
		if(Boolean(trans.blAttr.isFault) != destinationState) {	//状态要改变
			if(destinationState) {	//故障状态
				//故障样式
				trans.faultStyle();
			}else {	//正常状态
				window.clearTimeout(trans.timeOutId);
				trans.marker.setIcon(new BMap.Icon(transformerIcon));
			}
		}else {
			//状态不改变，do nothing
		}*/
	};
	Transfor.prototype.toFaultState = function() {
		this.blAttr.isFault = true;
		this.faultStyle();
	};
	Transfor.prototype.toNormal = function() {
		this.blAttr.isFault = false;
		this.normalStyle();
	};
	/**
	 * 将变压器显示在百度地图上。
	 */
	Transfor.prototype.show = function() {
		map.addOverlay(this.marker);
		return this;
	};
	
	/**
	 * 定位到一个变压器上
	 */
	Transfor.prototype.locate = function() {
		map.panTo(this.marker.getPosition());
		this.marker.openInfoWindow(this.infoWindow);
		return this;
	};

	/**
	 * 销毁变压器对象
	 */
	Transfor.prototype.destory = function() {
		this.marker.closeInfoWindow();
		map.removeOverlay(this.marker);
	};
	
	/**
	 * 通过线路名查找变压器
	 */
	Transformer.renderTransbyLineName = function(lineName) {
		if(!lineName) {
			throw new Error ("参数不能为空");
		}
		Comm.getRemoteData({ 
			url : Transformer.contextPath + "transformerInfo/getTransformerByLineWithJson.do",
			data : {
				lineName : lineName
			},
			dataType : Transformer.remoteDataType
		}, function(transformerDatas) {	//返回的数据是个数组
			Transformer.clearCurrentTrans();
			if(transformerDatas.length > 0) {
				Transformer.generateTrans(transformerDatas);
				$.each(currentTransformers, function(k, transformer) {
					transformer.show();
				});
			}
		});
	};
	/**
	 * 根据变压器数据生成变压器
	 */
	Transformer.generateTrans = function(transDataArr) {
		$.each(transDataArr, function(index, transData) {
			currentTransformers.push(new Transfor(transData));
		});
	};
	
	/**
	 * 根据变压器数据生成变压器并显示
	 */
	Transformer.generateTransAndShow = function(transDataArr) {
		Transformer.clearCurrentTrans();
		Transformer.generateTrans(transDataArr);
		if(currentTransformers.length == 1) {
			currentTransformers[0].show();
			currentTransformers[0].locate();
		}else {
			$.each(currentTransformers, function(k, transformer) {
				transformer.show();
			});
		}
	};
	/**
	 * 清除所有变压器
	 */
	Transformer.clearCurrentTrans = function() {
		$.each(currentTransformers, function(key, transFormer) {
			if(!transFormer.blAttr.isFault) {
				transFormer.destory();
			}
		});
		currentTransformers = [];
	};
	
	Transformer.addEventListener = function(eventName, handler) {
		events[eventName] = handler;
	};
	Transformer.removeEventListener = function(eventName) {
		delete events[eventName];
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
			dataType : Transformer.remoteDataType
			}, function(transformers) {
				handleTransData(transformers);
				callback && callback(transformers);
		});
	};
	
	/**
	 * 处理变压器数据
	 */
	function handleTransData(transformers) {
		var transfor = null;
		Transformer.clearCurrentTrans();
		if(transformers && transformers.length > 0) {
			if(transformers.length == 1) {
				transfor = new Transfor(transformers[0]);
				transfor.show();
				transfor.locate();
				currentTransformers.push(transfor);
			}else {
				Transformer.generateTrans(transformers);
				map.setViewport(BdMapUtil.extraCoordsArr(transformers, {
					lng : "bdCoorsX",
					lat : "bdCoorsY"
				}));
				$.each(currentTransformers, function(index, transformer) {
					transformer.show();
				});
			}
		}
	}
	/**
	 * 生成变压器信息窗口
	 */
	function transformerInfoWindow(transdata) {
		var infoWindow = Comm.generateInfoWindow(transdata, "变压器信息", 
				function(prop, dataObj) {
					if(prop == "transformerName") {
						return true;
					}
					return false; 
				});
		return infoWindow;
	}
}($, map);