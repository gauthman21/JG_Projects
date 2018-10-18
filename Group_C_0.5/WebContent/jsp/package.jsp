<%@page import="com.syntel.pojo.copy.Pack"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>

<% 
	if(session.getAttribute("logged") == null){
		response.sendRedirect("index.jsp");
		return;
	}
%>

<jsp:useBean id="packManage" class="com.syntel.pojo.copy.Pack" scope="page">
	<jsp:setProperty name="packManage" property="*"/>
</jsp:useBean>

<jsp:useBean id="packDetailsManage" class="com.syntel.pojo.copy.PackageDetails" scope="page">
	<jsp:setProperty name="packDetailsManage" property="*"/>
</jsp:useBean>

<%@ include  file="../html/nav.html"%>
<%@ include  file="../html/package.html"%>
<script>
		$(document).ready(function(){
			$("#navMenus li.active").removeClass("active");
		    $("#navMenus li.packli").addClass("active");

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
 	List<Pack> packs = packManage.getPacks();
	
%>

<div class="col-lg-3 col-md-4">
<a href="packManage.jsp">
	<div class="card card-user">
    	<div class="content">
    		 <div class="test-center">
    		 
    		 	 <p  class="glyphicon addbutton">
        			<span class="glyphicon">&#x2b;</span>
      			</p> 
      			<br />
      			<h6 class="text-center"></h6>
      			<br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
      			<br /><br /><br /><br /><br /><br />
    		 </div>
        </div>
    </div>
</a>
</div>
                

<script type="text/javascript">
function deletePack(id , type){
	window.location = "/Group_C_0.5/jsp/updatedeletePack.jsp?id="+id+"&type="+type ;
}
</script>
<div ckass="card-deck">
<%
	for(Pack pack: packs){
%>
				
                <div class="col-lg-3 col-md-4">
                
                    <div class="card card-user">
                 
                    <div class="image">
                                <img src="../imgs/<% out.println(pack.getPackageID()); %>.jpg" alt="no Image"/>
                    </div>
                    <div class="header">
                        <h5 class="title"><% out.println(pack.getPackageName()); %> </h5>
                        <small><% out.println(pack.getDescription()); %></small>
                    </div>
                    <hr>
                    <div class="content">
                            <div class="row">
                                <div class="col-md-12">
                                    <h5> <b>Price:</b> $<% out.println(pack.getPrice());  %></h5>
                                   
                                </div>
                              
                                <div class="col-md-12">
                                    <b>Items:</b>
									 <% 
								    	List<String> names = new ArrayList<String>();
										names = packDetailsManage.getItemName(pack.getPackageID()); 
								    	for(String s:names){
								    		out.print(s);
								    		out.print(", ");
								    	}
								    %>
                                </div>
                               </div>
                              
                                <div class="text-center">
                                <div class="row packbuttons">
                                    <div class="col-md-4">
                                        <td><button name="deleteitem"  class="btn btn-danger btn-fill btn-wd" onclick="deletePack('<%= pack.getPackageID() %>', 'del' );" >Delete</button>
                                    </div>
                                    <div class="col-md-4">
                                        <td><button name="edititem"  class="btn btn-info btn-fill btn-wd" onclick="deletePack('<%=  pack.getPackageID() %>', 'edit' );" >Update</button></td>
                                    </div>
                                </div>
                                </div>
                                
                            </div>
                   
	           
                   	</div>
                </div>
	
	 
	<%	
	}
%>
</div>
</table>