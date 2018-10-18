<%@page import="com.syntel.pojo.copy.CustomerOrder"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Date"%>
<%@page import="java.util.*" %>
<%@page import="java.util.Calendar" %>
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
<%@ include file="../html/todaysManage.html"%>
<script>
		$(document).ready(function(){
			$("#navMenus li.active").removeClass("active");
		    $("#navMenus li.todayli").addClass("active");
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
function manageDiscounts(){
	window.location = "/Group_C_0.5/jsp/discountManage.jsp";
}
</script>
<% List<CustomerOrder> o = new CustomerOrder().getAll(); %>

<% 
java.sql.Date today = new java.sql.Date(Calendar.getInstance().getTime().getTime());
%>
<div class="col-md-12">
	<div class="card card-plain">
<h3>Todays' Date: <%= today %> </h3>
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


<%

for(CustomerOrder co : o){
	StringBuilder odatesb = new StringBuilder(co.getOdate());
	odatesb = odatesb.delete(10, 20);
	java.sql.Date startdate = java.sql.Date.valueOf(odatesb.toString());
	StringBuilder oenddatesb = new StringBuilder(co.getOenddate());
	oenddatesb = oenddatesb.delete(10, 20);
	java.sql.Date enddate = java.sql.Date.valueOf(oenddatesb.toString());
	if( !startdate.before(today) || !enddate.after(today)){}
	else{%>
<tr>
	<td><% out.println(odatesb); %></td>
		<td><% out.println(oenddatesb); %></td>
	<td><% out.println(co.getOtime()); %></td>

	<td><% out.println(co.getOaddress()); %></td>
	<td><% out.println(co.getCid()); %></td>

	<td><% out.println(co.getPaymentstatus()); %></td>
	<td><% out.println(co.getOrderstatus()); %></td>
	<td><button name="delete" class="btn btn-danger btn-fill btn-wd" onclick="deleteOrder('<%= co.getOrderid() %>')">Delete</button>
	<td><button name="update" class="btn btn-info btn-fill btn-wd" onclick="updateOrder('<%= co.getOrderid() %>')">Update</button>
</tr>
<%}
	} %>
</tbody>
</table>
</div>
</div>
</div>

	<tr>
    <th>.</th>