<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>  
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	 <link rel="stylesheet" href="./bootstrap.css" type="text/css" >
    <link rel="stylesheet" href="./index.css" type="text/css"  >
<!-- Font awesome icons -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	


<!-- Bootstrap icons --> 
	<!-- <link rel="stylesheet"  href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">  -->


<!-- Google Icons --> 
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>


</head>
<body>

		<sql:setDataSource var="db" driver="com.mysql.cj.jdbc.Driver"  
    			 url="jdbc:mysql://localhost:3306/taxi?autoReconnect=true&useSSL=false"  user="root"  password="root"/>
    			 
<!--     <div id="loader">			  -->
<!-- 		    <div id="content"> -->
<!-- 		        <div id="circular-progress"> -->
<!-- 		            <span id="progress-value"> -->
<!-- 		                0% -->
<!-- 		            </span> -->
<!-- 		        </div> -->
<!-- 		        <span id="text">Loading...</span> -->
<!--     </div></div> -->

			<div id="load-content">
				<div class="spinner-border ml-3" style="width: 5.6rem; height: 5.6rem; color:white ;" role="status">
  					<span class="sr-only">Loading...</span>
				</div>
				<span class="mt-3 text-white" style="font-size :30px;">Loading....</span>
			</div>
    		
   <script src="./loader.js"></script>
   <script src="./Validator.js"></script>
 <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
 <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>