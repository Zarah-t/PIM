<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>账务明细管理</title>
<%--静态包含，头部信息
     base标签+jquery+css样式 --%>
<%@ include file="/pages/access/common/head.jsp" %>
<link rel="stylesheet" type="text/css" href="static/css/jedate.css">
<script type="text/javascript" src="static/script/jedate.min.js"></script>
<script type="text/javascript" src="static/script/time.js"></script>
<script type="text/javascript">
 	$(function(){
 		
 		//根据收支实现下拉列表级联
 		$("#parent").change(function(){
 	 	 	$.getJSON("${path}sortServlet","action=ajaxSort&parent="+$(this).val(),function(data){
	 			//获取分类的select
				sortSelect=$("#name");
				sortSelect.html("<option value='请选择'>-请选择-</option>")
	 			
 				for(var i=0;i<data.length;i++){
 					$("<option value="+data[i].name+">"+data[i].name+"</option>").appendTo(sortSelect);
 				} 
 			 })

 		})
 		
		
		$(".deleteB").click(function(){
			//confirm它是JavaScript语言中提供的一个确认提示框函数
			//传递的参数就是提示框提示内容
			//它有两个按钮，一个确定，一个取消，当用户点击了确定之后返回true，点击取消之后返回false
			return confirm("你确定要删除【"+$(this).parent().parent().find("td:eq(5)").text()+$(this).parent().parent().find("td:first").text()+"】这条明细吗");
		})
		
				$("#parent").find("option[value='${requestScope.parent}']").attr("selected",'selected');
 		 $("#name").append($("<option value=${requestScope.name } selected>${requestScope.name }</option>"))
		

		
	})

</script>

</head>
<body>
	
	<div id="header">
		<%--静态包含模块的菜单 --%>
		<%@ include file="/pages/access/common/login_success_menu.jsp" %>
	</div>
	
	<div style="margin-top:0px;font-size:20px;width:1200px;overflow:auto">
	<form method="post" action="billServlet">
		<input type="hidden" name="action" value="getBillPage"/>
		<a href="billServlet?action=getBillPage">收支管理</a>  
		<a href="sortServlet?action=querySorts">分类管理</a>
		
		起始:
		<input type="text" name="startTime" id="startTime" value="${requestScope.start }" readonly />
		至
		<input type="text" name="endTime" id="endTime" value="${requestScope.end }" readonly/>
		
		收支:
		<select id="parent" name="parent">
			<option value="请选择">-请选择-</option>
			<option value="收入">收入</option>
			<option value="支出">支出</option>		
		</select>
		
		分类:
		<select id="name" name="name">
			<option value="请选择">-请选择-</option>
		</select>
		
		<input type="submit" value="查询"/>
	</form>		
	</div>
	<div id="main">
		<table>
			<tr style="height: 10px; padding: 10px">
				<td>说明</td>
				<td>费用</td>
				<td>来源</td>
				<td>分类</td>
				<td>收支</td>
				<td>时间</td>
				<td colspan="2">操作</td>

			</tr>
			<tr>
				<td colspan="8">
					<div style="height: 270px; width: 980px; overflow: auto;">
						<table style="margin: 0px; align: center">
							<c:forEach items="${requestScope.billPage.items }" var="item">
								<tr>
									<td>${item.description }</td>
									<td>${item.money }</td>
									<td>${item.source }</td>
									<td>${item.name }</td>
									<td>${item.parent }</td>
									<td>${item.date }</td>
									<td><a href="billServlet?action=getBill&id=${item.id }">修改</a>&nbsp;&nbsp;
										<a class="deleteB"
										href="billServlet?action=delete&startTime=${requestScope.start }&endTime=${requestScope.end }&parent=${requestScope.parent }&name=${requestScope.name }&id=${item.id }">删除</a>
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
				<td></td>
				<td colspan="2">收入:<input type="text"
					value="${requestScope.billPage.in }" style="width: 100px" /></td>
				<td colspan="2">支出:<input type="text"
					value="${requestScope.billPage.out }" style="width: 100px" /></td>
				<td><a href="pages/access/bill/bill_edit.jsp">添加明细</a></td>
			</tr>
		</table>
		<br />
	</div>

	<%--静态包含引入页脚 --%>
	<%@ include file="/pages/access/common/footer.jsp" %>
</body>
</html>