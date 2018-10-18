<%@page import="com.syntel.ecmt.onStartup"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<link rel="icon" href="data:;base64,=">

<html>
	<head>
		<title>ECMT</title>
		<link rel="stylesheet" type="text/css" href="static/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="static/css/font-awesome.min.css"/>
		<link rel="stylesheet" type="text/css" href="static/css/app.css"/>
		
		<script src="static/js/angular.min.js"></script>
		<script src="static/js/angular-route.min.js"></script>
		<script src="static/js/app.js"></script>
		<script src="static/js/service/employee_service.js"></script>
		<script src="static/js/service/search_service.js"></script>
		<script src="static/js/controller/employee_controller.js"></script>
		<script src="static/js/controller/report_controller.js"></script>
		<script src="static/js/controller/search_controller.js"></script>
		
	</head>
	
	<body data-ng-app="myApp" class="ng-cloak">
	<% 
		//onStartup start = new onStartup();
		//start.createArrayListOfEmp();
		//start.updateCacheFileWithProfilesFromDatabase();
		//start.readFromCacheFile();
		//start.fetchSpecificProfileFromCache();
		//start.updateCache();
		//start.updateDB();
	%>
	<div id="body" data-ng-view></div>
</body>
</html>