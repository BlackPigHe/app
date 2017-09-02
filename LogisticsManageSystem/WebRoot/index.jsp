<%@ page language="java" contentType="textml; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title></title>
<meta http-equiv="Content-Type" content="textml;charset=utf-8" />
<script type="text/javascript" charset="utf-8"
	src="${ctx}/baidujs/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${ctx}/baidujs/ueditor.all.min.js">
	
</script>

<script type="text/javascript" charset="utf-8"
	src="${ctx}/baidujs/lang/zh-cn/zh-cn.js"></script>

<style type="text/css">
div {
	width: 100%;
}
</style>
</head>
<link rel="stylesheet" type="text/css"
	href="${ctx}/news-style/css/reset.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/news-style/css/addtext.css" />
<body>

<form id="formNews">
	<input type="hidden" name="NId" value="${newsInfo.NID}" id="NId">
	<div class="add">
		<span>类型:<select id="navlan" name="navlan">
				<option>请选择</option>
				<option value="N001"
					<c:if test="${newsInfo.NEWS_TYPE eq 'N001'}"> selected='selected' </c:if>>政策落地</option>
				<option value="N002"
					<c:if test="${newsInfo.NEWS_TYPE eq 'N002'}"> selected='selected' </c:if>>地市中支动态</option>
				<option value="N003"
					<c:if test="${newsInfo.NEWS_TYPE eq 'N003'}"> selected='selected' </c:if>>金融机构动态</option>
				<option value="N004"
					<c:if test="${newsInfo.NEWS_TYPE eq 'N004'}"> selected='selected' </c:if>>自治区动态</option>
				<option value="N005"
					<c:if test="${newsInfo.NEWS_TYPE eq 'N005'}"> selected='selected' </c:if>>总行及其他省份动态</option>
				<option value="N006"
					<c:if test="${newsInfo.NEWS_TYPE eq 'N006'}"> selected='selected' </c:if>>公告信息</option>
				<option value="N007"
					<c:if test="${newsInfo.NEWS_TYPE eq 'N007'}"> selected='selected' </c:if>>首页新闻</option>
		<lect> </span> <span>标题:<input type="" name="title" id="title"
			value="${newsInfo.TITLE }" />
		</span><br /> <span class="hid" id="hid">图片: <input type="file"
			name="file" id="file" value="" /> <img id="imgShow" width="100px"
			height="100px" /> </span>
	</div>
	<div id="result" name="result"></div>
	<div class="add-text">
		<span class="pull-left"> 内容: </span>
		<div class="pull-left add-sty">

			<script id="editor" type="text/plain"
				style="width:900px;height:200px;">${newsInfo.CONTENT}</script>
		</div>
	</div>

	<div class="clearfix"></div>
	<div class="up">
		是否发布: <label>是 <input type="radio" name="isShow" value="1">
		</label> <label>否<input type="radio" name="isShow" value="0"
			<c:if test="${empty newsInfo.IS_SHOW || newsInfo.IS_SHOW eq '0'}"> checked="checked"</c:if>>
		</label>
	</div>
	<div class="btns">
		<input type="button" value="提交" onclick="Add()" /> <input
			type="button" value="关闭" onclick="closeNews()" />
	</div>
	</form>
</body>
<ml>
<script src="${ctx}/news-style/js/jquery-1.11.1.min.js"
	type="text/javascript" charset="utf-8"></script>
<script src="${ctx}/news-style/js/uploadPreview.js"
	type="text/javascript" charset="utf-8"></script>
<script src="${ctx}/news-style/js/addtext.js" type="text/javascript"
	charset="utf-8"></script>
<script type="text/javascript">


	//实例化编辑器
	//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	var ue = UE.getEditor('editor');

		
		
		function Add() {

			newType = $("#navlan").val();
			alert("1"+newType);
			if (newType == "请选择") {
				alert("未选择新闻类型！");
			} else {
		    		$.ajax({
				        url:'${ctx}/newsManageveNewsMessages.do',
				        type : 'post',
						contentType : 'application/x-www-form-urlencoded;charset=utf-8',
				        data:$('#formNews').serialize(),
				        success: function(data) {
				           if (this.data == 1) {
								alert("提交成功！");
							} else {
								alert("提交失败！");
							}
							window.location
									.assign('${ctx}/newsManage/newsManageSetting.do');
						
				        }
		    		});

					
			
				
									
			}



		}

	function closeNews() {
		window.location.assign('${ctx}/newsManage/newsManageSetting.do');

	}
