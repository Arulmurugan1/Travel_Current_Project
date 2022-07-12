<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="dbconnection.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Travel</title>



</head>
<body>
	<jsp:include page="header.jsp" /> 
	    
    <div class="container-fluid mt-5 " >
        <div class="card jumbotron" style="margin : 1cm 6cm ;background-color: white;">
            <div class="card-body" id="body">
            
                <form  name=dummy method=post>
                    <div class="row">
                    	<input type=hidden name="mode" value=Q >
                    	
                        <div class="col-xl-6"><button class="btn text-primary" onclick="javascript:submitbooking();" >Bookings</button></div>
                        <div class="col-xl-6"><button class="btn text-primary" onclick="javascript:submitcustomer();">Customers</button></div>
                       
                    </div>
                    <div class="row mt-2">
                        <div class="col-xl-6 "><button class="btn text-primary" onclick="javascript:submitvehicle();">Vehicles</button></div>
                        <div class="col-xl-6 "><button class="btn text-primary" onclick="javascript:submitdriver();">Drivers</button></div>
                    </div>
                    <div class="row mt-2">
                        <div class="col-xl-6 "><button class="btn text-primary" onclick="javascript:submitRoute();">Routes</button></div>
                        <div class="col-xl-6 "><button class="btn text-primary" disabled onclick="javascript:submitdriver();">Users</button></div>
                    </div>
                </form>
            </div>
        </div>

    </div>



<script language="javascript">
 function submitbooking(){
 	with(document.dummy){
 		action="ListBookingServlet";
 		submit();
 	}
 }
 	function submitcustomer(){
 	 	with(document.dummy){
 	 		action="ListCustomerServlet";
 	 		submit();
 	 	}
 	}
 	 	function submitvehicle(){
 	 	 	with(document.dummy){
 	 	 		action="ListVehicleServlet";
 	 	 		submit();
 	 	 	}
 	 	}
 	 	 	function submitdriver(){
 	 	 	 	with(document.dummy){
 	 	 	 		action="ListDriverServlet";
 	 	 	 		submit();
 	 	 	 	}
 	 	 	}
 	function submitRoute(){
	 	 	with(document.dummy){
	 	 	 		action="ListRouteServlet";
	 	 	 		submit();
	 	 	 	}
 } 


</script>

</body>
</html>