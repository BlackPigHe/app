<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<table id="user_dg" class="easyui-datagrid" title="员工列表" fit=true
			data-options="
				singleSelect:true,
				collapsible:true,
				url:'',
				pagination:true, 
				pageSize:5,
				pageList:[5,10,15,20],
				method:'post',
				toolbar:userManagerbar">
	<thead>
		<tr>
			<th data-options="field:'userid',width:100,align:'center'">id</th>
			<th data-options="field:'username',width:100,align:'center'">用户名</th>
			<th data-options="field:'account',width:100,align:'center'">账号</th>
			<th data-options="field:'sex',width:100,align:'center'">性别</th>
			<th data-options="field:'age',width:100,align:'center'">年龄</th>	
			<th data-options="field:'tel',width:100,align:'center'">联系电话</th>
			<th data-options="field:'deptname',width:100,align:'center'">所属部门</th>		
		</tr>
	</thead>
</table>
<div id="userManagerbar" style="padding:0px;height:26px;line-height:26px">
	<div style="padding:0px;">
		<form id="searchUser">
			<input type="text" name="username" value="">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="tofindUser()">按公司编号查询</a>
		</form>
	</div>
</div>
<div id="dept_dlg" class="easyui-dialog" 
    data-options="closed:true,
               onResize:function(){
				$(this).dialog('center');
			  }"
			style="padding:10px;"></div>