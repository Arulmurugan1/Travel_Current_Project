<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>  
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ include file="dbconnection.jsp" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Travel</title>
</head>
<body>
   
   <div class="container-fluid mt-4">
               <div class="card" >
                   <div class="card-body bg-white">                       
                            <form name ="bookingInsert" method = "post">
                           		
                       <input type=hidden name=mode id=mode value=I />
                       <input type=hidden name=hStatus id=hStatus value='' />
                       <input type=hidden name=hFare id=hFare value='' /> 
                       <input type=hidden name=hVehicle id=hvehicle value=' '/> 
                       
                       
                       
             <div class="row d-flex justify-content-center">
             		<h4 style='color:green;'>${msg}</h4>
             </div>
            <div class="row d-flex justify-content-center"> 
              <div class='col-auto '>
                <fieldset class="form-group">
                    <label>Pickup From</label>
                        <select class="form-select form-control" name="pickup_from" id="pickup_from"
                            style="width :340px" onChange="javascript:fareClick();query();" required >
                             <option value="" selected></option>
                            <sql:query dataSource="${db}" var="rs">
								select Distinct start from route order by start;			
							</sql:query> 
							<c:forEach  var='vehicle' items='${rs.rows}'>
								<option value="${vehicle.start}">${vehicle.start}</option>
							</c:forEach>
                        </select>
                    
                </fieldset>
            </div>
            <div class='col-auto '>
                <fieldset class="form-group">
                    <label>Drop To</label>
                        <select class="form-select form-control" name="drop_at" id="drop_at" style="width :340px"
                            onChange="javascript:fareClick();query();" required>
                            <option value="" selected></option>
                            <sql:query dataSource="${db}" var="rs">
								select Distinct end from route where start ='${pickup}' order by end;			
							</sql:query> 
							<c:forEach  var='vehicle' items='${rs.rows}'>
								<option value="${vehicle.end}">${vehicle.end}</option>
							</c:forEach>
                    </select>
                    
                </fieldset>
            </div>
            <div class='col-auto '>
                <fieldset class="form-group">
                    <label>Vehicle No</label>              
                    <select class="form-select form-control" name="vehicle_no" id="vehicle_no" style="width :340px" required readonly>
<!--                         <option value="" selected ></option> -->
                        
<!--                         Data fetching from servlet method -->

<%--                         <c:forEach var="vehicle" items="${listvehicle}"> --%>
<%--                             <option value="${vehicle.no} - ${vehicle.model} - ${vehicle.type} - ${vehicle.color}">${vehicle.no} - ${vehicle.model} - ${vehicle.type} - ${vehicle.color}</option> --%>
<%--                         </c:forEach> --%>
			
			
				<sql:query dataSource="${db}" var="rs">
						select * from route where start ='${pickup}' and end ='${drop}';			
				</sql:query> 
							<c:forEach  var='vehicle' items='${rs.rows}'>
								<option value="${vehicle.vehicle_no}">${vehicle.vehicle_no}</option>
							</c:forEach>
                    </select>

                </fieldset>
            </div>
            <div class='col-auto'>

                <fieldset class="form-group">
                    <label>Driver Id</label>                 
                    <select class="form-select form-control" name="driver_id" id="driver_id" style="width :340px" required readonly>
                        <sql:query dataSource="${db}" var="rs">
                        	SELECT d.* from driver d,route r where d.vehicle_no =r.vehicle_no and r.start ='${pickup}' and r.end ='${drop}';
                        </sql:query>
                        <c:forEach var="driver" items="${rs.rows}">
                            <option value="${driver.driver_id} - ${driver.driver_name}">${driver.driver_id} - ${driver.driver_name}</option>
                        </c:forEach>
                        
                    </select>

                </fieldset>
            </div>
            <div class='col-auto'>
                <fieldset class="form-group">
                    <label>Customer Name</label>
                    <input class=" form-control" name="customer_name" id="customer_name" size=38 onchange='javascript:emailClick()'  required>

                </fieldset>
            </div>
            <div class='col-auto'>
                <fieldset class="form-group">
                    <label>Age</label>
                    	 <input type="number" class=" form-control" id="age" name="age" min="4" max="60" step="1"  required>

                </fieldset>
            </div>
          
          <div class='col-auto'>
                <fieldset class="form-group">
                    <label>Gender</label>
                    <select class="form-select form-control" name="gender" id="gender"
                            style="width :220px" onChange="javascript:genderSelection()" required>
                            <option value="" selected></option>
                            <c:forTokens items = "Male,Female,Transgender" delims="," var="gender">
                            	<option value='<c:out value='${gender}' />' >${gender}</option>
                            </c:forTokens>
                        </select>

                </fieldset>
            </div>
          
          <div class='col-auto'>
                <fieldset class="form-group">
                    <label>Email</label>
                    	<input class="form-control" name="email" id="email" value ='@gmail.com' size=38 required>

                </fieldset>
            </div>
            <div class='col-auto'>
                <div class="form-group">
                    <label>Phone/WhatsApp</label>
                    <div class=input-group>
                    	<input class="form-control col-sm-2 mr-1" name="stdcode" id="stdcode"  value='+91' readonly>
                    	<input type=text class="form-control" size=10 maxlength=10 name="phone" id="phone" onkeypress='return event.charCode >= 48 && event.charCode <= 57'  required>
					</div>
                </div>
            </div> 
            <div class='col-auto'>
                <fieldset class="form-group">
                    <label>Fare</label>
                    	<input class="form-control" name="fare" id="fare" size=38 style='cursor:not-allowed;'  readonly>

                </fieldset>
            </div>
            </div>  
            <div class="row d-flex justify-content-center  mb-4">
            
              <button type="submit" class="btn btn-primary mr-2 button-length" onclick="submitPage();">Add</button><br>	
               <input type="reset" class="btn btn-success  button-length mr-2" value=Reset>  
               <input type="button" class="btn btn-success  button-length" value=Back onclick ='window.close();'>
        	</div>    
                       </form></div>
                   </div>
               </div>
          
<script>
	
	var result = '${result}';
	if ( result.length > 0 )
	{
		
			try
			{
				window.opener.popupResult(result);
			}
			catch(err){}
			window.close();
		
		
	}	
	if ( '${pickup}' !="")
		document.bookingInsert.pickup_from.value = '${pickup}';
	if ( '${drop}' !="")	
		document.bookingInsert.drop_at.value = '${drop}';
	if ( '${fare}' !="")
		document.bookingInsert.fare.value = '${fare}';		
	
	
	function submitPage()
	{
		with(document.bookingInsert)
		{
		action ="Booking";
		submit();
		}
	}

</script>


</body>
</html>