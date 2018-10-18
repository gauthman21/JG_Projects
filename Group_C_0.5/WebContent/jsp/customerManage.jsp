<%@page import="com.syntel.pojo.copy.Customer"%>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="ord" class="com.syntel.pojo.copy.Customer" scope="page">
</jsp:useBean>
<% 
	if(session.getAttribute("logged") == null){
		response.sendRedirect("index.jsp");
	}
%>

<%@ include file="../html/nav.html"%>
<%@ include file="../html/customerManage.html"%>
<script>
		$(document).ready(function(){
			$("#navMenus li.active").removeClass("active");
		    $("#navMenus li.customerli").addClass("active");
		});
	
</script>
<script>
function deleteCustomer(id){
	window.location = "/Group_C_0.5/jsp/deleteCustomer.jsp?id="+id;
}
function updateCustomer(id){
	window.location = "/Group_C_0.5/jsp/updateCustomer.jsp?id="+id;
}
</script>
<% List<Customer> c = new Customer().getAll(); %>

<div class="col-md-12">
	<div class="card card-plain">
		<div class="content table-responsive table-full-width">
        	<table class="table table-hover">
            	<thead>
                	<th>Customer Name</th>
                	<th>Username</th>
					<th>Phone Number</th>
					<th>Email</th>
					<th>Status</th>
					<th>Action</th>
				</thead>


                	
			<tbody>
<% 
for(Customer cu : c){
	%>
<tr>
	<td><% out.println(cu.getcfname() + " " + cu.getclname()); %></td>
	<td><% out.println(cu.getcusername()); %></td>
	<td><% out.println(cu.getcphone()); %></td>
	<td><% out.println(cu.getcemail()); %></td>
	<td><% out.println(cu.getstatus()); %></td>
	<td><button name="delete" class="btn btn-danger btn-fill btn-wd" onclick="deleteCustomer('<%=cu.getcustomerid()%>')">Delete</button>
	<td><button name="update" class="btn btn-info btn-fill btn-wd" onclick="updateCustomer('<%=cu.getcustomerid()%>')">Update</button>
</tr>
<%} %>
</tbody>
</table>
</div>
</div>
</div>

	<tr>
    <th>.</th>