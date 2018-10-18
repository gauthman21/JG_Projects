<%@page import="com.syntel.pojo.copy.Area"%>
<%@page import="com.syntel.pojo.copy.Item"%>
<%@page import="com.syntel.pojo.copy.Pack"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>

<jsp:useBean id="packManage" class="com.syntel.pojo.copy.Pack" scope="page">
	<jsp:setProperty name="packManage" property="*"/>
</jsp:useBean>
<jsp:useBean id="itemManage" class="com.syntel.pojo.copy.Item" scope="page">
	<jsp:setProperty name="itemManage" property="*"/>
</jsp:useBean>
<jsp:useBean id="areaManage" class="com.syntel.pojo.copy.Area" scope="page">
	<jsp:setProperty name="areaManage" property="*"/>
</jsp:useBean>
<jsp:useBean id="packDetailsManage" class="com.syntel.pojo.copy.PackageDetails" scope="page">
	<jsp:setProperty name="packDetailsManage" property="*"/>
</jsp:useBean>
<jsp:useBean id="areapackManage" class="com.syntel.pojo.copy.AreaPackage" scope="page">
	<jsp:setProperty name="areapackManage" property="*"/>
</jsp:useBean>


 
<%
	if(request.getParameter("type").equals("del")){
		packManage.deletePack(request.getParameter("id"));
		response.sendRedirect("package.jsp");
		
	}else if(request.getParameter("type").equals("edit")) {
		
		Pack pack = packManage.getItem(request.getParameter("id"));
		
		//out.print(pack.getPackageID());
%>
<%@ include  file="../html/nav.html"%>
<%@ include  file="../html/updatedeletePack.html"%>
<script>
	$(document).ready(function(){
		$("#navMenus li.active").removeClass("active");
	    $("#navMenus li.packli").addClass("active");
	    
	});
	
	</script>
		
<% 
		if(session.getAttribute("type").equals("Driver") ){
		%>
			<script type="text/javascript">
				$(".adminlink").hide();
			</script>	
		<%
		}
		
		if(pack.getVegitarian() == 0){ %> <Script> document.getElementById("r0").checked = true; </script> <% } else if(pack.getVegitarian() == 1){ %> <Script> document.getElementById("r1").checked = true; </script> <% }
		
%>
		<script>
			document.getElementById("<%= pack.getMealTime() %>").selected = true;
		</script>
		<br><br><label>Items</label><br>

<% 
		List<Integer> ids = packDetailsManage.getItemIds(Integer.parseInt(request.getParameter("id")));
		List<Integer> areas = areapackManage.getAreaIds(Integer.parseInt(request.getParameter("id")));
		//out.print(areas);
		
 		List<Item> items = itemManage.getItems();
		for(Item item: items){	
%>	
			<input type="checkbox" name="items" id='<%= item.getItemID() %>' value='<%= item.getItemID() %>'><%= item.getItemName() %><br>
<%			
			for(Integer id: ids){
				if(id == item.getItemID() ){
%>	
					<script>
						document.getElementById(<%= item.getItemID() %>).checked = true;
					</script>
<%						
				}
			}
		}
%>
		<br><br><label>Areas</label><br>
<% 
	
		List<Area> arealist = areaManage.getAreas();
		for(Area area: arealist){
		
%>
			<input type="checkbox" name="locations" id='a<%= area.getAreaid() %>' value='<%= area.getAreaid() %>'><%= area.getLocation() %><br>
<%
			for(Integer areaId: areas){
				if(areaId == area.getAreaid()){
%>	
					<script>
						document.getElementById("a"+<%=area.getAreaid() %>).checked = true;
					</script>
<%	
				}
			}
		}
%>
		<br><br>
		<input type="submit"  class="btn btn-success btn-fill btn-wd" name="submit" value="Update Package"></input>
		</form>
<%
	}

	if (packManage.getPackageID() ==0) {
		out.println("");
	} else {
		packManage.updatePack();
		//response.sendRedirect("../jsp/package.jsp");
	}
	
%>
