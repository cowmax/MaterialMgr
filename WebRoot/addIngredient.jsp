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
<script type="text/javascript">

</script>
</head>

<body>
	<div align="center" style="width: 80%;  padding: 10px;">
		<div id="options" class="toolbar" style="float: left;" >
			<form id="saveBMIngred" action="ingraddIngDt.action" method="post" >
				<table align="center"  style="padding-left: 35px;">
	  				<tr>
		    			<td>
		    				<select class="easyui-combobox" id="ingredientTypeCmb" name="bmaterialIngredient.ingredientName" data-options="height:26,width:110,required:true,missingMessage:'请选择成分类型'" prompt="成分" panelHeight="100";>
								<option value=""></option>
								<c:forEach items="${allIngredientTypes}" var="ingType">
									<option value="${ingType.ingredientType}" >${ingType.ingredientType}</option>
								</c:forEach>
							</select>
		    			</td>
		    			<td style="padding-left: 10px;">
		    				<input id="BMprecent" class="easyui-textbox" type="text" name="bmaterialIngredient.precent" prompt="百分比" value="" style="height:26px;width:110px;"  data-options=" required:true, validType:['#snumber'],missingMessage:'请输入占比'" >
		    			</td>
		   			    <td>
		   			    	<input id="IngredMtlId" name="bmaterialIngredient.bmaterial.mtlId"  type="hidden" value="${requestScope.mtlId }" />
		   			    </td>
		   			    <td>
		   			    	<a class="easyui-linkbutton" onclick="javascript:addBMIngredient()" data-options="iconCls:'icon-add'" style="margin-left: 10px;">新增</a>
		   			    </td>
	  				</tr>
				</table>
			</form>
			<div style="width: 100%; height: 10px;float: left;"></div>
		</div>
		<div style="width:100%; float: left;" id = "imageTypeList">
			<table class="easyui-datagrid" style="width:80%; " singleSelect="true">
				<thead>
					<tr>
						<th data-options="field:'ingredientName'" width="100">成分</th>
						<th data-options="field:'precent'" width="100">百分比</th>
						<th data-options="field:'操作'">操作</th>
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
								
						<td>
							<a onclick="delingredient('${ingred.id}','${ingred.ingredientName }')">删除</a>
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
