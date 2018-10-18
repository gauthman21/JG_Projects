<%@page import="com.syntel.pojo.copy.Payment"%>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<% 
	if(session.getAttribute("logged") == null){
		response.sendRedirect("index.jsp");
	}
%>
<%@ include file="../html/nav.html"%>
<%@ include file="../html/paymentManage.html"%>

<script>
		$(document).ready(function(){
			$("#navMenus li.active").removeClass("active");
		    $("#navMenus li.paymentli").addClass("active");

		});
	
</script>

<script>
function updatePayment(id){
	window.location = "/Group_C_0.5/jsp/updatePayment.jsp?id="+id;
}
</script>
<% List<Payment> p = new Payment().getAll(); %>


<div class="col-md-12">
	<div class="card card-plain">
		<div class="content table-responsive table-full-width">
        	<table class="table table-hover">
            	<thead>
                	<th>Customer Id</th>
					<th>Payment Type</th>
					<th>Update info</th>
				</thead>
			<tbody>


<% for(Payment pa : p){ %>
<tr>
	<td><% out.println(pa.getCustomerid()); %></td>
	<td><% out.println(pa.getPaymenttype()); %></td>
	<td><button name="update" class="btn btn-info btn-fill btn-wd" onclick="updatePayment('<%= pa.getPaymentid() %>')">Update</button>
</tr>
<%} %>
</tbody>
</table>
</div>
</div>
</div>

	<tr>
    <th>.</th>