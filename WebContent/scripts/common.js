if (document.readyState != "complete") 
	{
			if ( $.trim($('#msg').val()) !='')
				alert($('#msg').val());
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
}








