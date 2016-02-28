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
/**
	 * 根据制定页面跳转
	 */
	function reload() {
		var offset = document.getElementById("offset").value;
		var idx = (offset == null) ? 0 : parseInt(offset) - 1;
			
		window.location = 'suingetAllSuins?offset=' + idx;
	}
	
	/**
	 * 根据条件查询
	 */
	function query() {
		var qsuid = $("#qsuid").val();
		var qsuna = $("#qsuna").val();
		var qpsad = $("#qpsad").val();
		var qpmad = $("#qpmad").val();
		var qwhad = $("#qwhad").val();
		var qcous = $("#qcous").val();
		var qtel = $("#qtel").val();
		var qrema = $("#qrema").val();
		
		window.location = 'suingetAllSuins.action?qsuid='+qsuid+'&qsuna='+qsuna
		+'&qpsad='+qpsad+'&qpmad='+qpmad+'&qwhad='+qwhad
		+'&qcous='+qcous+'&qtel='+qtel+'&qrema='+qrema;
		
	}
	
	/**
	* 翻到给定偏移量的页面
	*/
	function turnPage(offset){
		var qsuid = $("#qsuid").val();
		var qsuna = $("#qsuna").val();
		var qpsad = $("#qpsad").val();
		var qpmad = $("#qpmad").val();
		var qwhad = $("#qwhad").val();
		var qcous = $("#qcous").val();
		var qtel = $("#qtel").val();
		var qrema = $("#qrema").val();
	
		window.location = 'suingetAllSuins.action?qsuid='+qsuid+'&qsuna='+qsuna
					+'&qpsad='+qpsad+'&qpmad='+qpmad+'&qwhad='+qwhad
					+'&qcous='+qcous+'&qtel='+qtel+'&qrema='+qrema+'&offset=' + offset;			
	}
	
	/**
	 * 确认删除
	 * @returns {Boolean}
	 */
	function sureDelMaterial(suna,suid) {
		var msg = "确定要删除 ["+suna+"] 供应商吗？";
		if (confirm(msg) == true) {
			$.ajax({
				type:'POST',
				url:'suindelSuinInfo',
				data:{
					'suid':suid
				},
				dataType : 'json',
				success : function(data){
					if(data){
						$.messager.show({
							msg : '<div style="width:100%"><div style="line-height:50px;text-align:center;">删除供应商信息成功！</div></div>',
							timeout : 800,
							showSpeed : 200,
							showType : 'show',
							style : {
								right : '',
								top : '',
								bottom : ''
							}
						});
						window.location =  'suingetAllSuins.action';
					}
				}
			});
			return true;
		} else {
			return false;
		}
	}
</script>
</head>

<body>
	<div style="display: none">
	</div>
	<div style="margin:20px 20px 0px 20px; width:100%;">
		<div id="options" class="toolbar">
			<div style="width: 100%;float: none;">
				<div style="float: left;">
					<a onclick="addPanelExists('materialaddMtlOperation','新增面料档案')" class="easyui-linkbutton"
						data-options="iconCls:'icon-add'" style="margin-right: 10px;">新增</a>
					<a class="easyui-linkbutton" onclick="$('#win').window('open')" style="margin-right: 10px;">&nbsp;导入&nbsp;</a>
					<a href="suingetAllSuins" class="easyui-linkbutton"
						data-options="iconCls:'icon-reload'" style="margin-right: 10px;">刷新</a>
				</div>
				<div style="float: left; margin-bottom: 10px;">
					<input class="easyui-textbox" type="text" id="qsuid" prompt="供应商编码" value="${qsuid}" data-options="height:26">
					<input class="easyui-textbox" type="text" id="qsuna" prompt="供应商全称" value="${qsuna}" data-options="height:26">
					<input class="easyui-textbox" type="text" id="qpsad" prompt="营业部地址" value="${qpsad}" data-options="height:26">
					<input class="easyui-textbox" type="text" id="qpmad" prompt="工厂地址" value="${qpmad}" data-options="height:26"><br/><br/>
					<input class="easyui-textbox" type="text" id="qwhad" prompt="仓库地址" value="${qwhad}" data-options="height:26">
					<input class="easyui-textbox" type="text" id="qcous" prompt="联系人" value="${qcous}" data-options="height:26">
					<input class="easyui-textbox" type="text" id="qtel" prompt="联系电话" value="${qtel}" data-options="height:26">
					<input class="easyui-textbox" type="text" id="qrema" prompt="备注" value="${qrema}" data-options="height:26">
				</div>
				<div style=" float: left;">
					<input class="easyui-linkbutton"
						type="button" id="query" style="margin-left: 15px;"
						onclick="query()" value="查询">
					<a class="easyui-linkbutton" style="margin-left: 15px;"
						href="materialgetexport.action" >&nbsp;导出&nbsp;</a>
				</div>
			</div>
		</div>
		<div style="width:100%; float: left;" id = "materialShow">
			<table class="easyui-datagrid" style="width=100%;" singleSelect="true">
				<thead>
					<tr>
						<th data-options="field:'code'" width="">序号</th>
						<th data-options="field:'suid'">供应商编码</th>
						<th data-options="field:'suna '">供应商全称</th>
						<th data-options="field:'psad'">营业部地址</th>
						<th data-options="field:'pmad'">工厂地址</th>
						<th data-options="field:'whad'">仓库地址</th>
						<th data-options="field:'cous'">联系人</th>
						<th data-options="field:'tel1'">联系电话</th>
						<th data-options="field:'edus'">最后修改人</th>
						<th data-options="field:'rema'">备注</th>
						<th data-options="field:'操作'">操作</th>
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
						<c:forEach items="${allSuinList}" var="suin" varStatus="i">
							<tr>
								<td>${i.index+1}</td>
								
								<td>
									<c:out value="${suin.suid}" />
								</td>
								
								<td>
									<c:out value="${suin.suna}" />
								</td>
								
								<td>
									<c:out value="${suin.psad}" />
								</td>
								
								<td>
									<c:out value="${suin.pmad}" />
								</td>
								
								<td>
									<c:out value="${suin.whad}" />
								</td>
								
								<td>
									<c:out value="${suin.cous}" />
								</td>
								
								<td>
									<c:out value="${suin.tel1}" />
								</td>
								
								<td>
									<c:out value="${suin.edus}" />
								</td>
								
								<td>
									<c:out value="${suin.rema}" />
								</td>
								
								<td>
									<a onclick="addPanelExists('suinmergeSuinOpr?suid=${suin.suid}','编辑供应商信息')" style="text-decoration: underline;">编辑</a><b>|</b>
									<a onclick="javascript:return sureDelMaterial('${suin.suna}','${suin.suid}')" style="text-decoration: underline;">删除</a>
								</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				
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
