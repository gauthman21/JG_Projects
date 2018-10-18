<%@page import="com.syntel.pojo.copy.Employee"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
<%! static int count = 0; %>
    
<jsp:useBean id="adminLogin" class="com.syntel.pojo.copy.Employee" scope="page">
	<jsp:setProperty name="adminLogin" property="*"/>
</jsp:useBean>

<%@ include  file="../html/index.html"%>
<%
	


if(adminLogin.validateAdmin().equals(adminLogin.getEpassword())){
	Employee e = adminLogin.getEmployee();
	session.setAttribute("id", e.getEmployeeid());
	session.setAttribute("type", e.getEaccess());
	session.setAttribute("username", e.getEusername());
	session.setAttribute("logged", "yes");
	session.setAttribute("name", e.getEfname() + " "+ e.getElname());
	response.sendRedirect("home.jsp");
}else{
	%>
		<script>
			$('#faildiv').addClass('animated shake');
		</script>
	<%
}

%>
