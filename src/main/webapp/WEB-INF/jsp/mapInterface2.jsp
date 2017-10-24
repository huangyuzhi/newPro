<%@page import="com.chains.core.view.VPerson"%>
<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"
	session="true"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	VPerson person = (VPerson) session.getAttribute(session.getId()
			+ "person");
	request.setAttribute("person", person);
%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>地图展示</title>
<link rel="stylesheet"
	href="css/font-awesome-4.4.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/mapinterface.css">
<link rel="stylesheet" href="css/bootstrap-select.css" />
<link rel="stylesheet" href="css/jquery.typeahead.css" />
<link rel="stylesheet" href="css/modals.css">
<link rel="stylesheet" href="css/bootstrapValidator.min.css">
<!-- 消息提醒插件 -->
<link rel="stylesheet" href="css/amaran.min.css" />

<!-- 日期范围插件 -->
<link rel="stylesheet" href="css/daterangepicker.css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<style type="text/css">
body,html,#allmap {
	width: 100%;
	height: 100%;
	overflow: hidden;
	margin-top: 18px;
	font-family: "微软雅黑", cursive;
}
</style>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=http://api.map.baidu.com/api?v=2.0&ak=dnTeK9nGVeFhbvvWZ2eTKHjI"></script>
</head>
<body>
	<div id="allmap"></div>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="index.do"> <img
					src="img/maplogo.png" class="img-responsive" />
				</a>
				<div class="brand-text">
					<span>贵州电网公司乌当供电局配网抢修服务调度监控平台</span>
				</div>
				<ul class="nav navbar-top-links navbar-right">
					<li class="dropdown"><a><i class="fa fa-user">
								${person.personName}</i></a></li>
					<li class="dropdown"><a href="logout.do"><i
							class="fa fa-power-off"></i> 退出</a></li>
				</ul>
			</div>
		</div>
		<!-- /.container-fluid -->
	</nav>
	<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
		<div class="form-group"></div>
		<ul class="nav menu">
			<form id="searchByNumForm">
				<div class="typeahead-container">
					<div class="typeahead-field">
						<span class="typeahead-query"> <input id="searchByNum"
							name="customerNumber" type="search" placeholder="输入户号、名字或地址"
							autocomplete="off">
						</span> <span class="typeahead-button">
							<button id="searchByNumBtn" type="submit">
								<i class="typeahead-search-icon"></i>
							</button>
						</span>
					</div>
				</div>
			</form>
			<li>
				<select id="transformerCombo" class="selectpicker"
					data-live-search="true" data-size="7">
					<option value="0">变压器</option>
				</select>
			</li>
			<li role="presentation" class="divider"></li>
			<li>
				<div id="line10kvContainer">
					<select id="line10kv" class="selectpicker" data-live-search="true"
						data-size="7">
						<option>10kv线路</option>
					</select>
				</div>
			</li>
			<li role="presentation" class="divider"></li>
			<li>
				<div id="line35kvContainer">
					<select id="line35kv" class="selectpicker" data-live-search="true"
						data-size="5">
						<option>35kV线路</option>
					</select>
				</div>
			</li>
			<li role="presentation" class="divider"></li>
			<li>
				<div id="substationContainer">
					<select id="substationCombo" class="selectpicker"
						data-live-search="true" data-size="5">
						<option>变电站</option>
					</select>
				</div>
			</li>
		</ul>
	</div>
	<!--/.sidebar-->
	<div class="modal fade" id="dixianModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true"
		data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" >地线信息</h4>
				</div>
				<div class="modal-body">
					<form id="defaultForm" method="post"
						class="form-horizontal form-map-line">
						<div class="form-group">
							<label class="col-sm-4 control-label">主线名</label>
							<div id="line10kvModalContainer" class="col-sm-8">
								<select id="line10kvModal" class="form-control selectpicker"
									data-live-search="true" data-size="7">
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label">入地线缆名</label>
							<div class="col-sm-8">
								<input id="dixianName" type="text" class="form-control"
									name="dixianName" />
							</div>
						</div>
					</form>
				</div>
				<div id="modalControl" class="modal-footer">
					<button type="button" class="btn btn-success" id="validateBtn"
						data-dismiss="alert" data-save=true>
						<i class="fa fa-save" data-save=true> 保存</i>
					</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">
						<i class="fa fa-close" data-save=false> 关闭</i>
					</button>
				</div>
			</div>
		</div>
	</div>

	<div id="wqtModel" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" data-backdrop="static">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">抢修派工</h4>
				</div>
				<div class="modal-body">
					<form id="wqtForm" class="form-horizontal form-map-line"
						method="post">
						<!-- <input type="hidden" name="faultDeviceVoltageLevel" id="faultDeviceVoltageLevel">
						<input type="hidden" name="faultDeviceVoltageLevel" id="faultDeviceVoltageLevel">
						<input type="hidden" name="reportTime" id="reportTime">
						<input type="hidden" name="sendMissionTime" id="sendMissionTime">
						<input type="hidden" name="performer" id="performer"> -->
						<input type="hidden" name="destinationKey" id="destinationKey">
						<input type="hidden" name="destinationDeviceType" id="destinationDeviceType">
						<input type="hidden" name="destinationBdcoordX" id="destinationBdcoordX">
						<input type="hidden" name="destinationBdcoordY" id="destinationBdcoordY">
						<input type="hidden" name="lineQuality" id="lineQuality">
						<div class="form-group" >
							<label for="title" class="col-sm-2 control-label">任务标题</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="title" name="title">
							</div>
						</div>
						<div id="consumeNameGroup" class="form-group">
							<label for="consumeName" class="col-sm-2 control-label">客户姓名</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="consumeName"
									name="consumeName">
							</div>
						</div>
						<div id="consumePhoneGroup" class="form-group">
							<label for="consumePhone" class="col-sm-2 control-label">客户电话</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="consumePhone"
									name="consumePhone">
							</div>
						</div>
						<div class="form-group">
							<label for="setTime" class="col-sm-2 control-label">完成时间</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="setTime" name="setTime" />
							</div>
						</div> 
						<div class="form-group">
							<label for="content" class="col-sm-2 control-label">抢修内容</label>
							<div class="col-sm-10">
								<textarea class="form-control" id="content"	name="content">
								</textarea>
							</div>
						</div>
						<div class="form-group">
							<label for="persons" class="col-sm-2 control-label" style="padding-left: 0px;">抢修负责人</label>
							<div class="col-sm-10">
								<select id="persons" name="persons" class="selectpicker" data-size="5" multiple>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="responsiblyPerson" class="col-sm-2 control-label">负责人</label>
							<div class="col-sm-10">
								<select id="responsiblyPerson" name="responsiblyPerson" class="selectpicker" data-size="5" data-noneSelectedText="请选择" multiple>
								</select>
							</div>
						</div> 
						<div class="form-group">
							<label for="isHave" class="col-sm-2 control-label">返回照片</label>
							<div class="col-sm-10">
								<select id="isHave" name="isHave" class="selectpicker" data-noneSelectedText="请选择">
									<option value="Y" >是</option>
									<option value="N" >否</option>
								</select>
							</div>
						</div> 
						<div class="modal-footer">
							<button type="submit" class="btn btn-success">确认</button>
							<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="<%=path%>/js/config.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery-2.0.0.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/bootstrap-select.js"></script>
	<script type="text/javascript" src="<%=path%>/js/application.js"></script>
	<script type="text/javascript" src="<%=path%>/js/bootstrap-select.js"></script>
	<script type="text/javascript"
		src="<%=path%>/js/jquery.typeahead.min.js"></script>
	<script type="text/javascript"
		src="<%=path%>/js/bootstrapValidator.min.js"></script>
	<!-- moment -->	
	<script src="<%=path%>/js/thirdpart/moment-with-locales.min.js"></script>
	<!-- daterangepicker -->	
	<script src="<%=path%>/js/thirdpart/daterangepicker.js"></script>
	<!-- 引入dwr -->
	<script type="text/javascript" src="<%=path%>/dwr/engine.js"></script>
	<script type='text/javascript'
		src='<%=path%>/dwr/interface/LineInfoController.js'></script>
	<script type="text/javascript" src="<%=path%>/js/bdMap.js"></script>
	<!-- 百度地图的地图绘制工具    -->
	<link rel="stylesheet"
		href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css" />
	<script type="text/javascript"
		src="http://api.map.baidu.com/library/CurveLine/1.5/src/CurveLine.min.js"></script>
	<!--
	时间：2015-12-03   
	描述：百度地图的鼠标绘制库。
-->
	<!--
	作者：zw
	时间：2015-11-16
	描述：导入项目公共函数库
-->
	<script type="text/javascript" src="<%=path%>/js/CommUtil.js"></script>
	<!--
	作者：zw
	时间：2016-4-5
	描述：导入百度地图工具库
-->
	<script type="text/javascript" src="<%=path%>/js/bdMapUtil.js"></script>
	<!--
	作者：zw
	时间：2015-11-16
	描述：导入变压器函数
-->
	<script type="text/javascript" src="<%=path%>/js/transformerNew.js"></script>
	<!--
	作者：zw
	时间：2015-11-16 
	描述：导入变电站相关函数
-->
	<script type="text/javascript" src="<%=path%>/js/Substation.js"></script>
	<!--
	作者：zw 
	时间：2015-11-16 
	描述：导入人员位置相关函数
-->
	<script type="text/javascript" src="<%=path%>/js/PersonNew2.js"></script>

	<!--
	作者：zw 
	时间：2015-11-16
	描述：导入10kv和35kv线路函数
-->
	<script type="text/javascript" src="<%=path%>/js/LineInfoNew.js"></script>

	<!--
	作者：zw 
	时间：2015-11-16
	描述：导入客户数据
-->
	<script type="text/javascript" src="<%=path%>/js/CustomerInfo.js"></script>
	<!-- 消息弹出插件-->
	<script type="text/javascript" src="<%=path%>/js/jquery.amaran.js"></script>
	<!--加载鼠标绘制工具-->
	<script type="text/javascript"
		src="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js"></script>
</body>
<script type="text/javascript">
	/*************鼠标绘制***************/
	var currentClickOverlayPoint  = null;
	var drawingManager = new BMapLib.DrawingManager(map, {
		isOpen : false, //是否开启绘制模式
		enableDrawingTool : true,
		drawingToolOptions : {
			anchor : BMAP_ANCHOR_TOP_RIGHT, //位置
			offset : new BMap.Size(130, 0), //偏离值
			drawingModes : [ BMAP_DRAWING_POLYLINE ]
		},
		polylineOptions : {
			strokeColor : "#222222",
			strokeWeight : 3,
			strokeOpacity : 1
		}
	});
	var drawLine = null;
	drawingManager.addEventListener("polylinecomplete", function(polyLine) {
		//初始化模态框
		var dixianModal = $("#dixianModal").modal();
		dixianModal.on("hide.bs.modal", function() {
			map.removeOverlay(polyLine);
			$("#defaultForm").data("bootstrapValidator").resetForm(true);
			setTimeout("map.disableDoubleClickZoom()", 2000); //取消双击缩放功能
		});
		drawLine = polyLine;
		//初始化下拉框
		Comm.getRemoteData({
			url : Config.context + "/lineInfo/with10kvForJson.do",
			data : {
				selectField : "distinct belongLine as belongLine"
			},
			dataType : "json"
		}, function(dataList) {
			Comm.generateCombo("line10kvModalContainer", "line10kvModal",
					dataList, "belongLine");
		});
	});
	$("#setTime").daterangepicker({
		 startDate : moment().add(1, "d"),
		 timePicker: true,
		 timePicker24Hour : true,
		 timePickerSeconds : true,
		 linkedCalendars : false,
		 singleDatePicker : true,
		 cancelClass: "btn-success",
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
	var daterange = $("#setTime").data('daterangepicker');
	$("#defaultForm").bootstrapValidator({
		feedbackIcons : {
			valid : null,
			invalid : null,
			validating : null
		},
		live : 'enabled',
		submitButtons : "#validateBtn",
		fields : {
			"dixianName" : {
				validators : {
					notEmpty : {
						message : "地线名不能为空"
					}
				}
			}
		}
	});

	$("#validateBtn")
			.on(
					"click",
					function(e) {
						console.info($(e.target).data("save"));
						var isSave = $(e.target).data("save"), lineName, branchName, virtualBranchLine, bv = $(
								"#defaultForm").data("bootstrapValidator")
								.validate();
						if (isSave && bv.isValid()) {
							//保存编辑的入地线缆信息
							lineName = $("#line10kvModal option:selected")
									.text();
							branchName = $("#dixianName").val();
							virtualBranchLine = LineInfo.convertToDixianFrom(
									drawLine, {
										lineName : lineName,
										branchName : branchName
									});
							$("#dixianModal").modal("hide");
							map.removeOverlay(drawLine); //生成了正式的线路，绘制的线路需要删除	
							//LineInfoController._path = "http://127.0.0.1:8080/newPro/dwr/";
							LineInfoController
									.addVirtualLine(
											virtualBranchLine.towers,
											function(json) {
												//弹出消息框说明操作结果
												if (json.success) {
													$.amaran({
														content : {
															message : json.msg
														}
													});
												} else {
													$.amaran({
														content : {
															message : json.msg,
															color : "red"
														}
													});
													map
															.removeOverlay(virtualBranchLine.poly);
												}
											});
						} else {
							//do nothing
						}
					});

	/*************应用相关***************/
	var circleColor = "#FF0000";
	var customers = [];
	var reportTime;
	LineInfo.render10kVLine();
	LineInfo.render35kVLine();
	Substation.generateSubstationCombo(); //变电站下拉框
	PersonPosition.renderPersons(); //人员信息
	
	//为10kv下拉框添加数据
	Comm.getRemoteData({
		url : Config.context + "/lineInfo/with10kvForJson.do",
		data : {
			selectField : "distinct belongLine as belongLine"
		},
		dataType : "json"
	}, function(dataList) {
		dataList.unshift({
			belongLine : "10kV线路"
		});
		Comm.generateCombo("line10kvContainer", "line10kv", dataList,
				"belongLine", function(selectTag) {
					//添加事件
					selectTag.on("hidden.bs.select", function(e) {
						var selected = $('#line10kv option:selected').text();
						if (selected == "10kV线路") {
							LineInfo.show10kVLine("$");
							Transformer.clearCurrentTrans();
						} else {
							LineInfo.focuLine10kV(selected);
							Transformer.renderTransbyLineName(selected);
						}
					});
				});
	});
	Transformer.transformerCombo({
		select : "#transformerCombo",
		text : "transformerName"
	}); //生成变压器的下拉框
	
	//为变压器的下拉框添加事件
	$("#transformerCombo").on("hidden.bs.select", function() {
		var selected = $('#transformerCombo option:selected').val(),
			transformer = null;
		Transformer.clearCurrentTrans();
		if (selected != 0) {
			transformer = Transformer.getComboData(selected);
			LineInfo.show10kVLine(transformer.belongLine);
			Transformer.getRemoteTransformer(selected, function(transDataArr) {
				Transformer.generateTransAndShow(transDataArr);
			});
		} 
	});
	//为35kv下拉框添加数据
	Comm.getRemoteData({
		url : Config.context + "/lineInfo/with35kvForJson.do",
		data : {
			selectField : "distinct belongLine as belongLine"
		},
		dataType : "json"
	}, function(dataList) {
		dataList.unshift({
			belongLine : "35kV线路"
		});
		Comm.generateCombo("line35kvContainer", "line35kv", dataList,
				"belongLine", function(selectTag) {
					//添加事件
					selectTag.on("hidden.bs.select", function(e) {
						//改变选项时，定位线路
						var selected = $('#line35kv option:selected').text();
						if (selected == "35kV线路") {
							LineInfo.show35kVLine("$");
						} else {
							LineInfo.focuLine35kV(selected);
						}
					});
				});
	});

	
	//搜索户号
	$('#searchByNum').typeahead(
	{
		minLength : 1,
		maxItem : 15,
		dynamic : true,
		display : [ "customerNumber" ],
		source : {
			car : {
				url : {
					type : "POST",
					url : "customer/findByCustomerNumber.do",
					data : {
						customerNumber : "{{query}}"
					}
				}
			}
		},
		callback : {
			onSubmit : function(node, query, result, resultCount) {
				var searchKey = $(node).val();
				function callback(datas) {
					customers = datas;
				}
				Transformer.searchTrans(searchKey,
					function(result) {
						if (result.length == 0) { //按户名没有搜索到数据
							var geoc = new BMap.Geocoder();
							geoc.getPoint(searchKey,
								function(point) {
									if (point) {
										//以该坐标为中心进行搜索
										Transformer.getByCenter(point,1.5,
											function(transformers) {
												if (transformers.length == 0) {
													$.amaran({
														content : {
															message : "未找到对应的变压器",
															color : "red"
														}
													});
												}
											});
									} else {
										$.amaran({
											content : {
												message : "未找到对应的地理位置",
												color : "red"
											}
										});
									}
								},
								"贵阳市");
						}else {
							reportTime = new Date();
							if(searchKey.search(/^\d+/) == 0) {
								CustomerInfo.findByExample({customerNumber : searchKey}, callback);
							}else {
								CustomerInfo.findByExample({customerName : searchKey}, callback);
							}
							
						}
					});
			}
		}
	});
	$("#wqtModel").on("hidden.bs.modal", function(e) {
		$("#wqtForm").bootstrapValidator('resetForm');
		Comm.resetForm("#wqtForm");
	});
	$("#wqtForm").bootstrapValidator(
	{
		feedbackIcons : {
			valid : null,
			invalid : null,
			validating : null
		},
		live : 'enabled',
		submitButtons : 'button[type="submit"]',
		submitHandler : function(validator, form, submitButton) {
			$("#wqtModel").modal("hide");
			var formData = Comm.SerializerForm("#wqtForm"),
				personIds = Comm.collectionArray(formData.persons.concat(formData.responsiblyPerson)),
				servicemans = [];
			$.each(formData.persons, function(index, servicemanId) {
				servicemans.push(PersonPosition.getPersonByParam({
					memberId : servicemanId
				})[0]);
			});
			formData.exeUser = personIds.join(",");
			formData.setTime = daterange.startDate.format("YYYY-MM-DD HH:mm:ss");
			formData.performer = Comm.propArray(servicemans, "attr.name").join();
			formData.serviceman = Comm.propArray(servicemans, "attr.memberId").join();
			formData.masterServiceman = formData.responsiblyPerson.join(",");
			if(servicemans[0].attr.name && servicemans[0].attr.name.search(/运检/) > -1) {
				formData.faultDeviceVoltageLevel = "高压";
			}else{
				formData.faultDeviceVoltageLevel = "低压";
			}
			if(reportTime) {
				formData.reportTime = moment(reportTime).format("YYYY-MM-DD HH:mm:ss");
			}
			/*
				
			*/
		    PersonPosition.assignMission(formData, function(response) {
				if(response.success) {
					$.each(servicemans, function(key, serviceman) {
							currentClickOverlayPoint && serviceman.drivingSearch(currentClickOverlayPoint);
					}); 
					currentClickOverlayPoint = null;
					$.amaran({
						content : {
							message : "派发成功"
						}
					});
				}else {
					$.amaran({
						content : {
							message : response.msg,
							color : "red"
						}
					});
				}
			});
		},
		fields : {
			"title" : {
				validators : {
					notEmpty : {
						message : "任务标题不能为空"
					}
				}
			},
			"content" : {
				validators : {
					notEmpty : {
						message : "任务内容不能为空"
					}
				}
			},
			/* "consumeName" : {
				validators : {
					notEmpty : {
						message : "客户姓名不能为空"
					}
				}
			},
			"consumePhone" : {
				validators : {
					notEmpty : {
						message : "客户电话不能为空"
					}
				}
			}, maodian*/
			"persons" : {
				validators : {
					notEmpty : {
						message : "抢修人员不能为空"
					}
				}
			},
			"responsiblyPerson" : {
				validators : {
					notEmpty : {
						message : "负责人不能为空"
					}
				}
			}
		}
	});
	
	//为变压器添加事件处理函数
	Transformer.addEventListener("mouseover", function() {	//鼠标划入时添加菜单
		var transMenu = new BMap.ContextMenu(),
			trans = this,
			menuItems = [{
				text : "抢修派工",
				callback : function(point) {
					//显示并初始化表单
					/* $('#consumeNameGroup').show();
					$('#consumePhoneGroup').show(); */
					$("#wqtModel").modal("show");
					initMissionForm(trans.marker.getPosition(), {
						title : trans.blAttr.transformerName,
						content : trans.blAttr.transformerName,
						destinationKey : trans.blAttr.id,
						destinationDeviceType : "transformer",
						destinationBdcoordX : trans.blAttr.bdCoorsX,
						destinationBdcoordY : trans.blAttr.bdCoorsY
					});
				}
			}];
		$.each(menuItems, function(k, v) {
			transMenu.addItem(new BMap.MenuItem(menuItems[k].text,menuItems[k].callback));
			if(k < menuItems.length - 1) {
				transMenu.addSeparator();
			}
		});
		trans.marker.addContextMenu(transMenu);
	});
	
	
	/**
	 *初始化任务表单
	*/
	function initMissionForm(endpoint, initData) {
		var minDistancePerson,
			relationMasterIds = [];
		
		//填充抢修人员下拉框数据
		$("#persons").children().remove();
		Comm.fillSelect({
			select : "#persons",
			datas : PersonPosition.servicemanOptionModels(),
			id : "id",
			text : "label"
		});
		$("#persons").selectpicker('refresh');
		
		//填充负责人下拉框数据
		$("#responsiblyPerson").children().remove();
		Comm.fillSelect({
			select : "#responsiblyPerson",
			datas : PersonPosition.mastermanOptionModels(),
			id : "id",
			text : "label"
		});
		$("#responsiblyPerson").selectpicker('refresh');
		
		//初始化数据
		minDistancePerson = PersonPosition.minDistPerson(endpoint, PersonPosition.getServiceman());
		if(minDistancePerson) {
			$("#persons").selectpicker('val', minDistancePerson.attr.memberId);
			$.each(PersonPosition.getMasterServiceman(function(i, s){
				if(s.deptName == minDistancePerson.attr.serviceman.deptName && (s.post == "m" || s.post == "dm")) {
			    	return true;
			  	}
			}), function(index, serviceman) {
				relationMasterIds.push(serviceman.wqtMemberId);
			});
			$("#responsiblyPerson").selectpicker("val", relationMasterIds);
		}
		/* $("#responsiblyPerson").on('changed.bs.select', function (e, index, newValue, oldValue) {
			var masterDeptName = PersonPosition.getPersonByParam({memberId : responsiblyPersons[index].memberId})[0].attr.serviceman.deptName;
			$("#persons").children().remove();
			personPositions = PersonPosition.optionModels(PersonPosition.getServiceman(masterDeptName));
			Comm.fillSelect({
				select : "#persons",
				datas : personPositions,
				id : "memberId",
				text : "label"
			});
			$("#persons").selectpicker('refresh');
			
			minDistancePerson = PersonPosition.minDistPerson(endpoint, personPositions);
			if(minDistancePerson) {
				$("#persons").selectpicker('val', minDistancePerson.attr.memberId);
			}
		}); */
		$("#title").val(initData.title);
		$("#content").val(initData.content);
		$("#destinationKey").val(initData.destinationKey);
		$("#destinationBdcoordX").val(initData.destinationBdcoordX);
		$("#destinationBdcoordY").val(initData.destinationBdcoordY);
		$("#lineQuality").val(initData.lineQuality);
		$("#destinationDeviceType").val(initData.destinationDeviceType);
		daterange.setStartDate(moment().add(3,"d"));
		$("#isHave").selectpicker("val","Y");
		if(customers.length > 0) {
			$("#consumeName").val(customers[0].customerName);
			$("#consumePhone").val(customers[0].customerMobilePhone);
		}
		currentClickOverlayPoint = endpoint;
	}
</script>
<script src="http://cdn.gbtags.com/jqueryui/1.10.4/jquery-ui.min.js"></script>
<script>
	$("#wqtModel").draggable({
		handle: ".modal-header"
	});
</script>
</html>