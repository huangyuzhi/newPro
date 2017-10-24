//依赖Comm、bootstrap-select、jQuery。
var Substation = Substation ? Substation : {};
Substation.lastSubstationMarker = null;
Substation.substationDatas = null;
Substation.SubstationImg = "img/station.png";
Substation.baseController = Config.context + "/substation/";
	//获取数据并初始化变电站的下拉框
Substation.generateSubstationCombo = function () {
	Comm.getRemoteData({
		url : Substation.baseController+ "getData.do"
	}, function (substations) {
		substations.unshift({ 
			substationName : "变电站"
		});
		Substation.substationDatas = substations;
		$.each(substations, function(index, substation) {
			if(index > 0) {
				var substationMarker = Substation.generateMarker(substation);
				map.addOverlay(substationMarker);
				substationMarker.addEventListener("click", function() {
					substationMarker.openInfoWindow(Substation.infoWindow(substation));
				});
				substation.marker = substationMarker;
			}
		});
		Comm.generateCombo("substationContainer", "substationCombo", substations, "substationName", function(selectTag){
			//为下拉框添加事件
			selectTag.on("change", function(){
				var index = $('#substationCombo option:selected').val(),
					point,
					substationMarker;
				if(index > 0) {
					substationMarker = Substation.substationDatas[index].marker;
					Substation.selectOverlay(substationMarker.getPosition(), substationMarker);
					substationMarker.openInfoWindow(Substation.infoWindow(Substation.substationDatas[index]));
				}
			});
		});
	});
}

/**
 * 生成一个变电站的标注。参数substationData必须包含bdCoorsX和bdCoorsY两个属性
 */
Substation.generateMarker = function(substationData) {
	return Comm.generateMarker(new BMap.Point(substationData.bdCoorsX, substationData.bdCoorsY), Substation.SubstationImg);
}
//取消选中
Substation.unselectOverlay = function() {
	var lastSubstationMarker;
	if(lastSubstationMarker = Substation.lastSubstationMarker) {
		lastSubstationMarker = null;
	}
}

/**
 * 取消上一次选中的覆盖物，并移动到point指定处。
 */
Substation.selectOverlay = function(point, substationMarker) {
	Substation.unselectOverlay();
	map.closeInfoWindow();
	map.panTo(point);
	Substation.lastSubstationMarker = substationMarker;
}

/**
 * 生成变电站的信息窗口
 */
Substation.infoWindow = function(substationData) {
	var infoWindow = Comm.generateInfoWindow(substationData, "变电站信息", 
		function(prop, dataObj) {
			if(prop == "substationName") {
				return true;
			}
			return false;
		});
	/*var content = "",
		infoWindow;
		for(prop in substationData) {
			if(prop =="substationName" ) {	//信息窗口不展示这些数据
				content += substationData[prop] +"<br/>";
			}
		}
		infoWindow = new BMap.InfoWindow(content);*/
	return infoWindow;
}
