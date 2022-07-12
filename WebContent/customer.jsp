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
   
    <div class="d-flex justify-content-around mb-2 mt-2">
    	<span> ${msg} </span>
    	<div><a class="btn btn-success " href="ListCustomerServlet?mode=N">Add New Customer</a></div></div>
    <table class="table table-bordered  text-center text-white text-capitalize">
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
                                <th>Customer Id</th>
                                    <c:if test = "${sessionScope.role!='Guest'}" ><th>Action</th></c:if>
                            </tr>
                        </thead>
                        <tbody>
                            
                            <c:forEach var="user" items="${listUser}">

                                <tr>
                                	<td class ="no text-center"></td>
                                    <td>
                                        <c:out value="${user.customer_name}" />
                                    </td>
                                    <td >
                                        <c:out value="${user.start}" />
                                    </td>
                                    <td >
                                        <c:out value="${user.end}" />
                                    </td>
                                    <td >
                                        <c:out value="${user.age}" />
                                    </td>
                                    <td >
                                        <c:out value="${user.gender}" />
                                    </td>
                                    <td >
                                        <c:out value="${user.email}" />
                                    </td>
                                    <td >
                                        <c:out value="${user.phone}" />
                                    </td>
                                    <td >
                                        <c:out value="${user.customer_id}" />
                                   
                                       <c:if test ="${sessionScope.role!='Guest'}" ><td><a href="ListCustomerServlet?mode=E&customer_id=<c:out value='${user.customer_id}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="ListCustomerServlet?mode=D&customer_id=<c:out value='${user.customer_id}' />">Delete</a></td></c:if>
                                </tr>
                            </c:forEach>
                            
                        </tbody>
    </table>
    </div>
    

</body>
</html>