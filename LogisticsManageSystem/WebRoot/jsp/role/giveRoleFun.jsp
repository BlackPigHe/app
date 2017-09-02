<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="saveRolePower()">保存更改</a>
<ul id="funtree" class="ztree"></ul>
<script type="text/javascript">
	var setting = {
		check: {
            enable: true,
			chkStyle: "checkbox",
			chkboxType: { "Y": "p", "N": "s" }
        },
		data: {
		   key: {
		   		name: "funname"
		   },
		   simpleData: {
				enable: true,
				idKey: "funid",
				pIdKey: "funpid",
				rootPId: -1
		    }
		}
	}; 
	 function createFuntree(){
	 	var zNodes;
	 	$.ajax({
		   url: "Function/getAllFunction",
		   type: 'POST',
		   dataType:"json",
		   success:function(data){
		  		zNodes =data;
		   		$.fn.zTree.init($("#funtree"), setting, zNodes);
		   		var treeObj = $.fn.zTree.getZTreeObj("funtree");
                treeObj.expandAll(true);
		    }
		});
	  }
	  $(document).ready(function(){
	  	   createFuntree();
	  });
	  function saveRolePower(){
	  	   var row=$('role_dg').datagrid('getSelected');
	  	   if(row!=null){
	  	   		var treeFun = $.fn.zTree.getZTreeObj("funtree");
		  		var nodesFun = treeFun.getCheckedNodes(true);
		  		for(var i=0;i<nodesFun.length;i++){
		  			funid[i]=nodesFun[i].funid;	
		  		}
		  		var fun=funid.toString();
		  		var url="";
				var params ={"roleid":row.roleid,"fun":fun};
				$.post(url,params,function(data){
					data = eval('(' + data + ')');
			        if (data.status==1){ 
		       	   	   $.messager.show({
							title:'操作提示',
							msg:'权限分配成功',
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
							msg:'权限分配失败',
							showType:'fade',
							timeout:1000,
							style:{
								right:'',
								bottom:''
							}
			           });
			        }
				}); 
	  	   }else{
	  	   		alert("请选择分配权限的角色");
	  	   }
	  }
</script>
