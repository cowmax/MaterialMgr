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
<link rel="stylesheet" href="js/easyui/themes/default/easyui.css" type="text/css"></link>
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

</script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<body>
	<div align="center">
		<form action="suppliermergerSupplier" id="supplierSavefrorm" method="post">
			<div>
				<table class="form-table">
					<tr>
						<td>供应商</td>
						<td>
							<select class="easyui-combobox" style="width:148px;height:26px;" 
								 prompt="${editSupplier.suin.suna}"  panelHeight="100"; editable="false"; disabled="disabled">
							</select>
						</td>
					</tr>
					
					<tr>
						<td>供应方面料名称</td>
						<td>
							<input name="editSupplier.mtsName" style="height:26px;" value="${editSupplier.mtsName}" class="easyui-textbox" />
						</td>
					</tr>
					
					<tr>
						<td>供应方面料颜色</td>
						<td>
							<input name="editSupplier.mtsColorCount" style="height:26px;" value="${editSupplier.mtsColorCount}" class="easyui-textbox" />
						</td>
					</tr>
					
					<tr>
						<td>供应方面料编码</td>
						<td>
							<input name="editSupplier.mtsCode" style="height:26px;" value="${editSupplier.mtsCode}" class="easyui-textbox" />
						</td>
					</tr>
					<tr>
						<td ></td>
						<td>
							<input type="button" id="mergerSupplier" value='保存' class="easyui-linkbutton" onclick="mergerSupplier('${editSupplier.BMaterial.mtlId}')" />&nbsp;&nbsp;&nbsp;
							<input type="button" value="取消" onclick="closeWin('#mergerSupplier')" class="easyui-linkbutton">
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
</body>
</html>