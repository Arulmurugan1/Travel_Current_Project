<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>    


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../dbconnection.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">



</head>
<body>
	<jsp:include page="../header.jsp" /> 
   
   <div class="container col-md-5 mt-4">
               <div class="card ">
                   <div class="card-body bg-white">
                    
                       <div class="text-center">
                           <h2>  Add New Customer</h2>
                       </div>
                       <div class="text-center ">
                            <form action="ListCustomerServlet" method = "post">
                           
                       <input type=hidden name=mode value=I />
                       <div class="offset-1">
                       <div class="row ">
                       <fieldset class="form-group">
                         <div class=col-auto><label>Customer Name</label> <input type="text" class="form-control" name="customer_name" ></div>
                       </fieldset>
                         <fieldset class="form-group">
                         <div class=col-auto>  <label>City</label> <input type="text"  class="form-control" name="city" ></div>
                       </fieldset>
                       </div>
                       <div class=row>
                       <fieldset class="form-group">
                          <div class=col-auto> <label>Customer Id</label> <input type="text"  class="form-control" name="customer_id" ></div>
                       </fieldset>
	</div></div>
                     <div class="d-flex justify-content-center  mt-3">
                               <div class="mr-4"><button type="submit" class="btn btn-success">Add</button></div>
                               <div><input type="reset" class="btn btn-success" value=Reset ></div>    
                             </div>
                       
                       </form></div>
                   </div>
               </div>
           </div>
</body>
</html>