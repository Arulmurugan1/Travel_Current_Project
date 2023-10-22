<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ include file="../commonFiles.jsp"%>
<%@ include file="../dbconnection.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">



<style>
body {
	counter-reset: section;
}

td.no::before {
	counter-increment: section;
	content: counter(section);
}
</style>

</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="container-fluid mt-1 text-center">
		<div class="row">
			<div class="col pt-4 pb-2 text-center">
		<c:if test="<%= adminUser %>">
				<button class="btn btn-success button-length " id=newVehicle>Add New Vehicle</button>
		</c:if>
			<button type=button class='btn btn-success button-length ml-2' onclick='location.reload();'>
						<i class="fa fa-refresh mr-2"></i>Refresh
			</button>
			<button type=button class='btn btn-success button-length ml-2' onclick='history.back()'>
						<i class="fa fa-backward mr-2"></i>Back
			</button>
			</div>	
		</div>
		<div id =vehicleForm>
			<jsp:include page="Vehicleform.jsp"></jsp:include>
		</div>
		<div class="table-responsive table-wrapper">
		<table
			class="table table-bordered  text-center text-white text-capitalize">
			<thead class="bg-primary">
				<tr>
					<th>S.No</th>
					<th>Vehicle No</th>
					<th>Vehicle Model</th>
					<th>Vehicle Type</th>
					<th>Vehicle Color</th>
					<c:if test="<%= adminUser %>">
						<th>Action</th>
					</c:if>
				</tr>
			</thead>
			<tbody>

				<c:forEach var="user" items="${listUser}">

					<tr>
						<td class="no text-center"></td>
						<td><c:out value="${user.no}" /></td>
						<td><c:out value="${user.model}" /></td>
						<td><c:out value="${user.type}" /></td>
						<td><c:out value="${user.color}" /></td>

						<c:if test="<%= adminUser %>">
 							<td>
<!--
<%-- 							 <a	href="Vehicleform.jsp?mode=E&no=<c:out value="${user.no}" />&model=<c:out value="${user.model}" />&type=<c:out value="${user.type}" />&color=<c:out value="${user.color}" />">Edit</a> --%>
<!-- 								&nbsp;&nbsp;&nbsp;&nbsp; -->
								<a href="Vehicle?mode=D&no=<c:out value="${user.no}" />">Delete</a>
							</td>
						</c:if>

					</tr>
				</c:forEach>

			</tbody>
		</table></div>
	</div>
	
	<script type="text/javascript">
$(document).ready(()=>{
		
		$('#vehicleForm').hide();
		
		$('#newVehicle').click(()=>{
			$('#vehicleForm').fadeToggle(500);
		});
	})
		
	</script>	
	
	
</body>
</html>