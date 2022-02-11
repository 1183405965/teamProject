<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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



		<tr class="trOneLevel">
			<td width="200px;">程序设计</td>
			<td>程序设计相关分类</td>
			<td width="200px;">
				<a href="<c:url value='/adminjsps/admin/category/add2.jsp'/>">添加二级分类</a>
				<a href="<c:url value='/adminjsps/admin/category/edit.jsp'/>">修改</a>
				<a onclick="return confirm('您是否真要删除该一级分类？')" href="javascript:alert('删除一级分类成功！');">删除</a>
			</td>
		</tr>

		<%--一级分类--%><%--添加二级，删除，修改--%>
		<c:forEach items="${parents }" var="parent">
			<tr class="trOneLevel">
				<td width="200px;">${parent.cname }</td>
				<td>${parent.desc }</td>
				<td width="200px;">
					<a href="<c:url value='/admin/CategoryServlet?method=addChildPre&pid=${parent.cid }'/>">添加二级分类</a>
					<a href="<c:url value='/admin/CategoryServlet?method=editParentPre&cid=${parent.cid }'/>">修改</a>
					<a onclick="return confirm('您是否真要删除该一级分类？')" href="<c:url value='/admin/CategoryServlet?method=deleteParent&cid=${parent.cid }'/>">删除</a>
				</td>
			</tr>
			<%--二级分类--%><%--二级只有他的删除与修改--%>
			<c:forEach items="${parent.children }" var="child">
				<tr class="trTwoLevel">
					<td>${child.cname }</td>
					<td>${child.desc }</td>
					<td width="200px;" align="right">
						<a href="<c:url value='/admin/CategoryServlet?method=editChildPre&cid=${child.cid }'/>">修改</a>
						<a onclick="return confirm('您是否真要删除该二级分类？')" href="<c:url value='/admin/CategoryServlet?method=deleteChild&cid=${child.cid }'/>">删除</a>
					</td>
				</tr>
			</c:forEach>
		</c:forEach>
  <%--
    	<tr class="trOneLevel">
    		<td width="200px;">程序设计</td>
    		<td>程序设计相关分类</td>
    		<td width="200px;">
    		  <a href="<c:url value='/adminjsps/admin/category/add2.jsp'/>">添加二级分类</a>
    		  <a href="<c:url value='/adminjsps/admin/category/edit.jsp'/>">修改</a>
    		  <a onclick="return confirm('您是否真要删除该一级分类？')" href="javascript:alert('删除一级分类成功！');">删除</a>
    		</td>
    	</tr>
    	<tr class="trTwoLevel">
    		<td>Java Javascript</td>
    		<td>Java Javascript相关分类</td>
    		<td width="200px;" align="right">
    		  <a href="<c:url value='/adminjsps/admin/category/edit2.jsp'/>">修改</a>
    		  <a onclick="return confirm('您是否真要删除该二级分类？')" href="javascript:alert('删除二级分类成功！');">删除</a>
    		</td>
    	</tr>
      	<tr class="trTwoLevel">
    		<td>JSP</td>
    		<td>JSP相关分类</td>
    		<td width="200px;" align="right">
    		  <a href="<c:url value='/adminjsps/admin/category/edit2.jsp'/>">修改</a>
    		  <a onclick="return confirm('您是否真要删除该二级分类？')" href="javascript:alert('删除二级分类成功！');">删除</a>
    		</td>
    	</tr>
      	<tr class="trTwoLevel">
    		<td>C C++ VC VC++</td>
    		<td>C C++ VC VC++相关分类</td>
    		<td width="200px;" align="right">
    		  <a href="<c:url value='/adminjsps/admin/category/edit2.jsp'/>">修改</a>
    		  <a onclick="return confirm('您是否真要删除该二级分类？')" href="javascript:alert('删除二级分类成功！');">删除</a>
    		</td>
    	</tr>




    	<tr class="trOneLevel">
    		<td width="200px;">办公室用书</td>
    		<td>办公室用书相关分类</td>
    		<td width="200px;">
    		  <a href="<c:url value='/adminjsps/admin/category/add2.jsp'/>">添加二级分类</a>
    		  <a href="<c:url value='/adminjsps/admin/category/edit.jsp'/>">修改</a>
    		  <a onclick="return confirm('您是否真要删除该一级分类？')" href="javascript:alert('删除一级分类成功！');">删除</a>
    		</td>
    	</tr>
    	<tr class="trTwoLevel">
    		<td>微软Office</td>
    		<td>微软Office相关分类</td>
    		<td width="200px;" align="right">
    		  <a href="<c:url value='/adminjsps/admin/category/edit2.jsp'/>">修改</a>
    		  <a onclick="return confirm('您是否真要删除该二级分类？')" href="javascript:alert('删除二级分类成功！');">删除</a>
    		</td>
    	</tr>
      	<tr class="trTwoLevel">
    		<td>计算机初级入门</td>
    		<td>计算机初级入门相关分类</td>
    		<td width="200px;" align="right">
    		  <a href="<c:url value='/adminjsps/admin/category/edit2.jsp'/>">修改</a>
    		  <a onclick="return confirm('您是否真要删除该二级分类？')" href="javascript:alert('删除二级分类成功！');">删除</a>
    		</td>
    	</tr>

    	
    	<tr class="trOneLevel">
    		<td width="200px;">图形 图像 多媒体</td>
    		<td>图形 图像 多媒体相关分类</td>
    		<td width="200px;">
    		  <a href="<c:url value='/adminjsps/admin/category/add2.jsp'/>">添加二级分类</a>
    		  <a href="<c:url value='/adminjsps/admin/category/edit.jsp'/>">修改</a>
    		  <a onclick="return confirm('您是否真要删除该一级分类？')" href="javascript:alert('删除一级分类成功！');">删除</a>
    		</td>
    	</tr>
    	<tr class="trTwoLevel">
    		<td>Photoshop</td>
    		<td>Photoshop相关分类</td>
    		<td width="200px;" align="right">
    		  <a href="<c:url value='/adminjsps/admin/category/edit2.jsp'/>">修改</a>
    		  <a onclick="return confirm('您是否真要删除该二级分类？')" href="javascript:alert('删除二级分类成功！');">删除</a>
    		</td>
    	</tr>
      	<tr class="trTwoLevel">
    		<td>3DS MAX</td>
    		<td>3DS MAX相关分类</td>
    		<td width="200px;" align="right">
    		  <a href="<c:url value='/adminjsps/admin/category/edit2.jsp'/>">修改</a>
    		  <a onclick="return confirm('您是否真要删除该二级分类？')" href="javascript:alert('删除二级分类成功！');">删除</a>
    		</td>
    	</tr>
      	<tr class="trTwoLevel">
    		<td>网页设计</td>
    		<td>网页设计相关分类</td>
    		<td width="200px;" align="right">
    		  <a href="<c:url value='/adminjsps/admin/category/edit2.jsp'/>">修改</a>
    		  <a onclick="return confirm('您是否真要删除该二级分类？')" href="javascript:alert('删除二级分类成功！');">删除</a>
    		</td>
    	</tr>
       	<tr class="trTwoLevel">
    		<td>Flush</td>
    		<td>Flush相关分类</td>
    		<td width="200px;" align="right">
    		  <a href="<c:url value='/adminjsps/admin/category/edit2.jsp'/>">修改</a>
    		  <a onclick="return confirm('您是否真要删除该二级分类？')" href="javascript:alert('删除二级分类成功！');">删除</a>
    		</td>
    	</tr>
    	
    	<tr class="trOneLevel">
    		<td width="200px;">操作系统/系统开发</td>
    		<td>操作系统/系统开发相关分类</td>
    		<td width="200px;">
    		  <a href="<c:url value='/adminjsps/admin/category/add2.jsp'/>">添加二级分类</a>
    		  <a href="<c:url value='/adminjsps/admin/category/edit.jsp'/>">修改</a>
    		  <a onclick="return confirm('您是否真要删除该一级分类？')" href="javascript:alert('删除一级分类成功！');">删除</a>
    		</td>
    	</tr>
    	<tr class="trTwoLevel">
    		<td>Windows</td>
    		<td>Windows相关分类</td>
    		<td width="200px;" align="right">
    		  <a href="<c:url value='/adminjsps/admin/category/edit2.jsp'/>">修改</a>
    		  <a onclick="return confirm('您是否真要删除该二级分类？')" href="javascript:alert('删除二级分类成功！');">删除</a>
    		</td>
    	</tr>
      	<tr class="trTwoLevel">
    		<td>Linux</td>
    		<td>Linux相关分类</td>
    		<td width="200px;" align="right">
    		  <a href="<c:url value='/adminjsps/admin/category/edit2.jsp'/>">修改</a>
    		  <a onclick="return confirm('您是否真要删除该二级分类？')" href="javascript:alert('删除二级分类成功！');">删除</a>
    		</td>
    	</tr>
      	<tr class="trTwoLevel">
    		<td>系统开发</td>
    		<td>系统开发相关分类</td>
    		<td width="200px;" align="right">
    		  <a href="<c:url value='/adminjsps/admin/category/edit2.jsp'/>">修改</a>
    		  <a onclick="return confirm('您是否真要删除该二级分类？')" href="javascript:alert('删除二级分类成功！');">删除</a>
    		</td>
    	</tr>  	      	--%>

    </table>
  </body>
</html>
