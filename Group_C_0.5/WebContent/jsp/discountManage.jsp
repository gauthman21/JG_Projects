<%@page import="com.syntel.pojo.copy.Discounts"%>
<%@page import="java.util.*" %>
<%@page import="java.sql.Date" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="dis" class="com.syntel.pojo.copy.Discounts" scope="page">
	<jsp:setProperty name="dis" property="*" />
</jsp:useBean>
<% 
	if(session.getAttribute("logged") == null){
		response.sendRedirect("index.jsp");
	}
%>

<%@ include file="../html/nav.html"%>
<%@ include file="../html/discountManage.html"%>
<script>
		$(document).ready(function(){
			$("#navMenus li.active").removeClass("active");
		    $("#navMenus li.discountli").addClass("active");
		});
	
</script>
<% 
if(session.getAttribute("type") == null){
		response.sendRedirect("index.jsp");
	}else if(session.getAttribute("type").equals("Driver") ){
%>
			<script type="text/javascript">
				$(".adminlink").hide();
			</script>
		
<%
	}
%>

<%
	dis.addDiscount();
%>
<script>
function deleteDiscount(id){
	window.location = "/Group_C_0.5/jsp/deleteDiscount.jsp?id="+id;
}
function updateDiscount(id){
	window.location = "/Group_C_0.5/jsp/updateDiscount.jsp?id="+id;
}
</script>
<% List<Discounts> d = dis.getAll(); %>

<div class="col-md-12">
	<div class="card card-plain">
		<div class="content table-responsive table-full-width">
        	<table class="table table-hover">
            	<thead>
                	<th>Discount Date</th>
					<th>Package ID</th>
					<th>Percent</th>
					
                    <th colspan="2" style="text-align: center;">Action</th>
				</thead>
			<tbody>

<% for(Discounts di : d){ %>
<tr>
	<td><% out.println(di.getdiscountdate().toString()); %></td>
	<td><% out.println(di.getpackageid()); %></td>
	<td><% out.println(di.getpercentoff()); %></td>
	
	<td><button name="delete" class="btn btn-danger btn-fill btn-wd" onclick="deleteDiscount('<%= di.getdiscountid() %>')">Delete</button>
	<td><button name="update" class="btn btn-info btn-fill btn-wd" onclick="updateDiscount('<%= di.getdiscountid() %>')">Update</button>
</tr>
<%} %>
</tbody>
</table>
</div>
</div>
</div>

	<tr>
    <th>.</th>