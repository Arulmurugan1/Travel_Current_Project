<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="dbconnection.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Travel</title>
</head>
<body>
	<jsp:include page="header.jsp" /> 
    
	<div class="container col-md-5 mt-4">
                <div class="card">
                    <div class="card-body">

                        <caption>
                            <h1>
                                    Edit User
                            </h1>
                        </caption>
						
                            <form action="Vehicle" method = "post">
                            <input type=hidden name=mode value=U />	            
                       		
                       		<fieldset class="form-group">
                            <label>Vehicle No</label> <input type="text" value="<c:out value='${user.no}' />" class="form-control" name="vehicle_no">
                        	</fieldset>
                        	
                       		<fieldset class="form-group">
                            <label>Vehicle Model</label> <input type="text" value="<c:out value='${user.model}' />" class="form-control" name="vehicle_model" >
                        	</fieldset>

                        	<fieldset class="form-group">
                            <label>Vehicle Type</label> <input type="text" value="<c:out value='${user.type}' />" class="form-control" name="vehicle_type">
                        	</fieldset>
                        	
                        	<fieldset class="form-group">
                            <label>Vehicle Color</label> <input type="text" value="<c:out value='${user.color}' />" class="form-control" name="vehicle_color">
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