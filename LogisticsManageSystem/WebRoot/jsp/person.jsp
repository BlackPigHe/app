<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<style type="text/css">
	#div_persion{
		width:500px;
		margin:40px auto;
		text-align: center;
	}
	#div_persion td{
		width:150px;
		height:45px;
		text-align: center;
	}
</style>
<div id="div_persion">
	<table cellpadding="0" cellspacing="0" border="1px solid black">
		<tr>
			<td>姓名</td><td>${user.uname}</td><td>账号</td><td>${user.account}</td>
		</tr>
		<tr>
			<td>性别</td><td>${user.sex}</td><td>年龄</td><td>${user.age}</td>
		</tr>
		<tr>
			<td>公司</td><td>${user.company.getComname()}</td><td>部门</td><td>${user.dept.getDeptname()}</td>
		</tr>
		<tr>
			<td colspan="2">联系方式</td><td colspan="2">${user.tel}</td>
		</tr>
	</table>
</div>