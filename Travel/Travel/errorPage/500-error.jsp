<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">


<link rel="stylesheet" href='<%=request.getContextPath() %>/styleSheet/500-error.css' type="text/css">

</head>

<body>
	<div class="container">
        <h1><%=response.getStatus() +"- Internal Server Error" %></h1>
        <a type="button" href="javascript:location.reload()">Back</a>
    </div>
	
</body>
</html>