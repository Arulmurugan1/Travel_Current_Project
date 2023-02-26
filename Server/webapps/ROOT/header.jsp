<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	  <link rel="stylesheet" href="styleSheet/ajax-loader.css" type="text/css">
      <link href="http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">  
      <script src="http://code.jquery.com/jquery-1.10.2.js"></script>  
      <script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>  

 <style>  
         .ui-widget-header,.ui-state-default, ui-button{  
            background:crimson;  
            border: 2px solid brown;  
            color: white;  
            font-weight: bold; 
         }  
</style>  
	<div id=editProfileDialog>
		<jsp:include page="/editprofile.jsp"></jsp:include>
	</div>
	<div id=editProfileAfterDialog>
		
	</div>
		
      <div class="loader-ajax"></div>
	<div class="d-flex justify-content-between sticky-top" id=announcement>
		<div class="dropdown">
			<a href="home.jsp" title ="Back To Home"> <span><img
					src="./Images/Carousel/travel-logo-vector-19189674.jpg"
					alt="#logo img" width="90px" class="img-thumbnail clearfix ml-2"></span>
			</a>
			<div class="dropdown-content" style="width:65vh;">
				<div id="imge-slide" class="carousel slide" data-interval=1000 data-ride="carousel">
					<div class="carousel-inner">
						<div class="carousel-item active">
							<img class="d-block w-100" src="./Images/Carousel/time-to-travel-wooden-sign-beach-background-49509295.jpg" alt="Time To Travel">
						</div>
						<div class="carousel-item">
							<img class="d-block w-100" src="./Images/Carousel/Travelling-in-2022.webp" alt="Whole world">
						</div>
						<div class="carousel-item">
							<img class="d-block w-100" src="./Images/Carousel/travel-world.jpg" alt="Take A Break">
						</div>
						<div class="carousel-item">
							<img class="d-block w-100" src="./Images/Carousel/c029a13f80eca5c461644f003184b58c--mobile-backgrounds-desktop-backgrounds.jpg" alt="Take A Break">
						</div>
						<div class="carousel-item">
							<img class="d-block w-100" src="./Images/Carousel/0x0.jpg" alt="Take A Break">
						</div>
						<div class="carousel-item">
							<img class="d-block w-100" src="./Images/Carousel/pexels-photo-3278215.jpeg" alt="Take A Break">
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
			<c:choose>
				<c:when test="${sessionScope.role == 'Admin'}">
				Hi ${sessionScope.user}<i class="fa fa-thumbs-up ml-2" aria-hidden="true"></i><span class=ml-2>Role:${sessionScope.role}<i class="fa fa-unlock ml-2" style="font-size:25px;" aria-hidden="true"></i></span>
				</c:when>
				<c:otherwise>
				 Hi ${sessionScope.user}<i class="fa fa-thumbs-up ml-2" aria-hidden="true"></i><span class=ml-2>Role:${sessionScope.role}<i class="fa fa-lock ml-2" style="font-size:25px;" aria-hidden="true"></i></span>
				</c:otherwise>
			</c:choose>			
			</div>
			<div class="dropdown pt-3">
				<span class=dropbtn> 
					<i class="fa fa-hand-o-right mr-3" aria-hidden="true"></i> More 
					<i class="fa fa-caret-down ml-2" aria-hidden="true"></i>
				</span>
				<div class=dropdown-content>
					<a href="home.jsp">
						<i class="fa fa-home mr-2 text-success" aria-hidden="true"></i>
								Home
					</a>
					<a href="javascript:editProfile()">
						<i class="fa fa-user mr-2" aria-hidden="true"></i>
								Edit Profile
					</a> 
					<a href="Login?mode=L"><i class="fa fa-power-off mr-2 text-danger" aria-hidden="true"></i>Logout</a>
				</div>
			</div>
		</div>
	</div>
	
	<script>
		$('#editProfileDialog').hide();
		$('.loader-ajax').hide();
		function editProfile()
		{
					setProfileValues();
					$('#editProfileDialog').dialog({
						autoOpen : false ,
						buttons : {
							Update  : ()=> {
								callAjaxUpdate(document.editProfile) ;
							},
							OK : ()=>{
								setProfileValues();
								$('#editProfileDialog').dialog('close');
							}
						},
						title : " Edit / Update Profile",
						position :{
							my : "center",
							at : "center"
						},
 						closeonescape : true ,
						draggable : false ,
						modal : true ,
					});
					$('#editProfileDialog').dialog('open');
		}
	</script>
	
