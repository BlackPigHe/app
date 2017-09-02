<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<table id="userManager_dg" class="easyui-datagrid" title="员工列表" fit=true
			data-options="
				singleSelect:true,
				collapsible:true,
				url:'',
				pagination:true, 
				pageSize:5,
				pageList:[5,10,15,20],
				method:'post',
				toolbar:userbar">
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
<div id="userbar">
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" onclick="toAddUser()">添加员工</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" onclick="toUpdateDept()">修改员工</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" onclick="toDelDept()">删除员工</a>
</div>
<div id="dept_dlg" class="easyui-dialog" 
    data-options="closed:true,
               onResize:function(){
				$(this).dialog('center');
			  }"
			style="padding:10px;"></div>