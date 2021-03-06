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
		<div style="width:100%; float: left;" id = "suppliersList">
			<table class="easyui-datagrid" style="width=100%;" singleSelect="true">
				<thead>
					<tr>
						<th data-options="field:'code'" width="">序号</th>
						<th data-options="field:'productCode'" width="100">产品编码</th>
						<th data-options="field:'memo'" width="100">说明</th>
						<th data-options="field:'relateStatus'" width="100">状态</th>
					</tr>
				</thead>

				<c:forEach items="${bmRelateProducts}" var="relpdu" varStatus="i">
					<tr>
						<td>${i.index+1}</td>
						<td><c:out value="${relpdu.productCode }" /></td>
						<td><c:out value="${relpdu.memo }" /></td>
						<td><c:out value="${relpdu.relateStatus }" /></td>
					</tr>
				</c:forEach>
			</table>
			<div style="margin-top: 10px;">
				<a onclick="closeRelpdusWin()" class="easyui-linkbutton" style="margin-left: 10px;padding: 0px 3px;">确定</a>
			</div>
		</div>
	</div>

</body>
</html>
