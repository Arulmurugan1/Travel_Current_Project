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
}

$(document).ready( () =>{
	if ( !$('.table-responsive').hasClass('table-wrapper') )
	{
		$('.table-responsive').addClass('table-wrapper')
	}
	if (document.readyState != "complete") 
	{
				if (  $.trim(  $('#msg').val()   ) !='')
					alert($('#msg').val());
	}
});








