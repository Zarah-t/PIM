<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加账务明细</title>
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
		
		
		
		
		//
		$(":submit").click(function(){
			//判断说明
			if($("#description").val().trim()==""){
				$("#errorMsg").text("说明不能为空");
				return false;
			}
			//判断费用
			if($("#money").val().trim()<=0){
				$("#errorMsg").text("费用必须为大于0的数字");
				return false;
			}
			//判断来源
			if($("#source").val().trim()==""){
				$("#errorMsg").text("账户来源不能为空");
				return false;
			}
			//判断收支
			if($("#parent").val()=="请选择"){
				$("#errorMsg").text("请选择收入或分支其中一项");
				return false;
			}
			//判断分类
			if($("#name").val()=="请选择"){
				$("#errorMsg").text("请选择任意一项分类");
				return false;
			}
			//判断时间
			//匹配日期的正则表达式
			var reg=/^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/; 
			//获取输入框中的日期
			var arrDate=$("#date").val();		
			//获取提示框
			var tip=$("#errorMsg");
			if(!reg.test(arrDate)){
				tip.html("日期不合法");
				return false;
			}
	
			
		})
		
		//为所有信息框绑定获取焦点事件
		$("#description,#money,#source,#parent,#name,#date").focus(function(){
			$("#errorMsg").text("");
		})
		
		
		 $("#parent").find("option[value='${requestScope.bill.parent}']").attr("selected",'selected');
 		 $("#name").append($("<option value=${requestScope.bill.name } selected>${requestScope.bill.name }</option>"))
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
		<form action="billServlet" method="post">
			<input type="hidden" name="action" value="${empty param.id?'add':'update' }" /> 
			<input type="hidden" name="id" value="${requestScope.bill.id }" />
		 	<input type="hidden" name="uid" value="${sessionScope.user.id }" />
			<table>
				<tr>
					<td>说明</td>
					<td>费用</td>
					<td>来源</td>
					<td>收支</td>
					<td>分类</td>
					<td>时间</td>
					<td>操作</td>
				</tr>
				<tr>
					<td><input placeholder="说明信息不能为空" id="description" name="description"
						type="text" value="${requestScope.bill.description }" /></td>
					<td><input placeholder="费用必须大于0" id="money" name="money"
						type="text" value="${requestScope.bill.money }" /></td>
					<td><input placeholder="账户来源不能为空" id="source" name="source"
						type="text" value="${requestScope.bill.source }" /></td>						
					<td><select id="parent" name="parent">
							<option value="请选择">-请选择-</option>
							<option value="收入">收入</option>
							<c:if test="${requestScope.bill.parent=='支出' }">
								<option value="支出" selected>支出</option>
							</c:if>
							<c:if test="${requestScope.bill.parent!='支出' }">
								<option value="支出">支出</option>
							</c:if>
					</select></td>
					<td><select id="name" name="name">
							<option value="请选择">请选择</option>
					</select></td>
					<td><input placeholder="yyyy--MM-dd" id="date" name="date"
						type="text" value="${requestScope.bill.date }" /></td>
					<td><input type="submit" value="提交" /></td>
				</tr>
				<tr>
					<td colspan="7"><span id="errorMsg" style="color: red"></span></td>
				</tr>
			</table>
		</form>


	</div>

	<%@ include file="/pages/access/common/footer.jsp"%>
</body>
</html>