<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<style type="text/css">
	#pwdChange_div{
		padding:0px;
		margin:30px auto;
		width:500px;
		height:430px;
		text-align: center;
		line-height: 35px;
		font-size:20px;
	}
	#pwdChange_div input{
		width:200px;
		height:30px;
		border-radius:5px; 
	}
	#pwdChange_div #button{
		width:80px;
		height:30px;
		border-radius:5px;
		background-color:#F3F3F3 ; 
	}
</style>
<div id="pwdChange_div">
	<form id="pwdChangeForm" action="User/resetpwd/" method="post">
		<label>用&nbsp;户&nbsp;名：</label>
		<input type="text" name="uname" readonly="readonly" value="${user.uname}"><br>
		<label>&nbsp;账&nbsp;&nbsp;号：</label>
		<input type="text" name="account" readonly="readonly" value="${user.account}"><br>
		<label>旧&nbsp;密&nbsp;码：</label>
		<input type="password" name="oldPwd" value=""><br>
		<label>新&nbsp;密&nbsp;码：</label>
		<input id="newPwd1" type="password" name="newPwd1" value=""><br>
		<label>确认新密码：</label>
		<input id="newPwd2" type="password" name="newPwd2" value=""><br><br>
		<input id="button" type="button" value="提交" onclick="toChangePwd()"> 
	</form>
</div>
<script type="text/javascript">
	function toChangePwd(){
		var pwd1=document.getElementById("newPwd1").value;
		var pwd2=document.getElementById("newPwd2").value;
		if(pwd1==pwd2){
			$('#pwdChangeForm').form('submit', {    
	  			success: function(data){
			       	data = eval('(' + data + ')'); 
		       		if(data.status==0){
		       			alert("密码修改成功");
		       		}else if(data.status==1){
		       			alert("当前密码不正确");
		      		}else if(data.status==2){
		      			alert("新密码和当前密码不能相同");
		      		}else if(data.status==3){
		      			alert("密码修改失败");
		      		}
			        
			     }
			});
		}else{
			alert("新密码与确认新密码不一致");
		}	
	}
</script>
