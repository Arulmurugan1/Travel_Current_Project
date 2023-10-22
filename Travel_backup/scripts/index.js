function Submit(mode) 
{
	if (mode == 'login') 
	{
		with (document.form) 
		{

			if ( isEmptyNull('txtUser','txtPassword') )
				alert('Enter Login Credentials first ...');
			else if ( check( 'txtUser','valid' ) || check( 'txtPassword' ,'valid1' ) )
				alert( 'Invalid Special Characters not allowed ...' );
			else
			{
				const button = 'button:submit';

				$(button).empty();
				$(button).attr('disabled',true);
				$(button).append('<i class="fa fa-spinner fa-spin mr-2" style="font-size:15px;"></i>Loading')	;
				action = "Login?mode=" + mode +"&encodePassword="+encodeHashing();
				submit();
			}
		}
	}
	if (mode == 'register') 
	{
		with (document.form) 
		{
			if ( isEmptyNull('username','pass1','pass2','user_id') )
				alert('Enter Account Credentials first ...');
			else if (pass1.value.trim() != pass2.value.trim())
				alert(' password mismatch ...');
			else if (check('pass1', 'valid1')
					|| check('pass2', 'valid1'))
				alert(' Allowed Special Characters !@#$%&');
			else if (check('username', 'valid') || check('user_id', 'valid') )
				alert(' Special Characters not Allowed for Username or User Id');
			else 
			{
				action = "Login?mode=" + mode;
				submit();
			}
		}
	}
}

 $('.check-icon').click( (e)=>{
	if (  !$(e.target).hasClass('fa-eye-slash')  )
	{
		$(e.target).addClass('fa-eye-slash');
		$(e.target).removeClass('fa-eye');
		
		$('#'+ e.target.getAttribute('data-target') ).attr('type','password');
	}
	else
	{
		$(e.target).removeClass('fa-eye-slash');
		$(e.target).addClass('fa-eye');
		$('#'+ e.target.getAttribute('data-target') ).attr('type','text');
	}
} );

function encodeHashing()
{
	const password = $('#txtPassword').val();
	const salt = "jghdquytzqbnixqgevytbeuyxbqe";
	const encodedPassword = CryptoJS.SHA256(password + salt).toString();
	return encodedPassword ; 
}
