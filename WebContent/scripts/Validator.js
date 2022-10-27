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

function Submit()
{
	var success = true ;
	for ( var i=0 ; i < document.forms[0].length ; i++ )
	{
		if ( document.forms[0][i].disabled == false )
		{
			if ( document.forms[0][i].localName== "select" || ( document.forms[0][i].localName == "input" && document.forms[0][i].type != 'hidden' ) )
			{
				if ( ( document.forms[0][i].value ).trim() == "" || ( document.forms[0][i].value ).trim() == "@gmail.com" )
				{
					if ( $( document.forms[0][i] ).hasClass("is-invalid") == false )
					{
						if ( document.forms[0][i].localName== "select" )
						{
							$( document.forms[0][i] ).after("<div class='invalid-feedback'>Please Select a Value!</div>");	
						}
						else if ( document.forms[0][i].localName == "input" )
						{
							$( document.forms[0][i] ).after("<div class='invalid-feedback'>Please Enter a Value!</div>");	
						}	
						$( document.forms[0][i] ).addClass("is-invalid")
					} 
				}
				else
				{
					if ( $( document.forms[0][i]).hasClass('is-invalid') )
					{
						$( document.forms[0][i]).removeClass('is-invalid');
					}
					if ( $( document.forms[0][i]).hasClass('is-valid') == false )
					{
						$( document.forms[0][i] ).after("<div class='valid-feedback'>Looks Good!</div>");
						$( document.forms[0][i] ).addClass("is-valid");	
					}
				}
			}
			if ( $( document.forms[0][i]).hasClass('is-invalid') )
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