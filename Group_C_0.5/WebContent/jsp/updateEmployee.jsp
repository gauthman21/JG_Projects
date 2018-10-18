<%@page import="com.syntel.pojo.copy.Employee"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:useBean id="itemManage" class="com.syntel.pojo.copy.Employee" scope="page">
	<jsp:setProperty name="itemManage" property="*"/>
</jsp:useBean>
<%@ include file="../html/nav.html"%>
<%
	Employee e = itemManage.getEmployee(Integer.parseInt(request.getParameter("id")));
%>

<%@ include  file="../html/updateEmployee.html"%>
<script>
		document.getElementById("<%= itemManage.getEaccess() %>").selected = true;
</script>
<script>
	$(document).ready(function(){
		$("#navMenus li.active").removeClass("active");
	    $("#navMenus li.employeeli").addClass("active");
	    
	});
	
	</script>
<% if (itemManage.getEmployeeid() != 0) {
	itemManage.updateEmployee();
	//response.sendRedirect("../jsp/addEmployee.jsp");
}
 %>