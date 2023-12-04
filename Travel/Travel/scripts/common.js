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

	$('div.table-responsive').css({height: screen.availHeight - 450});
	$('div.table-responsive').height(screen.availHeight - $('div.table-responsive').height() );

	if ( !$('div.table-responsive table tbody').hasClass('table-wrapper') )
	{
		$('div.table-responsive table tbody').addClass('table-wrapper') ;
	}
	
	$('table tbody td a').each( function()
	{
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
	
	let arr = "";
	
	for(const data of formElements)
	{
		if(data.name != '')
		{
			if(data.type =="file")
			{
				const dat = data.files[0] ; 
			
				if(dat)
				{
					arr +="&" + "fileName" +"="+ dat.name ;
					arr +="&" +"fileSize" +"="+ dat.size ;
					arr +="&" +"fileType" +"="+ dat.type ;
				}
			}
			else
				arr += "&" + data.name +"="+ data.value ;
		}	
	}
	
	return arr ;
}