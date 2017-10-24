var CustomerInfo = CustomerInfo ? CustomerInfo : {};

+function($, map) {
	var baseController = Config.context + "/customer/";
	
	CustomerInfo.findByExample = function(queryData, callback) {
		Comm.getRemoteData({ 
			url : baseController + "findByExample.do",
			data : queryData,
			dataType : "json",
			}, function(customers) {
				callback(customers);
		});
	};
}($, map);