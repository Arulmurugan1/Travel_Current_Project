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
   
   <div class="container mt-4">
               <div class="card ">
                   <div class="card-body bg-white">
                    
                       <div class="text-center">
                           <h2>  Add New Driver</h2>
                       </div>
                       <div class="text-center ">
                            <form action="ListDriverServlet" method = "post">
                           
                       <input type=hidden name=mode value=I />
          <div class="offset-1">
               <div class="row ">
                       
                    <div class=col-auto>
                       <fieldset class="form-group">
                         <label>Driver Id</label> <input type="text" class="form-control" name="driver_id" disabled>
                       </fieldset>
                    </div>
                    <div class=col-auto>
                         <fieldset class="form-group">
                         <label>Driver Name</label> <input type="text"  class="form-control" name="driver_name" required>
                       </fieldset>
                    </div>
                    <div class=col-auto>
                       <fieldset class="form-group">
                           <label>Age</label> <input type="number"  class="form-control" name="age" required>
                       </fieldset>
                    </div>
                    <div class=col-auto>
                       <fieldset class="form-group">
                          <label>Gender</label>
                    			<select class="form-select form-control" name="gender" id="gender"
                            			style="width :210px" required>
                           		 <option value="" selected></option>
                            <c:forTokens items = "Male,Female,Transgender" delims="," var="gender">
                            	<option value='<c:out value='${gender}' />' >${gender}</option>
                            </c:forTokens>
                        </select>
                       </fieldset>
                    </div>
                    <div class=col-auto>
                       <fieldset class="form-group">
                          <label>Phone</label> <input type="text"  class="form-control" name="phone" maxlength="10" onkeypress='return event.charCode >= 48 && event.charCode <= 57' required>
                       </fieldset>
                    </div>
                    <div class=col-auto>
                       <fieldset class="form-group">
                           <label>City</label> <input type="text"  class="form-control" name="city"  required>
                       </fieldset>
                    </div>
                    <div class=col-auto>
		                <fieldset class="form-group">
		                    <label>Vehicle No</label>              
		                    <select class="form-control" name="vehicle_no" id="vehicle_no"  style="width:210px;" required >
		                        <option value="" selected ></option>
									<sql:query dataSource="${db}" var="rs">select * from car where vehicle_no not in ( select vehicle_no from driver );</sql:query> 
									<c:forEach var='vehicle' items='${rs.rows}'>
										<option value="${vehicle.vehicle_no}">${vehicle.vehicle_no}</option>
									</c:forEach>
		                    </select>
		               </fieldset>
              </div>
	</div></div>
                     <div class="row px-2 ">
                     	<div class="col text-center">
                               <button type="submit" class="btn btn-success button-length">Add</button>
                               <input type="reset" class="btn btn-success button-length" value=Reset >  
                               <a href="ListDriverServlet"  class="btn btn-success button-length">Back</a> 
                        </div>
                     </div>
                       
                       </form></div>
                   </div>
               </div>
           </div>
           
      <script>
    if ( "${listUser}" !="" )
    	{
    		driver_id.value ='${listUser.id}';
    		driver_name.value ='${listUser.name}';
    		age.value ='${listUser.age}';
    		phone.value ='${listUser.phone}';
    		city.value ='${listUser.city}';
    		vehicle_no.value ='${listUser.no}';
    		gender.value ='${listUser.gender}';
    		
    	}
      </script>
           
</body>
</html>