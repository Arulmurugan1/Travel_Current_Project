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
   
   <div class="container-fluid mt-4">
               <div class="card" >
                   <div class="card-body bg-white">                       
                            <form name ="bookingInsert" method = "post"  ><!-- class="needs-validation" --> <!-- novalidate -->
                       <input type=hidden name=mode id=mode value=I />
             <div class="row d-flex justify-content-center">
             		<h4 style='color:green;'>${msg}</h4>
             </div>
            <div class="row d-flex justify-content-center"> 
              <div class='col-auto '>
                <fieldset class="form-group">
                    <label>Pickup From</label>
                        <select class="form-select form-control" name="pickup_from" id="pickup_from"
                            style="width :340px" onChange="submitOnChange()" >
                             <option value="" selected></option>
                            <sql:query dataSource="${db}" var="rs">
								select Distinct start from route order by start;			
							</sql:query> 
							<c:forEach  var='vehicle' items='${rs.rows}'>
								<option value="${vehicle.start}">${vehicle.start}</option>
							</c:forEach>
                        </select>
                  <div class="valid-feedback">Looks good!</div>
                  <div class="invalid-feedback">Please Provide a Boarding Point!</div>
                </fieldset>
            </div>
            <div class='col-auto '>
                <fieldset class="form-group">
                    <label>Drop To</label>
                        <select class="form-select form-control" name="drop_at" id="drop_at" style="width :340px"
                            onChange="submitOnChange()">
                            <option value="" selected></option>
                            <sql:query dataSource="${db}" var="rs">
								select Distinct end from route where start ='${pickup}' order by end;			
							</sql:query> 
							<c:forEach  var='vehicle' items='${rs.rows}'>
								<option value="${vehicle.end}">${vehicle.end}</option>
							</c:forEach>
                    </select>
                    <div class="valid-feedback">Looks good!</div>
                  <div class="invalid-feedback">Please Provide a Destination!</div>
                </fieldset>
            </div>
            <div class='col-auto '>
                <fieldset class="form-group">
                    <label>Vehicle No</label>              
                    <select class="form-select form-control" name="vehicle_no" id="vehicle_no" style="width :340px" readonly>
				<sql:query dataSource="${db}" var="rs">
						select vehicle_no from route where start ='${pickup}' and end ='${drop}';			
				</sql:query> 
							<c:forEach  var='vehicle' items='${rs.rows}'>
								<option value="${vehicle.vehicle_no}">${vehicle.vehicle_no}</option>
							</c:forEach>
                    </select>
					<div class="valid-feedback">Looks good!</div>
                  <div class="invalid-feedback">Please choose Boarding and Destination</div>
                </fieldset>
            </div>
            <div class='col-auto'>

                <fieldset class="form-group">
                    <label>Driver Id</label>                 
                    <select class="form-select form-control" name="driver_id" id="driver_id" readonly style="width :340px">
                        <sql:query dataSource="${db}" var="rs">
                        	SELECT driver_id,driver_name from driver d,route r where d.vehicle_no =r.vehicle_no and r.start ='${pickup}' and r.end ='${drop}';
                        </sql:query>
                        <c:forEach var="driver" items="${rs.rows}">
                            <option value="${driver.driver_id} - ${driver.driver_name}">${driver.driver_id} - ${driver.driver_name}</option>
                        </c:forEach>
                        
                    </select>
					<div class="valid-feedback">Looks good!</div>
                  <div class="invalid-feedback">Please choose Boarding and Destination</div>
                </fieldset>
            </div>
            <div class='col-auto'>
                <fieldset class="form-group">
                    <label>Customer Name</label>
                    <input type=text class="form-control" name="customer_name" id="customer_name" size=38 >
					<div class="valid-feedback">Looks good!</div>
                  <div class="invalid-feedback">Please Enter Your Name!</div>
                </fieldset>
            </div>
            <div class='col-auto'>
                <fieldset class="form-group">
                    <label>Age</label>
                    	 <input type="number" class=" form-control" id="age" name="age" min="4" max="60" step="1">
					<div class="valid-feedback">Looks good!</div>
                  <div class="invalid-feedback">Please Enter Your Age!</div>
                </fieldset>
            </div>
          
          <div class='col-auto'>
                <fieldset class="form-group">
                    <label>Gender</label>
                    <select class="form-select form-control" name="gender" id="gender"
                            style="width :220px">
                            <option value="" selected></option>
                            <c:forTokens items = "Male,Female,Transgender" delims="," var="gender">
                            	<option value='<c:out value='${gender}' />' >${gender}</option>
                            </c:forTokens>
                        </select>
					<div class="valid-feedback">Looks good!</div>
                  <div class="invalid-feedback">Please Select Your Gender!</div>
                </fieldset>
            </div>
          
          <div class='col-auto'>
                <fieldset class="form-group">
                    <label>Email</label>
                    	<input class="form-control" name="email" id="email" value ='@gmail.com' size=38 >
					<div class="valid-feedback">Looks good!</div>
                  <div class="invalid-feedback">Please Enter Your Email!</div>
                </fieldset>
            </div>
            <div class='col-auto'>
                <div class="form-group">
                    <label>Phone/WhatsApp</label>
                    <div class=input-group>
                    	<input type=text class="form-control" size=38 maxlength=10 name="phone" id="phone" onkeypress='return event.charCode >= 48 && event.charCode <= 57'  >
					</div>
					<div class="valid-feedback">Looks good!</div>
                  <div class="invalid-feedback">Please Enter Your Mobile No!</div>
                </div>
            </div> 
            <div class='col-auto'>
                <fieldset class="form-group">
                    <sql:query dataSource="${db}" var="rs">
						select fare from route where start ='${pickup}' and end ='${drop}';			
					</sql:query>
					<c:forEach var="f" items="${rs.rows}">
							<label>Fare</label>
                            <input class="form-control" name="fare" id="fare" value ='${f.fare}' style='cursor:not-allowed;'  readonly>
                        </c:forEach>
                </fieldset>
            </div>
            </div>  
            <div class="row d-flex justify-content-center  mb-4">
            
              <button type="button" class="btn btn-primary mr-2 button-length" onclick="Submit()">Add</button><br>	
               <input type="reset" class="btn btn-success  button-length mr-2" value=Reset>  
               <input type="button" class="btn btn-success  button-length" value=Back onclick ='window.close()'>
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
	function submitOnChange()
	{
		with(document.bookingInsert)
		{
			action ="Common";
			submit();
		}
	}
	
	function Submit()
	{
		var success = true ;
		for ( var i=0 ; i < document.forms[0].length ; i++ )
			{
				if ( document.forms[0][i].localName== "select" || document.forms[0][i].localName == "input")
					{
						if ( ( document.forms[0][i].value ).trim() == "" || ( document.forms[0][i].value ).trim() == "@gmail.com" )
						{
							$( document.forms[0][i] ).addClass("is-invalid"); 
						}
						else
						{
							if ( $( document.forms[0][i]).hasClass('is-invalid') )
								$( document.forms[0][i]).removeClass('is-invalid')
							$( document.forms[0][i] ).addClass("is-valid");
						}
					}
				if ( $( document.forms[0][i]).hasClass('is-invalid') )
					{
					success = false ;
					return;
					}
			}
		if ( success )
			{
				document.forms[0].action = 'Booking';
				document.forms[0].submit();
			}
	}

</script>

<script>
// Example starter JavaScript for disabling form submissions if there are invalid fields

// (function() {
//   'use strict';
//   window.addEventListener('load', function() {
//     // Fetch all the forms we want to apply custom Bootstrap validation styles to
//     var forms = document.getElementsByClassName('needs-validation');
//     // Loop over them and prevent submission
//     var validation = Array.prototype.filter.call(forms, function(form) {
//       form.addEventListener('submit', function(event) {
//         if (form.checkValidity() == false) {
//           event.preventDefault();
//           event.stopPropagation();
//           alert('checkvalidity');
//         }
//         form.classList.add('was-validated');
//         alert('submit');
//       }, false);
//       alert('validation');
//     });
//     alert('load');
//   }, false);
// })();
</script>

</body>
</html>