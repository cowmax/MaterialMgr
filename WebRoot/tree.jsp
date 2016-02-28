<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'tree.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" href="js/easyui/demo/demo.css" type="text/css"></link>
<link rel="stylesheet" href="js/easyui/themes/icon.css" type="text/css"></link>
<link rel="stylesheet" href="js/easyui/themes/default/easyui.css" type="text/css"></link>
<script type="text/javascript" src="js/easyui/jquery-1.6.min.js"></script>
<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="script/common.js"></script>

<link href="css/style.css" rel="stylesheet" type="text/css">

<style type="text/css">
* {
	margin: 0px;
	padding: 0px;
}

</style>
<script type="text/javascript">

</script>
</head>
  
  <body>
   <div class="easyui-accordion" fit="true" border="false">
			<!-- selected -->
			<div title="档案管理" selected="true">
				<ul id="tt" class="easyui-tree">
					<li><span>面料管理</span>
						<ul>
							<li>
								<span><a id="selectMaterialInfo" onclick="addPanel('materialgetMaterialList','查询面料档案')">查询面料档案</a></span>
								<ul>
									<li>
										<span><a onclick="addPanel('materialaddMtlOperation','新增面料档案') ">新增面料档案</a> </span>
									</li>
								</ul>
							</li>
							<li>
								<span>面料收藏</span>
								<ul>
									<li>
										<span><a onclick="addPanel('creatFavor.jsp','新建收藏夹') ">新建收藏夹</a> </span>
									</li>
									<li>
										<span><a onclick="addPanel('addMaterialFavor.jsp','编辑收藏夹') ">编辑收藏夹</a> </span>
									</li>
									<li>
										<span><a onclick="addPanel('addMaterialFavor.jsp','添加到收藏项') ">添加到收藏项</a> </span>
									</li>
								</ul>
							</li>
							<li>
								<span><a id="selectMaterialInfo" onclick="addPanel('suingetAllSuins','查询供应商信息')">查询供应商信息</a></span>
								<ul>
									<li>
										<span><a onclick="addPanel('addSuin.jsp','新增供应商信息') ">新增供应商信息</a> </span>
									</li>
								</ul>
							</li>
						</ul>
						
					</li>
				</ul>
			</div>
		</div>
</body>
</html>
