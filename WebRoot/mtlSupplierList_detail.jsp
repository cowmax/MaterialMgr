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
<link rel="stylesheet" href="js/easyui/themes/default/easyui.css" type="text/css"></link>

<script type="text/javascript" src="js/easyui/jquery-1.6.min.js"></script>
<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="script/common.js"></script>
<script type="text/javascript" src="script/mtlSupplierList.js"></script>
<script type="text/javascript" src="js/easyui/themes/easyui-lang-zh_CN.js"></script>
<link href="css/style.css" rel="stylesheet" type="text/css">
<style type="text/css">

</style>
<script type="text/javascript">

</script>
</head>

<body>
	<div style="display: none;">
		<div id="addSupplier" class="easyui-window" title="供方信息列表"  modal="true"
			collapsible="false" minimizable="false" maximizable="false" closed="true" 
			 style="width:440px;height:300px; padding: 10px;" href="suinaddSupOperation"> 
		</div>  
	</div>
	<div style="margin-top:20px; width:100%;">
		<div id="options" class="toolbar" style="height: 30px;">
				<div style="float: left; margin-bottom: 10px;">
					<select class="easyui-combobox" id="qsupplierName" style="width:110px;height:26px" prompt="请选择供应商" panelHeight="100"; editable="false">
						<option value=""></option>
						<c:forEach items="${allSuin}" var="su">
							<c:choose>
								<c:when test="${su.suna == qsupplierName}">
									<option value="${su.suna}" selected="true">${su.suna}</option>
								</c:when>
								<c:otherwise>
									<option value="${su.suna}" >${su.suna}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
					<input class="easyui-textbox" type="text" id="qmtlName" prompt="面料名称" value="${qmtlName}" data-options="height:26,width:90">
					<input class="easyui-textbox" type="text" id="qmtlCode" prompt="面料编码" value="${qmtlCode}" data-options="height:26,width:90">
					<span>颜色数量</span>
					<input class="easyui-textbox" type="text" id="qminColorCount" value="${qminColorCount}" data-options="height:26,width:40">
					<span>-</span>
					<input class="easyui-textbox" type="text" id="qmaxColorCount" value="${qmaxColorCount}" data-options="height:26,width:40">
				</div>
				<div style=" float: left;">
					<input class="easyui-linkbutton" type="button" style="margin-left: 10px;"
						onclick="ms_query()" value="查询">
				</div>
			<div style="width: 100%; height: 10px;float: left;"></div>
		</div>
		<div style="width:100%; float: left;" id = "suppliersList">
			<table class="easyui-datagrid" style="width:100%;" singleSelect="true">
				<thead>
					<tr>
						<th data-options="field:'code'" width="">序号</th>
						<th data-options="field:'mtsCode'" width="100">供应商</th>
						<th data-options="field:'mtsName'" width="100">供应商名称</th>
						<th data-options="field:'mtlName'">面料名称</th>
						<th data-options="field:'mtlId '">面料编码</th>
						<th data-options="field:'colorCount'">颜色数量</th>
					</tr>
				</thead>
				
				<c:forEach items="${getSupListByOption}" var="supplier" varStatus="i">
					<tr>
						<td>${i.index+1}</td>
								
						<td>
							<c:out value="${supplier.suin.suid}" />
						</td>
								
						<td>
							<c:out value="${supplier.suin.suna}" />
						</td>
								
						<td>
							<c:out value="${supplier.mtsName}" />
						</td>
								
						<td>
							<c:out value="${supplier.mtsCode}" />
						</td>
								
						<td>
							<c:out value="${supplier.mtsColorCount}" />
						</td>
								
					</tr>
				</c:forEach>
			</table>
			<div class="pager" id="pagebar">
				共<b id="ttCount">${totalcount }</b>条记录 转到&nbsp;<input value="${offset+1}" size="2" id="offset" class="easyui-textbox" />&nbsp;页/<b id="ttPage">${totalpage }</b>页
				<button class="easyui-linkbutton jump-btn" width="20" onclick="ms_reload()">跳转</button>
				<a onclick="ms_turnPage(0)">&lt;&lt; 第一页</a> <a
					onclick="ms_turnPage(${offset-1})">&lt; 上一页</a> <a
					onclick="ms_turnPage(${offset+1})">下一页 &gt;</a> <a
					onclick="ms_turnPage(${totalpage-1})">最后一页 &gt;&gt;</a>
			</div>
			<div style="margin-top: 10px; text-align: center;">
				<a class="easyui-linkbutton" style="margin-left: 10px;padding: 0px 3px;" onclick="javascript:closeEditSupplier();">确定</a>
			</div>
		</div>
	</div>

</body>
</html>
