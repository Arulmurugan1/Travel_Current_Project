<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.io.*,java.util.*,java.sql.*,com.web.common.Constant"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet" href="bootstrap.css" type="text/css">
<link rel="stylesheet" href="index.css" type="text/css">
<link rel="icon" type="image/x-icon"
	href="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSxMIKOBnQXvA_pFc1yd_NX8_Hijxkvo7fHPw&usqp=CAU">
<!-- Font awesome icons -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- Google Icons -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="loader.js"></script>
<script src="Validator.js"></script>


</head>
<body>
	<input type=hidden name=msg id=msg value='${msg}'>

	<sql:setDataSource var="db" driver="com.mysql.cj.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/taxi?autoReconnect=true&useSSL=false"
		user="root" password="root" />

	<div id="load-content">
		<!--  Round spinner 
					<div class="spinner-border ml-3" style="width: 5.6rem; height: 5.6rem; color:white ;" role="status">
	  					<span class="sr-only">Loading...</span>
					</div>
				 -->
		<span class="loader-alt ml-4"> <span class=load></span>
		</span> <span class="mt-3 text-white" style="font-size: 30px;">Loading....</span>
	</div>
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