<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>账务分类管理</title>
<%--静态包含，头部信息
     base标签+jquery+css样式 --%>
<%@ include file="/pages/access/common/head.jsp" %>
<script type="text/javascript">
 	$(function() {
/* 		$(":submit")
				.click(
						function() {
							alert(1);
							return false;
							searchContact = $("#searchContact").val().trim();
							if (searchContact == "") {
								location.href = "contactServlet?action=page";
								return false;
							}
							location.href = "contactServlet?action=pageByContact&searchContact="
									+ searchContact;
						})  */

		$(".deleteB").click(
				function() {
					//confirm它是JavaScript语言中提供的一个确认提示框函数
					//传递的参数就是提示框提示内容
					//它有两个按钮，一个确定，一个取消，当用户点击了确定之后返回true，点击取消之后返回false
					return confirm("删除该项分类则对应的账务明细也随之删除，你确定要删除【"
							+ $(this).parent().parent().find("td:first").text()
							+ "】这项分类吗");
				})

	})
</script>

</head>
<body>
	
	<div id="header">
		<%--静态包含模块的菜单 --%>
		<%@ include file="/pages/access/common/login_success_menu.jsp" %>
	</div>
	
	<!-- <div style="margin-left:83px;font-size:20px"> -->
	<div style="margin-top:0px;font-size:20px;width:1200px;overflow:auto">
		<a href="billServlet?action=getBillPage">收支管理</a>  
		<a href="sortServlet?action=querySorts&">分类管理</a>

			
	</div>
	<div id="main">
		<table>
			<tr style="height:10px;padding:10px">
				<td>分类名称</td>
				<td>收支</td>
				<td>操作</td>
			</tr>
			<tr>
				<td colspan="3">
					<div style="height: 270px; width: 420px; overflow: auto;">
						<table style="margin:0px;">
							<c:forEach items="${requestScope.sorts }" var="sort">
								<tr>
									<td>${sort.name }</td>
									<td>${sort.parent }</td>
									<td><a
										href="sortServlet?action=getSort&id=${sort.id }">修改</a>&nbsp;&nbsp;
										<a class="deleteB"
										href="sortServlet?action=delete&id=${sort.id }">删除</a>
									</td>
								</tr>
							</c:forEach>
						</table>
					</div>

				</td>

			</tr>
			<tr>
				<td></td>
				<td></td>
				<td><a href="pages/access/bill/sort_edit.jsp">添加分类</a></td>
			</tr>
		</table>
		<br />

	</div>

	<%--静态包含引入页脚 --%>
	<%@ include file="/pages/access/common/footer.jsp" %>
</body>
</html>