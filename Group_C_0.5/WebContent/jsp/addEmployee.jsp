<%@page import="com.syntel.pojo.copy.Employee"%>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<jsp:useBean id="emp" class="com.syntel.pojo.copy.Employee" scope="page">
	<jsp:setProperty name="emp" property="*" />
</jsp:useBean>
<%@ include file="../html/nav.html"%>
<%@ include file="../html/addEmployee.html"%>
<script>
		$(document).ready(function(){
			$("#navMenus li.active").removeClass("active");
		    $("#navMenus li.employeeli").addClass("active");
	});
	
</script>

<%
emp.addEmployee();
%>
<script>
function deleteEmployee(id){
	window.location = "/Group_C_0.5/jsp/deleteEmployee.jsp?id="+id;
}
function updateEmployee(id){
	window.location = "/Group_C_0.5/jsp/updateEmployee.jsp?id="+id;
}
</script>
<% List<Employee> e = emp.getAll(); %>


<div class="col-md-12">
	<div class="card card-plain">
		<div class="content table-responsive table-full-width">
        	<table class="table table-hover">
            	<thead>
                	<th>Name</th>
					<th>Phone</th>

					<th>Access</th>
                    <th colspan="2" style="text-align: center;">Action</th>
				</thead>
			<tbody>

<% for(Employee em : e){ %>
<tr>
	<td><% out.println(em.getEfname() + " " + em.getElname()); %></td>
	<td><% out.println(em.getEphone()); %></td>
	
	<td><% out.println(em.getEaccess()); %></td>
	<td><button name="delete" class="btn btn-danger btn-fill btn-wd" onclick="deleteEmployee('<%= em.getEmployeeid() %>')">Delete</button>
	<td><button name="update" class="btn btn-info btn-fill btn-wd" onclick="updateEmployee('<%= em.getEmployeeid() %>')">Update</button>
</tr>
<%} %>
</tbody>
</table>
</div>
</div>
</div>

	<tr>
    <th>.</th>
  </tr>
 </div>
 </div>
 </div>