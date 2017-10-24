<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>session超时</title>
</head>
<body>
	<script type="text/javascript" src="js/jquery-2.0.0.min.js"></script>
	<script type="text/javascript">
		$(function() {
			returnUrlByTime();
		});
		var time = 4;
		function returnUrlByTime() {
			window.setTimeout('returnUrlByTime()', 1000);
			time = time - 1;
			document.getElementById("qc-sessionTimeOut-layer").innerHTML = time;
			if(time == 1) {
				//console.info(window);
				window.top.location.href="login.do";
			}
		}
	</script>

<h3>登录已超时！</h3>
    <b><span id="qc-sessionTimeOut-layer">4</span>秒后，转入登录界面。</b>
</body>
</html>