<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"
 session="true"%>
 <%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head lang="en">
	<base href="<%=path %>/"/>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>提交成功</title>
</head>	
<link rel="stylesheet" href="css/font-awesome-4.4.0/css/font-awesome.min.css">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-theme.min.css" rel="stylesheet">
<link href="css/button.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<body>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 col-xs-12">
                <img src="img/logo@2x.png" alt="logo" class="img-responsive center-block" />
            </div>
        </div>
    </div>
</nav>
<div class="page-header text-center">
</div>
<div class="col-lg-12">
    <div class="panel panel-success">
        <div class="panel-heading">
            <h3 class="panel-title">
                	提交信息
            </h3>
        </div>
        <div class="panel-body">
            <h2 class="text-center" style="color: #228B22;"><i class="fa fa-check"></i>您的信息已提交成功！</h2>
            <a href="powerBusinessInfo/management.do">返回</a>
        </div>
    </div>
</div>
</body>
</html>
