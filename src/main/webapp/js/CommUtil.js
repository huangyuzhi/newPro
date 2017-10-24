var Comm = Comm ? Comm : {};
/*
 * 公共方法，生成下拉框。指定的下拉框以及生成的下拉框都是bootStrap的select组件。
 * containerId指定容器的id， select指定select标签的id，dataList是数据数组，itemField是生成的option的文本值，afterGenerateCombo是回调函数。
*/
Comm.generateCombo = function (containerId, selectId, dataList, itemField, afterGenerateCombo) {
	var i = 0,
		selecteTag = $("<select id='"+selectId+"' data-live-search='true'></select>");
	$("#" + selectId).selectpicker("destroy");	//删除旧的下拉框
	for(; i < dataList.length; i++) {
		$("<option value="+i+">"+dataList[i][itemField]+"</option>").appendTo(selecteTag);
	}
	$("#" + containerId).append(selecteTag);
	selecteTag.selectpicker({
			size: 7
	});
	if(afterGenerateCombo) {
		afterGenerateCombo(selecteTag);
	}
};

/**
  * 获取远程服务器上的数据。默认为jsonp形式发送请求。
  * 必须设置options的url和data属性，dataType用于改变请求的类型。常用值有"json", "jsonp"。
 */
Comm.getRemoteData = function (options, callback) {
	var url;
	if(options.url && options.url.indexOf("?") > -1) {
		url = options.url + "&" + Comm.randomQueryParam();
	}else{
		url = options.url + "?" + Comm.randomQueryParam();
	}
	$.ajax({
        type: "post",
        async: true,
        cache : false,
        url: url,
        dataType: options.dataType ? options.dataType : "jsonp",
        data : options.data ? options.data : {},
        jsonp: "callback",//传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(一般默认为:callback)
        success: function(json){
        	callback(json);
        }
	});
};

/**
 * 生成标注。
 */
Comm.generateMarker = function (point, imgUrl) {
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
 * 根据图标对象生成标注。
 */
Comm.generateMarkerByIcon = function(point, icon) {
	var marker;
	if(icon) {
		marker = new BMap.Marker(point,{icon:icon});
	}else{
		marker = new BMap.Marker(point);
	}
	return marker;
};
/**
 * 重置表单
 */
Comm.resetForm = function(formSelector, afterReset) {
	$(formSelector).find(":input")
		.val("");
	afterReset && afterReset();	
};

/**
 * 序列化form表单,当一个select选择多值时，返回的值是一个数组
 */
Comm.SerializerForm = function(formSelector) {
	var result = {};
	$(formSelector).find(":input").each(function(index, domEle) {
		if(domEle.type !== "button") {	//不序列化button的值
			var name = $(domEle).attr("name");
			if(result[name]) {
				result[name] +=  "," + $(domEle).val();
			}else {
				result[name] = $(domEle).val();
			}
		}
	});
	return result;
};

/**
 * 用数据填充一个表格
 */
Comm.fillForm = function(formSelector, formData) {
	$(formSelector + " :input").each(function(index, domEle){
		$.each(formData, function(k, v) {
			if($(domEle).attr("name") == k) {
				$(domEle).val(v);
			}
		});
	});
};

/**
 * 返回一个url的随机参数
 */
Comm.randomQueryParam = function() {
	return "_r_" + Math.random();
};
/**
 * 填充下拉框
 * options : {
 *	   select : selector for selectTag,
 *     datas : [],
 * 	   id : "id",
 * 	   text : "text",
 * }
 */
Comm.fillSelect = function(options) {
	if(!options) {
		throw new Error("必须指定参数");
	}
	options.datas || (options.datas = []);
	options.id || (options.id = "id");
	options.text || (options.text = "text");
	$.each(options.datas, function(k, data) {
		var selecteTag = $(options.select);
		$("<option value="+data[options.id]+">"+data[options.text]+"</option>").appendTo(selecteTag);
	});
};
Comm.handlerJsonResponse = function(json) {
	if(json.success) {
		Comm.popNotify(true, json.msg);
	}else{
		Comm.popNotify(false, json.msg);
	}
};
/**
 * 生成信息窗口。dataObj是数据，caption是标题，filterFun是数据过滤函数。
 */
Comm.generateInfoWindow = function (dataObj, caption, filterFun) {
	var content = "<table class=table table-condensed>" 
				+ 		"<caption>"+caption+"</caption>	"
				+		"<tbody></tbody>"
				+ "</table>",
		tr, propZh;
		content = $(content);
	filterFun = filterFun || function(prop, dataObj) {	//默认策略
		if(prop != "id" && prop != "bdCoorsX" && prop != "bdCoorsY") {
			return true;
		}
		return false;
	};
	
	for(prop in dataObj) {
		if(filterFun(prop, dataObj)) {
			if(prop == "phone") {
				propZh = "手机号";
			}
			if(prop == "name") {
				propZh = "姓名";
			}
			if(prop == "towerNumber" ) {
				propZh = "杆塔编号";
			}
			if(prop == "lineName" || prop == "belongLine" ) {
				propZh = "线路名";
			}
			if(prop == "branchName" || prop == "belongBranch" ) {
				propZh = "支路名";
			}
			if(prop == "transformerName") {
				propZh = "变压器名";
			}
			if(prop == "substationName") {
				propZh = "变电站名";
			}
			if(prop == "workState") {
				propZh = "派工状态";
			}
			if(prop == "replyContent") {
				propZh = "任务内容";
			}
			tr = "<tr><td><strong>"+propZh+"</strong></td><td>"+dataObj[prop]+"</td></tr>";
			tr = $(tr);
			tr.appendTo(content.children("tbody"));
		}
	}
	return new BMap.InfoWindow(content[0]);
};
Comm.createLabel = function(content, point) {
	var opts = {
			  position : point,    // 指定文本标注所在的地理位置
			  offset   : new BMap.Size(30, -10)    //设置文本偏移量
			};
	var label = new BMap.Label(content, opts);  // 创建文本标注对象
	label.setStyle({
		 borderColor : "transparent",
		 color : "red",
		 fontSize : "12px",
		 height : "20px",
		 lineHeight : "20px",
		 fontFamily:"微软雅黑"
	 });
	return label;
};
Comm.popNotify = function(success, msg) {
	$.amaran({
        content:{ 
            message: msg,
            color : success ? null : "red"
        }
	});
};

/**
 * 去重函数。返回的数组每个对象只出现一次。
 */
Comm.collectionArray = function(array) {
	var result = [];
	$.each(array, function(index, value) {
		if(!Comm.isArrayContains(result, value)) {
			result.push(value);
		}
	});
	return result;
};

Comm.isArrayContains = function(array, key) {
	var result = false;
	$.each(array, function(index, value) {
		if(value == key) {
			result = true;
		}
	});
	return result;
};

/**
 * 从对象数据中抽取出option元素的数据。
 * objArr : 要处理对象数组。
 * params : {
 * 	id : expression (option元素的value) 
 * 	label :expression (option元素的文本)
 * }
 */
Comm.optionModel = function(objArr, params){
	var options = [];
	$.each(objArr, function(index, obj) {
		var option = {};
		option.id = Comm.extractProp(obj, params.id);
		option.label = Comm.extractProp(obj, params.label);
		options.push(option);
	});
	return options;
};
/**
 * 从对象数组中抽取指定属性，返回该属性值的数组。
 * objArr : 要处理对象数组。
 * expression : 属性表达式。
 */
Comm.propArray = function(objArr, expression) {
	var result = [];
	$.each(objArr, function(index, obj) {
		result.push(Comm.extractProp(obj, expression));
	});
	return result;
};

/**
 * 从对象中抽取出指定的属性。
 * expression: 属性表达式，支持"."符号。
 */
Comm.extractProp = function(obj, expression) {
	var targetProp = obj;
	$.each(expression.split("."), function(index, objProp) {
		targetProp = targetProp ? targetProp[objProp] : null;
	}); 
	return targetProp;
};