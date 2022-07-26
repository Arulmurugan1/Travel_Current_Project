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
	<jsp:include page="header.jsp" />   
    <div class="container-fluid table-responsive table-wrapper mt-1 text-center">
        <c:if test ="${sessionScope.role!='Guest'}" >
    		<div class="m-2"><a class="btn btn-success button-length w-25" href="Vehicleinsertform.jsp">Add New Vehicle</a></div>
    	</c:if>
    <table class="table table-bordered  text-center text-white text-capitalize">
       <thead class="bg-primary">
                            <tr>
                            	<th>S.No</th>
                                <th>Vehicle No</th>
                                <th>Vehicle Model</th>
                                <th>Vehicle Type</th>
                                <th>Vehicle Color</th>
                                    <c:if test ="${sessionScope.role!='Guest'}" ><th>Action</th></c:if>
                            </tr>
                        </thead>
                        <tbody>
                            <!--   for (Todo todo: todos) {  -->
                            <c:forEach var="user" items="${listUser}">

                                <tr>
                                <td class ="no text-center"></td>
                                    <td>
                                        <c:out value="${user.no}" />
                                    </td>
                                    <td >
                                        <c:out value="${user.model}" />
                                    </td>
                                    <td >
                                        <c:out value="${user.type}" />
                                    </td>
                                    <td >
                                        <c:out value="${user.color}" />
                                    </td>
                                
                                        <c:if test ="${sessionScope.role!='Guest'}" ><td><a href="Vehicle?mode=E&no=<c:out value="${user.no}" />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="Vehicle?mode=D&no=<c:out value="${user.no}" />">Delete</a></td></c:if>
                                </tr>
                            </c:forEach>
                            <!-- } -->
                        </tbody>
    </table>
    </div>

</body>
</html>