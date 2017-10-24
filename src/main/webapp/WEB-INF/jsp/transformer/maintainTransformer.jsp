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
		#bdMap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
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
		<li>
			<a href="faultStatisticsDetail/statistics.do">
				<i class="fa fa-bar-chart"></i>
				<span>报修统计</span>
			</a>
		</li>
		<li class="active">
			<div class="pointer">
				<div class="arrow"></div>
				<div class="arrow_border"></div>
			</div>
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
	<div class="container-fluid" style="margin-top: 50px;">
		<div class="row">
			<div class="col-md-6">
				<div id="toolbar" class="row">
					<div class="col-md-12">
						<div id="searchGroup" class="input-group">
							<form id="searchForm" class="bs-example bs-example-form" role="form">
								<div class="input-group">
									<input type="text" name="transformerName" class="form-control" placeholder="变压器名" >
								<span class="input-group-btn">
								   <button id="searchBtn" class="btn btn-default" type="button">
										<i class="fa fa-search"></i>
									</button>
								</span>
								</div>
							</form>
						</div>
					</div>
				</div>
				<table id="table"></table>
			</div>
			<div class="col-md-6" style="height: 590px; padding-right: 0px;">
				<div id="bdMap"></div>
			</div>
		</div>

	</div>
</div>

<div class="modal fade" id="transformerModel" tabindex="-1" role="dialog"
	 aria-labelledby="myModalLabel" aria-hidden="true"
	 data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" >变压器信息</h4>
			</div>
			<div class="modal-body">
				<form id="defaultForm" method="post"
					  class="form-horizontal form-map-line">
					<div class="form-group">
						<label class="col-sm-2 control-label">变压器名</label>
						<div class="col-sm-10">
							<input id="transformerName" type="text" class="form-control"
								   name="transformerName" />
						</div>
					</div>
					<div id="modalControl" class="modal-footer">
						<button type="button" class="btn btn-success" id="saveBtn"
								data-dismiss="alert" data-save=true>
							<i class="fa fa-save" data-save=true> 保存</i>
						</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<i class="fa fa-close" data-save=false> 关闭</i>
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- end main container -->
<script type="text/javascript"
		src="http://api.map.baidu.com/api?v=2.0&ak=http://api.map.baidu.com/api?v=2.0&ak=dnTeK9nGVeFhbvvWZ2eTKHjI"></script>
<script src="<%=path %>/js/config.js"></script>
<script src="<%=path %>/js/jquery-latest.js"></script>
<script src="<%=path %>/js/bootstrap.min.js"></script>
<script src="<%=path%>/js/theme.js"></script>
<script src="<%=path%>/js/bootstrap-table.js"></script>
<!-- bootstrap-table本地化js -->
<script src="<%=path%>/js/bootstrap-table-zh-CN.js"></script>
<!-- bootstrap-dialog need banana!!  -->
<script src="<%=path%>/js/bootstrap-dialog.js"></script>
<!-- bootstrap-select -->
<script type="text/javascript" src="<%=path%>/js/bootstrap-select.js"></script>
<!-- 消息弹出插件-->
<script type="text/javascript" src="<%=path%>/js/jquery.amaran.js"></script>
<!-- 自定义工具函数 -->
<script type="text/javascript" src="<%=path%>/js/CommUtil.js"></script>
<!-- moment -->
<script src="<%=path%>/js/thirdpart/moment-with-locales.min.js"></script>
<!-- 日期范围插件  -->
<script src="<%=path%>/js/thirdpart/daterangepicker.js"></script>
<!-- 消息弹出插件-->
<script type="text/javascript" src="<%=path%>/js/jquery.amaran.js"></script>

<script>
var basePath = "<%=path %>",
	$table = $("#table"),
	map = new BMap.Map("bdMap"),
	$transformerModel = $("#transformerModel"),
	currentClickPoint = undefined,
	transformerIcon = new BMap.Icon("<%=path%>/img/transformer.png" , new BMap.Size(50,50)),
	currentTransformer = undefined;

	$table.bootstrapTable(tableOptions());

	$table.on('click-row.bs.table', function (e, row, $element) {

		$('.success').removeClass('success');
		$($element).addClass('success');

		Transformer.cover(row);
	});
	map.centerAndZoom(new BMap.Point(106.826838, 26.663088), 14);  // 初始化地图,设置中心点坐标和地图级别
	map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
	map.disableDoubleClickZoom();	//禁用双击缩放


	function tableOptions() {
		var options = {};
		options = {
			url : "transformerInfo/pageData.do",
		    method : "post",
		    cache : false,
		    sidePagination : "server",
		    idField : "id",
			cardView : true,
		    striped : true,

			pagination : true,
			pageSize : 5,
			pageList : [5, 6, 7, 8, 9],
		     toolbar : "#toolbar",
		    contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		    showHeader : true,
		    showRefresh :true,
		    columns: [ {
		    	field : "transformerName",
		    	title : "变压器名"
		    },{
		    	field : "transformerType",
		    	title : "变压器类型"
		    },{
		    	field : "transformerNumber",
		    	title : "变压器编号",
				formatter : function (value) {
					return value ? value : " ";
				}
		    },{
		    	field : "operation",
		    	title : "操作",
				formatter : function () {
					return '<a class="del text-primary "  href="javascript:void(0)" title="删除">' +
							'<i class="fa fa-times fa-lg"></i>';
				},
				events : {
					"click .del" : function (e, value, row) {
						console.info(e,value,row);
						Transformer.del(row.id);
					}
						
				}
		    }]
		};
		return options;
	}
	transformerMenu();
	$("#saveBtn").click(function () {
		//add new transformer then show
		Transformer.add({
			transformerName : $("#transformerName").val(),
			bdCoorsX : currentClickPoint.lng,
			bdCoorsY : currentClickPoint.lat
		}, function (response) {
			if(response.success) {
				//clean currentTransformer
				if(currentTransformer) {
					currentTransformer.hide();
				}
				currentTransformer = new Transformer({
					transformerName : $("#transformerName").val(),
					bdCoorsX : currentClickPoint.lng,
					bdCoorsY : currentClickPoint.lat
				});
				currentTransformer.show();
				//clean currentClickPoint
				currentClickPoint = undefined;
				$transformerModel.modal("hide");
			}
		});
	});

function refreshTable(data) {
	$('#table').bootstrapTable('refresh', {
		query: {
			transformerName: data.transformerName
		}
	});
}
$("#searchBtn").click(function () {
		var data = Comm.SerializerForm($("#searchForm"));
		refreshTable(data);
	});
	function transformerMenu() {
		var menu = new BMap.ContextMenu();
		var txtMenuItem = [
			{
				text:'添加变压器',
				callback:function(point){
					currentClickPoint = point;
					//初始化数据
					$("#transformerName").val("");
					$transformerModel.modal("show");
				}
			}
		];
		for(var i=0; i < txtMenuItem.length; i++){
			menu.addItem(new BMap.MenuItem(txtMenuItem[i].text,txtMenuItem[i].callback,100));
		}
		map.addContextMenu(menu);
	}

	function Transformer(data) {
		this.blAttr = data;
		this.marker = Comm.generateMarkerByIcon(new BMap.Point(data.bdCoorsX, data.bdCoorsY), transformerIcon);
		this.infoWindow = transformerInfoWindow(data);
		var trans = this;
		this.marker.addEventListener("click", function() {
			this.openInfoWindow(trans.infoWindow);
		});
	}

	Transformer.add = function (data,afterResponse) {
		Comm.getRemoteData({
			url : "<%=path%>/transformerInfo/add.do",
			data : data,
			dataType : "json"
		}, function(data) {
			afterResponse && afterResponse(data);
		});
	};
	Transformer.del = function (id, afterResponse) {
		BootstrapDialog.show({
			title : "消息",
			spinicon : "fa fa-spinner fa-spin",
			message : function(dialog) {
				return '<p class="text-muted">是否删除选择的变压器?<p>';
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
						url : "<%=path%>/transformerInfo/del.do",
						data : {id : id},
						dataType : "json"
					}, function(data) {
						dialogRef.close();
						refreshTable({});
						currentTransformer.hide();
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

	};
	/**
	 * 将指定的数据显示在地图上
	 * @param data
	 */
	Transformer.cover = function (data) {
		if(currentTransformer) {
			currentTransformer.hide();
		}
		currentTransformer = new Transformer(data);
		currentTransformer.show();
		currentTransformer.panto();
		console.info(currentTransformer);
	};
	Transformer.prototype.show = function () {
		map.addOverlay(this.marker);
	};
	Transformer.prototype.hide = function () {
		map.removeOverlay(this.marker);
	};
	Transformer.prototype.panto = function () {
		map.panTo(this.marker.getPosition());
	};

	function transformerInfoWindow(transdata) {
		return Comm.generateInfoWindow(transdata, "变压器信息",
				function(prop ) {
					return prop == "transformerName";
				});
	}
</script>
</body>
</html>
