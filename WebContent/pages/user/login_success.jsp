<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录成功页面</title>
<%--静态包含，头部信息
     base标签+jquery+css样式 --%>
<%@ include file="/pages/access/common/head.jsp"%>
<style type="text/css">
h1 {
	text-align: center;
	margin-top: 200px;
}

h1 a {
	color: red;
}
</style>
</head>
<body>
	<div id="header">
		<%@ include file="/pages/access/common/login_success_menu.jsp"%>
	</div>

	<div id="main">
		<h1>欢迎登录</h1>
	</div>

	<%@ include file="/pages/access/common/footer.jsp"%>
</body>
</html>