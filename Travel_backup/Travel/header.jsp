<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String imagPath = "/Images/Carousel/";
%>  

<style>  
         .ui-widget-header,.ui-state-default, ui-button{  
            background:crimson;  
            border: 2px solid brown;  
            color: white;  
            font-weight: bold; 
         }
</style>  
	<div id=editProfileDialog>
		<jsp:include page="editprofile.jsp"></jsp:include>
	</div>
	
	<div id=editProfileAfterDialog>
		
	</div>
	<div class="d-flex justify-content-between sticky-top" id=announcement>
		<div class="dropdown">
			<a href="home.jsp" title ="Back To Home"> <span><img
					src="<%=request.getContextPath() + imagPath%>flight.jpg"
					alt="#Flight_logo" width="90px" class="img-thumbnail clearfix ml-2"></span>
			</a>
			<div class="dropdown-content" style="width:65vh;">
				<div id="imge-slide" class="carousel slide" data-interval=1000 data-ride="carousel">
					<div class="carousel-inner">
						<div class="carousel-item active">
							<img class="d-block w-100" src="<%=request.getContextPath() + imagPath%>1.jpg" alt="Time To Travel">
						</div>
						<div class="carousel-item">
							<img class="d-block w-100" src="<%=request.getContextPath() + imagPath%>2.webp" alt="Whole world">
						</div>
						<div class="carousel-item">
							<img class="d-block w-100" src="<%=request.getContextPath() + imagPath%>3.jpg" alt="Take A Break">
						</div>
						<div class="carousel-item">
							<img class="d-block w-100" src="<%=request.getContextPath() + imagPath%>4.jpg" alt="Take A Break">
						</div>
						<div class="carousel-item">
							<img class="d-block w-100" src="<%=request.getContextPath() + imagPath%>5.jpg" alt="Take A Break">
						</div>
						<div class="carousel-item">
							<img class="d-block w-100" src="<%=request.getContextPath() + imagPath%>6.jpeg" alt="Take A Break">
						</div>
					</div>
					<button type=button class="carousel-control-prev" href="#imge-slide" data-slide="prev"> 
						<span class="carousel-control-prev-icon"></span> 
						<span class="sr-only">Previous</span>
					</button>
					<button type=button class="carousel-control-next" href="#imge-slide" data-slide="next"> 
						<span class="carousel-control-next-icon"></span> 
						<span class="sr-only">Next</span>
					</button>
				</div>
				 <div class="desc">Travel anywhere ! Love the life</div>
			</div>
		</div>
		<div class="liveTime my-auto">
			<h3>
				<b class="text-lg">Last Logged at :</b> <span class="blinking">${sessionScope.timeStamp}</span>
			</h3>
		</div>
		<div class="adminContent text-center p-2">
			<div>
				<span class="fa fa-wifi">Connected</span>
			<c:choose>
				<c:when test="${sessionScope.role == 'Admin'}">
				Hi ${sessionScope.user}<i class="fa fa-thumbs-up ml-2" aria-hidden="true"></i><span class=ml-2>Role:${sessionScope.role}<i class="fa fa-unlock ml-2" style="font-size:25px;" aria-hidden="true"></i></span>
				</c:when>
				<c:otherwise>
				 Hi ${sessionScope.user}<i class="fa fa-thumbs-up ml-2" aria-hidden="true"></i><span class=ml-2>Role:${sessionScope.role}<i class="fa fa-lock ml-2" style="font-size:25px;" aria-hidden="true"></i></span>
				</c:otherwise>
			</c:choose>			
			</div>
			<div class="pt-3">
				<a href="home.jsp" class="mr-2"><i class="fa fa-home mr-2 text-success" aria-hidden="true"></i>
						Home</a>
					<a href="javascript:editProfile()" class="mr-2">
						<i class="fa fa-user mr-2 text-dark" aria-hidden="true"></i>Edit Profile
					</a> 
					<a href="Login?mode=L" class="mr-2"><i class="fa fa-power-off mr-2 text-danger" aria-hidden="true"></i>Logout</a>
			</div>
		</div>
	</div>
	
<script src="<%=request.getContextPath() %>/scripts/header.js<%= session.getAttribute("CACHE_VERSION") %>" type="text/javascript"></script>
