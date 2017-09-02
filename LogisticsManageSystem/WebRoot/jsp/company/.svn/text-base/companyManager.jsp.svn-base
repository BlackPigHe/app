<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<table id="comManag_dg" class="easyui-datagrid" title="公司列表" fit=true
			data-options="
				singleSelect:true,
				collapsible:true,
				url:'Company/compList',
				pagination:true, 
				pageSize:5,
				pageList:[5,10,15,20],
				method:'post',
				toolbar:comManagbar">
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
<div id="comManagbar">
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" onclick="toAddCompany()">添加公司</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" onclick="toUpdateCompany()">修改公司</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" onclick="toDelCompany()">删除公司</a>
</div>
<div id="company_dlg" class="easyui-dialog" 
    data-options="closed:true,
               onResize:function(){
				$(this).dialog('center');
			  }"
			style="padding:10px;"></div>
<script type="text/javascript">
	function toAddCompany(){
		var tit="添加公司";
		var url ="jsp/company/editCompany.jsp";
		showWinOper(tit,url);
	}
	function toUpdateCompany(){
		var row=$('#comManag_dg').datagrid('getSelected');
		if(row!=null){
			var tit="修改公司";
			var url ="Company/selectOneCompany?comid="+row.comid;
			showWinOper(tit,url);
		}else{
			alert("请选择要修改的公司");
		}
	}
	function toDelCompany(){
		var row=$('#comManag_dg').datagrid('getSelected');
		if(row!=null){
			var url ="Company/deleteCompany";
			var params = {"companyno":row.companyno};
        	var r=confirm("确定删除？？？？？");
        	if (r==true){
		    	$.post(url,params,function(data){
		    		data = eval('(' + data + ')'); 
		      		if (data.stuats==1){ 
		       	   	 	$.messager.show({
							title:'操作提示',
							msg:'删除公司成功',
							showType:'fade',
							timeout:1000,
							style:{
								right:'',
								bottom:''
							}
			         	 });
		         	 }else{
		         	 	$.messager.show({
							title:'操作提示',
							msg:'删除公司失败',
							showType:'fade',
							timeout:1000,
							style:{
								right:'',
								bottom:''
							}
			         	 });
		         	 }
		    	});
		    	$('#comManag_dg').datagrid('reload');   
			}
		}else{
			alert("请选择要删除的公司");
		}
	}
	function showWinOper(tit,url){
       $('#company_dlg').dialog({    
		    title: tit,    
		    width: 400,    
		    height: 400,
		    closed: false,    
		    cache: false,    
		    href: url, 
		    modal: true   
		}); 
     }
</script>