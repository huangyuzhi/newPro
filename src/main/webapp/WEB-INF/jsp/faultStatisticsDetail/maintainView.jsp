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
		<li class="active">
			<div class="pointer">
				<div class="arrow"></div>
				<div class="arrow_border"></div>
			</div>
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
							<input type="text" id="daterange" name="daterange" class="form-control"  style="width:190%"/>
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
        	<!-- 	<div class="col-sm-3">
        			<div class="input-group">
    					<button id="backfillBtn" class="btn btn-warning " data-toggle="modal" >
		        			<i class="fa fa-magic" ></i> 回填
		        		</button>
    				</div>
        		</div> -->
        	</div>
            <!-- products table-->
            <table id="table"></table>
  <!--   <form id="backfillForm" method="post">
        <div class="row">
            <div class="col-md-3">
                <div class="form-group">
                    <label for="crlb">传入类别</label>
                    <select class="form-control" id="category" name="category">
                        <option>营销系统</option>
                        <option>客户电话</option>
                        <option>客户微信</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="hfsj">回访客户时间</label>
                    <input type="text" class="form-control" id="reviewTime" name="reviewTime" placeholder="请输入回访客户时间">
                </div>
                <div class="form-group">
                    <label for="hfr">回访人</label>
                    <input type="text" class="form-control" id="reviewPerson" name="reviewPerson" placeholder="请输入回访人">
                </div>
                <div class="form-group">
                    <label for="sfmy">回访客户是否满意</label>
                    <select class="form-control" id="satisfaction" name="satisfaction">
                        <option value=true>是</option>
                        <option value=false>否</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="remarks">备注</label>
                    <input type="text" class="form-control" id="descriptor" name="descriptor">
                </div>

            </div>
            <div class="col-md-3">
                <div class="form-group">
                    <label for="name">报修人（姓名）</label>
                    <input type="text" class="form-control" id="reportCustomerName" name="reportCustomerName" disabled>
                </div>
                <div class="form-group">
                    <label for="number">报修人（客户号）</label>
                    <input type="text" class="form-control" id="reportCustomerNumber" name="reportCustomerNumber" disabled>
                </div>
                <div class="form-group">
                    <label for="bxsj">报修时间</label>
                    <input type="text" class="form-control" id="reportTime" name="reportTime" disabled>
                </div>
                <div class="form-group">
                    <label for="pgr">派工人</label>
                    <input type="text" class="form-control" id="handlerUser" name="handlerUser" disabled>
                </div>
                <div class="form-group">
                    <label for="pgrsj">派工时间</label>
                    <input type="text" class="form-control" id="sendMissionTime" name="sendMissionTime" disabled>
                </div>

            </div>
            <div class="col-md-3">
                <div class="form-group">
                    <label for="qxr">抢修人</label>
                    <input type="text" class="form-control" id="performer" name="performer" disabled>
                </div>
                <div class="form-group">
                    <label for="ddsj">抢修到达时间</label>
                    <input type="text" class="form-control" id="performerArriveTime" name="performerArriveTime" disabled>
                </div>
                <div class="form-group">
                    <label for="qxjssj">抢修结束时间</label>
                    <input type="text" class="form-control" id="repairEndtime" name="repairEndtime" disabled>
                </div>
                <div class="form-group">
                    <label for="cs">是否超时</label>
                    <input type="text" class="form-control" id="arriveTimeout" name="arriveTimeout" disabled>
                </div>
                <div class="form-group">
                    <label for="cqsb">是否客户产权设备</label>
                    <input type="text" class="form-control" id="customerDevice" name="customerDevice" disabled>
                </div>
            </div>
            <div class="col-md-3">
                <div class="form-group">
                    <label for="gzsb">故障设备(线路、台区)</label>
                    <input type="text" class="form-control" id="faultDevice" name="faultDevice" disabled>
                </div>

                <div class="form-group">
                    <label for="bxnr">报修内容</label>
                    <input type="text" class="form-control" id="repairContent" name="repairContent" disabled>
                </div>

                <div class="form-group">
                    <label for="gzlb">故障类别</label>
                    <input type="text" class="form-control" id="faultCategory" name="faultCategory" disabled>
                </div>
                <div class="form-group">
                    <label for="dydj">故障设备电压等级</label>
                    <input  type="text" class="form-control" id="faultDeviceVoltageLevel" name="faultDeviceVoltageLevel" disabled>
                </div>
            </div>
            <div class="col-md-3">
             	<div class="form-group">
                    <button class="btn btn-warning" type="submit" >提交</button>
                </div>
            </div>
        </div>
    </form> -->
  <!--   <div class="row" id="pic-row">
        <div class="col-sm-6 col-md-3">
            <a href="#" class="thumbnail">
                <img src="img/bg.jpg"
                     alt="通用的占位符缩略图">
            </a>
        </div>
        <div class="col-sm-6 col-md-3">
            <a href="#" class="thumbnail">
                <img src="img/bg.jpg"
                     alt="通用的占位符缩略图">
            </a>
        </div>
        <div class="col-sm-6 col-md-3">
            <a href="#" class="thumbnail">
                <img src="img/bg.jpg"
                     alt="通用的占位符缩略图">
            </a>
        </div>
        <div class="col-sm-6 col-md-3">
            <a href="#" class="thumbnail">
                <img src="img/bg.jpg"
                     alt="通用的占位符缩略图">
            </a>
        </div>
    </div> -->
</div>
        </div>
    </div>
</div>
<!-- end main container -->
<div id="backfillModal" class="modal fade" data-backdrop="static">
  <div class="modal-dialog modal-top">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">故障统计细节信息</h4>
      </div>

        <form id="backfillForm" class="form-horizontal" method="post">
        <input type="hidden" name="id" />
      <div class="modal-body">
			<div class="form-group">
				<label for="category" class="col-sm-2 control-label" >传入类型</label>
				<div class="col-sm-4">
				  <select id="category" name="category" class="form-control selectpicker">
					  <option>营销系统</option>
					  <option>客户电话</option>
					  <option>客户微信</option>
				  </select>
			    </div>
                <label for="reviewTime" class="col-sm-2 control-label">回访时间</label>
                <div class="col-sm-4">
                      <input type="text" class="form-control" id="reviewTime" name="reviewTime" />
                </div>
		    </div>

			<div class="form-group">
				<label for="reviewPerson" class="col-sm-2 control-label">回访人</label>
		  		<div class="col-sm-4">
		  			<input type="text" class="form-control" id="reviewPerson" name="reviewPerson" value="${person.personName }" >
		  		</div>

                <label for="satisfaction" class="col-sm-2 control-label clean-padding-top">回访客户是否满意</label>
                <div class="col-sm-4">
                        <select id="satisfaction" name="satisfaction" class="form-control selectpicker">
                        <option value=true >是</option>
                        <option value=false>否</option>
                    </select>
                </div>
			</div>

            <div class="form-group">
                <label for="descriptor" class="col-sm-2 control-label">备注</label>
                <div class="col-sm-10">
                    <textarea class="form-control" id="descriptor" name="descriptor" ></textarea>
                </div>
            </div>

            <div class="form-group">
                <label for="faultDevice" class="col-sm-2 control-label">故障设备</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="faultDevice" name="faultDevice">
                </div>

                <label for="customerDevice" class="col-sm-2 control-label clean-padding-top">是否客户产权设备</label>
                <div class="col-sm-4">
                    <select id="customerDevice" name="customerDevice" class="form-control selectpicker">
                        <option value=true>是</option>
                        <option value=false>否</option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label for="faultCategory" class="col-sm-2 control-label">故障类别</label>
                <div class="col-sm-4">
                	<select id="faultCategory" class="form-control" name="faultCategory">
                		
                	</select>
                    <!-- <input type="text" class="form-control" id="faultCategory" name="faultCategory"> -->
                </div>

                <label for="performerArriveTime" class="col-sm-2 control-label clean-padding-top">抢修到达时间</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="performerArriveTime" name="performerArriveTime">
                </div>
            </div>

            <div class="form-group">
                <label for="repairContent" class="col-sm-2 control-label">报修内容</label>
                <div class="col-sm-10">
                    <textarea class="form-control" id="repairContent" name="repairContent"></textarea>
                </div>
            </div>
      </div>
      <div class="modal-footer">
        <button id="ensureAdd" type="submit" class="btn btn-warning" >
        	 确认
        </button>
        <button type="button" class="btn btn-primary" data-dismiss="modal">取消</button>
      </div>
      </form>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

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
<!-- bootstrap表单验证插件 -->
<script type="text/javascript" src="js/bootstrapValidator.min.js"></script>
<!-- 自定义工具函数 -->
<script type="text/javascript" src="js/CommUtil.js"></script>
<!-- moment -->
<script src="js/thirdpart/moment-with-locales.min.js"></script>
<!-- 日期范围插件  -->
<script src="js/thirdpart/daterangepicker.js"></script>
<!-- 消息弹出插件-->
<script type="text/javascript" src="<%=path%>/js/jquery.amaran.js"></script>
<!--  -->
<script type="text/javascript" src="js/maintain/customerInfo.js"></script>
<script>
	var $table = $('#table'),
		$customerAddBtn = $("#customerAddBtn"),
		$backfillModal = $("#backfillModal"),
		selectedRow,
		submitButton = "#ensureAdd",
		basePath = "<%=path %>",
		daterangepicker,
		lastQueryParams,
        loginPersonName = "${person.personName }",
        singleDaterangeOption = {
        timePicker: true,
        timePicker24Hour : true,
        timePickerSeconds : true,
        linkedCalendars : false,
        singleDatePicker : true,
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
        } },
        reviewDatepicker,
        performerArriveTimeDatepicker;

	//填充故障类别下拉框
	Comm.fillSelect({
		select : "#faultCategory",
		datas : [{
			id : "公变令克",
			text : "公变令克"
		},{
			id : "公变桩头",
			text : "公变桩头"
		},{
			id : "变压器主体",
			text : "变压器主体"
		},{
			id : "公变其它",
			text : "公变其它"
		},{
			id : "杆上搭头高压",
			text : "杆上搭头高压"
		},{
			id : "杆上搭头低压",
			text : "杆上搭头低压"
		},{
			id : "高压开关",
			text : "高压开关"
		},{
			id : "分支箱高压",
			text : "分支箱高压"
		},{
			id : "分支箱低压",
			text : "分支箱低压"
		},{
			id : "刀闸高压",
			text : "刀闸高压"
		},{
			id : "刀闸低压",
			text : "刀闸低压"
		},{
			id : "分支令克",
			text : "分支令克"
		},{
			id : "空开保险故障",
			text : "空开保险故障"
		},{
			id : "电缆高压",
			text : "电缆高压"
		},{
			id : "电缆低压",
			text : "电缆低压"
		},{
			id : "高压架空线路",
			text : "高压架空线路"
		},{
			id : "低压架空线路",
			text : "低压架空线路"
		},{
			id : "塔杆故障",
			text : "塔杆故障"
		},{
			id : "高压计量",
			text : "高压计量"
		},{
			id : "低压计量",
			text : "低压计量"
		},{
			id : "避雷器",
			text : "避雷器"
		},{
			id : "下户线",
			text : "下户线"
		},{
			id : "匪警110",
			text : "匪警110"
		},{
			id : "高压引下线",
			text : "高压引下线"
		},{
			id : "火警119",
			text : "火警119"
		},{
			id : "安全隐患",
			text : "安全隐患"
		},{
			id : "调度下令",
			text : "调度下令"
		},{
			id : "用户变令克",
			text : "用户变令克"
		},{
			id : "用户变桩头",
			text : "用户变桩头"
		},{
			id : "用户变其它",
			text : "用户变其它"
		},{
			id : "进户线",
			text : "进户线"
		},{
			id : "内线",
			text : "内线"
		},{
			id : "端子箱",
			text : "端子箱"
		},{
			id : "刀闸",
			text : "刀闸"
		},{
			id : "空开",
			text : "空开"
		},{
			id : "电能表",
			text : "电能表"
		},{
			id : "欠费停电",
			text : "欠费停电"
		},{
			id : "复电",
			text : "复电"
		},{
			id : "其他故障",
			text : "其他故障"
		}]
	});
	
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
	    url: basePath + "/faultStatisticsDetail/getData.do",
	    method : "post",
	    dataField : "rows",
	    cache : false,
	    pagination : true,
	    pageSize : 5,
	    pageList : [5, 10],
	    sidePagination : "server",
	    idField : "id",
	    striped : true,
	    toolbar : "#toolbar",
	    contentType : "application/x-www-form-urlencoded; charset=UTF-8",
	    queryParams : function(reqParam) {
	    	reqParam.beginDate = daterangepicker.startDate.format("YYYY-MM-DD HH:mm:ss");
	    	reqParam.endDate = daterangepicker.endDate.format("YYYY-MM-DD HH:mm:ss");
	    	reqParam.sort = true;
	    	reqParam.sortedBy = "sendMissionTime";
	    	reqParam.order = "desc";
	    	lastQueryParams = reqParam;
	    	return reqParam;
	    },
	    onLoadSuccess : function(data) {
	    	var requestUrl = Config.context + "/faultStatisticsDetail/download.do";
	    	var querystring = "?_a_=" + new Date().getTime();
	    	for(prop in lastQueryParams) {
	    		if(lastQueryParams[prop] != undefined) {
	    			querystring += "&" + prop + "=" + lastQueryParams[prop];
	    		}
	    	}
	    	$("#downloadExcel").attr("href", requestUrl + querystring);
	    },
	    detailView : true,
	    detailFormatter : function(index, row, element) {
	    	var html = '<div class="row" >';
	    	var picPrefix = "http://111.85.191.253:50010";
	    	var picArr;
	    	if(row.picPath) {
	    		picArr = row.picPath.split(";");
	    		$.each(picArr, function(k, v) {
		    		if(v) {
		    			html += '<div class="col-sm-6 col-md-3">' +
				           ' <a href="javascript:void(0)" class="thumbnail"> ' +
				               ' <img src="'+ picPrefix + v +'" >' +
				            '</a>' +
				        '</div>';
		    		}
		    	});
	    	}
	    	html += "</div>";
	        return html;
	    },
	    icons : {
	    	paginationSwitchDown: 'glyphicon-collapse-down icon-chevron-down',
	        paginationSwitchUp: 'glyphicon-collapse-up icon-chevron-up',
	        refresh: 'glyphicon-refresh icon-refresh',
	    	toggle: 'glyphicon-list-alt icon-list-alt',
	        columns: 'glyphicon-th icon-th',
	        detailOpen: 'glyphicon-plus icon-plus',
	        detailClose: 'glyphicon-minus icon-minus'
	    },
	    showHeader : true,
	    showRefresh :true,
	    clickToSelect : true,
	    columns: [{
	    	field : "operator",
	    	title : "操作",
	    	align: 'center',
		    formatter : function() {
	        	return '<a class="remove text-primary "  href="javascript:void(0)" title="删除">' +
	        		   		'<i class="fa fa-times fa-lg"></i>' +
	        		   	'</a> ' +
		        		'<a class="update text-primary"  href="javascript:void(0)" title="回填">' +
	        		   		'<i class="fa fa-edit fa-lg"></i>' +
	        		   	'</a>';
	        },
	        events : {
	        	'click .remove' :  function (e, value, row, index) {
	        		BootstrapDialog.show({
        				title : "消息",
        				spinicon : "fa fa-spinner fa-spin",
        				message : function(dialog) {
        					return '<p class="text-muted">确认<code>删除</code>?<p>';
        				},
        				buttons: [{	//确认按钮
					        id: 'btn-ok',
					        icon: 'fa fa-ok',
					        label: '确认',
					        cssClass: 'btn-warning',
					        autospin: true,
					        action: function(dialogRef){
					        	dialogRef.setClosable(false);
					        	dialogRef.enableButtons(false);

					        	Comm.getRemoteData({
					        		url : "faultStatisticsDetail/del.do",
					        		dataType : "json",
					        		data : {id : row.id}
					        	}, function(json) {
					        		Comm.handlerJsonResponse(json);
					        		dialogRef.close();
					        		if(json.success) {
					        			refreshTableByQueryForm();
					        		}
					        	});
							}
					    }, {	//取消按钮
					    	id: 'btn-cancel',
					        icon: 'fa fa-cancel',
					        label: '取消',
					        cssClass: 'btn-primary',
					        action: function(dialogRef){
							    dialogRef.close();
							}
					    }]
        			});
	        	},
	        	'click .update' :  function (e, value, row, index) {
	        		//初始化表单
	        		if(!row.missionId) {
	        			$.amaran({
							content : {
								message : "数据错误，该记录没有任务编号",
								color : "red"
							}
						});
	        			return;
	        		}
					$.get(basePath + "/WqtController/getByMissonid.do", {missonId : row.missionId},
						function (wqtMission) {
							if(wqtMission != null && wqtMission.status != null && wqtMission.status == 3) {
								Comm.fillForm("#backfillForm",row);
								$("#reviewPerson").val(loginPersonName);
								reviewDatepicker.setStartDate(row.reviewTime ? moment(row.reviewTime) : moment());
								performerArriveTimeDatepicker.setStartDate(row.performerArriveTime ? moment(row.performerArriveTime) : null);
								$("#satisfaction").selectpicker("val", "true");
								$("#customerDevice").val("" + row.customerDevice);
								$("#customerDevice").selectpicker('refresh');
								$backfillModal.modal('show');
							}else {
								$.amaran({
									content : {
										message : "巡检人员未回填不能修改",
										color : "red"
									}
								});
							}
						});

	        	},
	        }
	    }, {
	        field: 'id',
	        title: 'id',
	        visible : false
	    }, {
	        field: 'category',
	        title: '传入类别'
	    }, {
	        field: 'faultDevice',
	        title: '故障设备'
	    }, {
	        field: 'repairContent',
	        title: '报修内容'
	    }, {
	        field: 'faultDeviceVoltageLevel',
	        title: '故障设备电压等级'
	    }, {
	        field: 'faultCategory',
	        title: '故障类别'
	    }, {
	        field: 'reportCustomerName',
	        title: '报修客户姓名'
	    },{
	        field: 'reportCustomerNumber',
	        title: '报修客户编号'
	    }, {
	        field: 'reportTime',
	        title: '报修时间',
	        formatter : function(value, row, index) {
	        	if(moment(value).isValid()) {
	        		return 	moment(value).format("YYYY-MM-DD HH:mm:ss");
	        	}
	        }
	    }, {
	        field: 'handlerUser',
	        title: '派工人',
	        formatter : function(value, row, index) {
	        	if(value == 1) {
	        		return "管理员";
	        	}else {
	        		return value;
	        	}
	        }
	    },{
	        field: 'sendMissionTime',
	        title: '派工时间',
	        formatter : function(value, row, index) {
	        	if(moment(value).isValid()) {
	        		return 	moment(value).format("YYYY-MM-DD HH:mm:ss");
	        	}
	        }
	    }, {
	        field: 'performer',
	        title: '抢修人'
	    }, {
	        field: 'performerArriveTime',
	        title: '抢修到达时间',
	        formatter : function(value, row, index) {
	        	if(moment(value).isValid()) {
	        		return 	moment(value).format("YYYY-MM-DD HH:mm:ss");
	        	}
	        }
	    },{
	        field: 'repairEndtime',
	        title: '抢修结束时间',
	        formatter : function(value, row, index) {
	        	if(moment(value).isValid()) {
	        		return 	moment(value).format("YYYY-MM-DD HH:mm:ss");
	        	}
	        }
	    },{
	        field: 'arriveTimeout',
	        title: '到达时间是否超时',
	        align : "center",
	        formatter : function(value, row, index) {
	        	if(value === true) {
	        		return "是";
	        	}else if(value === false){
	        		return "否";
	        	}else {
	        		return null;
	        	}
	        }
	    },{
	        field: 'repairTimeout',
	        title: '抢修时间是否超时',
	        formatter : function(value, row, index) {
	        	if(value === true) {
	        		return "是";
	        	}else if(value === false){
	        		return "否";
	        	}else {
	        		return null;
	        	}
	        }
	    },{
	        field: 'customerDevice',
	        title: '是否客户产权设备',
	        formatter : function(value, row, index) {
	        	if(value === true) {
	        		return "是";
	        	}else if(value === false){
	        		return "否";
	        	}else {
	        		return null;
	        	}
	        }
	    },{
	        field: 'reviewTime',
	        title: '回访客户时间',
	        formatter : function(value, row, index) {
	        	if(moment(value).isValid()) {
	        		return 	moment(value).format("YYYY-MM-DD HH:mm:ss");
	        	}
	        }
	    },{
	        field: 'reviewPerson',
	        title: '回访人'
	    },{
	        field: 'satisfaction',
	        title: '回访客户是否满意',
	        formatter : function(value, row, index) {
	        	if(value === true) {
	        		return "是";
	        	}else if(value === false){
	        		return "否";
	        	}else {
	        		return null;
	        	}
	        }
	    },{
	        field: 'descriptor',
	        title: '备注'
	    }]
	});
	$table.on('click-row.bs.table', function (e, row, $element) {
        $('.success').removeClass('success');
        $($element).addClass('success');
    });

	$("#reviewTime").daterangepicker(singleDaterangeOption);
    $("#performerArriveTime").daterangepicker(singleDaterangeOption);

    reviewDatepicker = $("#reviewTime").data("daterangepicker");
    performerArriveTimeDatepicker = $("#performerArriveTime").data("daterangepicker");


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

	$("#backfillForm").bootstrapValidator({
		message: '未通过验证',
		feedbackIcons: {
	        valid: 'glyphicon glyphicon-ok',
	        invalid: 'glyphicon glyphicon-remove',
	        validating: 'glyphicon glyphicon-refresh'
	    },
	    submitButtons : 'button[type="submit"]',
	    submitHandler: function(validator, form, submitButton) {
	    	var formData = Comm.SerializerForm("#backfillForm");
			$backfillModal.find(".modal-footer button").addClass("disabled",true).attr("disabled",true);
			$(submitButton).html('<i class="fa fa-spinner fa-spin"></i>' + $(submitButton).html());
			//发送请求
				$.ajax({
					type: "POST",
					url: basePath + "/faultStatisticsDetail/backfill.do",
					cache : false,
					data : formData,
					dataType : 'json',
					success: function(data){
						Comm.handlerJsonResponse(data);
						$backfillModal.modal("hide");
						$('#table').bootstrapTable('refresh');
					}
				});
	    },
	    fields : {
	    	reviewPerson : {
	    		validators : {
	    			notEmpty : {
	    				message : "回访人不能为空"
	    			}
	    		}
	    	}
	    }
	});

	$backfillModal.on("hidden.bs.modal", function() {
    	//重置模型框
    	$backfillModal.find(".modal-footer button").removeClass("disabled").removeAttr("disabled");
    	$("#ensureAdd").html("确认");
    	//reset Form
    	$("#backfillForm").bootstrapValidator('resetForm');
    	$('.selectpicker').selectpicker('refresh');
	});

</script>
</body>
</html>
