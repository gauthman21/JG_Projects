<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="orderM" class="com.syntel.pojo.copy.CustomerOrder" scope="page">
</jsp:useBean>
<% 
	if(session.getAttribute("logged") == null){
		response.sendRedirect("index.jsp");
	}
%>
<%
	orderM.deleteOrder(Integer.parseInt(request.getParameter("id")));
	response.sendRedirect("orderManage.jsp");
%>