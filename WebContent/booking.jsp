<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="dbconnection.jsp" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<title>Travel</title>
   

</head>
<body >
	 <jsp:include page="header.jsp" /> 


<c:if test="${sessionScope.role != 'Guest'}">
<form name=booking method=post> 
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
        <div class="d-flex justify-content-around">
	        <div class='row py-1 ' style="margin-left:30%;">
	        	<a onclick="javascript:window.open('bookinginsertform.jsp','_blank','top=80,left=340,toolbar=no,status=no,width=800,height=600');" class='btn btn-success button-length' style='width:170px;' > <i class="fa fa-plus mr-2"></i>Add New Booking</a>
	        	<button type=button class='btn btn-success button-length ml-2' onclick='location.reload();' ><i class="fa fa-refresh mr-2"></i>Refresh</button>
	        </div>
        	<div>
        		<input type="text" name=search id=search placeholder='search' class="form-control"></div>
        		
        	</div>
      </div>  
</form>
</c:if>
    <div class="container-fluid table-responsive mt-1 table-wrapper">
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
                    <c:if test="${sessionScope.role != 'Guest'}">
                        <th >Action</th>
                    </c:if>
                </tr>
            </thead>
            

    	<tbody>
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
                        <td> 
                        <c:if test="${sessionScope.role != 'Guest'}">
                           <a class=btn-link style='cursor:pointer;' onclick="javascript:window.open('Customer?mode=QE&customer_id=<c:out value="${user.customer_id}" />','_blank','top=50,left=190,toolbar=no,status=no,width=800,height=400');">
                        </c:if>  <c:out value="${user.customer_id}" /> </a>
                        </td>
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
                        <c:if test="${sessionScope.role!='Guest'}">
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
		
		if ( '${msg}' !='')
			alert('${msg}');
		


	</script>








</body>
</html>