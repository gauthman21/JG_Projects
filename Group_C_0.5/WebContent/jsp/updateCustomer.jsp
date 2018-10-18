<%@page import="com.syntel.pojo.copy.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<% 
	if(session.getAttribute("logged") == null){
		response.sendRedirect("index.jsp");
	}
%>

<jsp:useBean id="custManage" class="com.syntel.pojo.copy.Customer" scope="page">
	<jsp:setProperty name="custManage" property="*"/>
</jsp:useBean>
<%
	Customer c = custManage.getCustomer(Integer.parseInt(request.getParameter("id")));
%>
<%@ include  file="../html/nav.html"%>
<%@ include  file="../html/updateCustomer.html"%>
<script>
	$(document).ready(function(){
		$("#navMenus li.active").removeClass("active");
	    $("#navMenus li.customerli").addClass("active");
	    
	});
	
	</script>
<% if (custManage.getcustomerid() != 0) {
	custManage.updateCustomer();
	response.sendRedirect("../jsp/customerManage.jsp");
}
 %>