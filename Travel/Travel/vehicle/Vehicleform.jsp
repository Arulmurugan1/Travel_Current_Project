<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>    

<%@ include file="../commonFiles.jsp"%>
<%@ include file="../dbconnection.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>
			<form name=vehicle method="post">
				<div class="card-body text-white">
					<div class="row d-flex justify-content-center">

						<div class=col-auto>
							<label>Vehicle No</label> <input type="text" class="form-control"
								name="vehicle_no" id=vehicle_no maxlength="4"
								onkeyup="sendInfo()"
								onkeypress="return event.charCode >= 48 && event.charCode <= 57">
							<span id=error class="mt-2 text-danger"></span>
						</div>



						<div class=col-auto>
							<label>Vehicle Model</label> <select
								class="form-select form-control w-100" name="vehicle_model"
								id=vehicle_model >
								<option value="" selected></option>
								<sql:query dataSource="${db}" var="rs">
										select title from car_brand;			
									</sql:query>
								<c:forEach var='vehicle' items='${rs.rows}'>
									<option value="${vehicle.title}">${vehicle.title}</option>
								</c:forEach>
							</select>
						</div>

						<div class=col-auto>
							<label>Vehicle Type</label> <select
								class="form-select form-control " name="vehicle_type"
								id=vehicle_type style="width: 170px;" value='${type}'>
								<option value="" selected></option>
							</select>
						</div>
						<div class=col-auto>
							<label>Vehicle Color</label> 
							<select class="form-select form-control w-100" name="vehicle_color" id=vehicle_color value='${color}'>
									<option value="" selected></option>
								<c:forTokens items="Apricot,Black,Blue,Bluetiful,Blue Green,Blue Violet,Brown,Carnation Pink,Cerulean,Gray,Green,Green Yellow,Indigo,Orange,Red,Red Orange,Red Violet,Scarlet,Violet (purple),Violet Red,White,Yellow,Yellow Green,Yellow Orange" delims="," var="name">  
									  <option value="<c:out value="${name}"/>"><c:out value="${name}"/></option>  
								</c:forTokens> 
							</select>
						</div>

					</div>
				</div>
				<div class="row m-3">
					<div class="col text-center">
						<button type="button" class="btn btn-success button-length" onClick="Submit(document.vehicle)">Add</button>
						<input type="reset" class="btn btn-success button-length"
							value=Reset> 
					</div>
				</div>

			</form>

	<script>
    		
  function submitCall(success)
  {
     if (success)
     {
    	 if ( document.vehicle.vehicle_no.readOnly )
				document.vehicle.action ="Vehicle?mode=I";
		 else
				document.vehicle.action ="Vehicle?mode=U";
			
				document.vehicle.submit();
     }
     return;
  }
    </script>
</body>
</html>