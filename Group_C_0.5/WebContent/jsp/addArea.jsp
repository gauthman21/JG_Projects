<%@page import="com.syntel.pojo.copy.Area"%>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
    
<jsp:useBean id="ar" class="com.syntel.pojo.copy.Area" scope="page">
	<jsp:setProperty name="ar" property="*"/>
</jsp:useBean>
<% 
	if(session.getAttribute("logged") == null){
		response.sendRedirect("index.jsp");
	}
%>
<%@ include  file="../html/nav.html"%>
<%@ include  file="../html/addArea.html"%>
<script>
		$(document).ready(function(){
			$("#navMenus li.active").removeClass("active");
		    $("#navMenus li.areali").addClass("active");
		});
	
</script>
<%
ar.addArea();
%>
<script>
function deleteArea(id){
	window.location = "/Group_C_0.5/jsp/deleteArea.jsp?id="+id;
}
function updateArea(id){
	window.location = "/Group_C_0.5/jsp/updateArea.jsp?id="+id;
}
</script>
<% List<Area> areas = ar.getAll(); %>

<div class="col-md-12">
	<div class="card card-plain">
		<div class="content table-responsive table-full-width">
        	<table class="table table-hover">
            	<thead>
                	<th>Locations</th>
					<th>Zip Code</th>
                    <th colspan="2" style="text-align: center;">Action</th>
				</thead>
			<tbody>
<% for(Area a : areas){ %>
<tr>
	<td><% out.println(a.getLocation()); %></td>
	<td><% out.println(a.getZipcode()); %></td>
	<td><button name="delete" class="btn btn-danger btn-fill btn-wd" onclick="deleteArea('<%= a.getAreaid() %>')">Delete</button>
	<td><button name="update" class="btn btn-info btn-fill btn-wd" onclick="updateArea('<%= a.getAreaid() %>')">Update</button>
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