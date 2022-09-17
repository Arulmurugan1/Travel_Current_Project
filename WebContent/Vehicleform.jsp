<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ include file="dbconnection.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Travel</title>

<link
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.js"
	type="text/javascript"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="ajaxCall.js"></script>
</head>
<body style='overflow:hidden'>
	<jsp:include page="header.jsp" />
	<div class="container" >
		<div class="card border p-2" style="border-radius:30px">
			<form name=vehicle method="post">
				<div class="card-body bg-white text-center">
					<h2>Add New Vehicle</h2>
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
				<div class="row mt-2 mb-4 mt-3">
					<div class="col text-center">
					<c:if test="${mode!='E'}"><button type="button" class="btn btn-success button-length" onClick="Submit()">Add</button></c:if>
				    <c:if test="'${mode=='E'}"><button type="button" class="btn btn-danger button-length" onClick="Submit()">Update</button></c:if>
						<input type="reset" class="btn btn-success button-length"
							value=Reset> <a href="Vehicle"
							class="btn btn-success button-length">Back</a>
					</div>
				</div>

			</form>
		</div>
	</div>

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