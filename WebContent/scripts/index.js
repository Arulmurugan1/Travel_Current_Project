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
				action = "Login?mode=" + mode +"&encodePassword="+encodeHashing();
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

function Check()
{
	if (  !$('.check-icon').hasClass('fa-eye-slash')  ){
		$('.check-icon').addClass('fa-eye-slash');
		$('.check-icon').removeClass('fa-eye');
		document.form.txtPassword.type ='password';
	}
	else{
		$('.check-icon').removeClass('fa-eye-slash');
		$('.check-icon').addClass('fa-eye');
		document.form.txtPassword.type ='text';
	}
}

function encodeHashing()
{
	const password = $('#txtPassword').val();
	const salt = "jghdquytzqbnixqgevytbeuyxbqe";
	const encodedPassword = CryptoJS.SHA256(password + salt).toString();
	return encodedPassword ; 
	
}
