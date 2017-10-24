<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"
 session="true"%>
 <%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<base href="<%=path%>/"/>
	<meta name="viewport" content="width=device-width,initial-scale=1.0">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>tableindex</title>
	<!--BOOTStrap-->
	<link rel="stylesheet" href="css/bootstrap.css" />
	<!-- global styles -->
	<link rel="stylesheet" type="text/css" href="css/layout.css" />
	<link rel="stylesheet" type="text/css" href="css/elements.css" />
	<link rel="stylesheet" type="text/css" href="css/font-awesome-4.4.0/css/font-awesome.min.css" />

	<!-- this page specific styles -->
	<link rel="stylesheet" href="css/tables.css" type="text/css" media="screen" />

	<!-- bootstrap-table  -->
	<link rel="stylesheet" href="css/bootstrap-table.css">
	
	<!-- 日期范围选取css -->
	<link rel="stylesheet" href="css/daterangepicker.css">
    <style>
    	* {
    		font-family: "微软雅黑";
    	}
    	/*设置模态框样式*/
    	.modal-top {
    		margin-top: 60px;
    	}
    	/*设置验证消息样式*/
    	.help-block {
    		font-size : 12px;
    	}
    </style>
</head>
<body>
<!-- navbar -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="index.do">
				<img src="img/maplogo.png" class="img-responsive"/>
			</a>
			<div class="navbar-brand">
				<div class="brand-text">
					<span>贵州电网公司乌当供电局配网抢修服务调度监控平台</span>
				</div>
			</div>
		</div>
	</div><!-- /.container-fluid -->
</nav>
<!-- end navbar -->

<!-- sidebar -->
<div id="sidebar-nav">
	<ul id="dashboard-menu">
		<li>
			<a href="customer/maintainView.do">
				<i class="fa fa-users" aria-hidden="true"></i>
				<span>用户信息</span>
			</a>
		</li>
		<li class="active">
			<a href="customer/customerChangeInfo.do">
				<div class="pointer">
					<div class="arrow"></div>
					<div class="arrow_border"></div>
				</div>
				<i class="fa fa-plus-square"></i>
				<span>变更信息</span>
			</a>
		</li> 
		<li>
			<a href="faultStatisticsDetail/maintainView.do">
				<i class="fa fa-table"></i>
				<span>报修细节</span>
			</a>
		</li>
		<li>
			<a href="faultStatisticsDetail/statistics.do">
				<i class="fa fa-bar-chart"></i>
				<span>报修统计</span>
			</a>
		</li>
		<li>
			<a href="transformerInfo/maintainTransformer.do">
				<i class="fa fa-bar-chart"></i>
				<span>变压器维护</span>
			</a>
		</li>
		<li>
			<a class="dropdown-toggle" href="#">
				<i class="fa fa-mail-forward"></i>
				<span>返回</span>
				<i class="fa fa-chevron-down"></i>
			</a>
			<ul class="submenu">
				<li><a href="index.do">返回控制界面</a></li>
				<li><a href="logout.do">退出</a></li>
			</ul>
		</li>
	</ul>
</div>
<!-- end sidebar -->

<!-- main container -->
<div class="content">
    <div class="container-fluid">
        <div id="pad-wrapper">
        	<div id="toolbar" class="row"> 
        		<div class="col-sm-9">
        			<div id="searchGroup" class="input-group">
        				<form  role="form">
							<div class="input-group">
							<input type="text" id="daterange" name="daterange" class="form-control" style="width:190%" />
						    <span class="input-group-btn">
						       <button id="searchBtn" class="btn btn-default" type="button" style="margin-left:150px">
					        		<i class="fa fa-search"></i>
					        	</button>
						    </span>
							</div>
						</form>
			    	</div>
        		</div> 	
        		<div class="col-sm-3">
        			<a id="downloadExcel" class="btn btn-default" href="" style="margin-right:50px">
        				<i class="fa fa-download"></i>下载
        			</a>
        		</div>
        	</div>
            <!-- products table-->
            <table id="table"></table>
        </div>
    </div>
</div>

<script src="<%=path %>/js/config.js"></script>
<script src="js/jquery-latest.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/theme.js"></script>
<script src="js/bootstrap-table.js"></script>
<!-- bootstrap-table本地化js -->
<script src="js/bootstrap-table-zh-CN.js"></script>
<!-- moment -->
<script src="js/thirdpart/moment-with-locales.min.js"></script>
<!-- bootstrap 日期时间插件 -->
<script src="js/thirdpart/bootstrap-datetimepicker.js"></script>
<!-- 日期范围插件  -->
<script src="js/thirdpart/daterangepicker.js"></script>
<script src="js/CommUtil.js"></script>
<script>
	var $table = $('#table'),
		$customerAddBtn = $("#customerAddBtn"),
		$customerAddModal = $("#customerAddModal"),
		daterangepicker,
		lastQueryParams,
		basePath = "<%=path %>";
		$("#daterange").daterangepicker({
			 startDate : moment('2016-05-01 00:00:00'),
			 endDate : moment(),
			 timePicker: true,
			 timePicker24Hour : true,
			 timePickerSeconds : true,
			 linkedCalendars : false,
			 locale: {
			        "format": "YYYY-MM-DD HH:mm:ss",
			        "separator": " 至 ",
			        "applyLabel": "apply",
			        "cancelLabel": "取消",
			        "fromLabel": "From",
			        "toLabel": "To",
			        "customRangeLabel": "Custom",
			        "daysOfWeek": [
			            "日",
			            "一",
			            "二",
			            "三",
			            "四",
			            "五",
			            "六"
			        ],
			        "monthNames": [
			            "一月",
			            "二月",
			            "三月",
			            "四月",
			            "五月",
			            "六月",
			            "七月",
			            "八月",
			            "九月",
			            "十月",
			            "十一月",
			            "十二月"
			        ],
			        "firstDay": 1
			    },
	});
	daterangepicker = $('#daterange').data('daterangepicker');
	$table.bootstrapTable({ 
	    url: basePath + "/customer/getData.do",
	    method : "post", 
	    dataField : "rows",
	    cache : false,
	    pagination : true,
	    sidePagination : "server",
	    idField : "id",
	    striped : true,
	    sortName : "last_update_time",
	    sortOrder : "desc",
	    iconsPrefix : "fa",
	    toolbar : "#toolbar",
	    contentType : "application/x-www-form-urlencoded; charset=UTF-8",
	    queryParams : function(reqParam) {
	    	reqParam.beginDate = daterangepicker.startDate.format("YYYY-MM-DD HH:mm:ss");
	    	reqParam.endDate = daterangepicker.endDate.format("YYYY-MM-DD HH:mm:ss");
	    	reqParam.sortedBy = reqParam.sort;
	    	reqParam.sort = true;
	    	lastQueryParams = reqParam;
	    	return reqParam;
	    },
	    onLoadSuccess : function(data) {
	    	var requestUrl = Config.context + "/customer/downloadExcel.do";
	    	var querystring = "?_a_=" + new Date().getTime();
	    	for(prop in lastQueryParams) {
	    		if(lastQueryParams[prop] != undefined) {
	    			querystring += "&" + prop + "=" + lastQueryParams[prop];  
	    		}
	    	}
	    	$("#downloadExcel").attr("href", requestUrl + querystring);
	    },
	    showHeader : true,
	   /*  showRefresh :true,
	    icons : {
	    	refresh : "fa-refresh icon-refresh"
	    }, */
	    columns: [{
	        field: 'id', 
	        title: 'id',
	        visible : false
	    }, {
	        field: 'customerNumber',
	        sortable :true,
	        title: '客户编号'
	    }, {
	        field: 'customerName',
	        title: '客户名称',
	        formatter : function(value, row, index) {
	        	if(row.updateField != null) {
	        		if(Comm.isArrayContains(row.updateField.split(","), "customerName")) {
		        		return "<span style='color:red'>"+ row.customerName +"</span>";
		        	}
	        	}
	        	return row.customerName
	        }
	    }, {
	        field: 'electricType',
	        title: '用电类型',
	        formatter : function(value, row, index) {
	        	if(row.updateField != null) {
	        		if(Comm.isArrayContains(row.updateField.split(","), "electricTypeName")) {
		        		return "<span style='color:red'>"+ row.electricTypeName +"</span>";
		        	}
	        	}
	        	return row.electricTypeName
	        }
	    }, {
	        field: 'meteringMode',
	        title: '计量方式',
	        formatter : function(value, row, index) {
	        	if(row.updateField != null) {
	        		if(Comm.isArrayContains(row.updateField.split(","), "meteringModeName")) {
		        		return "<span style='color:red'>"+ row.meteringModeName +"</span>";
		        	}
	        	}
	        	return row.meteringModeName
	        }
	    }, {
	        field: 'voltageLevel',
	        title: '电压等级',
	        formatter : function(value, row, index) {
	        	if(row.updateField != null) {
	        		if(Comm.isArrayContains(row.updateField.split(","), "voltageLevel")) {
		        		return "<span style='color:red'>"+ row.voltageLevel +"</span>";
		        	}
	        	}
	        	return row.voltageLevel
	        }
	    }, {
	        field: 'industryClassification',
	        title: '行业分类',
	        formatter : function(value, row, index) {
	        	if(row.updateField != null) {
	        		if(Comm.isArrayContains(row.updateField.split(","), "industryClassificationName")) {
		        		return "<span style='color:red'>"+ row.industryClassificationName +"</span>";
		        	}
	        	}
	        	return row.industryClassificationName
	        }
	    }, {
	        field: 'consumerCategory',
	        title: '用户类别',
	        formatter : function(value, row, index) {
	        	if(row.updateField != null) {
	        		if(Comm.isArrayContains(row.updateField.split(","), "consumerCategoryName")) {
		        		return "<span style='color:red'>"+ row.consumerCategoryName +"</span>";
		        	}
	        	}
	        	return row.consumerCategoryName
	        }
	    }, {
	        field: 'riskLevel',
	        title: '风险等级',
	        formatter : function(value, row, index) {
	        	if(row.updateField != null) {
	        		if(Comm.isArrayContains(row.updateField.split(","), "riskLevelName")) {
		        		return "<span style='color:red'>"+ row.riskLevelName +"</span>";
		        	}
	        	}
	        	return row.riskLevelName
	        }
	    }, {
	        field: 'urbanRuralCode',
	        title: '城乡代码',
	        formatter : function(value, row, index) {
	        	if(row.updateField != null) {
	        		if(Comm.isArrayContains(row.updateField.split(","), "urbanRuralName")) {
		        		return "<span style='color:red'>"+ row.urbanRuralName +"</span>";
		        	}
	        	}
	        	return row.urbanRuralName
	        }
	    }, {
	        field: 'customerAddress',
	        title: '用电地址',
	        formatter : function(value, row, index) {
	        	if(row.updateField != null) {
	        		if(Comm.isArrayContains(row.updateField.split(","), "customerAddress")) {
		        		return "<span style='color:red'>"+ row.customerAddress +"</span>";
		        	}
	        	}
	        	return row.customerAddress
	        }
	    }, {
	        field: 'customerMobilePhone',
	        title: '客户电话',
	        formatter : function(value, row, index) {
	        	if(row.updateField != null) {
	        		if(Comm.isArrayContains(row.updateField.split(","), "customerMobilePhone")) {
		        		return "<span style='color:red'>"+ row.customerMobilePhone +"</span>";
		        	}
	        	}
	        	return row.customerMobilePhone
	        }
	    },
	    {
	    	field : 'lastUpdateTime',
	    	title : "更新日期",
	        formatter : function(value, row, index) {
	        	return moment(value).format("YYYY-MM-DD HH:mm:ss");
	        }
	    }]
	});
	$table.on('click-row.bs.table', function (e, row, $element) {
        $('.success').removeClass('success');
        $($element).addClass('success');
    });
	
	$("#searchBtn").click(function() {
		refreshTableByQueryForm();
	});
	
	function refreshTableByQueryForm() {
		var startDate = daterangepicker.startDate,
			endDate = daterangepicker.endDate;
		lastQueryParams.beginDate = startDate.format("YYYY-MM-DD HH:mm:ss");
		lastQueryParams.endDate = endDate.format("YYYY-MM-DD HH:mm:ss");
	 	$('#table').bootstrapTable('refresh', {
			query : {
				beginDate : startDate.format("YYYY-MM-DD HH:mm:ss"),
				endDate : endDate.format("YYYY-MM-DD HH:mm:ss")
			}
		});  
	}
</script>
</body>
</html>
