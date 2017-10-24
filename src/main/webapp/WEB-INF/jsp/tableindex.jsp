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
	<!-- bootstrap-dialog    -->
    <link rel="stylesheet" href="css/bootstrap-dialog.css" >
    <!-- bootstrap-select -->	
    <link rel="stylesheet" href="css/bootstrap-select.css"/>
    <!--消息通知插件   -->
    <link rel="stylesheet" href="css/amaran.min.css"/>
     <!--bootstrap表单验证插件   -->
    <link rel="stylesheet" href="css/bootstrapValidator.min.css"/>
    
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
		<li class="active">
			<a href="customer/maintainView.do">
				<div class="pointer">
					<div class="arrow"></div>
					<div class="arrow_border"></div>
				</div>
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
        		<div class="col-sm-3">
        			<div class="input-group">
    					<button id="customerAddBtn" class="btn btn-default" data-toggle="modal" data-target="#customerAddModal">
		        			<i class="fa fa-user-plus"></i> 添加
		        		</button>
    				</div>
        		</div>
        		<div class="col-sm-9">
        			<div id="searchGroup" class="input-group">
        				<form class="bs-example bs-example-form" role="form">
							<div class="input-group">
							<input type="text" name="customerNumber" class="form-control" placeholder="查询客户户号" >
						    <span class="input-group-btn">
						       <button id="searchBtn" class="btn btn-default" type="button">
					        		<i class="fa fa-search"></i>
					        	</button>
						    </span>
							</div>
						</form>
        				
				      <!-- 	<form action="">
				      		<input type="text" name="customerNumber" class="form-control" placeholder="查询客户户号">
					      	<span class="input-group-btn">
					        	<button id="searchBtn" class="btn btn-default" type="button">
					        		<i class="fa fa-search"></i>
					        	</button>
					        </span>
				      	</form> -->
			    	</div>
        		</div> 	
        	</div>
            <!-- products table-->
            <table id="table"></table>
        </div>
    </div>
</div>
<!-- end main container -->
<div id="customerAddModal" class="modal fade">
  <div class="modal-dialog modal-top">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">添加客户信息</h4>
      </div>
       <!-- customerAddForm -->
        <form id="customerAddForm" class="form-horizontal" method="post">
        	<input type="hidden" name="id" />
      <div class="modal-body">
			<div class="form-group">
				<label for="customerNumber" class="col-sm-2 control-label" >人员编号</label>
				<div class="col-sm-10">
			      <input type="text" class="form-control" id="customerNumber" placeholder="请输入人员编号" name="customerNumber">
			    </div>
		    </div>
			<div class="form-group">
			    <label for="customerName" class="col-sm-2 control-label">客户姓名</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="customerName" placeholder="请输入人员编号" name="customerName">
			    </div>
			</div>
			<div class="form-group">
				<label for="voltageLevel" class="col-sm-2 control-label">电压等级</label>
		  		<div class="col-sm-10">
		  			<input type="text" class="form-control" id="voltageLevel" placeholder="请输入电压等级" name="voltageLevel" >
		  		</div>
			</div>
			<div class="form-group">
				<label for="customerAddress" class="col-sm-2 control-label">用电地址</label>
				<div class="col-sm-10">
		  			<input type="text" class="form-control" id="customerAddress" placeholder="请输入用电地址" name="customerAddress" data-title="==请选择==">
		  		</div>
			</div>
			<div class="form-group">
				<label for="customerMobilePhone" class="col-sm-2 control-label">客户电话</label>
				<div class="col-sm-10">
		  			<input type="text" class="form-control" id="customerMobilePhone" placeholder="请输入用电地址" name="customerMobilePhone" data-title="==请选择==">
		  		</div>
			</div>
			<div class="form-group ">
				<label for="electricType" class="col-sm-2 control-label">用电类别</label>
				<div class="col-sm-4">
			    	<select id="electricType" name="electricType" class="form-control selectpicker" data-size="7" data-title="==请选择==">
				    </select>
			    </div>
			    <label for="meteringMode" class="col-sm-2 control-label">计量方式</label>
			    <div class="col-sm-4">
			    	<select id="meteringMode" name="meteringMode" class="form-control selectpicker" data-size="7" data-title="==请选择==">
					</select>
			    </div>
			</div>
		    <div class="form-group">
		    	<label for="urbanRuralCode"  class="col-sm-2 control-label" >城乡代码</label>
				<div class="col-sm-4">
					<select id="urbanRuralCode" name="urbanRuralCode" class="form-control selectpicker" data-size="7" data-title="==请选择==">
					</select>
				</div>
			    <label for="industryClassification" class="col-sm-2 control-label">行业分类</label>
			    <div class="col-sm-4">
			    	<select id="industryClassification" name="industryClassification" class="form-control selectpicker" data-size="7" data-live-search="true" data-title="==请选择==">
					</select>
			    </div>
		    </div>
			<div class="form-group">
				<label for="consumerCategory" class="col-sm-2 control-label">用户类别</label>
				<div class="col-sm-4">
					<select id="consumerCategory" name="consumerCategory" class="form-control selectpicker" data-size="7" data-title="==请选择==">
					</select>
				</div>
				<label for="riskLevel" class="col-sm-2 control-label">风险等级</label>
				<div class="col-sm-4">
					<select id="riskLevel" name="riskLevel" class="form-control selectpicker" data-size="7" data-title="==请选择==">
					</select>
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
<!--  -->
<script type="text/javascript" src="js/maintain/customerInfo.js"></script>
<script>
	var $table = $('#table'),
		$customerAddBtn = $("#customerAddBtn"),
		$customerAddModal = $("#customerAddModal"),
		basePath = "<%=path %>";
	CustomerMaintain.setContextPath(basePath);	
	$table.bootstrapTable({ 
	    url: basePath + "/customer/getData.do",//'json/customers.json',
	    method : "get", 
	    dataField : "rows",
	    cache : false,
	    pagination : true,
	    sidePagination : "server",
	    idField : "id",
	    striped : true,
	    sortName : "customer_number",
	    sortOrder : "asc",
	    iconsPrefix : "fa",
	    toolbar : "#toolbar",
	    icons : {
	    	refresh : "fa-refresh icon-refresh"
	    },
	    queryParams : function(reqParam) {
	    	reqParam.sortedBy = reqParam.sort;
	    	reqParam.sort = true;
	    	return reqParam;
	    },
	    showHeader : true,
	    showRefresh :true,
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
	        title: '客户名称'
	    }, {
	        field: 'electricType',
	        title: '用电类型',
	        formatter : function(value, row, index) {
	        	return row.electricTypeName
	        }
	    }, {
	        field: 'meteringMode',
	        title: '计量方式',
	        formatter : function(value, row, index) {
	        	return row.meteringModeName
	        }
	    }, {
	        field: 'voltageLevel',
	        title: '电压等级'
	    }, {
	        field: 'industryClassification',
	        title: '行业分类',
	        formatter : function(value, row, index) {
	        	return row.industryClassificationName
	        }
	    }, {
	        field: 'consumerCategory',
	        title: '用户类别',
	        formatter : function(value, row, index) {
	        	return row.consumerCategoryName
	        }
	    }, {
	        field: 'riskLevel',
	        title: '风险等级',
	        formatter : function(value, row, index) {
	        	return row.riskLevelName
	        }
	    }, {
	        field: 'urbanRuralCode',
	        title: '城乡代码',
	        formatter : function(value, row, index) {
	        	return row.urbanRuralName
	        }
	    }, {
	        field: 'customerAddress',
	        title: '用电地址'
	    }, {
	        field: 'customerMobilePhone',
	        title: '客户电话'
	    }, {
	        field: 'operate',
	        title: '操作',
	        align: 'center',
	        width : 100,
	        formatter : function() {
	        	return '<a class="remove text-primary "  href="javascript:void(0)" title="删除">' + 
	        		   		'<i class="fa fa-times fa-lg"></i>' +
	        		   	'</a> &nbsp;&nbsp;' +
	        		   	'<a class="update text-primary"  href="javascript:void(0)" title="修改">' + 
	        		   		'<i class="fa fa-edit fa-lg"></i>' +
	        		   	'</a>';
	        },
	        events :  {
	        	'click .update' :  function (e, value, row, index) {
	        		Comm.fillForm("#customerAddForm",row); 
	        		$('#customerAddModal').modal({
					  keyboard: false
					});
	        	},
        		'click .remove' :  function (e, value, row, index) {
        			BootstrapDialog.show({
        				title : "消息",
        				spinicon : "fa fa-spinner fa-spin",
        				message : function(dialog) {
        					return '<p class="text-muted">是否删除编号为 <code>'+row.customerNumber+'</code> 的客户?<p>';
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
					        	CustomerMaintain.del(row.id, function(json) {
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
        		}
	        }
	    }]
	});
	$table.on('click-row.bs.table', function (e, row, $element) {
        $('.success').removeClass('success');
        $($element).addClass('success');
    });
    /* 
     	模态框事件
     * */
     $customerAddModal.on("shown.bs.modal", function(e) {
    	 $('.selectpicker').selectpicker('refresh');
     }); 
    $customerAddModal.on("hidden.bs.modal", function(e) {
    	//重置模型框
    	$customerAddModal.find(".modal-footer button").removeClass("disabled").removeAttr("disabled");
    	$("#ensureAdd").html("确认");
    	//reset Form
    	$("#customerAddForm").bootstrapValidator('resetForm');
    	$('.selectpicker').selectpicker('refresh');
    });
	
	$("#customerAddBtn").click(function(){
		Comm.resetForm("#customerAddForm");
		 $('.selectpicker').selectpicker("deselectAll");
	});

	$("#searchBtn").click(function() {
		refreshTableByQueryForm();
	});
	CustomerMaintain.fillConsumerCategory("#consumerCategory");
	CustomerMaintain.fillElectricType("#electricType");
	CustomerMaintain.fillIndustryClassification("#industryClassification");
	CustomerMaintain.fillMeteringMode("#meteringMode");
	CustomerMaintain.fillRiskLevel("#riskLevel");
	CustomerMaintain.fillUrbanRuralCode("#urbanRuralCode");
	
	$("#customerAddForm").bootstrapValidator({
		message: '未通过验证',
		feedbackIcons: {
	        valid: 'glyphicon glyphicon-ok',
	        invalid: 'glyphicon glyphicon-remove',
	        validating: 'glyphicon glyphicon-refresh'
	    },
	    submitButtons : 'button[type="submit"]',
	    submitHandler: function(validator, form, submitButton) {
			$customerAddModal.find(".modal-footer button").addClass("disabled",true).attr("disabled",true);
			$(submitButton).html('<i class="fa fa-spinner fa-spin"></i>' + $(submitButton).html());
			//发送请求
			CustomerMaintain.saveOrUpdate(Comm.SerializerForm("#customerAddForm"), function(json) {
				Comm.handlerJsonResponse(json);
				$customerAddModal.modal("hide");
				$('#table').bootstrapTable('refresh', {
					query : {
						customerNumber : $('#searchGroup [name="customerNumber"]').val()
					}
				});
			}); 
	    },
	    fields : {
	    	customerNumber : {
	    		validators : { 
	    			notEmpty : { 
	    				message : "人员编号不能为空"
	    			}
	    		}
	    	},
	    	customerName : {
	    		validators : { 
	    			notEmpty : { 
	    				message : "客户名不能为空"
	    			}
	    		}
	    	},
	    	voltageLevel : {
	    		validators : { 
	    			notEmpty : { 
	    				message : "电压等级不能为空"
	    			}
	    		}
	    	}
	    }
	});
	
	function refreshTableByQueryForm() {
		var val = $("#searchGroup [name='customerNumber']").val();
		$('#table').bootstrapTable('refresh', {
			query : {
				customerNumber : val 
			}
		});
	}
</script>
</body>
</html>
