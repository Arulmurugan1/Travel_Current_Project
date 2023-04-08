function callParent()
{
	try
	{
		opener.popupResult(result);
	}
	catch(err)
	{
		alert(err);
	}
	close();	
}

function callAlert()
{
//	if ( !$('.table-responsive').hasClass('table-wrapper') )
//	{
//	$('.table-responsive').addClass('table-wrapper') ;
//	}
	$('div.table-responsive').css({height: screen.availHeight - 448});

	if ( !$('div.table-responsive table tbody').hasClass('table-wrapper') )
	{
		$('div.table-responsive table tbody').addClass('table-wrapper') ;
	}
	
	$('table tbody td a').each( function(){
		if ( $(this).text().trim().toLowerCase() === 'delete' )
			$(this).addClass('btn btn-primary');
	});

	setTimeout( ()=>{
		if (document.readyState == "complete") 
		{
			if (  $.trim(  $('#msg').val()   ) !='')
				alert($('#msg').val());
		}
	},200);
}