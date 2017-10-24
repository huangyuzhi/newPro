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
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <base href="<%=basePath%>"/>
    <title>业务办理</title>
</head>
<link href="css/bootstrap.min.css" rel="stylesheet">
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
    <div class="container">
        <div class="row">
            <div class="col-lg-12 col-xs-12">
                <div class="page-header text-center">
                    <h2>供电业务办理</h2>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <p>
                    <a href="powerBusinessInfo/xzsqForm.do" class="btn btn-block btn-lg btn-primary">新装申请</a>
                </p>
                <p>
                    <a href="powerBusinessInfo/zrsqForm.do" class="btn btn-block btn-lg btn-primary">增容申请</a>
                </p>
                <p>
                    <a href="powerBusinessInfo/ghsqForm.do" class="btn btn-block btn-lg btn-primary">过户申请</a>
                </p>
                <p>
                    <a href="powerBusinessInfo/glsqForm.do" class="btn btn-block btn-lg btn-primary">改类</a>
                </p>
                <p>
                    <a href="powerBusinessInfo/ybsqForm.do" class="btn btn-block btn-lg btn-primary">移表</a>
                </p>
                <p>
                    <a href="powerBusinessInfo/zcsqForm.do" class="btn btn-block btn-lg btn-primary">暂拆</a>
                </p>
                <p>
                    <a href="powerBusinessInfo/xhsqForm.do" class="btn btn-block btn-lg btn-primary">销户</a>
                </p>
            </div>
        </div>
    </div>

</body>
</html>
