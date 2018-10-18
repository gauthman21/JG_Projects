<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Deleted Discount</title>
<jsp:useBean id="discountManage" class="com.syntel.pojo.copy.Discounts" scope="page">
</jsp:useBean>
<% 
	if(session.getAttribute("logged") == null){
		response.sendRedirect("index.jsp");
	}
%>

<%
	discountManage.deleteDiscount(Integer.parseInt(request.getParameter("id")));
	response.sendRedirect("discountManage.jsp");
%>