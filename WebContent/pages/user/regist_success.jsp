<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册成功页面</title>
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
	</div>

	<div id="main">

		<h1>
			注册成功! <a href="index.jsp">转到登录页面</a>
		</h1>

	</div>

	<%@ include file="/pages/access/common/footer.jsp"%>
</body>
</html>