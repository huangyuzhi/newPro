<%@page import="com.chains.core.view.VPerson" %>
<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    VPerson person = (VPerson) session.getAttribute(session.getId()
            + "person");
    request.setAttribute("person", person);
%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>地图展示</title>
    <link rel="stylesheet"
          href="css/font-awesome-4.4.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/mapinterface.css">
    <link rel="stylesheet" href="css/bootstrap-select.css"/>
    <link rel="stylesheet" href="css/jquery.typeahead.css"/>
    <link rel="stylesheet" href="css/modals.css">
    <link rel="stylesheet" href="css/bootstrapValidator.min.css">
    <!-- 消息提醒插件 -->
    <link rel="stylesheet" href="css/amaran.min.css"/>
    <!-- 日期范围插件 -->
    <link rel="stylesheet" href="css/daterangepicker.css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no"/>

    <style type="text/css">
        body, html, #allmap {
            width: 100%;
            height: 100%;
            overflow: hidden;
            margin-top: 18px;
            font-family: "微软雅黑", cursive;
        }
    </style>
</head>
<body>
<div id="allmap"></div>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="index.do"> <img
                    src="img/maplogo.png" class="img-responsive"/>
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
                <h4 class="modal-title">地线信息</h4>
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
                                   name="dixianName"/>
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
                    <div class="form-group">
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
                            <input type="text" class="form-control" id="setTime" name="setTime"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="content" class="col-sm-2 control-label">抢修内容</label>
                        <div class="col-sm-10">
								<textarea class="form-control" id="content" name="content">
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
                                <option value="Y">是</option>
                                <option value="N">否</option>
                            </select>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-success">确认</button>
                        <button type="button" class="btn btn-default"
                                data-dismiss="modal">取消
                        </button>
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
<script type="text/javascript" src="<%=path%>/js/jquery.typeahead.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrapValidator.min.js"></script>
<!-- moment -->
<script src="<%=path%>/js/thirdpart/moment-with-locales.min.js"></script>
<!-- daterangepicker -->
<script src="<%=path%>/js/thirdpart/daterangepicker.js"></script>
<!-- 引入dwr -->
<script type="text/javascript" src="<%=path%>/dwr/engine.js"></script>
<script type='text/javascript' src='<%=path%>/dwr/interface/LineInfoController.js'></script>
<script type="text/javascript" src="<%=path%>/js/CommUtil.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path%>/openlayer/ol.css">
<script type="text/javascript" src="<%=path%>/openlayer/ol.js"></script>
<script type="text/javascript" src="<%=path%>/js2/map.js"></script>

<!--描述：导入配置函数-->
<script type="text/javascript" src="<%=path%>/js2/config.js"></script>

<!--描述：导入变压器函数-->
<script type="text/javascript" src="<%=path%>/js2/transformerNew.js"></script>

</body>

<script type="text/javascript">
    var config = Config2();

    //为10kv下拉框添加数据
    Comm.getRemoteData({
        url: Config.context + "/lineInfo/with10kvForJson.do",
        data: {
            selectField: "distinct belongLine as belongLine"
        },
        dataType: "json"
    }, function (dataList) {
        dataList.unshift({
            belongLine: "10kV线路"
        });
        Comm.generateCombo("line10kvContainer", "line10kv", dataList,
            "belongLine", function (selectTag) {
            });
    });

    //生成变压器的下拉框
    Transformer.transformerCombo({
        select: "#transformerCombo",
        text: "transformerName"
    });
</script>
<script src="http://cdn.gbtags.com/jqueryui/1.10.4/jquery-ui.min.js"></script>
<script>
    $("#wqtModel").draggable({
        handle: ".modal-header"
    });
</script>
</html>
