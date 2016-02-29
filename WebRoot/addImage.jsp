<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<script type="text/javascript" src="script/addImage.js"></script>
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

</script>

<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
-->
<body>
	<div style="display: none">
		<div id="photoTypeWin" class="easyui-window" title="照片类型" collapsible="false" minimizable="false" maximizable="false" closed="true" modal="true"
			 style="width:600;height:400px; padding: 10px;" href="itypeshowImageType.action"> 
		</div>  
	</div>
	<div style="display: none">
		<div id="photoColorWin" class="easyui-window" title="照片颜色" collapsible="false" minimizable="false" maximizable="false" closed="true" modal="true"
			 style="width:600px;height:400px; padding: 10px;" > 
			<div  style="float:left; width:280px;">
				<img id="imgeColor"  alt=""  src="" onerror="this.src='http://localhost:8080/materialManage/images/noPhoto.jpg'" style="width:380px;"></img>
			</div>
			<div id="content_one" style="float:left;width:160px; padding-top:200px;">
				<table class="form-table">
					<tr>
						<td>
						<input id="color" class="easyui-textbox"  value=""  style="height: 26px;"/> 
						</td>
					</tr>
					<tr>
						<td >
							<input class="easyui-linkbutton" type="button" onclick="saveColor()" value="保存" />&nbsp;&nbsp;&nbsp;
							<input class="easyui-linkbutton" type="button" onclick="goBack()" value="返回" />
						</td>
					</tr>
				</table>
			</div>
		</div>  
	</div>
	<div class="easyui-panel" align="center" style="width: 100%">
		<form action="imgsaveImage.action" id="saveformImage" method="post">
				<div>
					<h3 class="tab-subtitle">添加照片</h3>
				</div>
				<div id="content_one" style="width: 50%;height:400px; float: left;">
					<table class="form-table">
						<tr>
							<td>路径</td>
							<td>
								<input class="easyui-textbox" id="imgUlr" name="bmaterialImage.imgUrl"  value="http://localhost:8080/materialManage/images/"  style="height: 26px;"/>
								<span style="color: red;" id="msg"></span>
							</td>
						</tr>
						<tr>
							<td>名称</td>
							<td>
								<input class="easyui-textbox" id="imgName" name="bmaterialImage.imgTitle" data-options="onChange:function(){imageUrl()},required:true,missingMessage:'请输入照片名'"  value="" style="height: 26px;"/> 
								<span style="color: red;" id="msg"></span>
							</td>
						</tr>
						<tr>
							<td>类型</td>
							<td>
								<select class="easyui-combobox" id="imgType"  name="bmaterialImage.bmaterialImageType.imgType" style="width:112px;height:26px" prompt="照片类型" panelHeight="100"; editable="false"; data-options="required:true,missingMessage:'请选择照片类型'">
									<option value=""></option>
									<c:forEach items="${imgTypeList}" var="imty">
										<c:choose>
											<c:when test="${imty.imgTypeName == imgTypeName}">
												<option value="${imty.imgType}" selected="true">${imty.imgTypeName}</option>
											</c:when>
											<c:otherwise>
												<option value="${imty.imgType}" >${imty.imgTypeName}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
								<input class="easyui-linkbutton" type="button"  
									style="border-color: #95B8E7; color: #95B8E7" onclick="addPhotoType()" value="+">
								<span style="color: red;" id="msg"></span>
							</td>
						</tr>
						<tr>
							<td>颜色</td>
							<td>
								<select class="easyui-combobox" id="photoColor" name="bmaterialImage.imgColor" style="width:112px;height:26px" prompt="面料颜色" panelHeight="100"; editable="false"; data-options="required:true, missingMessage:'请选择照片颜色'">
									<option value=""></option>
									<c:forEach items="${requestScope.imageColorList}" var="imco">
										<c:choose>
											<c:when test="${imco==imgColor}">
												<option value="${imco}" selected="true">${imco}</option>
											</c:when><c:otherwise>
												<option value="${imco}" >${imco}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
								<input class="easyui-linkbutton" type="button" 
									style="border-color: #95B8E7; color: #95B8E7" onclick="addPhotoColor()" value="+">
								<span style="color: red;" id="msg"></span>
							</td>
						</tr>
						<tr>
							<td>大小</td>
							<td>
								<input class="easyui-textbox" id="imgSizeW" name="bmaterialImage.imgSizeW" value=""  prompt="照片宽度" style="width:70px;height: 26px;" data-options="validType:['#validateImgSize'],missingMessage:'请输入照片宽度'"/>
								<input class="easyui-textbox" id="imgSizeH" name="bmaterialImage.imgSizeH" value=""  prompt="照片高度" style="width:70px;height: 26px;" data-options="validType:['#validateImgSize'],missingMessage:'请输入照片高度'"/> 
								<span style="color: red;" id="msg"></span>
							</td>
						</tr>
						<tr>
							<td>描述</td>
							<td>
								<input class="easyui-textbox" id="imgDesciption" name="bmaterialImage.imgDesciption" value="" style="height: 26px;"/> 
								<span style="color: red;" id="msg"></span>
							</td>
						</tr>
						<tr>
							<td>
								<input id="photoMtlId" name="bmaterialImage.bmaterial.mtlId"  type="hidden" value="${requestScope.mtlId }" /> 
							</td>
						</tr>
						<tr>
							<td></td>
							<td>
								<a class="easyui-linkbutton" onclick="addsave()"   style="margin-left: 10px;">&nbsp;保存&nbsp;</a>&nbsp;&nbsp;&nbsp;
								<input class="easyui-linkbutton" type="button" onclick="goBackImage()" value="返回" />
							</td>
						</tr>
					</table>
				</div>
				<div id="content_two" style="width: 50%;float: left;">
					<table class="form-table">
						<tr>
							<td style="text-align:center"><a href="#">刷新</a></td>
							<td>
								<a style="text-decoration: underline;" onclick=" downloadImg()">下载原图</a>
							</td>
						</tr>
						<tr>
							<td colspan="2" >
								<img  id="imgrefresh"  alt="" src=""  onerror="this.src='http://localhost:8080/materialManage/images/noPhoto.jpg'"></img>
							</td>
						</tr>
					</table>
				</div>
		</form>
	</div>
</body>
</html>