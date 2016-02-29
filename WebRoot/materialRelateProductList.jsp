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
	<div style="display: none;">
		<!-- 产品信息  -->
		<div id="productList" class="easyui-window" title="产品信息"
			collapsible="false" minimizable="false" maximizable="false" closed="true"  modal="true"
			 style="width:800px;height:540px; padding: 10px;" href="productgetAllProduct.action?mtlId=${mtlId}"> 
		</div>  
	</div>
	<div align="center" style="padding: 20px;">
		<div id="options" class="toolbar" style="height: 30px;">
			<div style="float: left; margin-bottom: 10px;">
				<input class="easyui-textbox" id="relateProductCode" value="" style="width: 98px; height: 26px;" readonly="readonly"/> 
				<input class="easyui-linkbutton" type="button" id="query" 
					style="border-color: #95B8E7; color: #95B8E7; left:-10px" onclick="showProductList()" value="…">
				<input class="easyui-textbox" type="text" id="memo" prompt="说明" value="" data-options="height:26,width:200">
			</div>
			<div style=" float: left;">
				<a onclick="addRelateProduct('${mtlId}')" class="easyui-linkbutton"
					data-options="iconCls:'icon-add'" style="margin-left: 10px;">添加</a>
			</div>
			<div style="width: 100%; height: 10px;float: left;"></div>
		</div>
		<div style="width:100%; float: left;" id = "suppliersList">
			<table class="easyui-datagrid" style="width=100%;" singleSelect="true">
				<thead>
					<tr>
						<th data-options="field:'code'" width="">序号</th>
						<th data-options="field:'productCode'" width="100">产品编码</th>
						<th data-options="field:'memo'" width="100">说明</th>
						<th data-options="field:'relateStatus'" width="100">状态</th>
						<th data-options="field:'operations'">操作</th>
					</tr>
				</thead>

				<c:forEach items="${bmRelateProducts}" var="relpdu" varStatus="i">
					<tr>
						<td>${i.index+1}</td>
						<td><c:out value="${relpdu.productCode }" /></td>
						<td><c:out value="${relpdu.memo }" /></td>
						<td><c:out value="${relpdu.relateStatus }" /></td>
						<td><a onclick="javascript:return sureDelRelpdu('${relpdu.productCode}','${relpdu.mrpId }','${relpdu.BMaterial.mtlId}')">删除</a></td>
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
