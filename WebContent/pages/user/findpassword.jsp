<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>验证页面</title>
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
	$(function() {
		//获取提示框对象
		var errorMsg=$("#errorMsg");
		$("#email")
				.blur(
						function() {
							// 邮箱验证：xxxxx@xxx.com
							// 1 先获取邮箱内容
							var emailText = $("#email").val();
							// 2 创建邮箱正则表达式
							var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
							// 3 使用test方法验证结果
							if (!emailPatt.test(emailText)) {
								// 4 不合法时提示用户，阻止提交
								errorMsg.text("邮箱格式不合法!");
							}
							
						});
		$("#email")
			.focus(function(){
				errorMsg.text("")
				});

		
		var emailO=$("#email");
 		$(":button").click(function(){
 			if(errorMsg.text().trim()!=""){
				return;
			}
			$.getJSON("${path}userServlet", "action=sendEmail&email="+emailO.val(), function(data) {
				errorMsg.text(data.tip);

			});	 
		}) 

	});
</script>
</head>
<body>
		<div id="header">

		</div>
		
		<div id="main" style="border:0px;">
		<br/><br/><br/><br/>
		<table style="text-align:center;background-color:#fff;">
			<tr>
				<td style="border:0px"></td>
				<td style="border:0px"></td>
			</tr>	
			<tr>
				<td colspan="2" style="border:0px">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="pages/user/login.jsp" style="color:red;font-size:15px;text-decoration:none">返回登录</a><br/>
					邮箱:<input id="email" type="text"/>
				</td>
			</tr>	
			<tr>
				<td colspan="2" >
					<div style="background-color:#ADD8E6;width:185px">
					<input  style="border:0px;color:#fff;background-color:#ADD8E6" type="button" value="提交">
					</div>	
					<br/>			 
				</td>
			</tr>	
		</table>
			<center><span id="errorMsg" style="color:red;">请输入邮箱找回密码</span></center>		
		</div>
		
		<%@ include file="/pages/access/common/footer.jsp" %>
</body>
</html>