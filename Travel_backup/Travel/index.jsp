<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ include file="dbconnection.jsp"%>

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
										type="text" class="form-control " name="txtUser" id=txtUser
										maxlength="10" value="${txtuser}" size="25" autocomplete="off" spellcheck="false" >
								</div>
							</div>
							<div class="row">
								<div class="col-auto mb-3 ">
									<label for="password">Enter Password </label>
									<div class=input-group>
										<input type="password" class="form-control "
											name="txtPassword" id=txtPassword maxlength="15" value="" autocomplete="off">
										<div class=input-group-append>
											<div class=input-group-text>
												<i class="fa fa-eye-slash check-icon" data-target=txtPassword aria-hidden="true" ></i>
											</div>
										</div>
									</div>
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
										class="form-control" name="username" id=username>
								</div>
								<div class="col-auto">
									<label>Enter Password</label>
									<div class=input-group>
										<input type="password" class="form-control" name="pass1" id=pass1 size="16">
										<div class=input-group-append>
											<div class=input-group-text>
												<i class="fa fa-eye-slash check-icon" data-target=pass1 aria-hidden="true"></i>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row p-lg-3">

								<div class="col-lg-">
									<label>Re-Enter Password</label>
									<div class=input-group>
										<input type="password" class="form-control" name="pass2" id=pass2 size="16">
										<div class=input-group-append>
											<div class=input-group-text>
											<i class="fa fa-eye-slash check-icon" data-target=pass2 aria-hidden="true"></i>
											</div>
										</div>
									</div>
								</div>
								<div class="col-auto">
									<label>Enter Login Id</label> <input type="text"
										class="form-control" name="user_id" id=user_id>
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

	<script src="<%=request.getContextPath() %>/scripts/index.js<%= CACHE_VERSION %>"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/4.1.1/crypto-js.min.js" integrity="sha512-E8QSvWZ0eCLGk4km3hxSsNmGWbLtSCSUcewDQPQWZF6pEU8GlT8a5fF32wOl1i8ftdMhssTrF/OhyGWwonTcXA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</body>