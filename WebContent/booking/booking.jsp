<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>


<%@ include file="../dbconnection.jsp" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<title>Travel</title>
   
</head>
<body>
<form name=booking method=post> 
<jsp:include page="../header.jsp" /> 

<c:if test="${sessionScope.role == 'Admin'}">
    <div class="container-fluid mt-2 text-white ">
        <div class='row ml-3 d-flex justify-content-center'>
            <div class='col-sm-3 col-md-4 col-lg-3 col-xl-3'>
                <fieldset class="form-group">
                    <label>Booking No</label>
                    <input class="form-select form-control" name="booking_no" id="booking_no" readonly>
                </fieldset>
            </div>
            
            <div class='col-sm-3 col-md-4 col-lg-3 col-xl-3'>
                <fieldset class="form-group">
                    <label>Customer Id</label>
                        <input class="form-select form-control" name="customer_id" id="customer_id" readonly>
                </fieldset>
            </div>
        
        
        
            <div class='col-sm-3 col-md-4 col-lg-3 col-xl-3'>
                <fieldset class="form-group">
                    <label>Fare</label>
                    <input class="form-select form-control" name="fare" id="fare" readonly>
                </fieldset>
            </div>
            
        </div>
        <div class="d-flex justify-content-between">
        	<div></div>
	        <div class='row py-1' style="margin-left:30px;">
	        	<a onclick="javascript:window.open('Booking?mode=dummy','_blank','top=80,left=340,toolbar=no,status=no,width=800,height=600');" class='btn btn-success button-length' style='width:170px;' > <i class="fa fa-plus mr-2"></i>Add New Booking</a>
	        	<button type=button class='btn btn-success button-length ml-2' onclick='location.reload();' ><i class="fa fa-refresh mr-2"></i>Refresh</button>
	        </div>
        	<div>
        		<i class="fa fa-search icon" aria-hidden="true"></i>
        		<input type="text" name=search id=search placeholder='search' class="form-control">
        	</div>
        		
        </div>
      </div>  
</c:if>
    <div class="container-fluid info">
    	<div class="table-responsive mt-1 table-wrapper">
        <table class="table table-bordered  text-center text-white text-capitalize mb-1 ">
            <thead>
                <tr>
                    <th >Booking No</th>
                    <th >Pickup</th>
                    <th >Drop</th>
                    <th >Customer Id</th>
                    <th >Vehicle No</th>
                    <th >Driver id</th>
                    <th >Fare</th>
                    <th >Customer Name</th>
                    <c:if test="${sessionScope.role == 'Admin'}">
                        <th >Action</th>
                    </c:if>
                </tr>
            </thead>
            

    	<tbody class="table-wrapper">
                <c:forEach var="user" items="${listUser}">

                    <tr>
                    	
                        <td>
                            <c:out value="${user.booking_no}" />
                        </td>
                        <td>
                            <c:out value="${user.pickup_from}" />
                        </td>
                        <td >
                            <c:out value="${user.drop_at}" />
                        </td>
                        <c:choose>
                        	<c:when test="${sessionScope.role == 'Admin'}">
                          <td> <a class="btn-link"
                           	  onclick="Open(['<c:out value="${user.customer_id}" />',
                           			  		'<c:out value="${user.customer_name}" />',
                           			  		'<c:out value="${user.start}" />',
                           			  		'<c:out value="${user.end}" />',
                           			  		'<c:out value="${user.age}" />',
                           			  		'<c:out value="${user.gender}" />',
                           			  		'<c:out value="${user.email}" />',
                           			  		'<c:out value="${user.phone}" />'])">
                            <c:out value="${user.customer_id}" /></a></td>
                        	</c:when>
                        	<c:otherwise>
                        		<td><c:out value="${user.customer_id}" /></td>
                        	</c:otherwise>
                        </c:choose>
                        <td>
                            <c:out value="${user.vehicle_no}" />
                        </td>
                        <td>
                            <c:out value="${user.driver_id}" />
                        </td>
                        <td>
                            <c:out value="${user.fare}" />
                        </td>
                         <td>
                            <c:out value="${user.customer_name}" />
                        </td>
                        <c:if test="${sessionScope.role == 'Admin'}">
                            <td>
<%--                             	<a href="Booking?mode=E&booking_no=<c:out value='${user.booking_no}' />">Edit</a> --%>
                                &nbsp;&nbsp;&nbsp;&nbsp; 
                                <a href="Booking?mode=D&booking_no=<c:out value='${user.booking_no}' />&customer_id=<c:out value='${user.customer_id}' />">Delete</a>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        </div>
		<div class="card info-content d-none">
			<div class=row>
				<div class=col>
			<div class="card-body bg-white px-lg-5">
				<span style="font-size:20px;cursor:pointer;" onclick="$('.info-content').addClass('d-none');"><i class="fa fa-times" aria-hidden="true"></i></span>
				<div class="form-row d-flex justify-content-center mt-2">
					<div class='col-lg-6   form-group'>
						<label>Customer Id</label> 
						<input class="form-control bg-success" style="color:yellow;" id=c1 readonly />
					</div>
					<div class='col-lg-6   form-group'>
						<label>Customer Name</label> 
						<input class="form-control" id=c2 readonly />
					</div>
					<div class='col-lg-6   form-group'>
						<label>Boarding</label> 
						<input class="form-control" id=c3 readonly />
					</div>
					<div class='col-lg-6   form-group'>
						<label>Destination</label> 
						<input class="form-control" id=c4 readonly />
					</div>
					<div class='col-lg-6   form-group'>
						<label>Age</label> 
						<input class="form-control" id=c5 readonly />
					</div>
					<div class='col-lg-6   form-group'>
						<label>Gender</label> 
						<input class="form-control" id=c6 readonly />
					</div>
					<div class='col-lg-6   form-group'>
						<label>Email</label> 
						<input class="form-control" id=c7 readonly />
					</div>
					<div class='col-lg-6   form-group'>
						<label>Phone/WhatsApp</label> 
						<input class="form-control" id=c8 readonly />
					</div>
				</div>
			</div>
		</div>
	</div>
	</div></div>
</form>

<script>
	
	var info ="";
	
	function popupResult(result)
	{
		info = result.split(',');
		with (document.booking)
		{		
			if ( info.length > 0 )
			{
				booking_no.value 		=  info[0];
				customer_id.value	  	=  info[1];
				fare.value	  			=  info[2];
				booking_no.style.backgroundColor = "green";
				booking_no.style.color = "yellow";
				booking_no.style.cursor = "auto"
				customer_id.style.backgroundColor = "green";
				customer_id.style.color = "yellow";
				customer_id.style.cursor = "auto";
				fare.style.backgroundColor = "green";
				fare.style.color = "yellow";
				fare.style.cursor = "auto";
			}
		}
	}
	with (document.booking)
	{
			booking_no.style.backgroundColor = "grey";
			customer_id.style.backgroundColor = "grey";
			fare.style.backgroundColor = "grey";
			booking_no.style.cursor = "not-allowed";
			customer_id.style.cursor = "not-allowed";
			fare.style.cursor = "not-allowed";
	}
	
	
</script>

	<script>
		$(document).ready(function(){
			$('#search').keyup(function(){
				search_table($(this).val())
			});
		});
		
		function search_table(value)
		{
			$('tbody tr').each(function(){
				var found = false ;
				$(this).each(function(){
					if( $(this).text().toLowerCase().indexOf(value.toLowerCase()) >= 0)
						{
						found = true ;
						}
				});
				if ( found )
					{
					$(this).show();
					}
				else{
					$(this).hide();
				}
			});
		}
		function Open(arr)
		{
			$('.info-content').removeClass('d-none');
			var i1=1;
			for ( var i in arr )
				{
					if ( i1 == 9)
						return;
					$('#c'+i1).val(arr[i]);
					i1++;
				}
		}
	</script>








</body>
</html>