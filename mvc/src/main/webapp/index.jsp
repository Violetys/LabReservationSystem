<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>登录</title>
	</head>

	<body>
		<!--登录窗口-->
		<div class="login-box">
			<!--人像登录窗口-->
			<div id="face-login" class="btn-warning">
				<h4>人像</h4>
				<video id="video"></video>
				<canvas id="canvas" style="display: none;"></canvas>
			</div>
		</div>
	</body>
	<script src="jquery-3.3.1/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="js/GetFace.js"></script>
	<script type="text/javascript" src="js/LoginAjax.js"></script>
	<script type="text/javascript" src="js/index.js"></script>
</html>
