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
	
	#materialShow {
		width: 100%;
	}
	
	#materialShow .materialPhoto a {
		width: 150px;
		height: 90px;
		margin: 5px;
		padding: 0;
		border: gray 5px solid;
		float: left;
		overflow: hidden;
		text-decoration: none;
		display: block;
		position: relative
	}
	
	#materialShow .materialPhoto a span {
		display: none;
	}
	
	#materialShow .materialPhoto a img {
		border: none;
	}
	
	#materialShow .materialPhoto a:hover {
		border-color: red;
	}
	
	#materialShow  .materialPhoto a:hover span {
		position: absolute;
		background: red;
		color: white;
		width: 100%;
		padding-top: 3px;
		text-align: center;
		right: 0;
		bottom: 0;
		display: block;
		font-size: 11px;
		filter: Alpha(Opacity = 60);
		Opacity: 0.6;
	}
</style>

</head>

<body>
	<div class="easyui-panel" align="center" style="width: 100%;  padding: 20px;">
		<div id="options" class="toolbar" style="float: left;" >
			<form id="saveImageType" action="itypeaddPhoto.action" method="post" >
				<table>
	  				<tr>
		    			<td><input class="easyui-textbox" type="text" name="bmaterialImageType.imgTypeName" prompt="类型中文名称" value="" data-options="height:26,width:120"></td>
		    			<td><input class="easyui-textbox" type="text" name="bmaterialImageType.memo" prompt="说明" value="" data-options="height:26,width:200"></td>
		   			    <td><a class="easyui-linkbutton" onclick="javascript:addImageType()"  data-options="iconCls:'icon-add'" style="margin-left: 10px;">新增</a></td>
	  				</tr>
				</table>
			</form>
			<div style="width: 100%; height: 10px;float: left;"></div>
		</div>
		<div style="width:100%; float: left;" id = "imageTypeList">
			<table class="easyui-datagrid" style="width=100%;" singleSelect="true">
				<thead>
					<tr>
						<th data-options="field:'code'" width="">序号</th>
						<th data-options="field:'imgType'" width="100">类型ID</th>
						<th data-options="field:'imgTypeName'" width="100">类型名称</th>
						<th data-options="field:'memo'">说明</th>
						<th data-options="field:'操作'">操作</th>
					</tr>
				</thead>
						<c:forEach items="${imgTypeList }" var="typ" varStatus="i">
							<tr>
								<td>${i.index+1+offset*5 }</td>
								
								<td>
									<c:out value="${typ.imgType }" />
								</td>
								
								<td>
									<c:out value="${typ.imgTypeName }" />
								</td>
								
								<td>
									<c:out value="${typ.memo }" />
								</td>
								
								<td>
									<a onclick="delImageType('${typ.imgType}','${typ.imgTypeName }')">删除</a>
								</td>
							</tr>
						</c:forEach>
			</table>
			<div class="pager" id="pagebar">
				共<b id="ttCount">${totalcount }</b>条记录 转到&nbsp;<input value="${offset+1}" size="2" id="offset" class="easyui-textbox" />&nbsp;页/<b id="ttPage">${totalpage }</b>页
				<button class="easyui-linkbutton jump-btn" width="20" onclick="reload()">跳转</button>
				<a onclick="turnPage(0)">&lt;&lt; 第一页</a> <a
					onclick="turnPage(${offset-1})">&lt; 上一页</a> <a
					onclick="turnPage(${offset+1})">下一页 &gt;</a> <a
					onclick="turnPage(${totalpage-1})">最后一页 &gt;&gt;</a>
			</div>
		</div>
	</div>

</body>
</html>
