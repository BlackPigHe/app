<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<table id="com_dg" class="easyui-datagrid" title="公司列表" fit=true
			data-options="
				singleSelect:true,
				collapsible:true,
				url:'Company/compList',
				pagination:true, 
				pageSize:5,
				pageList:[5,10,15,20],
				method:'post',
				toolbar:companybar">
	<thead>
		<tr>
			<th data-options="field:'comid',width:100,align:'center'">id</th>
			<th data-options="field:'companyno',width:100,align:'center'">公司编号</th>
			<th data-options="field:'comname',width:100,align:'center'">公司全称</th>
			<th data-options="field:'shotname',width:100,align:'center'">公司简称</th>
			<th data-options="field:'bigboss',width:100,align:'center'">法人名称</th>
			<th data-options="field:'tel',width:100,align:'center'">联系电话</th>
			<th data-options="field:'addr',width:100,align:'center'">详细地址</th>
			<th data-options="field:'commanager',width:100,align:'center'">公司管理员</th>
			<th data-options="field:'date',width:100,align:'center'">成立日期</th>
		</tr>
	</thead>
</table>
<div id="companybar" style="padding:0px;height:26px;line-height:26px ">
	<div style="padding:0px;">
		<form id="searchCompany">
			<input type="text" name="companyno" value="">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="tofindCompany()">按公司编号查询</a>
		</form>
	</div>
</div>
<script type="text/javascript">
	function tofindCompany(){
		var fields = $("#searchCompany").serializeArray();
		var queryParam={};
		$.each(fields,function(i,el){
	         if(el.value){
	             queryParam[el.name] = el.value;
	         }
        }); 
		$('#com_dg').datagrid('load',queryParam); 
	}
</script>
