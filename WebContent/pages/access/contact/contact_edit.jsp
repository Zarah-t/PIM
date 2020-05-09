<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑联系人</title>
<%--静态包含，头部信息
     base标签+jquery+css样式 --%>
<%@ include file="/pages/access/common/head.jsp" %>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
	
	input {
		text-align: center;
	}
</style>
</head>
<script type="text/javascript">
	$(function(){
		$(":submit").click(function(){
			//判断描述框
			if($("#contact").val().trim()==""){
				$("#errorMsg").text("联系人不能为空");
				return false;
			}
		})
		
		$("#contact").focus(function(){
			$("#errorMsg").text("");
		})
	})

</script>
<body>
		<div id="header">
			<%--静态包含模块的菜单 --%>
			<%@ include file="/pages/access/common/login_success_menu.jsp" %>
		</div>
		
		<div id="main">
			<form action="contactServlet" method="post">
			<input type="hidden" name="action" value="${empty param.id?'add':'update' }"/>
			<input type="hidden" name="id" value="${requestScope.contact.id }"/>
			<input type="hidden" name="userId" value="${sessionScope.user.id }"/>
			<input type="hidden" name="pageNo" value="${param.pageNo }"/>
				<table>
					<tr>
						<td>联系人</td>
						<td>电话号码</td>
						<td>微信</td>
						<td>QQ</td>
						<td>生日</td>
						<td>地址</td>
						<td>操作</td>
					</tr>		
					<tr>
						<td><input placeholder="联系人不能为空" id="contact" name="contact" type="text" value="${requestScope.contact.contact }"/></td>
						<td><input id="phone" name="phone" type="text" value="${requestScope.contact.phone }"/></td>
						<td><input id="wechat" name="wechat" type="text" value="${requestScope.contact.wechat }"/></td>
						<td><input id="qq" name="qq" type="text" value="${requestScope.contact.qq }"/></td>
						<td><input id="birth" name="birth" type="text" value="${requestScope.contact.birth }"/></td>
						<td><input id="address" name="address" type="text" value="${requestScope.contact.address }"/></td>
						<td><input type="submit" value="提交"/></td>
					</tr>
 					<tr>
						<td><span id="errorMsg" style="color:red"></span></td>
						<td  colspan="6"></td>
					</tr>	
				</table>
			</form>
			
	
		</div>
		
		<%@ include file="/pages/access/common/footer.jsp" %>
</body>
</html>