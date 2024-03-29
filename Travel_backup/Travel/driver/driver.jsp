<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>    


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../dbconnection.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
   
    
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
<jsp:include page="../header.jsp" /> 
    
    <div class="container-fluid overflow-auto m-auto text-center">
    	<div class="m-3"> 
        <c:if test="<%= adminUser %>">
            <a class="btn btn-success button-length w-25" href="Driver?mode=N">Add New Driver</a>
        </c:if>
        	<button type=button class='btn btn-success button-length ml-2' onclick='location.reload();'>
						<i class="fa fa-refresh mr-2"></i>Refresh
			</button>
			<a type=button class='btn btn-success button-length ml-2' href='home.jsp'>
						<i class="fa fa-backward mr-2"></i>Back
			</a>
			</div>
        <div class=table-responsive>
        <table class="table table-bordered text-center text-white text-capitalize mt-2">
            <thead class="bg-primary">
                <tr>
                    <th>S.No</th>
                    <th>Driver Id</th>
                    <th>Driver Name</th>
                    <th>Gender</th>
                    <th>Age</th>
                    <th>Phone</th>
                    <th>City</th>
                    <th>Vehicle No</th>

                    <c:if test="<%= adminUser %>">
                        <th>Action</th>
                    </c:if>
                </tr>
            </thead>
            <tbody>

                <c:forEach var="user" items="${listUser}">

                    <tr>
                        <td class="no text-center"></td>
                        <td>
                            <c:out value="${user.id}" />
                        </td>
                        <td>
                            <c:out value="${user.name}" />
                        </td>
                        <td>
                            <c:out value="${user.gender}" />
                        </td>
                        <td>
                            <c:out value="${user.age}" />
                        </td>
                        <td>
                            <c:out value="${user.phone}" />
                        </td>
                        <td>
                            <c:out value="${user.city}" />
                        </td>
                        <td>
                            <c:out value="${user.no}" />
                        </td>
                        <c:if test="<%= adminUser %>">
                            <td>
                            <%-- <a href="Driver?mode=E&driver_id=<c:out value='${user.id}' />">Edit</a> --%> 
                            &nbsp;&nbsp;&nbsp;&nbsp; 
                            <a href="Driver?mode=D&driver_id=<c:out value='${user.id}' />">Delete</a></td>
                        </c:if>
                    </tr>
                </c:forEach>

            </tbody>
        </table>
        </div>
    </div>
</body>
</html>