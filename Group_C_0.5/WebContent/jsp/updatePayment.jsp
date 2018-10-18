<%@page import="com.syntel.pojo.copy.Payment"%>
<%@page import="com.syntel.dao.PaymentDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<% 
	if(session.getAttribute("logged") == null){
		response.sendRedirect("index.jsp");
	}
%>

<jsp:useBean id="payManage" class="com.syntel.pojo.copy.Payment"
	scope="page">
	<jsp:setProperty name="payManage" property="*" />
</jsp:useBean>
<%
	Payment paym = payManage.getPayment(Integer.parseInt(request
			.getParameter("id")));
%>
<%@ include file="../html/nav.html"%>
<%@ include file="../html/updatePayment.html"%>
<script>
	$(document).ready(function(){
		$("#navMenus li.active").removeClass("active");
	    $("#navMenus li.paymentli").addClass("active");
	    
	});
	
	</script>
<%
	if (payManage.getPaymentid() == 0) {

	} else {
		payManage.updatePayment();
		response.sendRedirect("paymentManage.jsp");
	}
%>