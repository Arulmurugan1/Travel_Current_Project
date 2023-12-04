<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">


<link rel="stylesheet" href="styleSheet/404-error.css" type="text/css">

</head>
<body>
	
	<div class="container">
        <h2>Oops ! Look's Like your Page is missing</h2>
        <h1>404</h1>
        <h3> We Can't Find anything from the Location</h3>
        <a type="button" href="<%=request.getContextPath() %>">Go Home</a>
    </div>
	
</body>
</html>