<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*" %>
<%@ include file="dbconnection.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Travel</title>
</head>
<body>
	<jsp:include page="header.jsp" /> 
   <div class="container col-md-5 mt-4">
               <div class="card ">
                   <div class="card-body bg-white">
                    
                       <div class="text-center">
                           <h2>  Add New Vehicle</h2>
                       </div>
                       <div class="text-center ">
                            <form name=vehicle action="ListVehicleServlet" method = "post">
                           
                       			<input type=hidden name=mode value=I />
                       			<input type=hidden name=mode value=I />
                       <div class="offset-1">
                       <div class="row ">
                       <fieldset class="form-group">
                         <div class=col-auto>
                         		<label>Vehicle No</label> 
                         		<input type="text" class="form-control" name="vehicle_no" maxlength="4" onchange="validateRoute();"
                         		onkeypress="return event.charCode >= 48 && event.charCode <= 57">
                         		<span id=error class="mt-2 text-danger"></span>
                         </div>
                         
                       </fieldset>
                         <fieldset class="form-group">
                         <div class=col-auto>  <label>Vehicle Model</label> <input type="text"  class="form-control" name="vehicle_model" ></div>
                       </fieldset>
                       </div>
                       <div class=row>
                       <fieldset class="form-group">
                          <div class=col-auto> <label>Vehicle Type</label> <input type="text"  class="form-control" name="vehicle_type" ></div>
                       </fieldset>
                       <fieldset class="form-group">
                          <div class=col-auto> <label>Vehicle Color</label> <input type="text"  class="form-control" name="vehicle_color" ></div>
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
       
       <div style="visibility:none;display:none;">
	       <script>
				 var vehicles = '${Vehicles}';
				 	 vehicles = vehicles.split(",");
						 function validateRoute()
				           {
								
				           	 if (document.vehicle.vehicle_no.value !="")
				           		{
				           			for ( let i=0 ;i<vehicles.length ; i++)
				           			{
				           				if( vehicles[i] == document.vehicle.vehicle_no.value )
				           					{
				           					document.vehicle.vehicle_no.focus();
				           					return document.getElementById('error').innerHTML = "Vehicle already exists";
				           					}
				           			}
				           		}
				           	return document.getElementById('error').innerHTML  = null;
				           }
			</script>
		</div>
</body>
</html>