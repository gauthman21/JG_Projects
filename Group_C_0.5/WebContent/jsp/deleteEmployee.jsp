<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:useBean id="itemManage" class="com.syntel.pojo.copy.Employee" scope="page">
</jsp:useBean>
<% 
	if(session.getAttribute("logged") == null){
		response.sendRedirect("index.jsp");
	}
%>
<%
	itemManage.deleteEmployee(Integer.parseInt(request.getParameter("id")));
	response.sendRedirect("addEmployee.jsp");
%>