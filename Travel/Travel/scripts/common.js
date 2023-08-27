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
	$('div.table-responsive').css({height: screen.availHeight - 450});
	$('div.table-responsive').height(screen.availHeight - $('div.table-responsive').height() );

	if ( !$('div.table-responsive table tbody').hasClass('table-wrapper') )
	{
		$('div.table-responsive table tbody').addClass('table-wrapper') ;
	}
	
	$('table tbody td a').each( function(){
		if ( $(this).text().trim().toLowerCase() === 'delete' )
			$(this).addClass('btn btn-primary rounded-circle');
	});

	setTimeout( ()=>{
		if (document.readyState == "complete") 
		{
			if (  $.trim(  $('#msg').val()   ) !='')
				alert($('#msg').val());
			if (  $.trim(  $('#function').val()   ) =='editProfile')
				editProfile();	
		}
	},200);
}


function getFormData(frm)
{
	let formElements = frm && frm.elements;
	
	let arr =new FormData();
	
	for(const data of formElements)
	{
		if(data.type =="file")
			arr += "&"+$(data)[0].files[0]+"="+$(data)[0].value ;
		else
			arr += "&"+$(data)[0].name+"="+$(data)[0].value ; 
	}
	
	return arr ;
}