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
	width: 8em;
}
</style>
<script type="text/javascript">
function saveTestReport(){
	
}
</script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<body>
	<div align="center">
		<form action="" id="testReportSaveform" method="post">
			<div>
				<table class="form-table">
					<tr>
						<td>测试报告编码</td>
						<td>
							<input class="easyui-textbox" id="relateProducts" value="" style="width: 108px; height: 26px;" disabled="disabled" /> 
							<input class="easyui-linkbutton" type="button" id="query" 
								style="border-color: #95B8E7; color: #95B8E7" onclick="showTypeList()" value="…" disabled="disabled">
						</td>
					</tr>
					
					<tr style="margin-bottom: 50px;">
						<td>轮廓风险</td>
						<td>
							<input name="testReport.riskOfLineament" value="" style="height: 26px;" class="easyui-textbox" />
						</td>
					</tr>
					
					<tr style="margin-bottom: 50px;">
						<td>类目风险</td>
						<td>
							<input name="testReport.riskForClass" value="" style=" height: 26px;" class="easyui-textbox" />
						</td>
					</tr>
					
					<tr style="margin-bottom: 50px;">
						<td>扫描件</td>
						<td >
							<a href="#" style="padding: 0px 20px;color: blue;">上传</a>
							<a href="#" style="color:blue">下载</a>
						</td>
					</tr>
					<tr>
						<td colspan="2" style="padding-left: 100px;">
							<input type="button" value='保存' class="easyui-linkbutton" onclick="addTestReport()" />&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" value="取消" onclick="closeWin('#addTestReport')" class="easyui-linkbutton">
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
</body>
</html>