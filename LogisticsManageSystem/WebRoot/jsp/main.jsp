<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">  
    <title>My JSP 'main.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/easyui/themes/gray/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css">
		<link rel="stylesheet" href="js/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="js/tree/jquery.ztree.all-3.5.min.js"></script>
	<style type="text/css">
		.cs_north_box{   
			background:-moz-linear-gradient(#fff,#333);
	        background:-webkit-gradient(linear,center top,center bottom,from(#fff),to(#333));
	        background:-webkit-linear-gradient(#fff,#333);
	        background:-o-linear-gradient(#fff,#333);
	        background:-ms-linear-gradient(#fff,#333);
	        background:linear-gradient(#fff,#333); color:white; 
	        text-align: center;}
		.cs_south_box{  
			background:-moz-linear-gradient(#fff,#333);
	        background:-webkit-gradient(linear,center top,center bottom,from(#fff),to(#333));
	        background:-webkit-linear-gradient(#fff,#333);
	        background:-o-linear-gradient(#fff,#333);
	        background:-ms-linear-gradient(#fff,#333);
	        background:linear-gradient(#fff,#333);
	        color:white;
	        height:35px;
	        line-height:25px;
	        text-align: right;
	        padding:0px; 
	   }
	   #funList ul{
	   		padding:0px;
			margin: 0px;
	   }
	  	#funList li{
	  		border-bottom:1px solid #D3D3D3;
	   		padding:0px;
			margin: 0px;
			list-style:none;
			height:35px;
			line-height: 35px;
			text-align: center;
			background-color:#F3F3F3;
		}
   		#funList li:hover{ 
   			background-color:#0092DC;
   		}
   		#exit{
   			color:white;
   		}
   		a{
   			color:#575765;
   			text-decoration: none;
   		}
	</style>
  </head>
  <body>
  	 <div class="easyui-layout" data-options="fit:true">
  	 	<div class="cs_north_box" data-options="region:'north',split:true" style="height:80px">
    		<h1>物流管理平台</h1>
    		<div  style="font-size: 10px;letter-spacing: 0px; text-align: right;">
    			欢迎 &nbsp;${user.uname}&nbsp;登录&nbsp;
    			<a id="exit" href="jsp/exit.jsp">[安全退出]</a><br>
    		</div>
    	</div>
    	<div class="cs_south_box" data-options="region:'south',split:true">
			<small>当前时间：</small><small id="lblTime"></small>
		</div>
		<div data-options="region:'west',split:true" title="菜单" style="width:200px;" id="div_region">
			<div id="funList" class="easyui-accordion" data-options="fit:true">
  	 			
  	 		</div>
		</div>
		<div data-options="region:'center',iconCls:'icon-no'">
			<div id="id_tabs" class="easyui-tabs" data-options="fit:true"></div>
		</div>
  	</div>
	<script type="text/javascript">
		var leftList ={
			name:'funname',
			idName:'funid',
			pidName:'funpid',
			levelName:'funlevel',
			href:'href'
		};
		$(function() {
			$.ajax({
				type : "get",
				url : "Menu/menu/",
				data: "userid=${sessionScope.user.userid}",
				dataType : "JSON",
				success : function(msg) {
					$(msg).each(function(i) {
						if(null!=msg[i]){
							var level=eval("msg[i]."+leftList.levelName)-0;
							var id=eval("msg[i]."+leftList.idName)-0;
							var pid=eval("msg[i]."+leftList.pidName)-0;
							var name=eval("msg[i]."+leftList.name);
							var url=eval("msg[i]."+leftList.href);
							if (level == 1) {
								 $("#funList").accordion('add', {
									title : msg[i].funname,
									content : '<ul class="list" value="'+id+'" id="fun_ul_'+id+'"></ul>',
									selected : false
								}); 
							} else if(msg[i].funlevel == 2){
								$("#fun_ul_"+pid).append("<li value='"+id+"'><a href='"+url+"'>"+name+"</a></li>");
							}
						}
					});
				}
			});
		});
		var timeID;
	  	function showTime(){
		  	var myDate=new Date();
		  	var olbl=document.getElementById("lblTime");
		  	olbl.innerHTML=myDate.toLocaleString();
		}
		$(function (){
			timeID=window.setInterval("showTime()",1000);
		});
		$("#div_region").on("click","a",function(e){	
    		e.preventDefault();
    		var st=$(this).text();
    		var hrf=$(this).attr("href");
    		addPanel(st,hrf);
    	});
    	function addPanel(st,hrf){
    		 var curTab = $('#id_tabs').tabs('getTab',st);
    		 if(curTab){
    			 var index = $('#id_tabs').tabs('getTabIndex',curTab);
              	 $('#id_tabs').tabs('select',index);
              	  curTab.panel('refresh',hrf);
    		 }else{
    		 	 $('#id_tabs').tabs('add',{
					 title: st,
					 href: hrf,
					 closable: true
				 });
    		 }
		}
		$("#exit").on("click",function(e){	
    		e.preventDefault();
    		var hrf=$(this).attr("href");
    		var r=confirm("确认退出");
			if (r==true){
				 window.location=hrf;
			}    		
	    });
	</script>
  </body>
</html>
