<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>日常安排</title>
<%--静态包含，头部信息
     base标签+jquery+css样式 --%>
<%@ include file="/pages/access/common/head.jsp" %>
<script type="text/javascript">
	$(function(){
		$(".deleteB").click(function(){
			//confirm它是JavaScript语言中提供的一个确认提示框函数
			//传递的参数就是提示框提示内容
			//它有两个按钮，一个确定，一个取消，当用户点击了确定之后返回true，点击取消之后返回false
			//this指当前相应的dom对象
			return confirm("你确定要删除事务【"+$(this).parent().parent().find("td:first").text()+$(this).parent().parent().find("td:first").next().text()+$(this).parent().parent().find("td:first").next().next().text()+"】吗");
		})
		
		
		$("#searchButton").click(function(){		
			//匹配日期的正则表达式
			var reg=/^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/; 
			//获取输入框中的日期
			var arrDate=$("#searchDate").val();
			
			//获取提示框
			var tip=$("#errorMsg");
			if(!reg.test(arrDate)){
				tip.html("日期不合法");
				return false;
			}
			
			location.href="DailyServlet?action=pageByDate&searchDate="+arrDate;
		})
		
		$("#searchDate").focus(function(){
			$("#errorMsg").text("");
		})
		
		//显示全部
		$("#allButton").click(function(){
				location.href="DailyServlet?action=page";
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
				<td>日期</td>
				<td>地点</td>
				<td>描述</td>
				<td >操作</td>
				<td></td>
			</tr>
			<c:forEach items="${requestScope.page.items }" var="daily">
				<tr>
				<td>${daily.arrDate }</td>
				<td>${daily.place }</td>
				<td>${daily.description }</td>
				<td>
					<a href="DailyServlet?action=getDaily&id=${daily.id }&pageNo=${requestScope.page.pageNo }">修改</a>&nbsp;&nbsp;
				</td>
				<td>
					<a class="deleteB" href="DailyServlet?action=delete&id=${daily.id }&pageNo=${requestScope.page.pageNo }">删除</a>
				</td>	
					
			</tr>
			</c:forEach>			
			<tr>
				<td><span id="errorMsg" style="color:red;"></span></td>
				<td colspan="3">
						日期:<input placeholder="日期格式:yyyy-mm-dd" type="text" value="${param.searchDate }" id="searchDate"/>
						<input type="button" value="查询" id="searchButton" />
						<input type="button" value="显示全部" id="allButton"/>
					</td>
				<td><a href="pages/access/daily/daily_edit.jsp">添加日常</a></td>
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