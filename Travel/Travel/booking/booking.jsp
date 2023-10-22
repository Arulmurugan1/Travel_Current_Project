<%@ include file="../commonFiles.jsp"%>
<%@ include file="../dbconnection.jsp"%>
<jsp:include page="../header.jsp" />

<form name=booking method=post>
	<c:if test="<%= adminUser %>">
		<div class="container-fluid mt-2 text-white ">
			<div class='row ml-3 justify-content-center'>
				<div class='col-sm-3 col-md-4 col-lg-3 col-xl-3'>
					<fieldset class="form-group">
						<label>Booking No</label> <input class="form-select form-control"
							name="booking_no" id="booking_no" readonly>
					</fieldset>
				</div>
				<div class='col-sm-3 col-md-4 col-lg-3 col-xl-3'>
					<fieldset class="form-group">
						<label>Customer Id</label> <input class="form-select form-control"
							name="customer_id" id="customer_id" readonly>
					</fieldset>
				</div>



				<div class='col-sm-3 col-md-4 col-lg-3 col-xl-3'>
					<fieldset class="form-group">
						<label>Fare</label> <input class="form-select form-control"
							name="fare" id="fare" readonly>
					</fieldset>
				</div>

			</div>
		</div>
	</c:if>
	
	<jsp:include page="../tableComponents.jsp"></jsp:include>
	
	<div class="info mt-2">
		<div class="table-responsive mt-1">
			<table
				class="table table-bordered  text-center text-white text-capitalize mb-1 ">
				<thead>
					<tr>
						<th>Booking No</th>
						<th>Pickup</th>
						<th>Drop</th>
						<th>Customer Id</th>
						<th>Vehicle No</th>
						<th>Driver id</th>
						<th>Fare</th>
						<th>Customer Name</th>
						<th>Booking Status</th>
						<c:if test="<%= adminUser %>">
							<th>Action</th>
						</c:if>
					</tr>
				</thead>


				<tbody>
					<c:forEach var="user" items="${listUser}">

						<tr id='${user.booking_no}'>

							<td>${user.booking_no}</td>
							<td>${user.pickup_from}</td>
							<td>${user.drop_at}</td>
							<td><a class="btn w-50 user-detail" style="border-radius : 25px 0;background: linear-gradient(360deg,pink,orange);"
								id='${user.booking_no}'>
									${user.customer_id}
								<span id=detail class=d-none>${user.customer_id},${user.customer_name},${user.start},${user.end},${user.age},${user.gender},${user.email},${user.phone}</span>
								</a>
                           </td>
							<td>${user.vehicle_no}</td>
							<td>${user.driver_id}</td>
							<td>${user.fare}</td>
							<td>${user.customer_name}</td>
							<td>
								<c:choose>
									<c:when test="<%= adminUser %>">
										<div class="btn-group dropleft w-75">
											<button type="button" class="btn dropdown-toggle " data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
											<c:choose>
												<c:when test="${user.status !=''}">
													${user.status}
												</c:when>
												<c:otherwise>
													Booking Status
												</c:otherwise>
											</c:choose>
											</button>
											<div class="dropdown-menu text-center">
												<a class="dropdown-item text-danger status" status = 'WIP' id='${user.booking_no}'>WIP</a> 
												<a class="dropdown-item text-primary status" status = 'RAC' id ='${user.booking_no}'>RAC</a> 
												<a class="dropdown-item text-success status" status = 'Confirmed' id='${user.booking_no}'>Confirmed</a>
											</div>
										</div>
									</c:when>
									<c:otherwise>
										${user.status}
									</c:otherwise>
								</c:choose>
							</td>
							<c:if test="<%= adminUser %>">
								<td><a
									href="Booking?mode=D&booking_no=<c:out value='${user.booking_no}' />&customer_id=<c:out value='${user.customer_id}' />">Delete</a>
								</td>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="card info-content" style="display:none;">
			<div class=row>
				<div class=col>
					<div class="card-body bg-white px-lg-5">
						<span style="font-size: 20px; cursor: pointer;"
							onclick="$('.info-content').hide();"><i
							class="fa fa-times" aria-hidden="true"></i></span>
						<div class="form-row justify-content-center mt-2">
							<div class='col-lg-6   form-group'>
								<label>Customer Id</label> <input
									class="form-control bg-success" style="color: yellow;" id=c0
									readonly />
							</div>
							<div class='col-lg-6   form-group'>
								<label>Customer Name</label> <input class="form-control" id=c1
									readonly />
							</div>
							<div class='col-lg-6   form-group'>
								<label>Boarding</label> <input class="form-control" id=c2
									readonly />
							</div>
							<div class='col-lg-6   form-group'>
								<label>Destination</label> <input class="form-control" id=c3
									readonly />
							</div>
							<div class='col-lg-6   form-group'>
								<label>Age</label> <input class="form-control" id=c4 readonly />
							</div>
							<div class='col-lg-6   form-group'>
								<label>Gender</label> <input class="form-control" id=c5 readonly />
							</div>
							<div class='col-lg-6   form-group'>
								<label>Email</label> <input class="form-control" id=c6 readonly />
							</div>
							<div class='col-lg-6   form-group'>
								<label>Phone/WhatsApp</label> <input class="form-control" id=c7
									readonly />
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form>

<script src="<%=request.getContextPath() %>/scripts/booking.js<%= CACHE_VERSION %>" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/scripts/Pagination.js<%= CACHE_VERSION %>"></script>
<script src="<%=request.getContextPath() %>/scripts/search.js<%= CACHE_VERSION %>"></script>
