<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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

<title>My JSP 'da.jsp' starting page</title>

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
<script type="text/javascript" src="js/easyui/themes/easyui-lang-zh_CN.js"></script>
<link href="css/style.css" rel="stylesheet" type="text/css">
<style type="text/css">
</style>
<script type="text/javascript">

</script>
</head>

<body>
	<div align="center" style="padding: 20px;">
		<div id="options" class="toolbar" style="height: 30px;">
			<div style="float: left; margin-bottom: 10px;">
				<select class="easyui-combobox" id="pmtlType" style="width:108px;height:26px;"  prompt="选择父类型" panelHeight="100"; editable="false";>
					<option value=""></option>
					<c:forEach items="${parentMtlTypeList}" var="ptype">
						<option value="${ptype.mtlType}" >${ptype.mtlTypeName}</option>
					</c:forEach>
				</select>
				<span style="padding-left: 5px;"></span>
				<input class="easyui-textbox" type="text" id="cTypeName" prompt="新类型名称" value="" data-options="height:26,width:200">
			</div>
			<div style=" float: left;">
				<a onclick="addType()" class="easyui-linkbutton"
					data-options="iconCls:'icon-add'" style="margin-left: 10px;">新增</a>
			</div>
			<div style="width: 100%; height: 10px;float: left;"></div>
		</div>
		<div style="width:100%; float: left;" id = "suppliersList">
			<table class="easyui-datagrid" style="height:300px;" singleSelect="true">
				<thead>
					<tr>
						<th data-options="field:'code'" width="">序号</th>
						<th data-options="field:'pType'" width="100">父类型</th>
						<th data-options="field:'typeName'" width="100">类型名称</th>
						<th data-options="field:'操作'">操作</th>
					</tr>
				</thead>

				<c:forEach items="${allTypeList}" var="etype" varStatus="i">
					<tr>
						<td>${i.index+1}</td>

						<td><c:out value="${etype.mtlTypeName }" /></td>

						<td><c:out value="${etype.ptypeName }" /></td>

						<td><a onclick="javascript:return sureDelType('${etype.mtlTypeName}','${etype.mtlType }')">删除</a></td>
					</tr>
				</c:forEach>
			</table>
			<div style="margin-top: 10px;">
				<a onclick="closeTypeListWin()" class="easyui-linkbutton" style="margin-left: 10px;padding: 0px 3px;">确定</a>
			</div>
		</div>
	</div>

</body>
</html>
