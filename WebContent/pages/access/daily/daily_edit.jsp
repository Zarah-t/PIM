<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑日常</title>
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
			
			//匹配日期的正则表达式
			var reg=/^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/; 
			//获取输入框中的日期
			var arrDate=$("#arrDate").val();
			
			//获取提示框
			var tip=$("#errorMsg");
			if(!reg.test(arrDate)){
				tip.html("日期不合法");
				return false;
			}
			
			//判断地点框
			if($("#place").val().trim()==""){
				tip.html("地点不能为空");
				return false;
			}
			
			//判断描述框
			if($("#description").val().trim()==""){
				tip.html("描述不能为空");
				return false;
			}
		})
		
		$("#arrDate,#place,#description").focus(function(){
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
			<form action="DailyServlet" method="post">
			<input type="hidden" name="action" value="${empty param.id?'add':'update' }"/>
			<input type="hidden" name="id" value="${requestScope.daily.id }"/>
			<input type="hidden" name="userId" value="${sessionScope.user.id }"/>
			<input type="hidden" name="pageNo" value="${param.pageNo }"/>
				<table>
					<tr>
						<td></td>
						<td>日期</td>
						<td>地点</td>
						<td>描述</td>
						<td>操作</td>
					</tr>		
					<tr>
						<td id="errorMsg" style="color:red;"></td>
						<td><input placeholder="日期格式:yyyy-mm-dd" id="arrDate" name="arrDate" type="text" value="${requestScope.daily.arrDate }"/></td>
						<td><input id="place" name="place" type="text" value="${requestScope.daily.place }"/></td>
						<td><input id="description" name="description" type="text" value="${requestScope.daily.description }"/></td>
						<td><input type="submit" value="提交"/></td>
					</tr>
				</table>
			</form>
			
	
		</div>
		
		<%@ include file="/pages/access/common/footer.jsp" %>
</body>
</html>