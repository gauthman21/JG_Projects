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
<%@ include  file="../html/itemManage.html"%>
<script>
		$(document).ready(function(){
			$("#navMenus li.active").removeClass("active");
		    $("#navMenus li.itemli").addClass("active");
		    
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
	itemManage.insertItem();
%> 
<%
	List<Item> items = itemManage.getItems();
	
%>
<script type="text/javascript">

function deleteItem(id , type){
	window.location = "/Group_C_0.5/jsp/updatedeleteItem.jsp?id="+id+"&type="+type ;
}
</script>


<div class="col-md-12">
	<div class="card card-plain">
		<div class="content table-responsive table-full-width">
        	<table class="table table-hover">
            	<thead>
                	<th>Name</th>
					<th>Category</th>
					<th>Description</th>
                    <th colspan="2" style="text-align: center;">Action</th>
				</thead>
			<tbody id="itemtable">
<%
	for(Item item: items){
	%>
	 <tr>
	    <td><% out.println(item.getItemName()); %> </td>
	    <td><% out.println(item.getItemCategroy()); %></td>
	    <td><% out.println(item.getDescription());  %></td>
	    <td><button name="deleteitem" class="btn btn-danger btn-fill btn-wd" onclick="deleteItem('<%= item.getItemID() %>', 'del' );" >Delete</button>
	     <td><button name="edititem" class="btn btn-info btn-fill btn-wd" onclick="deleteItem('<%= item.getItemID() %>', 'edit' );" >Update</button>
	  </tr>
	
	<%	
	}
%>
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