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
	body {
		background: #f1f1f1;
		margin: 0
	}
</style>

</head>

<body>
	<div align="center" style="width: 80%;  padding: 10px;">
		<div style="width:100%; float: left;" id = "imageTypeList">
			<table class="easyui-datagrid" style="width:80%; " singleSelect="true">
				<thead>
					<tr>
						<th data-options="field:'ingredientName'" width="100">成分</th>
						<th data-options="field:'precent'" width="100">百分比</th>
					</tr>
				</thead>
				<c:forEach items="${ingredientList }" var="ingred" varStatus="i">
					<tr>
						<td>
							<c:out value="${ingred.ingredientName }" />
						</td>
								
						<td>
							 <fmt:setLocale value="zh_cn" />  
							 <fmt:formatNumber type="percent" value="${ingred.precent }" />
						</td>
					</tr>
				</c:forEach>
			</table>
			<div style="margin-top: 10px;">
				<a onclick="closeIngredientWin()" class="easyui-linkbutton" style="margin-left: 10px;padding: 0px 3px;">确定</a>
			</div>
		</div>
		
	</div>

</body>
</html>
