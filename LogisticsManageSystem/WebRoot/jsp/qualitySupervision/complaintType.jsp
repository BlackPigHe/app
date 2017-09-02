<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<table id="com_dg" class="easyui-datagrid" title="公司列表" fit=true
			data-options="
				singleSelect:true,
				collapsible:true,
				url:'Complain/complist',
				pagination:true, 
				pageSize:5,
				pageList:[5,10,15,20],
				method:'post',
				toolbar:complaintTypeBar">
	<thead>
		<tr>
			<th data-options="field:'complainid',width:150,align:'center'">id</th>
			<th data-options="field:'comptype',width:150,align:'center'">投诉类型</th>
		</tr>
	</thead>
</table>
<div id="complaintTypeBar">
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" onclick="toAddComplaint()">添加投诉类型</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" onclick="toUpdateComplaint()">修改投诉类型</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" onclick="toDelComplaint()">修改投诉类型</a>
</div>
<script type="text/javascript">
	
</script>