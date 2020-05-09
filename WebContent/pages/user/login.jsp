<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录页面</title>
<%--静态包含，头部信息
     base标签+jquery+css样式 --%>
<%@ include file="/pages/access/common/head.jsp" %>

</head>
<body>
		<div id="login_header">
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">个人日常信息管理系统</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1></h1>
								<a href="pages/user/findpassword.jsp">忘记密码</a>
								<a href="pages/user/regist.jsp">立即注册</a>
							</div>
							<div class="msg_cont">
								<b></b>
								<span class="errorMsg">
								<%-- <%=request.getAttribute("msg")==null ? "请输入用户名和密码" : request.getAttribute("msg") %> --%>
								${empty requestScope.msg?"请输入用户名和密码":requestScope.msg }
								</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<input type="hidden" name="action" value="login"/>
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username"
									<%-- value="<%=request.getAttribute("username")==null ? "" : request.getAttribute("username") %>" --%>
									value="${requestScope.username }" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" />
									<br />
									<br />
									<input type="submit" value="登录" id="sub_btn" />
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<%@ include file="/pages/access/common/footer.jsp" %>
</body>
</html>