<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>分类列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/adminjsps/admin/css/category/list.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/css.css'/>">
  </head>
  
  <body>
    <h2 style="text-align: center;">分类列表</h2>
    <table align="center" border="1" cellpadding="0" cellspacing="0">
    	<caption class="captionAddOneLevel">
    	  <a href="<c:url value='/adminjsps/admin/category/add.jsp'/>" >添加一级分类</a>
    	</caption>
    	<tr class="trTitle">
    		<th>分类名称</th>
    		<th>描述</th>
    		<th>操作</th>
    	</tr>

<%--		<jsp:useBean id="parents" scope="session" type="java.util.List"/>--%>
		<div class="items">
		<c:forEach items="${parents}" var="parent">
			<tr class="trOneLevel">
				<td width="200px;">${parent.cname }</td>
				<td>${parent.desc}</td>
				<td width="200px;">			<%--添加和修改的准备工作，将数据传过去，将页面--%>
					<a href="<c:url value='/admin/CategoryServlet?method2=addChildPre&pid=${parent.cid }'/>" >添加二级分类</a>
					<a href="<c:url value='/admin/CategoryServlet?method2=editParentPre&cid=${parent.cid }'/>">修改</a>
					<a onclick="return confirm('您是否真要删除该一级分类？')" href="<c:url value='/admin/CategoryServlet?method=deleteParent&cid=${parent.cid }'/>">删除</a>
				</td>
			</tr>
			<%--二级分类--%><%--二级只有他的删除与修改--%>
			<c:forEach items="${parent.children }" var="child">
				<tr class="trTwoLevel">
					<td>${child.cname }</td>
					<td>${child.desc }</td>
					<td width="200px;" align="right">
						<a href="<c:url value='/admin/CategoryServlet?method2=editChildPre&cid=${child.cid}'/>">修改</a>
						<a onclick="return confirm('您是否真要删除该二级分类？')" href="<c:url value='/admin/CategoryServlet?method=deleteChild&cid=${child.cid }'/>">删除</a>
					</td>
				</tr>
			</c:forEach>
		</c:forEach>
		</div>

    </table>
  </body>
</html>
