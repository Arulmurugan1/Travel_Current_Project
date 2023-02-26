function check(value,chars)
{
	var valid = "";

	if ( chars.trim() == "valid" )
	{
		valid  = "!@#$%&*()_+~|\:>?<,./";
		valid += "'";
		valid += '"';
	}
	else if ( chars.trim() == "valid1" )
	{
		valid  = "*()_+~|\:>?<,./";
		valid += "'";
		valid += '"';
	}
	else if ( chars.trim() == "email" )
	{
		valid  = "!#$%&*()_+~|\:>?<,/";
		valid += "'";
		valid += '"';
	}
	var n = false ;
	for ( var i = 0 ; i < valid.length ; i++ )
	{
		if ( value.includes( valid.charAt(i) ) )
		{
			n = true ;
			break ;
		}
	}
	return n ;
}
function isNumber()
{
	if  ( event.charCode >= 48 && event.charCode <= 57 ) 
	{
		return true;
	}
	return false;
}
function charNumbers()
{
	if  ( ( event.charCode >= 48 && event.charCode <= 57 ) ||
			( event.charCode >= 65 && event.charCode <= 90 ) ||
			( event.charCode >= 97 && event.charCode <= 122 ) ) 
	{
		return false;
	}
	return true;
}

function Submit(form)
{
	var form = form == null ? "" :form , success = true;
	for ( var i=0 ; i < form.length ; i++ )
	{
		if ( form[i].disabled == false )
		{
			if ( form[i].localName== "select" || ( form[i].localName == "input" && form[i].type != 'hidden' ) )
			{
				if ( ( form[i].value ).trim() == "" || ( form[i].value ).trim() == "@gmail.com" )
				{
					if ( $( form[i] ).hasClass("is-invalid") == false )
					{
						if ( form[i].localName== "select" )
						{
							$( form[i] ).after("<div class='invalid-feedback'>Please Select a Value!</div>");	
						}
						else if ( form[i].localName == "input" )
						{
							$( form[i] ).after("<div class='invalid-feedback'>Please Enter a Value!</div>");	
						}	
						$( form[i] ).addClass("is-invalid")
					} 
				}
				else
				{
					if ( $( form[i]).hasClass('is-invalid') )
					{
						$( form[i]).removeClass('is-invalid');
					}
					if ( $( form[i]).hasClass('is-valid') == false )
					{
						$( form[i] ).after("<div class='valid-feedback'>Looks Good!</div>");
						$( form[i] ).addClass("is-valid");	
					}
				}
			}
			if ( $( form[i]).hasClass('is-invalid') )
			{
				success = false ;
				return;
			}
		}
	}
	submitCall(success);
}
function submitCall(success)
{
	
}