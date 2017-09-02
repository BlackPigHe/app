<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<table id="dept_dg" class="easyui-datagrid" title="部门列表" fit=true
			data-options="
				singleSelect:true,
				collapsible:true,
				url:'Dept/deptList',
				pagination:true, 
				pageSize:5,
				pageList:[5,10,15,20],
				method:'post',
				toolbar:deptbar">
	<thead>
		<tr>
			<th data-options="field:'deptid',width:100,align:'center'">id</th>
			<th data-options="field:'deptno',width:100,align:'center'">部门编号</th>
			<th data-options="field:'deptname',width:100,align:'center'">部门名称</th>
			<th data-options="field:'empcount',width:100,align:'center'">部门人数</th>	
		</tr>
	</thead>
</table>
<div id="deptbar" style="padding:0px;height:26px;line-height:26px ">
	<div style="padding:0px;">
		<form id="searchDept">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" onclick="toAddDept()">添加部门</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" onclick="toUpdateDept()">修改部门</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" onclick="toDelDept()">删除部门</a>
			<input type="text" name="deptname" value="">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="tofindDept()">按部门名称查询</a>
		</form>
	</div>
</div>
<div id="dept_dlg" class="easyui-dialog" 
    data-options="closed:true,
               onResize:function(){
				$(this).dialog('center');
			  }"
			style="padding:10px;"></div>
<script type="text/javascript">
	function toAddDept(){
		var tit="添加部门";
		var url ="jsp/company/editDept.jsp";
		showWinOper(tit,url);
	}
	function tofindDept(){
		var fields = $("#searchDept").serializeArray();
		var queryParam={};
		$.each(fields,function(i,el){
	         if(el.value){
	             queryParam[el.name] = el.value;
	         }
        }); 
		$('#dept_dg').datagrid('load',queryParam); 
	}
	function toUpdateDept(){
		var row=$('#dept_dg').datagrid('getSelected');
		if(row!=null){
			var tit="修改部门";
			var url ="?deptid="+row.deptid;
			showWinOper(tit,url);
		}else{
			alert("请选择要修改的部门");
		}
	}
	function toDelDept(){
		var row=$('#dept_dg').datagrid('getSelected');
		if(row!=null){
			var url ="";
			var params = {"deptid":row.deptid};
        	var r=confirm("确定删除？？？？？");
        	if (r==true){
		    	$.post(url,params,function(data){
		    		data = eval('(' + data + ')'); 
		      		if (data.stuats==1){ 
		       	   	 	$.messager.show({
							title:'操作提示',
							msg:'删除部门成功',
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
							msg:'删除部门失败',
							showType:'fade',
							timeout:1000,
							style:{
								right:'',
								bottom:''
							}
			         	 });
		         	 }
		    	});
		    	$('#dept_dg').datagrid('reload');   
			}
		}else{
			alert("请选择要删除的公司");
		}
	}
	function showWinOper(tit,url){
       $('#dept_dlg').dialog({    
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