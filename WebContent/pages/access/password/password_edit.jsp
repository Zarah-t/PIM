<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加/更改密码</title>
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

input {
	text-align: center;
}
</style>
</head>
<script type="text/javascript">
	$(function(){

		$(":submit").click(function(){
			//对应说明不能为空
			if($("#pname").val().trim()==""){
				$("#errorMsg").text("密码对应说明不能为空");
				return false;
			}
			//密码不能为空
			if($("#pass").val().trim()==""){
				$("#errorMsg").text("密码不能为空");
				return false;
			}
	
			
		})
		
		//为所有信息框绑定获取焦点事件
		$("#pname,#pass").focus(function(){
			$("#errorMsg").text("");
		})
		
	})

</script>
<body>
	<div id="header">
		<%--静态包含模块的菜单 --%>
		<%@ include file="/pages/access/common/login_success_menu.jsp"%>
	</div>

	<div id="main">
		<form action="passwordServlet" method="post">
			<input type="hidden" name="action" value="${empty param.id?'add':'update' }" /> 
			<input type="hidden" name="id" value="${requestScope.password.id }" />
		 	<input type="hidden" name="uid" value="${sessionScope.user.id }" />
			<table>
				<tr>
					<td>应用/网站</td>
					<td>密码</td>
					<td>操作</td>
				</tr>
				<tr>
					<td><input  id="pname" name="pname"
						type="text" value="${requestScope.password.pname }" /></td>
					<td><input  id="pass" name="pass"
						type="text" value="${requestScope.password.pass }" /></td>
					<td><input type="submit" value="提交" /></td>
				</tr>
				<tr>
					<td colspan="3"><span id="errorMsg" style="color: red"></span></td>
				</tr>
			</table>
		</form>


	</div>

	<%@ include file="/pages/access/common/footer.jsp"%>
</body>
</html>