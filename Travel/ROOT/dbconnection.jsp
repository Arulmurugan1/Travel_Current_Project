<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*,java.sql.*,com.web.common.*,com.web.util.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
boolean adminUser = CommonFactory.isNull(session.getAttribute("role")).equals("Admin") ;
Properties prop    = Dbmanager.getProperties(Constant.CONNECTION_PROPERTIES);
%>

<script>
	var ADMIN_USER = <%=adminUser%> ;
</script>
<body>

	<input type=hidden name=msg id=msg value='${msg}'>
	<input type=hidden name=function id=function value='${action}'>
	<input type=hidden name=adminUser id=adminUser value= <%= adminUser %> >

	<sql:setDataSource var="db" driver="<%=prop.getProperty(Constant.DRIVER) %>"
		url="<%=prop.getProperty(Constant.URL) %>"
		user="<%=prop.getProperty(Constant.USER) %>" password="<%=prop.getProperty(Constant.PASSWORD) %>" />
	
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