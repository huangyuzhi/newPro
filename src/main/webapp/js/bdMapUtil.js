/*依赖Common、jQuery、百度map对象。*/
var BdMapUtil = BdMapUtil ? BdMapUtil : {};	//命名空间
+ function($, map, Comm) {	
	/**
	 * 移动到指定的区域。调用该函数后，视图将移动到区域的中心点，并且正好能包含整个区域。
	 * 参数bound为百度风格的Bounds。
	 * {
	 * 	sw : BMap.Point	//西南角的坐标
	 *  ne : BMap.Point //东北角的坐标
	 * }
	 */
	BdMapUtil.panToBounds = function(bounds) {
		map.panTo(bounds.sw);
		map.setViewport([bounds.sw, bounds.ne]);
	};
	
	/**
	 * map :
	 * {
	 * 	lng: 经度
	 * 	lat: 纬度
	 * }
	 * 返回一个坐标集合。
	 */
	BdMapUtil.extraCoordsArr = function(datas, map) {
		var result = [];
		$.each(datas, function(k, value) {
			result.push(
				new BMap.Point(value[map.lng ? map.lng : "lng"],
							   value[map.lat ? map.lat : "lat"])
			);
		});
		return result;
	};
}($, map, Comm);


