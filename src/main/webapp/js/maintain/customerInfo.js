var CustomerMaintain = CustomerMaintain ? CustomerMaintain : {}; //依赖Jquery、Comm
+function(jQuery){
	var $ = jQuery,
		baseUrl = Config.context + "/customer/";
	CustomerMaintain.setContextPath = function(otherUrl) {
		baseUrl = otherUrl + "/customer/";
	}
	function getCustomerData(params, handlerData) {
		Comm.getRemoteData({
			url : baseUrl + "getCodeMapping.do",
			dataType : "json",
			data : params
		}, handlerData);
	}
	
	function addRequest(customer, handlerResponse) {	//添加请求
		Comm.getRemoteData({
			url : baseUrl + "add.do",
			dataType : "json",
			data : customer
		}, handlerResponse);
	}
	
	function updateRequest(customer, handlerResponse) {	//修改请求
		if(!customer.id) {
			throw new Error("修改请求必须带有id参数");
		}
		Comm.getRemoteData({
			url : baseUrl + "update.do",
			dataType : "json",
			data : customer
		}, handlerResponse);
	}
	
	CustomerMaintain.saveOrUpdate = function(customer, handlerResponse) {
		if(customer.id) { //修改
			updateRequest(customer, handlerResponse);
		}else {	//增加
			addRequest(customer, handlerResponse);
		}
	}; 
	
	CustomerMaintain.del = function(ids, handlerData) {
		Comm.getRemoteData({
			url : baseUrl + "del.do",
			dataType : "json",
			data : {
				ids : ids
			}
		}, handlerData);
	};
	
	CustomerMaintain.fillConsumerCategory = function(selectTag) {
		getCustomerData({
			codeType : "CONSUMER_CATEGORY"
		}, function(codeMaps) {
				Comm.fillSelect({
				select : selectTag,
				id : "codeValue",
				text : "codeName",
				datas : codeMaps
			});
		});
	};
	CustomerMaintain.fillElectricType = function(selectTag) {
		getCustomerData({
			codeType : "ELECTRIC_TYPE"
		}, function(codeMaps) {
				Comm.fillSelect({
				select : selectTag,
				id : "codeValue",
				text : "codeName",
				datas : codeMaps
			});
		});
	};
	CustomerMaintain.fillIndustryClassification = function(selectTag) {
		getCustomerData({
			codeType : "INDUSTRY_CLASSIFICATION"
		}, function(codeMaps) {
				Comm.fillSelect({
				select : selectTag,
				id : "codeValue",
				text : "codeName",
				datas : codeMaps
			});
		});
	};
	CustomerMaintain.fillMeteringMode = function(selectTag) {
		getCustomerData({
			codeType : "METERING_MODE"
		}, function(codeMaps) {
				Comm.fillSelect({
				select : selectTag,
				id : "codeValue",
				text : "codeName",
				datas : codeMaps
			});
		});
	};
	CustomerMaintain.fillRiskLevel = function(selectTag) {
		getCustomerData({
			codeType : "RISK_LEVEL"
		}, function(codeMaps) {
				Comm.fillSelect({
				select : selectTag,
				id : "codeValue",
				text : "codeName",
				datas : codeMaps
			});
		});
	};
	CustomerMaintain.fillUrbanRuralCode = function(selectTag) {
		getCustomerData({
			codeType : "URBAN_RURAL_CODE"
		}, function(codeMaps) {
				Comm.fillSelect({
				select : selectTag,
				id : "codeValue",
				text : "codeName",
				datas : codeMaps
			});
		});
	};
}(jQuery);

