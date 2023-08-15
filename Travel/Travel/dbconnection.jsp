<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*,java.sql.*,com.web.common.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>SK Travels</title>

<link rel="stylesheet" href="<%=request.getContextPath() %>/styleSheet/bootstrap.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/styleSheet/index.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/styleSheet/Pagination.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/styleSheet/searchBox.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/styleSheet/header.css" type="text/css">


<link rel="icon" type="image/x-icon" href="<%=request.getContextPath() %>/Images/favIcon/fav.png">

<!-- Font awesome icons -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="<%=request.getContextPath() %>/scripts/ajax/libs/jquery/3.5.1/jquery.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/scripts/ajax/libs/jquery/3.6.0/jquery.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/scripts/ajax/libs/jquery/1.9.1/jquery.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/scripts/ajax/libs/jqueryUi/1.10.2/jquery-ui.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/scripts/ajax/libs/jquery/3.5.0/jquery.min.js" type="text/javascript"></script>



<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.js" type="text/javascript"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js" type="text/javascript"></script>
	
<script src="<%=request.getContextPath() %>/scripts/Validator.js"></script>
<script src="<%=request.getContextPath() %>/scripts/ajaxCall.js"></script>
<script src="<%=request.getContextPath() %>/scripts/common.js"></script>


<%
	boolean adminUser = StringChecker.isNull(session.getAttribute("role")).equals("Admin") ;
%>

<script>
	var ADMIN_USER = <%=adminUser%> ;
</script>

</head>
<body>

	<input type=hidden name=msg id=msg value='${msg}'>
	<input type=hidden name=adminUser id=adminUser value= <%= adminUser %> >

	<%@ include file="loader/loader.jsp" %>

	<sql:setDataSource var="db" driver="com.mysql.cj.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/taxi?autoReconnect=true&useSSL=false"
		user="root" password="root" />

	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
			
</body>
</html>