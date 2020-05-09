<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑分类信息</title>
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
			//判断分类信息框
			if($("#name").val().trim()==""){
				$("#errorMsg").text("分类名称不能为空");
				return false;
			}
			if($("#errorMsg").text()!=""){
				return false;
			}
		})
		
		//验证分类名称是否存在
		$("#name").blur(function(){
			$.getJSON("sortServlet","action=ajaxTestSort&name="+$("#name").val().trim(),function(data){
				if(data!=null){
					$("#errorMsg").text("分类名称已存在，请换一个");
				}
				
			})
		})
		
		
		$("#name").focus(function(){
			$("#errorMsg").text("");
		})
	})

</script>
<body>
	<div id="header">
		<%--静态包含模块的菜单 --%>
		<%@ include file="/pages/access/common/login_success_menu.jsp"%>
	</div>
	<div style="margin-top:0px;font-size:20px;width:1200px;overflow:auto">
		<a href="billServlet?action=getBillPage">收支管理</a> <a
			href="sortServlet?action=querySorts&">分类管理</a>
	</div>
	<div id="main">
		<form action="sortServlet" method="post">
			<input type="hidden" name="action" value="${empty sort.id?'add':'update' }" /> 
			<input type="hidden" name="id" value="${requestScope.sort.id }" />
		 	<input type="hidden" name="userId" value="${sessionScope.user.id }" />
			<table>
				<tr>
					<td>分类名称</td>
					<td>收支</td>
					<td>操作</td>
				</tr>
				<tr>
					<td><input placeholder="分类信息不能为空" id="name" name="name"
						type="text" value="${requestScope.sort.name }" /></td>
					<td><select id="parent" name="parent">
							<option value="收入">收入</option>
							<c:if test="${requestScope.sort.parent=='支出' }">
								<option value="支出" selected>支出</option>
							</c:if>
							<c:if test="${requestScope.sort.parent!='支出' }">
								<option value="支出">支出</option>
							</c:if>
					</select></td>
					<td><input type="submit" value="提交" /></td>
				</tr>
				<tr>
					<td  colspan="2"><span id="errorMsg" style="color: red"></span></td>
					<td></td>
				</tr>
			</table>
		</form>


	</div>

	<%@ include file="/pages/access/common/footer.jsp"%>
</body>
</html>