<%@page import="com.syntel.pojo.copy.Item"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <jsp:useBean id="itemManage" class="com.syntel.pojo.copy.Item" scope="page">
	<jsp:setProperty name="itemManage" property="*"/>
</jsp:useBean>
<% 
	if(session.getAttribute("logged") == null){
		response.sendRedirect("index.jsp");
	}
%>
<%@ include  file="../html/nav.html"%>
<%
	if(request.getParameter("type").equals("del")){
		itemManage.deleteItem(request.getParameter("id"));
		response.sendRedirect("itemManage.jsp");
	}else if(request.getParameter("type").equals("edit")) {
		Item item =  itemManage.getItem(request.getParameter("id"));
		//out.print(item.getItemID());
	
%>

	<%@ include  file="../html/updatedeleteItem.html"%>
	<script>
	$(document).ready(function(){
		$("#navMenus li.active").removeClass("active");
	    $("#navMenus li.itemli").addClass("active");
	    
	});
	
	</script>
	<script>
		document.getElementById("<%= item.getItemCategroy() %>").selected = true;
	</script>
<%
	}
%>	
<%
	if(session.getAttribute("type").equals("Driver") ){
	%>
		<script type="text/javascript">
			$(".adminlink").hide();
		</script>	
	<%
	}

	if (itemManage.getItemID() ==0) {
		out.println("");
	} else {
		itemManage.updateItem();
		response.sendRedirect("itemManage.jsp");
	}
%> 
