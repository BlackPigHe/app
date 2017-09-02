<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<style type="text/css">
	#editCompanydiv{
		width:360px;
		margin-top: 20px;
		text-align: center;
		line-height: 32px;
	}
	#editCompany_id{
		width:171px;
	}
</style>
<div id="editCompanydiv">
	<input id="comid" type="hidden" name="comid" value="${requestScope.company.comid}">
	<form id="editCompanyForm" method="post">
		<label>&nbsp;公司编号：</label>
		<input type="text" name="companyno" value="${requestScope.company.companyno}"><br>
		<label>&nbsp;公司名称：</label>
		<input type="text" name="comname" value="${requestScope.company.comname}"><br>
		<label>&nbsp;公司简称：</label>
		<input type="text" name="shotname" value="${requestScope.company.shotname}"><br>
		<label>&nbsp;法人名称：</label>
		<input type="text" name="bigboss" value="${requestScope.company.bigboss}"><br>
		<label>&nbsp;联系电话：</label>
		<input type="text" name="tel" value="${requestScope.company.tel}"><br>
		<label>公司管理员：</label>
		<input type="text" name="commanager" value="${requestScope.company.commanager}"><br>
		<label>&nbsp;详细地址：</label>
		<input type="text" name="addr" value="${requestScope.company.addr}"><br>
		<label>&nbsp;成立日期：</label>
		<input id="editCompany_id" type="date" name="date" value="${requestScope.company.date}"><br>
		<input type="button" value="提交" onclick="doEditCompany()">
	</form>
</div>

<script type="text/javascript">
	function doEditCompany(){
		var comid=$('#comid').val();
		if(comid==""){
			comid=0;
		}
		
		$('#editCompanyForm').form('submit', {  
			url: 'Company/saveCompany?comid='+comid,
		    success: function(data){
		        data = eval('(' + data + ')'); 
		        if(data.status==1){
	        		 $.messager.show({
						title:'操作提示',
						msg:'添加公司成功',
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
						msg:'添加公司失败',
						showType:'fade',
						timeout:1000,
						style:{
							right:'',
							bottom:''
						}
					});
		        }
		       	$('#company_dlg').dialog('close'); 
		        $('#comManag_dg').datagrid('reload');
		     }
		}); 
	}
</script>