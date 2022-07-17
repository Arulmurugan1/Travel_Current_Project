<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<style>
	
#announcement{
	height:100px; 
	background-color: lightcyan;

}
.blinking{
font-family: San Serif;
font-weight: bold;
color :blue;
font-size: 28px;
}
.blinking:hover {
            animation: blinkingText .5s infinite;
            font-family: San Serif;
            font-weight: bold;
            width: 100%;
            margin: 0;
            color :blue;
            font-size: 40px;
        }
       @keyframes blinkingText {
   
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
</style>
	<script>
// 	function blinktext() {
// 		  var f = document.getElementById('announcement');
// 		  setInterval(function() {
// 		    f.style.visibility = (f.style.visibility == 'hidden' ? '' : 'hidden');
// 		  }, 5000);
// 		}

	
	</script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
   	<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.10.2/jquery-ui.min.js"></script>
</head>
<body> 
	<div class="d-flex justify-content-between" id=announcement>
		<div class="image">
         <a href="home.jsp">
            <span><img src="https://cdn5.vectorstock.com/i/1000x1000/96/74/travel-logo-vector-19189674.jpg" alt="#logo img" width="90px" class="img-thumbnail clearfix ml-4"></span>
        </a>
        </div>
        <div class="liveTime" style="display:flex;justify-content:center;align-items:end;">
        	<h6>Last Login : <span class="blinking">${sessionScope.timeStamp}</span></h6>
<%--         	<marquee width="300" scrollamount="100" scrolldelay="1000" loop="7">${sessionScope.timeStamp}</marquee> --%>
        </div>
       <div class="adminContent">
         <div class="row m-3">   
                <span>Hi ${sessionScope.user}, Role : ${sessionScope.role}</span>
          </div> 
           <div class="row m-4">
			        <div><a href="home.jsp" class="mr-2">Home</a></div> |
			        <div><a href="LoginServlet?mode=L" class="ml-3">Logout</a></div>
	       		</div>
       </div>
    </div>
   <script>

	  $(document).ready(function() {
			    	        function disableBack() { window.history.forward() }

			    	        window.onload = disableBack();
			    	        window.onpageshow = function(evt) { if (evt.persisted) disableBack() }
			    	    });
			    

    </script>
</body>
</html>