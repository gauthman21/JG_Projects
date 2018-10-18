<%@page import="com.syntel.pojo.copy.CustomerOrder"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="orderManage" class="com.syntel.pojo.copy.CustomerOrder" scope="page">
	<jsp:setProperty name="orderManage" property="*"/>
</jsp:useBean>
<%
	CustomerOrder co = orderManage.getOrder(Integer.parseInt(request.getParameter("id")));
%>
<%@ include file="../html/nav.html"%>
<%@ include  file="../html/updateOrder.html"%>
<% 
 if(session.getAttribute("type").equals("Driver") ){
%>
	<script type="text/javascript">
		$(".adminlink").hide();
	</script>	
<%
 }
%>
<script>
	$(document).ready(function(){
		$("#navMenus li.active").removeClass("active");
	    $("#navMenus li.orderli").addClass("active");
	    
	});
	
	</script>
<%
	if (orderManage.getOrderid()==0) {
		out.println("");
	} else {
		orderManage.updateOrder();
		//response.sendRedirect("../jsp/orderManage.jsp");
		//return;
	}
%>
