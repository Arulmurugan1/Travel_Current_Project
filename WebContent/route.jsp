<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="dbconnection.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Travel</title>


	<style>
    	body{
    		counter-reset :section ;
    	}
    	
    	td.no::before {
    		counter-increment :section ;
    		content : counter(section);
    	
    	}
    	
    </style>
    
</head>
<body style="overflow:hidden">
<jsp:include page="header.jsp" /> 
    
    <div class="container-fluid mt-1">
   
    <div class="d-flex justify-content-center mb-2 mt-2">

    	<script> 
    	var msg = '${msg}';
    	var message = msg.trim();
    	if ( message !=null && message != '')
    		{
    			alert(message);
    			message =' ';
    		}    	
    	</script>
    	<div>
    		<button class="btn btn-success button-length">Add Route</button>
    	</div></div>
  
  <form name=route method=post action=Route>
  	<div id=insert style="margin-bottom :20px;color:white;">
  			<input type="hidden" name=mode id=mode value=''>
  		
  			<div class =form-row style="display:flex;justify-content:center;">
  				<div class=col-auto >
  					<label for="vehicle_no" >Vehicle No</label>
  						<select class="form-control" name="vehicle_no" id="vehicle_no"  style="width:210px;" required >
		                        <option value="" selected ></option>
									<sql:query dataSource="${db}" var="rs">select * from car where vehicle_no not in ( select vehicle_no from route );</sql:query> 
									<c:forEach var='vehicle' items='${rs.rows}'>
										<option value="${vehicle.vehicle_no}">${vehicle.vehicle_no}</option>
									</c:forEach>
		                </select>
  				</div>
  				<div class=col-auto >
  					<label for="start" >Boarding</label>
  					<select class="form-control" name="start" id="start"  style="width:210px;" required >
		                        <option value="" selected ></option>
									<sql:query dataSource="${db}" var="rs">select start,count(*) count from route group by start having count(*)<6;</sql:query> 
									<c:forEach var='board' items='${rs.rows}'>
										<option value="${board.start}">${board.start}</option>
									</c:forEach>
		           	</select>
  				</div>
  				<div class=col-auto >
  					<label for="end" >Destination</label>
  					<select class="form-control" name="end" id="end"  style="width:210px;" required >
		                        <option value="" selected ></option>
									<sql:query dataSource="${db}" var="rs">select routes from route_service;</sql:query> 
									<c:forEach var='board' items='${rs.rows}'>
										<option value="${board.routes}">${board.routes}</option>
									</c:forEach>
		           	</select>
  				</div>
  			</div>
  			<div class=row>
  				<div class="col text-center">
  					<button type=submit class="btn btn-danger button-length mt-4" onclick="Submit();">Finish</button>
  					<button type=button class='btn btn-success button-length ml-2 mt-4' onclick='location.reload();' ><i class="fa fa-refresh mr-2"></i>Refresh</button>
  				</div>
  			</div>
  		
  	</div>
  	<div id=update style="display:none;margin-bottom :20px;color:white;">
  			<div class ="form-row d-flex justify-content-center">
  				<div class=col-auto >
  					<label for="vehicle_no" >Vehicle No</label>
  					<input type="text" class="form-control" readonly onmouseover="this.style.cursor='not-allowed'" maxlength=4 onkeypress="event.charChode >=48 && event.charChode <=57" name=uvehicle_no>
  				</div>
  				<div class=col-auto >
  					<label for="start" >Boarding</label>
  					<select class="form-select form-control" name="ustart" id="ustart" style="width :210px"
                            onChange="javascript:routeClick();" required>
                            <option value="" selected></option>
                            <c:forTokens items = "Chennai,Salem,Madurai,Kanyakumari,Coimbatore,Erode,Vellore" delims="," var="drop">
                            	<option value='<c:out value='${drop}' />' >${drop}</option>
                            </c:forTokens>
                        </select>
  				</div>
  				<div class=col-auto >
  					<label for="end" >Destination</label>
  					<select class="form-select form-control" name="uend" id="uend" style="width :210px"
                            onChange="javascript:routeClick();" required>
                            <option value="" selected></option>
                            <c:forTokens items = "Chennai,Salem,Madurai,Kanyakumari,Coimbatore,Erode,Vellore" delims="," var="drop">
                            	<option value='<c:out value='${drop}' />' >${drop}</option>
                            </c:forTokens>
                        </select>
  				</div>
  			</div>
  			<div class=row>
  				<div class="col text-center">
  					<button type=submit class="btn btn-danger button-length mt-4" onclick="Update();">Update</button>
  					<button type=button class='btn btn-success button-length ml-2 mt-4' onclick='location.reload();' ><i class="fa fa-refresh mr-2"></i>Refresh</button>
  				</div>
  			</div>
  	</div>
  	  </form>  </div>
  	<div class="table-responsive table-wrapper">
      <table class="table table-bordered  text-center text-white text-capitalize ">
       <thead>
                            <tr>
                            	<th>S.No</th>
                                <th>Vehicle No</th>
                                <th>Boarding</th>
                                <th>Destination</th>
                                
                                    <c:if test = "${sessionScope.role!='Guest'}" ><th>Action</th></c:if>
                            </tr>
                        </thead>
                        <tbody>
                            
                            <c:forEach var="lists" items="${list}">

                                <tr>
                                	<td class ="no text-center"></td>
                                    <td>
                                        <c:out value="${lists.vehicle_no}" />
                                    </td>
                                    <td >
                                        <c:out value="${lists.start}" />
                                    </td>
                                    <td >
                                        <c:out value="${lists.end}" />
                                    </td>
                                       <c:if test ="${sessionScope.role!='Guest'}" ><td><a href=#  onclick="edit('<c:out value="${lists.vehicle_no}" />','<c:out value="${lists.start}" />','<c:out value="${lists.end}" />')">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="Route?mode=D&vehicle_no=<c:out value='${lists.vehicle_no}' />">Delete</a></td></c:if>
                                </tr>
                            </c:forEach>
                            
                        </tbody>
    </table></div>
 
    <script>
	    $(document).ready(function(){
	    	 $("#insert").hide();
	    	  $("button").click(function(){
	    		  $("#update").hide();
	    	    $("#insert").fadeToggle(1000);
	    	  });
	    	});
	    
	    function Submit()
	    {
	    	with(document.route)
	    	{
	    		mode.value = 'I';
	    		submit();
	    	}
	    }
	    function Update()
	    {
	    	with(document.route)
	    	{
	    		mode.value = 'U';
	    		submit();
	    	}
	    }
	    
	    function edit(no,start,end)
	    {
	    	 $(document).ready(function(){
		    	 $("#insert").hide();
		    	    $("#update").show(500);
		    	});
	    	 
	    	 var a = no ;
	    	 var b = start ;
	    	 var c  = end;
	    	with(document.route)
	    	{
	    		uvehicle_no.value	=a;
	    		ustart.value		=b;
	    		uend.value		 	=c;
	    		mode.value		 	='U';
	    	}
	    }
	    
    </script>

</body>
</html>