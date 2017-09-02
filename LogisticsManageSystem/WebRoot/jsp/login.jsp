<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'Login.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
	<style type="text/css">
		.class_body{
			width:1000px;
			height:600px;
			margin:0px auto;
			background-image: url("images/login.gif");
		}
		.class_divA{
			text-align:center;
			width:300px;
			height:200px;
			position:relative;
			top:274px;
			left:340px;
		}
		.input1{
			width:150px;
			height:28px;
			text-align: center;
			border-radius:5px; 
		}
		#div_idA{
			height:7px;
		}
		#div_idB{
			height:15px;
		}
		.input2{
			border:1px solid honeydew;
			height:25px;
			width:70px;
			background-color:honeydew;
		}
		label{
			font-size:16px;
		}
	</style>
  </head>
  
  <body>
  	<div class="class_body">
  		<div class="class_divA">
  			<div><h1>物流管理平台</h1></div>
  			<form id="loginFrom" action="User/login" method="post">
  				<label>账户名：</label>
		    	<input class="input1" type="text" name="account" value="" placeholder="请输入帐户名"><br>
		    	<div id="div_idA"></div>
		    	<label>密&nbsp;码：</label>
		    	<input class="input1" type="password" name="pwd" value="" placeholder="请输入密码"><br>
		    	<div id="div_idB"></div>
		  		<input class="input2" type="button" value="登录" onclick="toSubmit()">
		  		<input class="input2" type="reset" value="重置">
  			</form>
  		</div>
  	</div>
  	<script type="text/javascript">
  		function toSubmit(){
  			 $('#loginFrom').form('submit',{ 
  				success: function(data){
		       		data = eval('(' + data + ')'); 
		       		if(data.status==1){
		       			window.location="jsp/main.jsp";
		       		}else if(data.status==2){
		       			alert("不存在该账号");
		      		}else if(data.status==3){
		      			alert("您输入的密码错误");
		      		}else if(data.status==0){
		      			alert("登录失败");
		      		}
		    	 }
			});
  		}
  	</script>
  </body>
</html>
