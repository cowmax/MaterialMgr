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
	$(function(){
		$("#mtlType").combobox({
	    	onSelect:function checkRoleName(params) {
	    		var mtlType=$("#mtlType").combobox("getValue");
	    		$.ajax({
					type : 'POST',
					url : 'materialgetMtlCode.action',
					data : {
						'mtlTypeId':mtlType
					},
					dataType : 'json',
					success : function(data) {
						$("#materialCode").textbox('setValue',data.selfMtlCode);
					}
				});
	    	}
     	});
     	
     	$('#uid').textbox({
		  onChange: function(value){
		    var v = parseFloat(value);
		    if (!isNaN(v)){
			    while(v > 1) v = v/10;
			    while(v < 10) v = v*10;
			    
			    v = Math.round(v)/100;
			    
			    $('#uid').textbox('initValue', v); // 注意：不能使用 setValue，以免循环触发 onChange 事件
		    }
		  }
		});
		
		$('#uid2').textbox({
		  onChange: function(value){
		    var v = parseFloat(value);
		    if (!isNaN(v)){
			    while(v > 1) v = v/10;
			    while(v < 10) v = v*10;
			    
			    v = Math.round(v)/100;
			    
			    $('#uid2').textbox('initValue', v); // 注意：不能使用 setValue，以免循环触发 onChange 事件
		    }
		  }
		});

     	$("#uidUnit").combobox({
	    	onSelect:function checkRoleName(params) {
	    		var uidUnit=$("#uidUnit").combobox("getValue").trim();
	    		$("#euidUnit").html("");
	    		if(uidUnit=="横"){
	    			$("#euidUnit").html("纵");
	    		}else{
	    			$("#euidUnit").html("纬");
	    		}
	    	}
     	});
     	
     	$("#mtlUnit").combobox({
	    	onSelect:function checkMtlUnit(params) {
	    		var mtlUnit=$("#mtlUnit").combobox("getValue").trim();
	    		$("#emtlUnit").html("");
	    		if(mtlUnit=="公斤"){
	    			$("#emtlUnit").html("元/公斤");
	    			
	    			var mtlNtxPrice = $("#mtlNtxPrice").val();
					var mtlPrice = $("#mtlPrice").val();
					var weigth = $("#weigth").val();
					var width = $("#width").val();
					
					if(weigth!=""&&width!=""){
						if(mtlNtxPrice!=""){
							var entxPrice = mtlNtxPrice*1000/(weigth*width);
							$("#emtlNtxPrice").textbox("setValue",entxPrice);
						}else if(mtlPrice!=""){
							var eprice = mtlPrice*1000/(weigth*width);
							$("#emtlPrice").textbox("setValue",eprice);
						}
					}
	    		}else {
	    			if(mtlUnit=="米"){
		    			$("#emtlUnit").html("元/米");
		    		}else if(mtlUnit=="码"){
		    			$("#emtlUnit").html("元/码");
		    		}else{
		    			$("#emtlUnit").html("元/英尺");
		    		}
		    		
		    		$("#emtlNtxPrice").textbox("setValue","");
		    		$("#emtlPrice").textbox("setValue","");
	    		}
	    	}
     	});
     	
     	$("#ingredientTypeCmb").combobox({
	    	onSelect:function checkRoleName(params) {
	    		var json = '${ingTypeJson}';
				data = $.parseJSON(json);
				$("#ingredientTypeCmb").combobox("loadData",data);
	    	}
     	});
     	
	})
	
	//初始化类型集合
	window.onload=loadType;
	function loadType(){
		var json = '${typesJson}';
		data = $.parseJSON(json);
		
		$("#mtlType").combobox("loadData",data);
	}
	
	/**
	 *打开品牌选择窗口
	 */
	function showBrands() {
		var mtlBrands = $("#mtlBrand").val();
		var brandArr=new Array();
		brandArr =mtlBrands.split(",");
		var data_json = new Array();
		
		$.ajax({
			type : 'POST',
			url : 'brandgetAllBrands?mtlId=${newMaterial.mtlId}',
			dataType : 'json',
			success : function(data) {
				$('#brandsList').datagrid({"onLoadSuccess":function(source){
				    for(var brand in data){
						var rowdata={
							'brand':data[brand].brand,
							'brandName':data[brand].brandName,
							'checked':data[brand].choose
						};
						if(data[brand].choose){
							$('#brandsList').datagrid('selectRow',brand);
						}
						
						data_json.push(rowdata);
					}
				}}).datagrid('loadData',data_json);
				$('#brandsList').datagrid('loadData',data_json);
			}
		});
		$("#chooseBrand").window('open');
	}
		
	/**
	 *打开添加供应方信息窗口
	 */
	function showEditSupplier(){  
		$('#editSupplier').window({left:"200px", top:"100px"}); 
		$("#editSupplier").window('open');
	}
	
	/**
	 *打开添加测试报告窗口
	 */
	function showTestReport(){  
		$("#addTestReport").window('open');
	}
	
	/**
	 *打开季节选择窗口
	 */
	function showSeasonList(){  
		$("#chooseSeason").window('open');
	}
	
	/**
	 *打开面料类型列表窗口
	 */
	function showTypeList(){  
		$('#getMaterialTypeList').window({left:"200px", top:"100px"}); 
		$("#getMaterialTypeList").window('open');
	}
	
	/**
	 * 打开相关产品列表窗口
	 */
	function showRelateProducts(){
		$("#getRelateProductList").window('open');
	}
	
	/**
	 * 打开相关产品列表窗口
	 */
	function showProductList(){
		$("#productList").window('open');
	}
	
	/**
	 * 显示修改供应商页面
	 */
	function showMergerSupplier(mtsId){
		$("#mergerSupplier").window('open');
		var url = "suppliereditSupplierOpt?qmtsId="+mtsId ;
		$('#mergerSupplier').window('refresh',url);
		
	}
	
	/**
	 * 修改供应商信息
	 */
	function mergerSupplier(mtlId){
		$.ajax({
			url : 'suppliermergerSupplier.action',
			type : "POST",
			data : $("#supplierSavefrorm").serialize(),
			dataType : 'json',
			success : function(data) {
				if(data){
					$("#mergerSupplier").window('close');
					var trgUrl = 'supplierloadAllSupperList.action?mtlIdCode='+mtlId;
					$('#editSupplier').window('refresh', trgUrl); 
				}
			}
		})

	}
	
	/**
	 *添加品牌信息
	 */
	function addBrands(){
		var mtlBrandInp = $("#mtlBrand").val();
		var rows = $("#brandsList").datagrid("getSelections"); // 获取所有选中的行
		var beanList = [];

		for (var i = 0; rows && i < rows.length; i++) {
			var bean = {};
			var row = rows[i];
			var index = $("#brandsList").datagrid("getRowIndex", row); // 获取该行的索引
			bean.brand = row.brand;
			bean.brandName = row.brandName;
			beanList.push(bean);
		}
		var param = JSON.stringify(beanList);
		$.ajax({
			type : 'POST',
			url : 'brandsaveBrand.action',
			data : {
				'param':param
			},
			dataType : 'json',
			success : function(data) {
			    $("#chooseBrand").window('close');
				$("#mtlBrand").textbox('setValue',data.brandValue);//赋值
			}
		});
	}
	
	/**
	 * 添加测试报告
	 */
	function addTestReport(){
		$.ajax({
			url : 'treportaddTestReport.action',
			type : "POST",
			data : $("#testReportSaveform").serialize(),
			dataType : 'json',
			success : function(data) {
				if(data){
					$.messager.show({
						msg : '<div style="width:100%"><div style="line-height:50px;text-align:center;">添加测试报告信息成功！</div></div>',
						timeout : 800,
						showSpeed : 200,
						showType : 'show',
						style : {
							right : '',
							top : 200,
							bottom : ''
						}
					});
					$("#addTestReport").window('close');
				}
			}
		})
	}

	/**
	 * 保存照片信息
	 */
	function savaphotoImage(){
		if (!$("#saveformImage").form('validate')){
			$.messager.show({
				msg : '<div style="width:100%"><div style="line-height:50px;text-align:center;">请正确输入！</div></div>',
				timeout : 800,
				showSpeed : 200,
				showType : 'show',
				style : {
					right : '',
					top : '-200',
					bottom : ''
				}
			});
			
		}else{
		 	var mtlId=$("#photoMtlId").val();
		 	
			$.ajax({
				url : 'imgsaveImage.action',
				type : "POST",
				data : $("#saveformImage").serialize(),
				dataType : 'json',
				success : function(data) {
					// Clear all images
					var imgList = document.getElementById("imageList");
					while(imgList.hasChildNodes()){
						imgList.removeChild(imgList.firstChild);
					}
							
							//显示照片信息
					// Clear all images
				var imgList = document.getElementById("imageList");
				while(imgList.hasChildNodes()){
					imgList.removeChild(imgList.firstChild);
				}
				// Add images
				jQuery.each(data.jsonList, function(i,item){
					var div = document.createElement("div");
						div.setAttribute("id","imageShow");
						div.className = "photoDiv";
				
						//添加图片
						var divIamge = document.createElement("div");
						var oImg = document.createElement("img");
				
						oImg.src = item.imgUrl;
						divIamge.setAttribute("id","imageDiv");
						divIamge.appendChild(oImg);
				
				
						//添加文字显示及删除
						var divLink = document.createElement("div");
						divLink.setAttribute("id","linkDiv");
				
						//颜色显示
						var colorName = document.createElement("span");
						colorName.innerText = item.imgColor;
						colorName.className = "colorSpan";
				
						//删除操作
						var delOpr = document.createElement("a");
						delOpr.text = "删除";
						delOpr.onclick= function()
						{ 
							sureDelImage(item.imgId,item.mtlId); 
						};
						divLink.appendChild(colorName);
						divLink.appendChild(delOpr);
				
						div.appendChild(divIamge);
						div.appendChild(divLink);
				
						$("#imageList").append(div);
					}) 
					$('#imageWin').window('close');
				}
			});
		}
	}
	
	/**
	 * 删除照片信息
	 */
	function sureDelImage(imgId,mtlId){
		var msg = "确认删除照片信息吗？";
		if (confirm(msg) == true) {
			$.ajax({
				type:'POST',
				url:'imgdelImage',
				data:{
					'imgId':imgId,
					'mtlId':mtlId
				},
				dataType : 'json',
				success : function(data){
					if(data.flag){
						$.messager.show({
							msg : '<div style="width:100%"><div style="line-height:50px;text-align:center;">删除照片信息成功！</div></div>',
							timeout : 800,
							showSpeed : 200,
							showType : 'show',
							style : {
								right : '',
								top : '',
								bottom : ''
							}
						});
					}
					// Clear all images
					var imgList = document.getElementById("imageList");
					while (imgList.hasChildNodes()) {
						imgList.removeChild(imgList.firstChild);
					}
	
					// Add images
					jQuery.each(data.jsonList, function(i,item){   
					    
						var div = document.createElement("div");
						div.setAttribute("id","imageShow");
						div.className = "photoDiv";
				
						//添加图片
						var divIamge = document.createElement("div");
						var oImg = document.createElement("img");
				
						oImg.src = item.imgUrl;
						divIamge.setAttribute("id","imageDiv");
						divIamge.appendChild(oImg);
				
				
						//添加文字显示及删除
						var divLink = document.createElement("div");
						divLink.setAttribute("id","linkDiv");
				
						//颜色显示
						var colorName = document.createElement("span");
						colorName.innerText = item.imgColor;
						colorName.className = "colorSpan";
				
						//删除操作
						var delOpr = document.createElement("a");
						delOpr.text = "删除";
						delOpr.onclick= function()
						{ 
							sureDelImage(item.imgId); 
						};
						divLink.appendChild(colorName);
						divLink.appendChild(delOpr);
				
						div.appendChild(divIamge);
						div.appendChild(divLink);
				
						$("#imageList").append(div);
				    });   
							
					$('#imageWin').window('close');
				}
			});
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 根据选择获取季节显示
	 */
	function getSeasons(){
		var rows = $("#seasonList").datagrid("getSelections"); // 获取所有选中的行
		var season="";
		for (var i = 0; rows && i < rows.length; i++) {
			var row = rows[i];
			var index = $("#seasonList").datagrid("getRowIndex", row); // 获取该行的索引
			if(i==0){
				season += row.season;
			}else{
				season += ","+row.season;
			}
		}
		$("#seasons").textbox('setValue', season);
		$("#chooseSeason").window('close');
	}
	
	/**
	 * mtlNtxPrice改变
	 */
	function mtlNtxPriceChange(){
		var mtlUnit=$("#mtlUnit").combobox("getValue").trim();
		if(mtlUnit=="公斤"){
			var mtlNtxPrice = $("#mtlNtxPrice").val();
			var weigth = $("#weigth").val();
			var width = $("#width").val();
			
			if(mtlNtxPrice!=""&&weigth!=""&&width!=""){
				var eprice = mtlNtxPrice*1000/(weigth*width);
				
				if(!isNaN(eprice)){
					$("#emtlNtxPrice").textbox("setValue",eprice.toFixed(2));
				}else{
					$("#emtlNtxPrice").textbox("setValue","");
				}
			}
		}else{
			$("#emtlNtxPrice").textbox("setValue","");
		}
	}
	
	/**
	 * mtlPrice改变
	 */
	function mtlPriceChange(){
		var mtlUnit=$("#mtlUnit").combobox("getValue").trim();
		if(mtlUnit=="公斤"){
			var mtlPrice = $("#mtlPrice").val();
			var weigth = $("#weigth").val();
			var width = $("#width").val();
			
			if(mtlPrice!=""&&weigth!=""&&width!=""){
				var eprice = mtlPrice*1000/(weigth*width);
				if(!isNaN(eprice)){
					$("#emtlPrice").textbox("setValue",eprice.toFixed(2));
				}else{
					$("#emtlPrice").textbox("setValue","");
				}
			}
		}else{
			$("#emtlPrice").textbox("setValue","");
		}
	}
	
	/**
	 * weigth改变
	 */
	function weigthChange(){
		var mtlUnit=$("#mtlUnit").combobox("getValue").trim();
		if(mtlUnit=="公斤"){
			var mtlNtxPrice = $("#mtlNtxPrice").val();
			var mtlPrice = $("#mtlPrice").val();
			var weigth = $("#weigth").val();
			var width = $("#width").val();
			
			if(weigth!=""&&width!=""){
				if(mtlNtxPrice!=""){
					var entxPrice = mtlNtxPrice*1000/(weigth*width);
					
					if(!isNaN(entxPrice)){
						$("#emtlNtxPrice").textbox("setValue",entxPrice.toFixed(2));
					}else{
						$("#emtlNtxPrice").textbox("setValue","");
					}
				}
				if(mtlPrice!=""){
					var eprice = mtlPrice*1000/(weigth*width);
					if(!isNaN(eprice)){
						$("#emtlPrice").textbox("setValue",eprice.toFixed(2));
					}else{
						$("#emtlPrice").textbox("setValue","");
					}
					
				}
			}
		}else{
			$("#emtlNtxPrice").textbox("setValue","");
			$("#emtlPrice").textbox("setValue","");
		}
	}
	
	/**
	 * width改变
	 */
	function widthChange(){
		var mtlUnit=$("#mtlUnit").combobox("getValue").trim();
		if(mtlUnit=="公斤"){
			var mtlNtxPrice = $("#mtlNtxPrice").val();
			var mtlPrice = $("#mtlPrice").val();
			var weigth = $("#weigth").val();
			var width = $("#width").val();
			
			if(weigth!=""&&width!=""){
				if(mtlNtxPrice!=""){
					var entxPrice = mtlNtxPrice*1000/(weigth*width);
					if(!isNaN(entxPrice)){
						$("#emtlNtxPrice").textbox("setValue",entxPrice.toFixed(2));
					}else{
						$("#emtlNtxPrice").textbox("setValue","");
					}
					
				}
				if(mtlPrice!=""){
					var eprice = mtlPrice*1000/(weigth*width);
					
					if(!isNaN(eprice)){
						$("#emtlPrice").textbox("setValue",eprice.toFixed(2));
					}else{
						$("#emtlPrice").textbox("setValue","");
					}
				}
			}
		}else{
			$("#emtlNtxPrice").textbox("setValue","");
			$("#emtlPrice").textbox("setValue","");
		}
	}
	
</script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<body>
	<div style="display: none;">
		<div align="center"  class="easyui-window" title="季节列表" collapsible="false" minimizable="false" maximizable="false" closed="true"  modal="true"
			style="width:300px; padding: 20px 20px; float: left;" id="chooseSeason">
			<table class="easyui-datagrid" style="width:100%" id = "seasonList" data-options="singleSelect:false,url:'seasonsData.json'">
				<thead>
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
						<th data-options="field:'code',width:80">序号</th>
						<th data-options="field:'season',width:100">季节</th>
					</tr>
				</thead>
			</table>
			
			<div style="margin-top: 10px;">
				<a onclick="getSeasons()" class="easyui-linkbutton" style="margin-left: 10px;padding: 0px 3px;">保存</a>
				<a onclick="closeWin('#chooseBrand')" class="easyui-linkbutton" style="margin-left: 10px;padding: 0px 3px;">返回</a>
			</div>
		</div>
		<!-- 添加供方信息  -->
		<div id="editSupplier" class="easyui-window" title="添加面料供方信息"
			collapsible="false" minimizable="false" maximizable="false" closed="true"  modal="true"
			style="width:660px; padding: 10px 30px;" href="supplierloadAllSupperList.action?mtlIdCode=${newMaterial.mtlId}"> 
		</div>  
		<!-- 测试报告  -->
		<div id="addTestReport" class="easyui-window" title="测试报告"
			collapsible="false" minimizable="false" maximizable="false" closed="true"  modal="true"
			 style="width:500px;height:300px; padding: 10px;" href="treportaddTestReportOpt.action?mtlId=${newMaterial.mtlId}"> 
		</div> 
		<!-- 添加面料类型  -->
		<div id="getMaterialTypeList" class="easyui-window" title="面料类型"
			collapsible="false" minimizable="false" maximizable="false" closed="true"  modal="true"
			 style="width:500px; padding: 20px 20px 20px 20px;" href="materialgetMtlTypeList"> 
		</div> 
		<!-- 添加面料品牌  -->
		<div id="chooseBrand" class="easyui-window" title="适用品牌"
			collapsible="false" minimizable="false" maximizable="false" closed="true"  modal="true"
			 style="width:400px; padding: 20px 20px 20px 20px;" href="brandaddBrandOperation?mtlId=${newMaterial.mtlId}"> 
		</div> 
		<!-- 添加相关产品  -->
		<div id="getRelateProductList" class="easyui-window" title="相关产品"
			collapsible="false" minimizable="false" maximizable="false" closed="true"  modal="true"
			 style="width:580px; padding: 20px 20px 20px 20px;" href="relpdugetRelateProductS?mtlId=${newMaterial.mtlId}"> 
		</div> 
		<!-- 添加照片 -->
		<div id="imageWin" class="easyui-window" title="选择照片" collapsible="false" minimizable="false" maximizable="false" closed="true" modal="true"
			 style="width:1000px;height:520px; padding: 10px;" href="itypeallImageType?mtlId=${newMaterial.mtlId}"> 
		</div>  
		
		<!-- 添加成分 -->
		<div id="ingredientWin" class="easyui-window" title="添加成分" collapsible="false" minimizable="false" maximizable="false" closed="true" modal="true"
			 style="width:500px; padding: 10px;" href="ingrshowIngredient?mtlId=${newMaterial.mtlId}"> 
		</div> 
	
	</div>
	<div class="easyui-panel" align="center">
		<form action="materialaddMaterial.action" id="saveMaterialForm" method="post">
			<div style="width: 800px; float: left;" id="content">
				<div>
					<h3 class="tab-subtitle">录入面料档案</h3>
				</div>
				<div id="content_one" style="width: 50%;float: left;">
					<table class="form-table">
						<tr>
							<td>面料编码</td>
							<td>
								<input class="easyui-textbox" id="materialCode" name="newMaterial.mtlCode" value="" style="height: 26px;" disabled="disabled"  />
							</td>
						</tr>
						<tr>
							<td>面料名称</td>
							<td>
								<input class="easyui-textbox" id="mtlName" name="newMaterial.mtlName" value="" style="height: 26px;"/> 
							</td>
						</tr>
						<tr>
							<td>面料类型</td>
							<td>
								<div style="width: 148px;">
									<select name="newMaterial.BMaterialType.mtlType" class="easyui-combobox" id="mtlType" style="width:115px;height:26px" prompt="子类目";
											data-options="valueField:'value', textField:'text'"; editable="false";>
									</select>
									<input  type="button" id="query" style="border-color: #95B8E7; color: #95B8E7;width:26px;height:26px" onclick="showTypeList()" value="+">
								</div>
							</td>
						</tr>
						<tr>
							<td>不含税价</td>
							<td>
								<div style="width: 148px;">
									<input class="easyui-textbox" id="mtlNtxPrice" name="newMaterial.mtlNtxPrice" value="" style="width: 60px; height: 26px;" 
									data-options="onChange:function(){mtlNtxPriceChange()}, required:false,validType:['#validateNum'],missingMessage:'请输入不含税价'"/> 
									<select id="mtlUnit" name="newMaterial.mtlUnit" class="easyui-combobox" style="width:80px;height:26px" panelHeight="100"; editable="false">
										<option value="公斤">元/公斤</option>
										<option value="米">元/米</option>
										<option value="码">元/码</option>
										<option value="英尺 ">元/英尺</option>
									</select> 
								</div>
							</td>
						</tr>
						<tr>
							<td></td>
							<td>
								<input class="easyui-textbox" value="" id="emtlNtxPrice" style="width: 110px; height: 26px;"prompt="单价/(幅宽x克重)" disabled="disabled" /> <span>元/米</span>
							</td>
						</tr>
						<tr>
							<td>缩水率</td>
							<td>
								<div style="width: 200px;">
									<select id="uidUnit" class="easyui-combobox" style="width:56px;height:26px;margin-right:5px; " panelHeight="100"; editable="false">
										<option value="横 ">横 </option>
										<option value="经">经</option>
									</select> 
									<input class="easyui-textbox" id="uid" name="newMaterial.shrinkW" value="" style="width: 48px; height: 26px;" data-options="validType:['#shrink'],missingMessage:'请输入横向收缩率'"/>
									<span id="euidUnit">纵</span>
									<input class="easyui-textbox" id="uid2" name="newMaterial.shrinkH" value="" style="width: 48px; height: 26px;" data-options="validType:['#shrink'],missingMessage:'请输入纵向收缩率'"/>
								</div>
							</td>
						</tr>
						<tr>
							<td>库存数量</td>
							<td>
								<input class="easyui-textbox" name="newMaterial.amount" value="" style="height: 26px;" data-options=" validType:['#validateNum'],missingMessage:'请输入库存数量'"/> 
								<span style="color: red;" id="msg"></span>
							</td>
						</tr>
						<tr>
							<td>生产周期</td>
							<td>
								<input class="easyui-textbox" name="newMaterial.prdCycle" value="" style="height: 26px;" data-options="validType:['#validateNum'],missingMessage:'请输入生产周期'"/> 
								<span style="color: red;" id="msg"></span>
							</td>
						</tr>
						<tr>
							<td>最小订单量</td>
							<td>
								<input class="easyui-textbox" name="newMaterial.minOrder" value="" style="height: 26px;" data-options="validType:['#validateNum'],missingMessage:'请输入最小订单量'"/> 
								<span style="color: red;" id="msg"></span>
							</td>
						</tr>
						<tr>
							<td>供方信息</td>
							<td>
								<a onclick="showEditSupplier()" class="easyui-linkbutton" style="padding: 0px 3px;">编辑</a>
							</td>
						</tr>
						<tr>
							<td>面料照片</td>
							<td style="width: 180px;">
								<a onclick="addImage()" class="easyui-linkbutton" id="addPhoto" style="padding: 0px 3px;">添加</a>
							</td>
						</tr>
					</table>
				</div>
				<div id="content_two" style="width: 50%;float: left;">
					<table class="form-table">
						<tr>
							<td>颜色数</td>
							<td>
								<input class="easyui-textbox" name="newMaterial.colorCount" value="" style="height: 26px;" data-options="validType:['#validateNum'],missingMessage:'请输入颜色数量'" /> 
							</td>
						</tr>
						<tr>
							<td>适用季节</td>
							<td>
								<div style="width: 148px;">
									<input class="easyui-textbox" id="seasons" name="newMaterial.season" value="" style="width:115px;height: 26px;" disabled="disabled"/> 
									<input  type="button" id="seasonBtn" style="border-color: #95B8E7; color: #95B8E7;width:26px;height:26px;"  onclick="showSeasonList()" value="+">
								</div>
							</td>
						</tr>
						<tr>
							<td>适用品牌</td>
							<td>
								<div style="width: 148px;">
									<input class="easyui-textbox" id="mtlBrand"  value="" style="width:115px;height: 26px;" disabled="disabled"/> 
									<input  type="button" id="brands" style="border-color: #95B8E7; color: #95B8E7;width:26px;height:26px;"  onclick="showBrands()" value="+">
								</div>
							</td>
						</tr>
						<tr>
							<td>含税单价</td>
							<td>
								<div style="width: 148px;">
									<input class="easyui-textbox" id="mtlPrice" name="newMaterial.mtlPrice" value="" style="width: 60px; height: 26px;" 
									data-options="onChange:function(){mtlPriceChange()}, required:false,validType:['#validatePrice'],missingMessage:'请输入含税单价'"/> 
									<span id="emtlUnit">元/公斤</span>
								</div>
							</td>
						</tr>
						<tr>
							<td></td>
							
							<td>
								<input class="easyui-textbox" value="" id="emtlPrice" style="width: 110px; height: 26px;"prompt="单价/(幅宽x克重)"  disabled="disabled"/> <span>元/米</span>
							</td>
						</tr>
						<tr>
							<td>克重</td>
							<td>
								<input class="easyui-textbox" id="weigth" name="newMaterial.weigth" value="" style="width:110px;height: 26px;" data-options="onChange:function(){weigthChange()},validType:['#validateNum'],missingMessage:'请输入克重'" /> 
								<span>克/CM²</span>
							</td>
						</tr>
						<tr>
							<td>幅宽</td>
							<td>
								<input class="easyui-textbox" id="width" name="newMaterial.width" value="" style="width:110px;height: 26px;" data-options="onChange:function(){widthChange()},validType:['#validateNum'],missingMessage:'请输入幅宽'" /> 
								<span>厘米</span>
							</td>
						</tr>
						
						<tr>
							<td>成分</td>
							<td>
								<div style="width: 148px;">
									<input class="easyui-textbox" id="ingredients" value="" style="width:115px;height: 26px;" disabled="disabled"/> 
									<input type="button" id="query"  style="border-color: #95B8E7; color: #95B8E7; width:26px;height:26px;" onclick="addIngredient()" value="+">
								</div>
							</td>
						</tr>
						
						<tr>
							<td>关联产品</td>
							<td>
								<div style="width: 148px;">
									<input class="easyui-textbox" id="relateProducts" value="" style="width:115px; height: 26px;" disabled="disabled"/> 
									<input type="button" id="query" style="border-color: #95B8E7; color: #95B8E7; width:26px;height:26px; " onclick="showRelateProducts()" value="+">
								</div>
							</td>
						</tr>
						
						<tr>
							<td>测试报告</td>
							<td>
								<a onclick="showTestReport()" class="easyui-linkbutton" style="padding: 0px 3px;">编辑</a>
							</td>
						</tr>
						<tr>
							<td>风险自评</td>
							<td>
								<input class="easyui-textbox" id="riskSelfAssessment" name="newMaterial.riskSelfAssessment" value="" style="height: 26px;" />
							</td>
						</tr>
					</table>
				</div>
				<div id="content_photo" style="width: 100%;">
					<table class="form-table" style="padding-top:0px;">
						<tr>
							<td></td>
							<td colspan="3" >
								<div style="height: 100px; width:546px; border: 1px solid #95B8E7;" id="imageList"></div>
							</td>
						</tr>
					</table>
					<a  class="easyui-linkbutton" onclick="javascript:addMaterial()" style="margin-left: 10px;margin-bottom:10px; padding: 0px 3px;">保存</a>
					<a  class="easyui-linkbutton" onclick="removeCurrentPanel();" style="margin-left: 10px;margin-bottom:10px; padding: 0px 3px;">返回</a>
				</div>
			</div>
		</form>
	</div>
</body>
</html>