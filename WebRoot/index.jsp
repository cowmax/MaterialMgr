<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <style type="text/css">
        body { background:#f1f1f1; margin:0 }
        #workDemo { width:100%; }
        #workDemo a { width: 150px; height:90px; margin:5px; padding:0; border:gray 5px solid; float:left; overflow:hidden; text-decoration:none; display:block; position:relative }
        #workDemo a span { display:none; }
        #workDemo a img { border:none; }
        #workDemo a:hover { border-color:red; }
        #workDemo a:hover span { position:absolute; background:red; color:white; width:100%; padding-top:3px; text-align:center;  right:0; bottom:0; display:block; font-size:11px; filter: Alpha(Opacity=60);Opacity:0.6; }
    </style>
    <script type="text/javascript">
    
    </script>
</head>
<body>
	<div id="workDemo">
		<a href="#"><img src="images/1-s.jpg" /><span>把浏览器窗口放小后点击</span></a>
		<a href="#"><img src="images/1-s.jpg" /><span>把浏览器窗口放小后点击</span></a>
	</div>
</body></html>
