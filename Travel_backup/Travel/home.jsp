<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ include file="dbconnection.jsp"%>
<body>
	<jsp:include page="header.jsp" />

	<div class="container">
		<div class="content">
			<div class="card-body">
				<form name=home id=center>
					<input type=hidden name=mode value=''>
					<div class="row">
							
					</div>
				</form>
			</div>

		</div>
	</div>	

<script src="<%=request.getContextPath() %>/scripts/home.js<%= CACHE_VERSION %>" type="text/javascript"></script>

</body>