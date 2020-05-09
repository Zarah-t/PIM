<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div style="background-color:00CACA;width:1200px;">
	<a style="font-size:30px;color:black;text-decoration:underline" href="pages/user/updatePassword.jsp">修改密码</a>
`	<a style="font-size:30px;color:black;text-decoration:underline" href="billServlet?action=getBillPage&userId=${sessionScope.user.id }">账务管理</a>&nbsp;
	<a style="font-size:30px;color:black;text-decoration:underline" href="passwordServlet?action=queryPasswords">密码管理</a>&nbsp;
	<a style="font-size:30px;color:black;text-decoration:underline" href="DailyServlet?action=page&pageNo=${requestScope.page.pageNo }">日常事务</a>&nbsp;
	<a style="font-size:30px;color:black;text-decoration:underline" href="contactServlet?action=page">联系人管理</a>
	<span style="font-size:30px;">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</span>
	<span>当前用户<span class="um_span">${sessionScope.user.username }</span></span>
		<a href="userServlet?action=logout">退出</a>&nbsp;&nbsp;
		<a href="pages/user/login_success.jsp">返回首页</a>
</div>

<!-- &id=${daily.id } -->