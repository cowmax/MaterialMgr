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
//事件绑定部分
$(function(){
	
	var pid = $("#qpmtlType").combobox("getValue");
	if(!pid==""){
		$.ajax({
			type : 'POST',
			url : 'materialgetMtlTypeListByPid.action',
			data : {
				'qpid' : pid
			},
			dataType : 'json',
			success : function(data) {
				$('#qmtlType').combobox('setValue','');
				var data_json = new Array();
				for(var typeInfo in data){
					var rowdata={
						'id':data[typeInfo].id,
						'text':data[typeInfo].text,
						'checked':data[typeInfo].checked
					}
					data_json.push(rowdata);
				}
				$('#qmtlType').combobox('loadData',data_json);
				//window.location = 'materialgetMaterialList';
			}
		});
	}
	// 点击选择渠道/店铺去掉提示
	$("#qpmtlType").combobox({
	   	 onSelect:function(){
			var pid = $("#qpmtlType").combobox("getValue");
			if(pid!=null){
				$.ajax({
					type : 'POST',
					url : 'materialgetMtlTypeListByPid.action',
					data : {
						'qpid' : pid
					},
					dataType : 'json',
					success : function(data) {
						var data_json = new Array();
						$('#qmtlType').combobox('clear');
						for(var typeInfo in data){
							var rowdata={
								'id':data[typeInfo].id,
								'text':data[typeInfo].text,
								'checked':data[typeInfo].checked
							}
							data_json.push(rowdata);
						}
						$('#qmtlType').combobox('loadData',data_json);
						//window.location = 'materialgetMaterialList';
					}
				});
			}
	   	 }
	});
	
	var data_json = new Array();
});

	/**
	 *照片不存在触发事件
	 */
	function nofind(){

	var img=event.srcElement;
	
	img.src="http://localhost:8080/materialManage/images/noPhoto.jpg";
	
	
	}
	
	/**
	 *解析照片路径
	 */
	function photoFieldFmtr(val, row){
		val = val.trim();
		var doc = $.parseXML(val);
		var url = $($(doc).find("img_url")[0]).text();
		return "<input type='hidden' value='"+ val +"' name='xmlImgurl'>" + 
			"<a href='#'><img class = 'materialPhoto' onerror='nofind();' style='width: 64px; height: 64px;' src='"+ url +"'></img></a>";
	}
	
	/**
	 *解析相关产品信息
	 */
	function productFieldFmtr(val, row){
		val = val.trim();
		var doc = $.parseXML(val);
		var pduCode = $($(doc).find("product_code")[0]).text();
		return "<input type='hidden' value='"+ val +"' name='xmlProductString'>" + 
			"<a href='#'>"+pduCode+"</a>";
	}
	
	/**
	 *解析相关供应商信息
	 */
	function supplierFieldFmtr(val, row){
		val = val.trim();
		var doc = $.parseXML(val);
		var suna = $($(doc).find("suna")[0]).text();
		return "<input type='hidden' value='"+ val +"' name='xmlImgurl'>" + 
			"<a href='#'>"+suna+"</a>";
	}
	
   /**
	 * 根据制定页面跳转
	 */
	function reload() {
		var offset = document.getElementById("offset").value;
		var idx = (offset == null) ? 0 : parseInt(offset) - 1;
			
		window.location = 'materialgetMaterialList?offset=' + idx;
	}
	
	/**
	 * 根据条件查询
	 */
	function query() {
		var qmtlCode = $("#qmtlCode").val();
		var qmtlName = $("#qmtlName").val();
		var qpmtlType = $("#qpmtlType").combobox("getValue");
		var qmtlType = $("#qmtlType").combobox("getValue");
		var qseason = $("#qseason").combobox("getValue");
		var qcolor = $("#qcolor").combobox("getValue");
		var qscreateDt = $("#qscreateDt").datetimebox('getValue');
		var qecreateDt = $("#qecreateDt").datetimebox('getValue');
		
		var qminMtlPrice = $("#qminMtlPrice").val();
		var qmaxMtlPrice = $("#qmaxMtlPrice").val();
		
		var qminWeigth = $("#qminWeigth").val();
		var qmaxWeigth = $("#qmaxWeigth").val();
		
		var qminPrdCycle = $("#qminPrdCycle").val();
		var qmaxPrdCycle = $("#qmaxPrdCycle").val();
		
		var qminOrder = $("#qminOrder").val();
		var qmaxOrder = $("#qmaxOrder").val();
		
		var qsupplierName = $("#qsupplierName").val();
		
		window.location = 'materialgetMaterialList.action?qmtlCode='+ qmtlCode+'&qmtlName='+ qmtlName
					+'&qpmtlType='+ qpmtlType+'&qmtlType='+ qmtlType+'&qseason='+ qseason+'&qcolor='+ qcolor
					+'&qscreateDt='+ qscreateDt+'&qecreateDt='+ qecreateDt+'&qminMtlPrice='+ qminMtlPrice+'&qmaxMtlPrice='+ qmaxMtlPrice
					+'&qminWeigth='+ qminWeigth+'&qmaxWeigth='+ qmaxWeigth+'&qminPrdCycle='+ qminPrdCycle+'&qmaxPrdCycle='+ qmaxPrdCycle
					+'&qminOrder='+ qminOrder+'&qmaxOrder='+ qmaxOrder+'&qsupplierName='+ qsupplierName;
		
	}
	
	/**
	* 翻到给定偏移量的页面
	*/
	function turnPage(offset){
		var qmtlCode = $("#qmtlCode").val();
		var qmtlName = $("#qmtlName").val();
		var qpmtlType = $("#qpmtlType").combobox("getValue");
		var qmtlType = $("#qmtlType").combobox("getValue");
		var qseason = $("#qseason").combobox("getValue");
		var qcolor = $("#qcolor").combobox("getValue");
		var qscreateDt = $("#qscreateDt").datetimebox('getValue');
		var qecreateDt = $("#qecreateDt").datetimebox('getValue');
		
		var qminMtlPrice = $("#qminMtlPrice").val();
		var qmaxMtlPrice = $("#qmaxMtlPrice").val();
		
		var qminWeigth = $("#qminWeigth").val();
		var qmaxWeigth = $("#qmaxWeigth").val();
		
		var qminPrdCycle = $("#qminPrdCycle").val();
		var qmaxPrdCycle = $("#qmaxPrdCycle").val();
		
		var qminOrder = $("#qminOrder").val();
		var qmaxOrder = $("#qmaxOrder").val();
		
		var qsupplierName = $("#qsupplierName").val();
		
		window.location = 'materialgetMaterialList.action?qmtlCode='+ qmtlCode+'&qmtlName='+ qmtlName
					+'&qpmtlType='+ qpmtlType+'&qmtlType='+ qmtlType+'&qseason='+ qseason+'&qcolor='+ qcolor
					+'&qscreateDt='+ qscreateDt+'&qecreateDt='+ qecreateDt+'&qminMtlPrice='+ qminMtlPrice+'&qmaxMtlPrice='+ qmaxMtlPrice
					+'&qminWeigth='+ qminWeigth+'&qmaxWeigth='+ qmaxWeigth+'&qminPrdCycle='+ qminPrdCycle+'&qmaxPrdCycle='+ qmaxPrdCycle
					+'&qminOrder='+ qminOrder+'&qmaxOrder='+ qmaxOrder+'&qsupplierName='+ qsupplierName+ '&offset=' + offset;			
	}
	
	/**
	 * 确认删除
	 * @returns {Boolean}
	 */
	function sureDelMaterial(mtlName,mtlId) {
		var reason=prompt("面料档案删除后，将不能恢复，是否继续删除？","请输入删除面料的原因");
		
		if (reason!=null && reason!="")  {
			$.ajax({
				type:'POST',
				url:'materialdelMaterial',
				data:{
					'mtlId':mtlId
				},
				dataType : 'json',
				success : function(data){
					if(data){
						$.messager.show({
							msg : '<div style="width:100%"><div style="line-height:50px;text-align:center;">删除面料信息成功！</div></div>',
							timeout : 800,
							showSpeed : 200,
							showType : 'show',
							style : {
								right : '',
								top : '',
								bottom : ''
							}
						});
						window.location =  'materialgetMaterialList.action';
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
		<div id="win" class="easyui-window" title="文件上传"  style="width:350px;height:200px;" collapsible="false" minimizable="false" maximizable="false" closed="true" >
			<div align="center" style="margin-top: 20px;" class="toolbar">
			<form action="materialuploadFiles.action" method="post" enctype="multipart/form-data">  
				<table>    
			    	<tr>  
			        	<td>上传文件&nbsp;&nbsp;</td>  
			            <td><input class="easyui-filebox" id="uploadUrl" name="myFile" buttonText="浏览"></td>  
			        </tr> 
			        <tr style="text-align: center;">
			        	<td colspan="2" style="height:40px;"><a href="materialimportTemplate.action" style="color:blue;text-decoration:underline;">下载导入模板</a></td>
			        </tr>
			        <tr style="text-align: center;">  
			            <td colspan="2" >
			            	<input class="easyui-linkbutton" type="submit" onclick="return checkUploadUrl()" value="上传">
			            	<span style="margin-right: 10px;"></span>
			            	<input class="easyui-linkbutton" type="reset" value="重置">
			            </td>  
			         </tr>  
			     </table>  
			  </form>  
			  </div>
		</div>
	</div>
	<div style="margin:20px 20px 0px 20px; width:100%;">
		<div id="options" class="toolbar">
			<div style="width: 100%;float: none;">
				<div style="float: left;">
					<a onclick="addPanelExists('materialaddMtlOperation','新增面料档案')" class="easyui-linkbutton"
						data-options="iconCls:'icon-add'" style="margin-right: 10px;">新增</a>
					<a class="easyui-linkbutton" onclick="$('#win').window('open')" style="margin-right: 10px;">&nbsp;导入&nbsp;</a>
					<a href="materialgetMaterialList" class="easyui-linkbutton"
						data-options="iconCls:'icon-reload'" style="margin-right: 10px;">刷新</a>
				</div>
				<div style="float: left; margin-bottom: 10px;">
					<input class="easyui-textbox" type="text" id="qmtlCode" prompt="面料编码" value="${qmtlCode}" data-options="height:26,width:80">
					<input class="easyui-textbox" type="text" id="qmtlName" prompt="面料名称" value="${qmtlName}" data-options="height:26,width:80">
					<select class="easyui-combobox" id="qpmtlType" style="width:80px;height:26px" prompt="类型" panelHeight="100"; editable="false">
						<option value=""></option>
						<c:forEach items="${parentMtlTypeList}" var="ptype">
							<c:choose>
								<c:when test="${ptype.mtlType == qpmtlType}">
									<option value="${ptype.mtlType}" selected="true">${ptype.mtlTypeName}</option>
								</c:when><c:otherwise>
									<option value="${ptype.mtlType}" >${ptype.mtlTypeName}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
					<input class="easyui-combobox" id="qmtlType" style="width:80px;height:26px" prompt="子类型" panelHeight="100";editable="false" data-options="valueField:'id',textField:'text'" >
					<select id="qseason" class="easyui-combobox" id="qseason" style="width:70px;height:26px" prompt="季节" panelHeight="100"; editable="false">
						<option value=""></option>
						<option value="春 " <c:if test="${qsena=='春'}"> selected="true"</c:if>> 春 </option>
						<option value="夏" <c:if test="${qsena=='夏'}">selected="true"</c:if>> 夏</option>
						<option value="秋" <c:if test="${qsena=='秋'}">selected="true"</c:if>> 秋</option>
						<option value="冬" <c:if test="${qsena=='冬'}">selected="true"</c:if>> 冬</option>
					</select>
					<select id="qcolor" class="easyui-combobox" id="qcolor" style="width:70px;height:26px" prompt="颜色" panelHeight="100"; editable="false">
						<option value=""></option>
						<option value="白 " <c:if test="${qcolor=='白'}"> selected="true"</c:if>> 白 </option>
						<option value="黑" <c:if test="${qcolor=='黑'}">selected="true"</c:if>> 黑</option>
						<option value="红" <c:if test="${qcolor=='红'}">selected="true"</c:if>> 红</option>
						<option value="绿" <c:if test="${qcolor=='绿'}">selected="true"</c:if>> 绿</option>
					</select>
					<input class="easyui-textbox" type="text" id="qsupplierName" prompt="供应商" value="${qsupplierName}" data-options="height:26,width:90">
				</div>
				<div style=" float: left;">
					<input class="easyui-linkbutton" type="button" onclick="showExpert()"
						 style="margin-left: 10px;" value="高级">
					<input class="easyui-linkbutton"
						type="button" id="query" style="margin-left: 15px;"
						onclick="query()" value="查询">
					<a class="easyui-linkbutton" style="margin-left: 15px;"
						href="materialgetexport.action" >&nbsp;导出&nbsp;</a>
					<input class="easyui-linkbutton"
						type="button" id="query" style="margin-left: 10px;"
						onclick="query()" value="创建收藏夹">
				</div>
			</div>
			<div id="expertQuery" style=" display: none; width: 100%;float: left;margin-bottom: 10px;">
				<input class="easyui-textbox" type="text" id="qminMtlPrice" prompt="单价" value="${qminMtlPrice}" data-options="height:26,width:60">
				<span>-</span>
				<input class="easyui-textbox" type="text" id="qmaxMtlPrice" prompt="单价" value="${qmaxMtlPrice}" data-options="height:26,width:60" >
				<input class="easyui-textbox" type="text" id="qminWeigth" prompt="克重" value="${qminWeigth}" data-options="height:26,width:60" >
				<span>-</span>
				<input class="easyui-textbox" type="text" id="qmaxWeigth" prompt="克重" value="${qmaxWeigth}" data-options="height:26,width:60" >
				<input class="easyui-textbox" type="text" id="qminPrdCycle" prompt="周期" value="${qminPrdCycle}" data-options="height:26,width:60" >
				<span>-</span>
				<input class="easyui-textbox" type="text" id="qmaxPrdCycle" prompt="周期" value="${qmaxPrdCycle}" data-options="height:26,width:60">
				<input class="easyui-textbox" type="text" id="qminOrder" prompt="起订量" value="${qminOrder}" data-options="height:26,width:80">
				<span>-</span>
				<input class="easyui-textbox" type="text" id="qmaxOrder" prompt="起订量" value="${qmaxOrder}" data-options="height:26,width:80">
				<input  class="easyui-datetimebox"  id="qscreateDt" type="text" editable="false" value="${qscreateDt}" data-options="height:24,width:120"" prompt="创建时间"/>
				<span>-</span>
				<input  class="easyui-datetimebox"  id="qecreateDt" type="text" editable="false" value="${qecreateDt}" data-options="height:24,width:120"" prompt="创建时间"/>
			</div>
		</div>
		<div style="width:100%; float: left;" id = "materialShow">
			<table class="easyui-datagrid" style="width=100%;" singleSelect="true">
				<thead>
					<tr>
						<th data-options="field:'code'" width="">序号</th>
						<th data-options="field:'photo'" width="100" formatter="photoFieldFmtr">照片</th>
						<th data-options="field:'mtlId '">面料编码</th>
						<th data-options="field:'mtlName'">面料名称</th>
						<th data-options="field:'mtlType'">类型</th>
						<th data-options="field:'mtlPrice'">单价</th>
						<th data-options="field:'supplierCode'" formatter="supplierFieldFmtr">供应商</th>
						<th data-options="field:'colorCount'">颜色数量</th>
						<th data-options="field:'width'">幅宽</th>
						<th data-options="field:'weigth'">克重</th>
						<th data-options="field:'amount'">库存</th>
						<th data-options="field:'minOrder'">起订量</th>
						<th data-options="field:'prdCycle'">生产周期</th>
						<th data-options="field:'relateProducts'"  formatter="productFieldFmtr">关联产品</th>
						<th data-options="field:'createDt'">建档时间</th>
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
						<c:forEach items="${ebmaterialList}" var="ebm" varStatus="i">
							<tr>
								<td>${i.index+1}</td>
								
								<td>${ebm.imgUrl}</td>
								
								<td>
									<c:out value="${ebm.mtlCode}" />
								</td>
								
								<td>
									<c:out value="${ebm.mtlName}" />
								</td>
								
								<td>
									<c:out value="${ebm.mtlTypeName}" />
								</td>
								
								<td>
									<c:out value="${ebm.mtlPrice}" />
								</td>
								
								<td>${ebm.suna }</td>
								
								<td>
									<c:out value="${ebm.colorCount}" />
								</td>
								
								<td>
									<c:out value="${ebm.width}" />
								</td>
								
								<td>
									<c:out value="${ebm.weigth}" />
								</td>
								
								<td>
									<c:out value="${ebm.amount}" />
								</td>
								
								<td>
									<c:out value="${ebm.minOrder}" />
								</td>
								
								<td>
									<c:out value="${ebm.prdCycle}" />
								</td>
								
								<td>${ebm.relProduct }</td>
								
								<td>
									<fmt:setLocale value="zh_cn" /> 
									<fmt:formatDate value="${ebm.createDt}" type="date" dateStyle="default" />
								</td>
								
								<td>
									<a style="text-decoration: underline;" onclick="">收藏</a><b>|</b>
									<a onclick="addPanelExists('materialgetMateialDetail?mtlId=${ebm.mtlId}','面料详细信息')" style="text-decoration: underline;">详情</a><b>|</b>
									<a onclick="addPanelExists('materialmergeMaterial?mtlId=${ebm.mtlId}','编辑面料信息')" style="text-decoration: underline;">编辑</a><b>|</b>
									<a onclick="javascript:return sureDelMaterial('${ebm.mtlName}','${ebm.mtlId}')" style="text-decoration: underline;">删除</a>
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
					onclick="if(${offset-1}>=0){turnPage(${offset-1})}">&lt; 上一页</a> <a
					onclick="if(${offset+1}!=${totalpage}){turnPage(${offset+1})}">下一页 &gt;</a> <a
					onclick="turnPage(${totalpage-1})">最后一页 &gt;&gt;</a>
			</div>
		</div>
	</div>

</body>
</html>
