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
<html>
<head lang="zh">
	<base href="<%=path %>/" />
    <meta charset="UTF-8">
    <title>page home</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--bootstrap-->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome-4.4.0/css/font-awesome.css" rel="stylesheet">
    <link href="css/pagehome.css" rel="stylesheet">
</head>
<body>
<div class="site-wrapper">
    <div class="site-wrapper-inner">
        <div class="cover-container">
            <div class="masthead clearfix">
                <div class="inner">
                    <h3 class="masthead-brand"><img src="img/logofff.png"></h3>
                    <nav>
                         <ul class="nav masthead-nav">
                            <li class="active"><a ><i class="fa fa-home fa-lg"></i> 首页</a></li>
                            <li class=""><a><i class="fa fa-user fa-lg"></i> ${person.personName }</a></li>
                            <li class="dropdown"><a href="logout.do"><i
							class="fa fa-power-off"></i> 退出</a></li>
                         </ul>
                    </nav>
                </div>
            </div>
            <div class="inner cover">
                <h2 class="cover-heading">贵州电网公司乌当供电局配网抢修服务调度监控平台</h2>
                <div class="row">
                    <div class="col-xs-6 col-sm-6 col-md-3">
                        <div class="blok text-center">
                            <div class="hexagon-a">
                                <a class="hlinktop" href="#">
                                    <div class="hexa-a">
                                        <div class="hcontainer-a">
                                            <div class="vertical-align-a">
                                                <span class="texts-a"><i class="fa fa-comments"></i></span>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                            <div class="hexagon">
                                <a class="hlinkbott" href="#">
                                    <div class="hexa">
                                        <div class="hcontainer">
                                            <div class="vertical-align">
                                                <span class="texts"></span>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                            <h4>操作信息</h4>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-6 col-md-3">
                        <div class="blok text-center">
                            <div class="hexagon-a">
                                <a class="hlinktop" href="mapInterface2.do">
                                    <div class="hexa-a">
                                        <div class="hcontainer-a">
                                            <div class="vertical-align-a">
                                                <span class="texts-a"><i class="fa fa-map"></i></span>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                            <div class="hexagon">
                                <a class="hlinkbott" href="#">
                                    <div class="hexa">
                                        <div class="hcontainer">
                                            <div class="vertical-align">
                                                <span class="texts"></span>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                            <h4>地图中心</h4>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-6 col-md-3">
                        <div class="blok text-center">
                            <div class="hexagon-a">
                                <a class="hlinktop" href="customer/maintainView.do">
                                    <div class="hexa-a">
                                        <div class="hcontainer-a">
                                            <div class="vertical-align-a">
                                                <span class="texts-a"><i class="fa fa-edit"></i></span>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                            <div class="hexagon">
                                <a class="hlinkbott" href="#">
                                    <div class="hexa">
                                        <div class="hcontainer">
                                            <div class="vertical-align">
                                                <span class="texts"></span>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>

                            <h4>数据中心</h4>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-6 col-md-3">
                        <div class="blok text-center">

                            <div class="hexagon-a">
                                <a class="hlinktop" href="#">
                                    <div class="hexa-a">
                                        <div class="hcontainer-a">
                                            <div class="vertical-align-a">
                                                <span class="texts-a"><i class="fa fa-bar-chart"></i></span>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                            <div class="hexagon">
                                <a class="hlinkbott" href="#">
                                    <div class="hexa">
                                        <div class="hcontainer">
                                            <div class="vertical-align">
                                                <span class="texts"></span>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                            <h4>绩效考核</h4>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-12">
        <div class="mastfoot">
            <div class="inner">
                <p>中国南方电网有限责任公司版权所有</p>
            </div>
        </div>
    </div>
    </div>
    </div>
<script src="js/jquery-2.0.0.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>