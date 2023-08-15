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
<body style="overflow: hidden">
	<jsp:include page="../header.jsp" />

	<div class="container-fluid mt-1">
		<div class="d-flex justify-content-center mb-2 mt-2">
				<div class="w-100 text-center">
		<c:if test="<%= adminUser %>">
			
					<button class="btn btn-success button-length add w-25">Add
						Route</button>
				
		</c:if>
		
		<button type=button class='btn btn-success button-length ml-2'
						onclick='location.reload();'>
						<i class="fa fa-refresh mr-2"></i>Refresh
					</button>
					<a type=button class='btn btn-success button-length ml-2'
						href='home.jsp'>
						<i class="fa fa-backward mr-2"></i>Back
					</a>
</div>
			</div>
		<form name=route method=post>
			<div id=insert style="margin-bottom: 20px; color: white;">
				<input type="hidden" name=mode id=mode value=''>

				<div class=form-row style="display: flex; justify-content: center;">
					<div class=col-auto>
						<label for="vehicle_no">Vehicle No</label> <select
							class="form-control" name="vehicle_no" id="vehicle_no"
							style="width: 210px;" required>
							<option value="" selected></option>
							<sql:query dataSource="${db}" var="rs">select * from vehicle where vehicle_no not in ( select vehicle_no from route );</sql:query>
							<c:forEach var='vehicle' items='${rs.rows}'>
								<option value="${vehicle.vehicle_no}">${vehicle.vehicle_no}</option>
							</c:forEach>
						</select>
					</div>
					<div class=col-auto>
						<label for="start">Boarding</label> <select class="form-control"
							name="start" id="start" style="width: 210px;">
							<option value="" selected></option>
							<sql:query dataSource="${db}" var="rs">select start from route_service where start not in (select start from route group by start having count(*) = 9)</sql:query>
							<c:forEach var='board' items='${rs.rows}'>
								<option value="${board.start}">${board.start}</option>
							</c:forEach>
						</select>
					</div>
					<div class=col-auto>
						<label for="end">Destination</label> <select class="form-control"
							name="end" id="end" style="width: 210px;">
							<option value="" selected></option>
							<sql:query dataSource="${db}" var="rs">select end from route_service </sql:query>
							<c:forEach var='board' items='${rs.rows}'>
								<option value="${board.end}">${board.end}</option>
							</c:forEach>
						</select>
					</div>
					<div class=col-auto>
						<label for="fare">Fare</label> <input class="form-control"
							type=text name=fare id=fare maxlength=4 value="">
					</div>
				</div>
				<div class=row>
					<div class="col text-center">
						<button type=button class="btn btn-danger button-length mt-4"
							onclick="Submit(document.route)">Finish</button>
					</div>
				</div>

			</div>
		</form>
	</div>
	<div class="table-responsive">
		<table
			class="table table-bordered  text-center text-white text-capitalize ">
			<thead>
				<tr>
					<th>S.No</th>
					<th>Vehicle No</th>
					<th>Boarding</th>
					<th>Destination</th>
					<th>Fare</th>
					<c:if test="<%= adminUser %>">
						<th>Action</th>
					</c:if>
				</tr>
			</thead>
			<tbody>

				<c:forEach var="lists" items="${list}">

					<tr>
						<td class="no text-center"></td>
						<td><c:out value="${lists.vehicle_no}" /></td>
						<td id='data-start'><c:out value="${lists.start}" /></td>
						<td id='data-end'><c:out value="${lists.end}" /></td>
						<td><c:out value="${lists.fare}" /></td>
						<c:if test="<%= adminUser %>">
							<td>
								<%--                                        <a href=#  onclick="edit('<c:out value="${lists.vehicle_no}" />','<c:out value="${lists.start}" />','<c:out value="${lists.end}" />','<c:out value="${lists.fare}" />')">Edit</a>  --%>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="<%=Constant.ROUTE_SERVLET %>?mode=D&vehicle_no=<c:out value='${lists.vehicle_no}' />">Delete</a>
							</td>
						</c:if>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>

	<script>
	    $(document).ready(function(){
	    	 $("#insert").hide();
	    	  $(".add").click(function(){
	    	   	 $("#insert").toggle(300);
	    	  });
	    	  
	    	  $('select:not(#vehicle_no)').change( function(e){
	    		  search_table_specific()
	    	  });
	    	  
	    	});
	    
		function submitCall(success)
		{
			with (document.route)
			{
				if ( success )
				{
					if( (start.value).trim() == (end.value).trim() )
						{
						alert('Boarding , Destination cannot be Same ! ');
						return;
						}
					else
					{
						action = "<%=Constant.ROUTE_SERVLET %>?mode=I";
						submit();	
					}
				}	
			}
		}
	    
	    function edit(no,board,destination,cost)
	    {
	    	 $(document).ready(function(){    		 
		    	 $("#insert").show(500);
		    	 $("#vehicle_no").empty();
		    	 $("#start").empty();
		    	 $("#vehicle_no").append("<option value="+no+">"+no+"</option>")
		    	 $("#start").append("<option value="+board+">"+board+"</option>")
		    	});
	  
	    	with(document.route)
	    	{
	    		vehicle_no.value	=no;
	    		start.value			=board;
	    		end.value		 	=destination;
	    		fare.value 			=cost ;
	    		mode.value		 	='U';
	    	}
	    }
    </script>
<script src="<%=request.getContextPath() %>/scripts/search.js"></script>
</body>
</html>