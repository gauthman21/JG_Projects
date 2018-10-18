<%@page import="com.syntel.pojo.copy.Item"%>
<%@page import="com.syntel.pojo.copy.Area, org.apache.jasper.tagplugins.jstl.core.ForEach, java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<% 
	if(session.getAttribute("logged") == null){
		response.sendRedirect("index.jsp");
	}
%>

 <jsp:useBean id="areaManage" class="com.syntel.pojo.copy.Area" scope="page">
	<jsp:setProperty name="areaManage" property="*"/>
</jsp:useBean>
<jsp:useBean id="packManage" class="com.syntel.pojo.copy.Pack" scope="page">
	<jsp:setProperty name="packManage" property="*"/>
</jsp:useBean>
 <jsp:useBean id="itemManage" class="com.syntel.pojo.copy.Item" scope="page">
	<jsp:setProperty name="itemManage" property="*"/>
</jsp:useBean>

<%@ include  file="../html/nav.html"%>
<%@ include  file="../html/packManage.html"%>
<script>
		$(document).ready(function(){
			$("#navMenus li.active").removeClass("active");
		    $("#navMenus li.packli").addClass("active");

		});
	
</script> 

 <div class="form-group"  data-toggle="collapse"  data-target="#demo">
     <label>Items: </label>&nbsp;<h6 class="glyphicon glyphicon-chevron-down icon-info"></h6>
 </div>

 <div id="demo" class="collapse">

<% 
	List<Item> items = itemManage.getItems();
	for(Item item: items){
%>
		
		<input type="checkbox" name="items" value='<%= item.getItemID() %>'><%= item.getItemName() %> <br>
<%
	}
%>
</div><br />
<div class="form-group" data-toggle="collapse"  data-target="#demo1">
     <label>Areas:</label>&nbsp;<h6 class="glyphicon glyphicon-chevron-down icon-info"></h6>
 </div>
<div id="demo1" class="collapse">
<% 
	
	List<Area> arealist = areaManage.getAll();
	for(Area area: arealist){
%>
		<input type="checkbox" name="locations" value='<%= area.getAreaid() %>'><%= area.getLocation() %><br>
<%
	}
%>
</div>

	<input type="submit" class="btn btn-success btn-fill btn-wd" name="submit" value="Add Package"></input>
	</form>

	<%
		packManage.insertPack();
		
	%>