<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>    


<%@ include file="dbconnection.jsp" %>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Travel</title>
</head>
<body>

	<div class="container">
		<div class="content">
			<div class="card-body">
				<nav>
					<div class="nav nav-tabs justify-content-end" id="nav-tab"
						role="tablist">
						<a class="nav-item nav-link active" id="nav-login-tab"
							data-toggle="tab" href="#loginTab" role="tab"
							aria-controls="loginTab" aria-selected="true"> Login </a> <a
							class="nav-item nav-link" id="nav-register-tab" data-toggle="tab"
							href="#registerTab" role="tab" aria-controls="registerTab"
							aria-selected="false"> Register </a>
					</div>
				</nav>
				<form name="form" method=post id=center>

					<div class="tab-content" id="nav-tabContent">
						<div class="tab-pane fade show active text-center" id="loginTab"
							role="tabpanel" aria-labelledby="nav-login-tab">
							<div class="row">
								<div class="col-auto mb-3">
									<label for="username">Enter UserName </label> <input
										type="text" class="form-control " name="txtUser"
										maxlength="30" value="${txtuser}">
								</div>
							</div>
							<div class="row">
								<div class="col-auto mb-3 ">
									<label for="password">Enter Password </label> <input
										type="password" class="form-control " name="txtPassword"
										maxlength="15" value="">
								</div>
							</div>

							<div class="row">
								<div class="col text-center">
									<button type="submit" onclick="Submit('login')"
										class="btn btn-success button-length btn-block w-100 mt-2">
										<i class="fa fa-sign-in mr-2"></i>Login
									</button>
									<button type="reset"
										class="btn btn-success button-length btn-block w-100 mt-4">
										<i class="fa fa-close mr-2"></i>Cancel
									</button>
								</div>
							</div>

						</div>
						<div class="tab-pane fade text-center p-3 m-3" id="registerTab"
							role="tabpanel" aria-labelledby="nav-register-tab">
							<h4 class=text-center>Add New User</h4>

							<div class=row>
								<div class="col-auto">
									<label>Enter UserName</label> <input type="text"
										class="form-control" name="username">
								</div>
								<div class="col-auto">
									<label>Enter Password</label> <input type="password"
										class="form-control" name="pass1">
								</div>
							</div>
							<div class="row">

								<div class="col-auto">
									<label>Re-Enter Password</label> <input type="text"
										class="form-control" name="pass2">
								</div>
								<div class="col-auto">
									<label>Enter Login Id</label> <input type="text"
										class="form-control" name="user_id">
								</div>
							</div>

							<div class="d-flex justify-content-center  mt-3">
								<div class="mr-4">
									<button type="button" class="btn btn-success button-length"
										onclick="Submit('register');">Add</button>
								</div>
								<div>
									<input type="reset" class="btn btn-success button-length mr-4"
										value=Reset>
								</div>


							</div>

						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<script>
		function Submit(mode) 
		{
			with (document.form) 
			{
				if (mode == 'login') 
				{
					if (((txtUser.value).trim()) == ""
							|| ((txtPassword.value).trim()) == ""
							|| ((txtUser.value).trim()).toLowerCase == 'null'
							|| ((txtPassword.value).trim()).toLowerCase == 'null')
						alert('Enter Login Credentials first ...');
					else if ( check( (txtUser.value).trim(),'valid' ) || check( (txtPassword.value).trim(),'valid1' ) )
						alert('Invalid Special Characters not allowed ...');
					else
					{
						$('button').ready(function(){
							$('button').empty();
							$('button').append('<i class="fa fa-spinner fa-spin mr-2" style="font-size:15px;"></i>Loading')	;
						});
						action = "Login?mode=" + mode;
						submit();
					}
				}
				if (mode == 'register') 
				{
					if ((username.value).trim() == ""
							|| (pass1.value).trim() == ""
							|| (pass2.value).trim() == ""
							|| (user_id.value).trim() == "")
						alert('Enter Account Credentials first ...');
					else if (((username.value).trim()).toUpperCase() == "NULL"
							|| ((pass1.value).trim()).toUpperCase() == "NULL"
							|| ((pass2.value).trim()).toUpperCase() == "NULL"
							|| ((user_id.value).trim()).toUpperCase() == "NULL")
						alert('null Cannot be inserted ! Enter Valid Credentials ...');
					else if ((pass1.value).trim() != (pass2.value).trim())
						alert(' password mismatch ...');
					else if (check((pass1.value).trim(), 'valid1')
							|| check((pass2.value).trim(), 'valid1'))
						alert(' Allowed Special Characters !@#$%&');
					else if (check((username.value).trim(), 'valid')
							|| check((user_id.value).trim(), 'valid'))
						alert(' Special Characters not Allowed for Username or User Id');
					else 
					{
						action = "Login?mode=" + mode;
						submit();
					}
				}
			}
		}
	</script>
	<script>
		if (("${msg}").trim() != "") {
			alert("${msg}");
		}
	</script>
</body>
</html>