<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"
 session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head lang="zh">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>登录界面</title>
    <link rel="stylesheet" href="css/font-awesome-4.4.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <link href="css/entry.css" rel="stylesheet">
</head>
<body>
<div id="container">
    <div class="inner-bg">
        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-sm-offset-2 text">
                    <img src="img/logos.png" class="img-responsive center-block">
                    <div class="description">
                        <p>
                            贵州电网公司乌当供电局配网抢修服务调度监控平台
                        </p>
                    </div>
                </div>
                <div class="login">
                    <form class="form-login" id="form-login" action="sys_user_login_form.do" method="post">
                        <div class="form-group form-input clearfix">
                            <span><i class="fa fa-envelope fa-lg"></i></span>
                            <input type="text" class="input-lg" id="Username" placeholder="请输入您的账号" name="j_username">
                        </div>
                        <div class="form-group form-input clearfix">
                            <span><i class="fa fa-key fa-lg"></i></span>
                            <input type="password" class="input-lg" id="Password" placeholder="请输入您的密码" name="j_password">
                        </div>
                        <button type="submit" class="btn btn-primary btn-lg btn-block" id="form-submit">登&nbsp;&nbsp;&nbsp;&nbsp;录</button>
                        
                        <div class="form-group form-signin">
                            <c:if test="${param.authentication_error eq 2}">
							<div class='form-group'>
								<label class='col-2 control-label'></label>
								<div class='col-10 col-last'>
									<font color="red">${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}</font>
								</div>
							</div>
							</c:if> 
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 推荐开源CDN来选取需引用的外部JS //-->
<script src="js/jquery-2.0.0.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/entry.js"></script>
</body>
</html>