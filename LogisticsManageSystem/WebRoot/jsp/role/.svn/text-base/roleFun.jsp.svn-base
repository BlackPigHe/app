<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'west',split:true" style="width:400px;">
		<table id="role_dg" class="easyui-datagrid" title="角色列表" 
			data-options="
				fit:true,
				singleSelect:true,
				collapsible:true,
				url:'Role/getRole',
				pagination:true, 
				pageSize:5,
				pageList:[5,10,15,20],
				method:'post',
				toolbar:rolebar">
			<thead>
				<tr>
					<th data-options="field:'roleid',width:100,align:'center'">id</th>
					<th data-options="field:'rolename',width:100,align:'center'">角色名称</th>
				</tr>
			</thead>
		</table>
	</div>
	<div data-options="region:'center',title:'分配权限',split:true,href:'jsp/role/giveRoleFun.jsp'" ></div>
</div>
<div id="Role_dlg" class="easyui-dialog" 
    data-options="closed:true,
               onResize:function(){
				$(this).dialog('center');
			  }"
			style="padding:10px;"></div>
<div id="rolebar">
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" onclick="toAddRole()">添加角色</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" onclick="toUpdateRole()">修改角色</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" onclick="toDelRole()">删除角色</a>
</div>
<script type="text/javascript">
	function toAddRole(){
		var tit="添加角色";
		var url ="";
		showWinOper(tit,url);
	}
	function toUpdateRole(){
		var row=$('role_dg').datagrid('getSelected');
		if(row!=null){
			var tit="修改角色";
			var url ="?roleid="+row.roleid;
			showWinOper(tit,url);
		}else{
			alert("请选择要修改的角色");
		}
	}
	function toDelRole(){
		var row=$('#role_dg').datagrid('getSelected');
		if(row!=null){
			var url ="";
			var params = {"roleid":row.roleid};
        	var r=confirm("确定删除？？？？？");
        	if (r==true){
		    	$.post(url,params,function(data){
		    		data = eval('(' + data + ')'); 
		      		if (data.stuats==1){ 
		       	   	 	$.messager.show({
							title:'操作提示',
							msg:'删除角色成功',
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
							msg:'删除角色失败',
							showType:'fade',
							timeout:1000,
							style:{
								right:'',
								bottom:''
							}
			         	 });
		         	 }
		    	});
		    	$('#role_dg').datagrid('reload');   
			}
		}else{
			alert("请选择要删除的公司");
		}
	}
	$('#role_dg').datagrid({
		onDblClickCell:function(){
			var row=$('#role_dg').datagrid('getSelected');
			var url ="";
			var params ={"roleid": row.roleid};
			$.post(url,params,function(data){
				if(data!=0){
					var treeObj = $.fn.zTree.getZTreeObj("funtree");
					var nodes = treeObj.getNodes();
					for (var i=0, l=nodes.length; i < l; i++) {
						var roleid=nodes[i].roleid;
						if(roleid==data){
							treeObj.checkNode(nodes[i], true, true);
						}
					}
				}
			});	
		}
	});
	function showWinOper(tit,url){
       $('#Role_dlg').dialog({    
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
