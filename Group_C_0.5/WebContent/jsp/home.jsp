<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<jsp:useBean id="ord" class="com.syntel.pojo.copy.CustomerOrder" scope="page">
</jsp:useBean>
<jsp:useBean id="customerManage" class="com.syntel.pojo.copy.Customer" scope="page">
</jsp:useBean>
<jsp:useBean id="employeeManage" class="com.syntel.pojo.copy.Employee" scope="page">
</jsp:useBean>
<jsp:useBean id="packManage" class="com.syntel.pojo.copy.Pack" scope="page">
</jsp:useBean>


<% 
	if(session.getAttribute("logged") == null){
		response.sendRedirect("index.jsp");
		return;
	}
%>

<%@ include  file="../html/nav.html"%>
<%@ include  file="../html/home.html"%>
<script>
		$(document).ready(function(){
			$("#navMenus li.active").removeClass("active");
		    $("#navMenus li.dashli").addClass("active");
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
    </div>
</div>


</body>






