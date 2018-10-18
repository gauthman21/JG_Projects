<%@page import="com.syntel.pojo.copy.Area"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<% 
	if(session.getAttribute("logged") == null){
		response.sendRedirect("index.jsp");
	}
%>

<jsp:useBean id="areaManage" class="com.syntel.pojo.copy.Area" scope="page">
	<jsp:setProperty name="areaManage" property="*"/>
</jsp:useBean>

<%@ include  file="../html/nav.html"%>
<script>
	$(document).ready(function(){
		$("#navMenus li.active").removeClass("active");
	    $("#navMenus li.areali").addClass("active");
	    
	});
	
	</script>
<%
	Area a = areaManage.getArea(Integer.parseInt(request.getParameter("id")));
%>
<%@ include  file="../html/updateArea.html"%>
<%
	if (areaManage.getAreaid()==0) {
		out.println("");
	} else {
		areaManage.updateArea();
		response.sendRedirect("addArea.jsp");
	}
%>