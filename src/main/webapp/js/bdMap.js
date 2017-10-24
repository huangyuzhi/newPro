/*创建百度地图对象,依赖百度地图API*/
var map = new BMap.Map("allmap");    // 创建Map实例
+function() {
	var point = new BMap.Point(106.826838, 26.663088);//中心点的位置，初始化后地图显示的位置
    var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
    var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
    map.centerAndZoom(point, 14); 	//初始化地图,设置中心点坐标和地图级别
    map.enableScrollWheelZoom(true);  //开启鼠标滚轮缩放
    map.addControl(top_left_control);
    map.addControl(top_left_navigation);
    map.disableDoubleClickZoom();	//禁用双击缩放
	generateTipControl();	//加上地图信息标志
	
	function generateTipControl() {	//生成地图信息标志
		var tipControl = new BMap.Control();
		tipControl.defaultAnchor = BMAP_ANCHOR_TOP_RIGHT;   
		tipControl.defaultOffset = new BMap.Size(0, 0); 
		tipControl.initialize = function(map) {
			var tipImg = $("<img src='img/identifying.png'></img>")[0];
			 // 添加DOM元素到地图中   
			map.getContainer().appendChild(tipImg);     
			 // 将DOM元素返回  
			return tipImg;  
		},
		map.addControl(tipControl);
	}
}();
   