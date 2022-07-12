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
    
    <div class="container-fluid table-responsive mt-1">
        <c:if test ="${sessionScope.role!='Guest'}" >
    <div class="d-flex justify-content-between m-2">
    	<span> ${msg} </span>
    	<div><a class="btn btn-success" href="ListDriverServlet?mode=N">Add New Driver</a></div></div></c:if>
    <table class="table table-bordered  text-center text-white text-capitalize">
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
                           
                                    <c:if test = "${sessionScope.role!='Guest'}" ><th>Action</th></c:if>
                            </tr>
                        </thead>
                        <tbody>
                            
                            <c:forEach var="user" items="${listUser}">

                                <tr>
                                <td class ="no text-center"></td>
                                    <td>
                                        <c:out value="${user.id}" />
                                    </td>
                                    <td >
                                        <c:out value="${user.name}" />
                                    </td>
                                    <td >
                                        <c:out value="${user.gender}" />
                                    </td>
                                    <td >
                                        <c:out value="${user.age}" />
                                    </td>
                                    <td >
                                        <c:out value="${user.phone}" />
                                    </td>
                                    <td >
                                        <c:out value="${user.city}" />
                                    </td>
                                   <td >
                                        <c:out value="${user.no}" />
                                    </td>
                                       <c:if test ="${sessionScope.role!='Guest'}" ><td><a href="ListDriverServlet?mode=E&driver_id=<c:out value='${user.id}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="ListDriverServlet?mode=D&driver_id=<c:out value='${user.id}' />">Delete</a></td></c:if>
                                </tr>
                            </c:forEach>
                            
                        </tbody>
    </table>
    </div>

</body>
</html>