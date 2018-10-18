<%@page import="com.syntel.pojo.copy.CustomerOrder"%>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="ord" class="com.syntel.pojo.copy.CustomerOrder" scope="page">
</jsp:useBean>
<% 
	if(session.getAttribute("logged") == null){
		response.sendRedirect("index.jsp");
	}
%>

<%@ include file="../html/nav.html"%>
<%@ include file="../html/orderManage.html"%>
<script>
		$(document).ready(function(){
			$("#navMenus li.active").removeClass("active");
		    $("#navMenus li.orderli").addClass("active");
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

<script>
function deleteOrder(id){
	window.location = "/Group_C_0.5/jsp/deleteOrder.jsp?id="+id;
}
function updateOrder(id){
	window.location = "/Group_C_0.5/jsp/updateOrder.jsp?id="+id;
}
</script>
<% List<CustomerOrder> o = new CustomerOrder().getAll(); %>

<div class="col-md-12">
	<div class="card card-plain">
		<div class="content table-responsive table-full-width">
        	<table class="table table-hover">
            	<thead>
                	<th>Start Date</th>
                	<th>End Date</th>
					<th>Time</th>
					<th>Address</th>
					<th>Cust Id</th>
					<th>Payment</th>
					<th>Order Status</th>
					<th>Actions</th>
				</thead>
				<tbody id="ordertable">
<% for(CustomerOrder co : o){
StringBuilder odatesb = new StringBuilder(co.getOdate());
	odatesb = odatesb.delete(10, 20);
	java.sql.Date startdate = java.sql.Date.valueOf(odatesb.toString());
	StringBuilder oenddatesb = new StringBuilder(co.getOenddate());
	oenddatesb = oenddatesb.delete(10, 20);
	java.sql.Date enddate = java.sql.Date.valueOf(oenddatesb.toString());
%>
<tr>
	<td><% out.println(startdate); %></td>
	<td><% out.println(enddate); %></td>
	<td><% out.println(co.getOtime()); %></td>
	<td><% out.println(co.getOaddress()); %></td>
	<td><% out.println(co.getCid()); %></td>
	<td><% out.println(co.getPaymentstatus()); %></td>
	<td><% out.println(co.getOrderstatus()); %></td>
	<td><button name="delete" class="btn btn-danger btn-fill btn-wd" onclick="deleteOrder('<%= co.getOrderid() %>')">Delete</button>
	<td><button name="update" class="btn btn-info btn-fill btn-wd" onclick="updateOrder('<%= co.getOrderid() %>')">Update</button>
</tr>
<%}%>
</tbody>
</table>
</div>
</div>
</div>

	<tr>
    <th>.</th>