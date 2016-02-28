<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

<script type="text/javascript" src="script/mtlSupplierList.js"></script>
<script type="text/javascript" src="js/easyui/jquery-1.6.min.js"></script>
<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="script/common.js"></script>
<script type="text/javascript" src="script/materialTypeList.js"></script>
<script type="text/javascript" src="script/mtlSupplierList.js"></script>
<script type="text/javascript" src="script/addImage.js"></script>
<script type="text/javascript" src="script/addMtlImage.js"></script>
<script type="text/javascript" src="script/relateProductList.js"></script>
<script type="text/javascript" src="script/Ingredient.js"></script>
<script type="text/javascript" src="script/addMaterial.js"></script>
<link href="css/style.css" rel="stylesheet" type="text/css">

<style type="text/css">
* {
	margin: 0px;
	padding: 0px;
}

.form-table tr td:first-child {
	width: 7em;
}
.form-table tr .textbox{
	height: 24px;
}
.form-table tr{
	height: 30px;
}
</style>
<script type="text/javascript">
	function mergeSuin(){
		var obj=document.getElementById('mergeSuinForm'); 
		obj.submit(); 
	}
</script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<body>
	<div class="easyui-panel" align="center">
		<form action="suinmergeSuinInfo" id="mergeSuinForm" method="post">
			<div style="width: 800px; float: left;" id="content">
				<div>
					<h3 class="tab-subtitle">添加供应商信息</h3>
				</div>
				<div id="content_one" style="width: 50%;float: left;">
					<table class="form-table">
						<tr>
							<td>供应商</td>
							<td>
								<input class="easyui-textbox" id="suna" name="newSuin.suna" value="${newSuin.suna}" style="height: 26px;" />
							</td>
						</tr>
						
						<tr>
							<td>联系人</td>
							<td>
								<input class="easyui-textbox" id="cous" name="newSuin.cous" value="${newSuin.cous}" style="height: 26px;" />
							</td>
						</tr>
						
						<tr>	
							<td>联系电话</td>
							<td>
								<input class="easyui-textbox" id="phon" name="newSuin.phon" value="${newSuin.phon}" style="height: 26px;" />
							</td>
						</tr>
						
						<tr>	
							<td>座机</td>
							<td>
								<input class="easyui-textbox" id="tel1" name="newSuin.tel1" value="${newSuin.tel1}" style="height: 26px;" />
							</td>
						</tr>
						
						<tr>	
							<td>传真</td>
							<td>
								<input class="easyui-textbox" id="fax" name="newSuin.fax" value="${newSuin.fax}" style="height: 26px;" />
							</td>
						</tr>
						
						<tr>	
							<td>地址</td>
							<td>
								<input class="easyui-textbox" id="psad" name="newSuin.psad" value="${newSuin.psad}" style="height: 26px;" />
							</td>
						</tr>
						
						<tr>	
							<td>邮箱/QQ</td>
							<td>
								<input class="easyui-textbox" id="emai" name="newSuin.emai" value="${newSuin.emai}" style="height: 26px;" />
							</td>
						</tr>
						
						<tr>	
							<td>备注</td>
							<td>
								<input class="easyui-textbox" id="rema" name="newSuin.rema" value="${newSuin.rema}" style="height: 26px;" />
							</td>
						</tr>
						
					</table>
					<a class="easyui-linkbutton" onclick="javascript:mergeSuin()" style="margin-left: 10px;margin-bottom:10px; padding: 0px 3px;">保存</a>
					<a class="easyui-linkbutton" onclick="removeCurrentPanel();" style="margin-left: 10px;margin-bottom:10px; padding: 0px 3px;">返回</a>
				</div>
			</div>
		</form>
	</div>
</body>
</html>