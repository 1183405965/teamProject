<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>left</title>
    <base target="body"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/menu/mymenu.js'/>"></script>
	<link rel="stylesheet" href="<c:url value='/menu/mymenu.css'/>" type="text/css" media="all">
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/left.css'/>">
<script language="javascript">
var bar = new Q6MenuBar("bar", "网上书城");
$(function() {//jquery的函数调用，$（）是jquery的函数，function是参数也是一个函数，该函数会在页面完后执行，用来做初始化
	bar.colorStyle = 4;
	bar.config.imgDir = "<c:url value='/menu/img/'/>";
	bar.config.radioButton=true;
//以下代码需要使用jstl的标签，结合一二级的菜单结构来动态生成
	bar.add("程序设计", "Java Javascript", "/jsps/book/list.jsp", "body");
	bar.add("程序设计", "JSP", "/jsps/book/list.jsp", "body");
	bar.add("程序设计", "C C++ VC VC++", "/jsps/book/list.jsp", "body");
	
	bar.add("办公室用书", "微软Office", "/jsps/book/list.jsp", "body");
	bar.add("办公室用书", "计算机初级入门", "/jsps/book/list.jsp", "body");
	
	bar.add("图形 图像 多媒体", "Photoshop", "/jsps/book/list.jsp", "body");
	bar.add("图形 图像 多媒体", "3DS MAX", "/jsps/book/list.jsp", "body");
	bar.add("图形 图像 多媒体", "网页设计", "/jsps/book/list.jsp", "body");
	bar.add("图形 图像 多媒体", "Flush", "/jsps/book/list.jsp", "body");
	
	bar.add("操作系统/系统开发", "Windows", "/jsps/book/list.jsp", "body");
	bar.add("操作系统/系统开发", "Linux", "/jsps/book/list.jsp", "body");
	bar.add("操作系统/系统开发", "系统开发", "/jsps/book/list.jsp", "body");
	<%--
        <c:forEach items="一级分类的pojo集合" var="item1">
       	 <c:forEach items="item.二级分类属性" var="item2">
       	 bar.add("item1.name", "item2.name", "list.jsp", "body");
      	  </c:forEach>
        </c:forEach> --%>
	$("#menu").html(bar.toString());
});
</script>
</head>
  
<body>  
  <div id="menu"></div>
</body>
</html>
