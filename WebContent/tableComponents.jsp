<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	boolean adminUser = session.getAttribute("role").equals("Admin") ;
%>

<div class="container-fluid d-flex justify-content-around mt-3">
	<div class="d-flex">
		<div class=form-group>
			<select name=state id=maxRows class="form-control">
				<option value=5000>--Select No of Rows--</option>
				<option value=5000>Show All Records </option>
				<option value=5>5 - records</option>
				<option value=10>10 - records</option>
			</select>
		</div>
	</div>
	<div class="row">
				<div class='col py-1 text-center'>
				  <c:if test="<%= adminUser %>">
					<a
						onclick="javascript:window.open('Booking?mode=dummy','_blank','top=80,left=340,toolbar=no,status=no,width=800,height=600');"
						class='btn btn-success button-length' style='width: 170px;'>
						<i class="fa fa-plus mr-2"></i>Add New Booking
					</a>
				</c:if>
					<button type=button class='btn btn-success button-length ml-2'
						onclick='location.reload();'>
						<i class="fa fa-refresh mr-2"></i>Refresh
					</button>
					<a type=button class='btn btn-success button-length ml-2'
						href=home.jsp>
						<i class="fa fa-backward mr-2"></i>Back
					</a>
				</div>
			</div>
	<div class="my-auto">
		<div class="search-box">
    		<input type="text" name="search" id="search" class="search-txt" placeholder="Type To Search..." autocomplete="true">
		    	<div class="search-btn">
		        	<i class="fa fa-search" aria-hidden="true"></i>
		    	</div>
   		</div>
	</div>
	<div class="pagination my-auto d-none">
		<ul></ul>
	</div>
</div>

<script src="scripts/Pagination.js"></script>
<script src="scripts/search.js"></script>