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

    <div class="container">
        <div class="content">
            <div class="card-body" >
            <form name=home id=center >
            	<input type=hidden name=mode value='' >
                <div class="row">
                    <div class="col-sm-12 col-md-6 col-lg-4 ">
                        <a class="home-button" onclick="Submit('Booking')"><i class="fa fa-book" aria-hidden="true"></i>Bookings</a>
                    </div>
                    <div class="col-sm-12 col-md-6 col-lg-4 ">
                        <a class="home-button" onclick="Submit('Customer')"><i class="fa fa-user" aria-hidden="true"></i>Customers</a>
                    </div>
                    <div class="col-sm-12 col-md-6 col-lg-4 ">
                        <a class="home-button" onclick="Submit('Vehicle')"><i class="fa fa-bus" aria-hidden="true"></i>Vehicles</a>
                    </div>
                    <div class="col-sm-12 col-md-6 col-lg-4 ">
                        <a class="home-button" onclick="Submit('Driver')"><i class="fa fa-users" aria-hidden="true"></i>Drivers</a>
                    </div>
                    <div class="col-sm-12 col-md-6 col-lg-4 ">
                        <a class="home-button" onclick="Submit('RouteNew')" ><i class="fa fa-road" aria-hidden="true"></i>Routes</a>
                    </div>
                    <div class="col-sm-12 col-md-6 col-lg-4 ">
                        <a class="bg-danger home-button" disabled>Users</a>
                    </div>
                </div></form>
            </div>

        </div>
    </div>
    
     <script>
     function Submit(servlet)
	{
		with(document.home)
		{
		action =servlet;
		submit();
		}
	}
     </script>
</body>
</html>