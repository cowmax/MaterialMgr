<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	width: 7em;
}
</style>
<script type="text/javascript">
function clearForm(){
	$("#resetForm").trigger("click");//触发reset按钮 }
}
window.onload=clearForm;//不要括号

</script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<body>
	<div align="center">
		<form id="supplierSaveform" action="" method="post">
			<table class="form-table">
				<tr>
					<td>供应商</td>
					<td>
						<select class="easyui-combobox" id="supplier" style="width:148px;height:26px" prompt="请选择供应商" panelHeight="100"; editable="false">
							<option value=""></option>
							<c:forEach items="${allSuinList}" var="suin">
								<option value="${suin.suid}">${suin.suna}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
					
				<tr style="margin-bottom: 50px;">
					<td>供方面料名称</td>
					<td>
						<input id="mtsName" value="" class="easyui-textbox" style="height: 26px;"/>
					</td>
				</tr>
					
				<tr style="margin-bottom: 50px;">
					<td>供方颜色数量</td>
					<td>
						<input id="mtsColorCount" value="" class="easyui-textbox" style="height: 26px;" data-options=" validType:['#validateColCount'],missingMessage:'请输入颜色数量'" />
					</td>
				</tr>
					
				<tr style="margin-bottom: 50px;">
					<td>供方面料编码</td>
					<td>
						<input id="mtsCode" value="" class="easyui-textbox" style="height: 26px;" />
					</td>
				</tr>
				<tr>
					<td ></td>
					<td>
						<input type="button" id="saveSupplier" value='保存' class="easyui-linkbutton" onclick="addSupplier()" />&nbsp;&nbsp;&nbsp;
						<input type="button" value="取消" onclick="closeWin('#addSupplier')" class="easyui-linkbutton">
						<input type="reset" id="resetForm" name="reset" style="display: none;" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>