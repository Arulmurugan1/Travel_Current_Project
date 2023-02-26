function callParent()
{
	try
	{
		window.opener.popupResult(result);
	}
	catch(err)
	{
		alert(err);
	}
	window.close();	
}

function callAlert()
{
	if ( !$('.table-responsive').hasClass('table-wrapper') )
	{
		$('.table-responsive').addClass('table-wrapper')
	}
	setTimeout( ()=>{
		if (document.readyState == "complete") 
		{
			if (  $.trim(  $('#msg').val()   ) !='')
				alert($('#msg').val());
		}
	},200);
}








