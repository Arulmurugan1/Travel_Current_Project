<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="dbconnection.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Travel</title>


	<style>
    	body{
    		counter-reset :section ;
    	}
    	
    	td.no::before {
    		counter-increment :section ;
    		content : counter(section);
    	
    	}
    	
    </style>
    
</head>
<body >
 <% if ( request.getParameter("mode") !="QE") {%>
 		<jsp:include page="header.jsp" /> 
 
 <% } %>
    
   <div class="table-wrapper table-responsive mt-4">
        <table class="table table-bordered text-center text-white text-capitalize">
            <thead>
                <tr>
                    <th>S.No</th>
                    <th>Customer Name</th>
                    <th>Boarding</th>
                    <th>Drop</th>
                    <th>Age</th>
                    <th>Gender</th>
                    <th>Email</th>
                    <th>Phone/WhatsApp</th>
                </tr>
            </thead>
            <tbody>

                <c:forEach var="user" items="${listUser}">

                    <tr>
                        <td class="no text-center"></td>
                        <td>
                            <c:out value="${user.customer_name}" />
                        </td>
                        <td>
                            <c:out value="${user.start}" />
                        </td>
                        <td>
                            <c:out value="${user.end}" />
                        </td>
                        <td>
                            <c:out value="${user.age}" />
                        </td>
                        <td>
                            <c:out value="${user.gender}" />
                        </td>
                        <td>
                            <c:out value="${user.email}" />
                        </td>
                        <td>
                            <c:out value="${user.phone}" />
                        </td>
                    </tr>
                </c:forEach>

            </tbody>
        </table>
    </div>    

</body>
</html>