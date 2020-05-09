<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>联系人管理</title>
<%--静态包含，头部信息
     base标签+jquery+css样式 --%>
<%@ include file="/pages/access/common/head.jsp" %>
<script type="text/javascript">
	$(function(){
		$(":submit").click(function(){
/* 			alert(1);
			return false; */
			searchContact=$("#searchContact").val().trim();
			if(searchContact==""){
				location.href="contactServlet?action=page";
				return false;
			}
			location.href="contactServlet?action=pageByContact&searchContact="+searchContact;
		})
		
		$(".deleteB").click(function(){
			//confirm它是JavaScript语言中提供的一个确认提示框函数
			//传递的参数就是提示框提示内容
			//它有两个按钮，一个确定，一个取消，当用户点击了确定之后返回true，点击取消之后返回false
			return confirm("你确定要联系人【"+$(this).parent().parent().find("td:first").text()+"】吗");
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
			<tr>
				<td>联系人</td>
				<td>电话</td>
				<td>微信</td>
				<td>QQ</td>
				<td>生日</td>
				<td >地址</td>
				<td >操作</td>
			</tr>
			<c:forEach items="${requestScope.page.items }" var="contact">
				<tr>
				<td>${contact.contact }</td>
				<td>${contact.phone }</td>
				<td>${contact.wechat }</td>
				<td>${contact.qq }</td>
				<td>${contact.birth }</td>
				<td>${contact.address }</td>
				<td>
					<a href="contactServlet?action=getDaily&id=${contact.id }&pageNo=${requestScope.page.pageNo }">修改</a>&nbsp;&nbsp;
					<a class="deleteB" href="contactServlet?action=delete&id=${contact.id }&pageNo=${requestScope.page.pageNo }">删除</a>
				</td>		
			</tr>
			</c:forEach>			
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td colspan="2">
						联系人:<input placeholder="查询联系人" type="text" value="${param.contact }" id="searchContact"/>
						<input type="submit" value="查询" />
					</td>
				<td><a href="pages/access/contact/contact_edit.jsp">添加联系人</a></td>
			</tr>	
		</table>
		<br/>

	</div>
	<%-- 静态包含引入分页条 --%>
	<%@ include file="/pages/access/common/page.jsp" %>
	
	<%--静态包含引入页脚 --%>
	<%@ include file="/pages/access/common/footer.jsp" %>
</body>
</html>