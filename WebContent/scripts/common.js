if (document.readyState != "complete") 
{
	if ( document.querySelector("#load-content").style.visibility == "hidden" )
	{
		if ( document.querySelector(".container").style.opacity == "1" || document.querySelector("#announcement").style.opacity == "1")
		{
			if ( ($('#msg').val()).trim() !='')
				alert($('#msg').val());
		}
	}
	
}

function callParent()
{
	try
	{
		window.opener.popupResult(result);
	}
	catch(err){}
	window.close();	
}
function callAlert()
{
	if ( ($('#msg').val()).trim() !='')
		alert($('#msg').val());
}
