<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<div
		class="container-fluid mt-1 text-center">
		<c:if test="<%= adminUser %>">
			<div class="m-2">
				<a class="btn btn-success button-length w-25"
					href="Vehicle?mode=dummy">Add New Vehicle</a>
			</div>
		</c:if>
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
</body>
</html>