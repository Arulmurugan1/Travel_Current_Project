<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>    


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../dbconnection.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Travel</title>
</head>
<body>
	<jsp:include page="../header.jsp" /> 
    
	<div class="container col-md-5 mt-4">
                <div class="card">
                    <div class="card-body">

                        <caption>
                            <h1>
                                    Edit User
                            </h1>
                        </caption>
						
                            <form action="Booking"  method = "post">
                            <input type=hidden name=mode value=U />	
                            <input type="hidden" name="booking_no" value="<c:out value='${user.booking_no}' />" />
                       
                       		<fieldset class="form-group">
                            <label>Booking No</label> <input disabled type="text" value="<c:out value='${user.booking_no}' />" class="form-control" name="booking_no1" >
                        	</fieldset>
                        	
                        	<fieldset class="form-group">
                           <label>Customer Id</label> 
                           <select class="form-select form-control" name="customer_id" id="customer_id" style ="width :500px">
                         	<option value="${user.customer_id}" selected >${user.customer_id}</option>
                             	<c:forEach var = "i" begin = "1" end ="120">
    									<option value="${i}">${i}</option>
 								</c:forEach>
  	                      </select>
                          
                       </fieldset>
 
                       <fieldset class="form-group">
							 <label>Vehicle No</label> 
                          <select class="form-select form-control" name="vehicle_no" id="vehicle_no" style ="width :500px">
                         	<option value="${user.vehicle_no}" selected >${user.vehicle_no}</option>
                             	<c:forEach var = "i" begin = "3792" end ="3850">
    									<option value="${i}">${i}</option>
 								</c:forEach>
  	                      </select>
                          
                       </fieldset>

                        	<fieldset class="form-group">
                            <label>Driver Id</label>
								<select class="form-select form-control" name="driver_id" id="driver_id" style ="width :500px">
                         	<option value="${user.driver_id}" selected >${user.driver_id}</option>
                             	<c:forEach var = "i" begin = "101" end ="120">
    									<option value="${i}">${i}</option>
 								</c:forEach>
  	                      </select>
                        	</fieldset>
                         
                             <div class="d-flex justify-content-center mt-3">
                                <div class="mr-2"><button type="submit" class="btn btn-success">Update</button></div>
                                   
                              </div>
      	</form>
                        
                        
						
                            
                    </div>
                </div>
            </div>
</body>
</html>