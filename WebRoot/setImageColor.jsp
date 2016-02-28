<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="content-type" content="text/html;charset=utf-8">

<link rel="stylesheet" href="js/easyui/demo/demo.css" type="text/css"></link>
<link rel="stylesheet" href="js/easyui/themes/icon.css" type="text/css"></link>
<link rel="stylesheet" href="js/easyui/themes/default/easyui.css"
	type="text/css"></link>
<script type="text/javascript" src="js/easyui/jquery-1.6.min.js"></script>
<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="script/common.js"></script>

<link href="css/style.css" rel="stylesheet" type="text/css">

<style type="text/css">
* {
	margin: 0px;
	padding: 0px;
}
.form-table tr td:first-child {
	width: 6em;
}
.form-table tr .textbox{
	height: 24px;
}
.form-table tr{
	height: 30px;
}
</style>
<script type="text/javascript">
	$(function(){
		var materialCode = creatMaterialCode();
		$("#materialCode").textbox('setValue',materialCode);//赋值;
	})
</script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<body>
	<div class="easyui-panel" align="center" style="width: 400px;height: 300px;" id="content">
		<div id="content_one" style="width: 180px;height:160px; margin:20px 0px 0px 20px; float: left;">
			<img alt="" src="">		
		</div>
		<div id="content_two" style="width:180px;height:40px; float: right;margin-top: 150px;">
			<input class="easyui-textbox" type="text" id="qsupplierName" prompt="输入颜色名称" value="" data-options="height:26,width:120">
		</div>
		<div style="width: 100%; float: left; height: 40px; ">
			<a onclick="" class="easyui-linkbutton" style="margin-left: 10px;padding: 0px 3px;">保存</a>
			<a onclick="" class="easyui-linkbutton" style="margin-left: 10px;padding: 0px 3px;">返回</a>
		</div>
	</div>
</body>
</html>