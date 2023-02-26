<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>    


<%@ include file="../dbconnection.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer</title>
</head>
<body class="overflow-hidden">
<sql:query dataSource="${db}" var="rs">
		select * from customer where customer_id = '${customer_id}';			
</sql:query>
<c:forEach  var='ls' items='${rs.rows}'>
	<div class="container-fluid mt-4">
		<div class="card">
			<div class="card-body bg-white">
				<div class="row d-flex justify-content-center">
					<div class='col-sm-6 form-group'>
						<label>Customer Id</label> 
						<input class="form-control bg-success" style="color:yellow;" value="${ls.customer_id}" style='cursor: not-allowed;' disabled />
					</div>
					<div class='col-sm-6 form-group'>
						<label>Customer Name</label> 
						<input class="form-control" value="${ls.customer_name}" style='cursor: not-allowed;' disabled />
					</div>
					<div class='col-sm-6 form-group'>
						<label>Boarding</label> 
						<input class="form-control" value="${ls.start}" style='cursor: not-allowed;' disabled />
					</div>
					<div class='col-sm-6 form-group'>
						<label>Destination</label> 
						<input class="form-control" value="${ls.end}" style='cursor: not-allowed;' disabled />
					</div>
					<div class='col-sm-6 form-group'>
						<label>Age</label> 
						<input class="form-control" value="${ls.age}" style='cursor: not-allowed;' disabled />
					</div>
					<div class='col-sm-6 form-group'>
						<label>Gender</label> 
						<input class="form-control" value="${ls.gender}" style='cursor: not-allowed;' disabled />
					</div>
					<div class='col-sm-6 form-group'>
						<label>Email</label> 
						<input class="form-control" value="${ls.email}" style='cursor: not-allowed;' disabled />
					</div>
					<div class='col-sm-6 form-group'>
						<label>Phone/WhatsApp</label> 
						<input class="form-control" value="${ls.phone}" style='cursor: not-allowed;' disabled />
					</div>
				</div>
			</div>
		</div>
	</div>
</c:forEach>
</body>
</html>