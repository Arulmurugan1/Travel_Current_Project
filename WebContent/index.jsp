<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="dbconnection.jsp" %>
<!DOCTYPE html >
<html>
<head>

<meta charset="ISO-8859-1">
<title>Travel</title>



</head>
<body>
	
	<div class="container content">
       <div class="card">
           <div class="card-body size">
           
           <nav>
			  <div class="nav nav-tabs justify-content-end" id="nav-tab" role="tablist">
			    <a class="nav-item nav-link active" id="nav-login-tab" data-toggle="tab" href="#loginTab" role="tab" aria-controls="loginTab" aria-selected="true">
			    	Login
			   	</a>
			    <a class="nav-item nav-link" id="nav-register-tab" data-toggle="tab" href="#registerTab" role="tab" aria-controls="registerTab" aria-selected="false">
			    	Register
			    </a>
			  </div>
		</nav>
	 <form name="form" method=post >
	 		<input type=hidden name=mode id=mode value=''>
	 
		<div class="tab-content" id="nav-tabContent">
 				<div class="tab-pane fade show active text-center m-5" id="loginTab" role="tabpanel" aria-labelledby="nav-login-tab" >
	             <div class="offset-3">
	               	 <h5 style="color:white;">${msg}</h5> 
	               		 <div class="row">
			                   <div class="col-auto mb-3 w-75">
			                       <label for="username">Enter UserName </label>
			                       <input type="text" class="form-control " name="txtUser" maxlength="30" value="${txtuser}">
			                   </div>
			             </div>
			             <div class="row">
			                   <div class="col-auto mb-3 w-75">
			                       <label for="password">Enter Password </label>
			                       <input type="password" class="form-control " name="txtPassword" maxlength="15" value="">
			                   </div>
			              </div></div>
	                   <div class="row m-4">
	                   	<div class="col text-center">
	                        <button type="submit" onclick="login()" class="btn btn-success button-length btn-block login w-100 mt-2" >
	                        			<i class="fa fa-sign-in mr-2"></i>Login
	                        </button>
	                        <button type="reset" class="btn btn-success button-length btn-block w-100 mt-4">
	                        	<i class="fa fa-close mr-2" ></i>Cancel
	                        </button>
	                      </div>
	                   </div>
	                  
               </div>
           

   
    
    		<div  class="tab-pane fade p-4" id="registerTab" role="tabpanel" aria-labelledby="nav-register-tab" >           
                       <h4 class=text-center> Add New User</h4>
                       <div class="text-center text-dark offset-3 ">
                            <script> 
	                            var msg ="${msg}";
	                            if ( msg !=null && msg.trim().length > 0)
	                            	alert(msg) ;
                            </script> 
                       
                           
                      
                       <div class=row>
                       <fieldset class="col-auto w-75">
                           <label>Enter UserName</label> <input type="text"  class="form-control" name="username" >
                       </fieldset>
						</div>
						<div class=row>
                       <fieldset class="col-auto w-75">
                           <label>Enter Password</label> <input type="password"  class="form-control" name="pass1">
                       </fieldset>
                       </div>
                       <div class="row">
                        
                        <fieldset class="col-auto w-75">
                           <label>Re-Enter Password</label> <input type="text"  class="form-control" name="pass2">
                       </fieldset>
                       </div>
                       <div class=row>
                       <fieldset class="col-auto w-75">
                           <label>Enter Login Id</label> <input type="text"  class="form-control" name="user_id">
                       </fieldset>
                       </div></div>
                       <div class="d-flex justify-content-center  mt-3">
                               <div class="mr-4"><button type="submit" onclick="register()" class="btn btn-success button-length">Add</button></div>
                               <div><input type="reset" class="btn btn-success button-length mr-4" value=Reset ></div> 
                                  
                            
                       </div>
                  
              </div>
                   </div></form>
               </div>
    </div></div>
    <div style="display:none;"> 
       <script>
       		function login()
       		{
       			with(document.form)
       			{
       				mode.value ="login";
       				action     ="LoginServlet";
       				submit();
       			}
       		}
       		function register()
       		{
       			with(document.form)
       			{
       				mode.value ="register";
       				action     ="LoginServlet";
       				submit();
       			}
       		}
       </script>
 </div>
    
</body>
</html>