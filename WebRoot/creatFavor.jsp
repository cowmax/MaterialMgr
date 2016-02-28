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
	width: 6em;
}
.form-table tr .textbox{
	height: 24px;
}
.form-table tr{
	height: 30px;
}
</style>
<script type="text/javascript">
	$(function(){
		var materialCode = creatMaterialCode();
		$("#materialCode").textbox('setValue',materialCode);//赋值;
	})
</script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<body>
	<div class="easyui-panel" align="center">
		<form action="puseraddUser" id="saveform" method="post">
			<div style="width: 800px; float: left;" id="content">
				<div id="content_one" style="width: 50%;height:400px; float: left;">
					<table class="form-table">
						<tr>
							<td>产品编码</td>
							<td>
								<input class="easyui-textbox" id="materialCode" name=""value="" />
								<span style="color: red;" id="msg"></span>
							</td>
						</tr>
						<tr>
							<td>关注度</td>
							<td>
								<input class="easyui-textbox" id="uid" name="pu.userId" value="" /> 
								<span style="color: red;" id="msg"></span>
							</td>
						</tr>
						<tr>
							<td>状态</td>
							<td>
								<select class="easyui-combobox" id="qmtlType" style="width:112px;height:26px" prompt="子类目" panelHeight="100"; editable="false";>
									<option value=""></option>
									<c:forEach items="" var="ctype">
										<c:choose>
											<c:when test="">
												<option value="" selected="true"></option>
											</c:when><c:otherwise>
												<option value="" ></option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
								<input class="easyui-linkbutton" type="button" id="query" 
									style="border-color: #95B8E7; color: #95B8E7" onclick="" value="+">
								<span style="color: red;" id="msg"></span>
							</td>
						</tr>
						<tr>
							<td>收藏夹说明</td>
							<td>
								<input class="easyui-textbox" id="uid" name="pu.userId" value="" /> 
								<span style="color: red;" id="msg"></span>
							</td>
						</tr>
					</table>
					<a href="#">保存</a>
					<a href="#">取消</a>
				</div>
				<div id="content_two" style="width: 50%;float: left;">
					<a href="#">添加</a>
					<table class="easyui-datagrid" style="width=100%;" singleSelect="true">
						<thead>
							<tr>
								<th data-options="field:'code'" width="">序号</th>
								<th data-options="field:'mtlId '">面料编码</th>
								<th data-options="field:'mtlName'">面料名称</th>
							</tr>
						</thead>
						<c:choose>
							<c:when test="">
								<tr style="text-align: center;">
									<td colspan="11">
										<span style="color: grey;"></span>
									</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach items="" var="ebm" varStatus="i">
									<tr>
										<td></td>
										<td>
											<c:out value="" />
										</td>
										<td>
											<c:out value="" />
										</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</table>
				</div>
			</div>
		</form>
	</div>
</body>
</html>