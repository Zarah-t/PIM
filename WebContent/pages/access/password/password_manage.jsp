<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>各类密码管理</title>
<%--静态包含，头部信息
     base标签+jquery+css样式 --%>
<%@ include file="/pages/access/common/head.jsp" %>
<script type="text/javascript">
 	$(function() {

		$(".deleteB").click(
				function() {
					//confirm它是JavaScript语言中提供的一个确认提示框函数
					//传递的参数就是提示框提示内容
					//它有两个按钮，一个确定，一个取消，当用户点击了确定之后返回true，点击取消之后返回false
					return confirm("你确定要删除【"
							+ $(this).parent().parent().find("td:first").text()
							+ "】这项密码吗");
				})

	})
</script>

</head>
<body>
	
	<div id="header">
		<%--静态包含模块的菜单 --%>
		<%@ include file="/pages/access/common/login_success_menu.jsp" %>
	</div>
	<div id="main">
		<table>
			<tr style="height:10px;padding:10px">
				<td>应用/网站</td>
				<td>密码</td>
				<td>操作</td>
			</tr>
			<tr>
				<td colspan="3">
					<div style="height: 270px; width: 420px; overflow: auto;">
						<table style="margin:0px;">
							<c:forEach items="${requestScope.passwords}" var="password">
								<tr>
									<td>${password.pname }</td>
									<td>${password.pass }</td>
									<td><a
										href="passwordServlet?action=getPassword&id=${password.id }">修改</a>&nbsp;&nbsp;
										<a class="deleteB"
										href="passwordServlet?action=delete&id=${password.id }">删除</a>
									</td>
								</tr>
							</c:forEach>
						</table>
					</div>

				</td>

			</tr>
			<tr>
				<td></td>
				<td colspan="2"><a href="pages/access/password/password_edit.jsp">添加应用/网站密码</a></td>
			</tr>
		</table>
		<br />

	</div>

	<%--静态包含引入页脚 --%>
	<%@ include file="/pages/access/common/footer.jsp" %>
</body>
</html>