<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<style>
#announcement {
	height: 130px;
	background-color: lightcyan;
}

.blinking {
	font-family: San Serif;
	font-weight: bold;
	color: blue;
	font-size: 28px;
}

.blinking:hover {
	animation: blinkingText 1s infinite;
	font-family: San Serif;
	font-weight: bold;
	width: 100%;
	margin: 0;
	color: blue;
	font-size: 40px;
}

@keyframes blinkingText 
{
   
            0% {
                opacity: 0;
                color :green;
            }
            25%{
            	opacity: .3;
            	color :yellow;
            }
            50% {
                opacity: .5;
                color :red;
            }
            75%{
            	opacity: .8;
            	color :violet;
            }
            100% {
                opacity: 1;
                color :pink;
            }
        }
.dropbtn {
	border: none;
	cursor: pointer;
	background: none;
	width: 100px;
}

.dropdown {
	position: relative;
	display: inline-block;
}

.dropdown-content {
	display: none;
	position: absolute;
	background-color: #f9f9f9;
	min-width: 160px;
	z-index: 1;
}

.dropdown-content a {
	color: black;
	padding: 12px;
	text-decoration: none;
	display: block;
}

.dropdown-content a:hover {
	background-color: #f1f1f1;
}

.dropdown:hover .dropdown-content {
	display: block;
}

.dropdown {
	position: relative;
	display: inline-block;
}

.dropdown:hover .dropdown-content {
	display: block;
}

.desc {
	padding: 15px;
	text-align: center;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.10.2/jquery-ui.min.js"></script>
</head>
<body>
	<div class="d-flex justify-content-between sticky-top" id=announcement>
		<div class="dropdown">
			<a href="home.jsp"> <span><img
					src="https://cdn5.vectorstock.com/i/1000x1000/96/74/travel-logo-vector-19189674.jpg"
					alt="#logo img" width="120px" class="img-thumbnail clearfix ml-4"></span>
			</a>
			<div class="dropdown-content" style="width:75vh;">
				<div id="imge-slide" class="carousel slide" data-interval=1500 data-ride="carousel">
					<div class="carousel-inner">
						<div class="carousel-item active">
							<img class="d-block w-100" src="https://thumbs.dreamstime.com/b/time-to-travel-wooden-sign-beach-background-49509295.jpg" alt="Time To Travel">
						</div>
						<div class="carousel-item">
							<img class="d-block w-100" src="https://static.toiimg.com/thumb/msid-90681912,width-748,height-499,resizemode=4,imgsize-64316/Travelling-in-2022.jpg" alt="Whole world">
						</div>
						<div class="carousel-item">
							<img class="d-block w-100" src="https://youmatter.world/app/uploads/sites/2/2019/11/travel-world.jpg" alt="Take A Break">
						</div>
						<div class="carousel-item">
							<img class="d-block w-100" src="https://i.pinimg.com/736x/c0/29/a1/c029a13f80eca5c461644f003184b58c--mobile-backgrounds-desktop-backgrounds.jpg" alt="Take A Break">
						</div>
						<div class="carousel-item">
							<img class="d-block w-100" src="https://imageio.forbes.com/specials-images/imageserve//62a31db6a2f18e6796812329/0x0.jpg?format=jpg&crop=1943,1183,x0,y113,safe&width=1200" alt="Take A Break">
						</div>
						<div class="carousel-item">
							<img class="d-block w-100" src="https://images.pexels.com/photos/3278215/pexels-photo-3278215.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500" alt="Take A Break">
						</div>
					</div>
<!-- 					<button type=button class="carousel-control-prev" href="#imge-slide" data-slide="prev">  -->
<!-- 						<span class="carousel-control-prev-icon"></span>  -->
<!-- 						<span class="sr-only">Previous</span> -->
<!-- 					</button> -->
<!-- 					<button type=button class="carousel-control-next" href="#imge-slide" data-slide="next">  -->
<!-- 						<span class="carousel-control-next-icon"></span>  -->
<!-- 						<span class="sr-only">Next</span> -->
<!-- 					</button> -->
				</div>
				 <div class="desc">Travel anywhere ! Love the life</div>
			</div>
		</div>
		<div class="liveTime"
			style="display: flex; justify-content: center; align-items: end;">
			<h3>
				Last Login<i class="fa fa-clock-o m-2" style="font-size:30px" aria-hidden="true"></i><span class="blinking">${sessionScope.timeStamp}</span>
			</h3>
		</div>
		<div class="adminContent text-center m-3" style="width: 20%;">
			<div>
				  <c:if test="${sessionScope.role=='Admin'}">
					Hi ${sessionScope.user}<i class="fa fa-thumbs-up ml-2" aria-hidden="true"></i><span class=ml-2>Role:${sessionScope.role}<i class="fa fa-unlock ml-2" style="font-size:25px;" aria-hidden="true"></i></span>    	
				  </c:if>
				   <c:if test="${sessionScope.role!='Admin'}">
				    Hi ${sessionScope.user}<i class="fa fa-thumbs-up ml-2" aria-hidden="true"></i><span class=ml-2>Role:${sessionScope.role}<i class="fa fa-lock ml-2" style="font-size:25px;" aria-hidden="true"></i></span>
				  </c:if>				
			</div>
			<hr>
			<div class="dropdown">
				<span class=dropbtn> 
					<i class="fa fa-hand-o-right mr-3" aria-hidden="true"></i> More 
					<i class="fa fa-caret-down ml-2" aria-hidden="true"></i>
				</span>
				<div class=dropdown-content>
					<a href="home.jsp">
						<i class="fa fa-home mr-2 text-success" aria-hidden="true"></i>
								Home
					</a> 
					<a href="Login?mode=L"><i class="fa fa-power-off mr-2 text-danger" aria-hidden="true"></i>Logout</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>