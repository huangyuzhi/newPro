<%@page import="com.chains.core.view.VPerson"%>
<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"
 session="true"%>
 <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	VPerson person = (VPerson)session.getAttribute(session.getId() + "person");
 	request.setAttribute("person", person); 
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<base href="<%=path%>/"/>
	<meta name="viewport" content="width=device-width,initial-scale=1.0">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>故障报修统计明细表</title>
	<!--BOOTStrap-->
	<link rel="stylesheet" href="css/bootstrap.min.css" />
	<!-- global styles -->
	<link rel="stylesheet" type="text/css" href="css/layout.css" />
	<link rel="stylesheet" type="text/css" href="css/elements.css" />
	<link rel="stylesheet" type="text/css" href="css/font-awesome-4.4.0/css/font-awesome.min.css" />

	<!-- this page specific styles -->
	<link rel="stylesheet" href="css/tables.css" type="text/css" media="screen" />

	<!-- bootstrap-table  -->
	<link rel="stylesheet" href="css/bootstrap-table.css">
	<!-- bootstrap-dialog    -->
    <link rel="stylesheet" href="css/bootstrap-dialog.css" >
    <!-- bootstrap-select -->
    <link rel="stylesheet" href="css/bootstrap-select.css"/>
    <!--消息通知插件   -->
    <link rel="stylesheet" href="css/amaran.min.css"/>
     <!--bootstrap表单验证插件   -->
    <link rel="stylesheet" href="css/bootstrapValidator.min.css"/>
    <!-- 日期范围选取css -->
	<link rel="stylesheet" href="css/daterangepicker.css">
	<!-- 消息提醒插件 -->
	<link rel="stylesheet" href="css/amaran.min.css" />
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
        /*自定义样式*/
        label.control-label.clean-padding-top {
            padding-top : 0px;
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
		<li >
			<a href="customer/customerChangeInfo.do">
				<i class="fa fa-plus-square"></i>
				<span>变更信息</span>
			</a>
		</li> 
		<li >
			<a href="faultStatisticsDetail/maintainView.do">
				<i class="fa fa-table"></i>
				<span>报修细节</span>
			</a>
		</li>
		<li class="active">
			<div class="pointer">
				<div class="arrow"></div>
				<div class="arrow_border"></div>
			</div>
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
     		<form id="searchForm"  role="form" class="form-horizontal">
     			<div class="form-group">
     				<label for="groupField" class="col-sm-1 control-label">查询字段：</label>
     				<div class="col-sm-2">
	     				<select id="groupField" name="groupField" class="selectpicker form-control" multiple>
							<option value="category" selected>传入类别</option>
							<option value="faultCategory">故障类别</option>
							<option value="faultDevice">故障设备</option>
							<option value="faultDeviceVoltageLevel">故障设备电压等级</option>

						</select>
					</div>
					<label for="year" class="col-sm-1 control-label">年份：</label>
					<div class="col-sm-2">
						<select id="year" name="year" class="form-control">
							<option value="2016">2016</option>
							<option value="2017" >2017</option>
							<option value="2018" >2018</option>
							<option value="2019" >2019</option>
							<option value="2020" >2020</option>
						</select>
					</div>
					<label for="month" class="col-sm-1 control-label">月份：</label>
					<div class="col-sm-2">
						<select id="month" name="month" class="form-control">
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
							<option value="7">7</option>
							<option value="8">8</option>
							<option value="9">9</option>
							<option value="10">10</option>
							<option value="11">11</option>
							<option value="12">12</option>
						</select>
					</div>
					<button id="searchBtn" type="button" class="btn btn-default"><i class="fa fa-search"></i>查询</button>
					<a id="staticExcel" class="btn btn-default" href="">
        				<i class="fa fa-download"></i>下载
        			</a>
     			</div>
					<!-- <input type="text" id="daterange" name="daterange" class="form-control"  style="width:190%"/> -->
			</form>
        	<div id="toolbar" class="row">
        	</div>
            <!-- products table-->
            <table id="table"></table>
		</div>
     </div>
</div>
</div>
<!-- end main container -->


<script src="<%=path %>/js/config.js"></script>
<script src="js/jquery-latest.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/theme.js"></script>
<script src="js/bootstrap-table.js"></script>
<!-- bootstrap-table本地化js -->
<script src="js/bootstrap-table-zh-CN.js"></script>
<!-- bootstrap-dialog need banana!!  -->
<script src="js/bootstrap-dialog.js"></script>
<!-- bootstrap-select -->
<script type="text/javascript" src="js/bootstrap-select.js"></script>
<!-- 消息弹出插件-->
<script type="text/javascript" src="js/jquery.amaran.js"></script>
<!-- 自定义工具函数 -->
<script type="text/javascript" src="js/CommUtil.js"></script>
<!-- moment -->
<script src="js/thirdpart/moment-with-locales.min.js"></script>
<!-- 日期范围插件  -->
<script src="js/thirdpart/daterangepicker.js"></script>
<!-- 消息弹出插件-->
<script type="text/javascript" src="<%=path%>/js/jquery.amaran.js"></script>
<script>
	var basePath = "<%=path %>",
		$table = $('#table'),
		lastQueryParams;

	initSearchForm("#year", "#month");
	$table.bootstrapTable(tableOptions());
	$table.on('click-row.bs.table', function (e, row, $element) {
        $('.success').removeClass('success');
        $($element).addClass('success');
    });
	$("#searchBtn").click(function() {
		var formData = Comm.SerializerForm("#searchForm"),
			groupFieldParams = [];
		$('#table').bootstrapTable("destroy");

		$.each(formData.groupField, function (index, obj) {
			groupFieldParams.push({
				field : obj,
				title : $("#groupField").find(":selected:eq("+index+")").text(),
				valign : "middle"
			});
		})
		$('#table').bootstrapTable(tableOptions(groupFieldParams));
	});
	
	function tableOptions(groupFieldParams) {
		var options,
			columns = [];
		groupFieldParams = groupFieldParams && groupFieldParams.length > 0 ? groupFieldParams : [{
			field : "category",
	    	title : "传入类别"
		}];
		columns = columns.concat(groupFieldParams);
		columns = columns.concat([{
			field : "currentMonthAmount",
			title : "当月总计"
		},{
			field : "totalAmount",
			title : "累计"
		},{
			field : "lastMonthAmount",
			title : "上月总计"
		},{
			field : "lastYearAmount",
			title : "去年同月总计"
		}]);
		options = {
			url: basePath + "/faultStatisticsDetail/getViewModel.do",
		    method : "post",
		    cache : false,
		    sidePagination : "server",
		    idField : "id",
		    striped : true,
		   /*  toolbar : "#toolbar", */
		    contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		    queryParams : function(reqParam) {
		    	var formData = Comm.SerializerForm("#searchForm");
		    	reqParam.year = formData.year;
		    	reqParam.month = formData.month;
		    	reqParam.groupField = formData.groupField ? formData.groupField.join() : null;
		    	lastQueryParams = reqParam;
		    	return reqParam;
		    },
		    onLoadSuccess : function(data) {
		    	var requestUrl = Config.context + "/faultStatisticsDetail/staticExcel.do";
		    	var querystring = "?_a_=" + new Date().getTime();
		    	for(prop in lastQueryParams) {
		    		if(lastQueryParams[prop] != undefined) {
		    			querystring += "&" + prop + "=" + lastQueryParams[prop];
		    		}
		    	}
				if(data.rows.length > 0) {
					mergeTable($table, Comm.SerializerForm("#searchForm").groupField);
				}
		    	$("#staticExcel").attr("href", requestUrl + querystring);
		    },
		    showHeader : true,
		    showRefresh :true,
		    columns: columns
		};
		return options;
	}
	
	//初始化查询表单
	function initSearchForm(yearSelector, monthSelector) {
		$(yearSelector).val(new Date().getFullYear());
		$(monthSelector).val(new Date().getMonth() + 1);
	}

	function mergeTable($table, groupFields) {
		mergeRows(0, $table.bootstrapTable("getData").length - 1, $table, groupFields, 0);
	}

	function mergeRows(beginIndex, endIndex, $table, groupFields, groupFieldIndex) {
		var rows = $table.bootstrapTable("getData"),
			previousFieldValue = rows[beginIndex][groupFields[groupFieldIndex]],
			indexRange, i, currentFieldValue;

		indexRange = new IndexRange(beginIndex, beginIndex);

		for (i = beginIndex + 1; i <= endIndex; i++) {
			currentFieldValue = rows[i][groupFields[groupFieldIndex]];
			if(currentFieldValue == previousFieldValue ) {
				indexRange.increaseRange();
			}else {
				mergeing($table, groupFields, groupFieldIndex, indexRange);
				previousFieldValue = currentFieldValue;
				indexRange = new IndexRange(i,i);
			}
		}
		mergeing($table, groupFields, groupFieldIndex, indexRange);
	}
	function mergeing($table, groupFields, groupFieldIndex, indexRange) {
		$table.bootstrapTable("mergeCells",{
			index : indexRange.beginIndex,
			field : groupFields[groupFieldIndex],
			rowspan : indexRange.endIndex - indexRange.beginIndex + 1,
			colspan : 1
		});

		if(groupFields.length > groupFieldIndex + 1) {
			mergeRows(indexRange.beginIndex, indexRange.endIndex, $table, groupFields, groupFieldIndex + 1);
		}
	}

	function IndexRange(b, e) {
		this.beginIndex = b;
		this.endIndex = e;
		this.increaseRange = function () {
			this.endIndex++;
		}
	}
</script>
</body>
</html>
