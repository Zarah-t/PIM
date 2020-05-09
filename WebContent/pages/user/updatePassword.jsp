<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改密码页面</title>
<!-- 静态包含，头部信息
     base标签+jquery+css样式 -->
<%@ include file="/pages/access/common/head.jsp" %>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
</style>
<script type="text/javascript">
	$(function(){
		//获取错误码提示框
		var errorMsg=$("#errorMsg");
		
		
		//为修改和确认密码绑定获取焦点事件
		$("#modify,#identify").focus(function(){
			if(errorMsg.text()!=""){
				errorMsg.text("");
				
			}
		})
		

		
		//修改的密码要符合要求
		$(":button").click(function(){
			
			//获取密码输入框中的内容
			var modifyText= $("#modify").val();		
			//获取确认密码框中的内容
			var identifyText = $("#identify").val();
			
            //验证密码：必须由字母，数字下划线组成，并且长度为5到12位
			//2 创建正则表达式对象
			var usernamePatt = /^\w{5,12}$/;
			//3 调用test方法验证结果
			if ( !usernamePatt.test(modifyText)) {
				//4 如果不合法，要提示用户，并阻止提交。
				errorMsg.text("密码需由5至12位的数字或字母组成");
				return false; 
			} 
			
			//判断密码是否一致
			if(modifyText!=identifyText){
				//4 如果不一致，要提示用户，并阻止提交。
				errorMsg.text("密码不一致");
			}else{
				errorMsg.text("");
  				$.getJSON("${path}userServlet","action=updatePassword&id=${sessionScope.user.id}&password="+modifyText,function(data){
					if(data.flag){
						alert("修改成功")
					}
				}) 
			} 
		})

})
</script>
</head>
<body>
		<div id="header">
				<!-- <img class="logo_img" alt="" src="#" > -->
				<%@ include file="/pages/access/common/login_success_menu.jsp" %>
		</div>
		
		<div id="main">
		<table>
			<tr>
				<th colspan="2" style="color:red">登录密码修改</th>
			</tr>
			<tr><td colspan="2"></td></tr>
			<tr><td colspan="2"></td></tr>
			<tr>
				<td >
					新密码
				</td>
				<td>
					<input id="modify" type="password" />
				</td>
			</tr>	
			<tr>
				<td >
					确认密码
				</td>
				<td>
					<input id="identify" type="password"/>
				</td>
			</tr>	
			<tr>
				<td colspan="2">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="提交">
				</td>
			</tr>	
		</table>
			<center><span id="errorMsg" style="color:red;"></span></center>		
		</div>
		
		<%@ include file="/pages/access/common/footer.jsp" %>
</body>
</html>