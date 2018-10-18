<%@page import="com.syntel.pojo.copy.Discounts"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:useBean id="discountManage" class="com.syntel.pojo.copy.Discounts" scope="page">
	<jsp:setProperty name="discountManage" property="*"/>
</jsp:useBean>
<%
	Discounts d = discountManage.getDiscount(Integer.parseInt(request.getParameter("id")));
%>
<%@ include file="../html/nav.html"%>
<%@ include  file="../html/updateDiscount.html"%>
<script>
	$(document).ready(function(){
		$("#navMenus li.active").removeClass("active");
	    $("#navMenus li.discountli").addClass("active");
	    
	});
</script>
<%
	if(session.getAttribute("type").equals("Driver") ){
	%>
		<script type="text/javascript">
			$(".adminlink").hide();
		</script>	
	<%
	}
	if (discountManage.getdiscountid()==0) {
		out.println("");
	} else {
		discountManage.updateDiscount();
		response.sendRedirect("../jsp/discountManage.jsp");
	}
%>