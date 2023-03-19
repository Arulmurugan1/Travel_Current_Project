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
		$('.table-responsive').addClass('table-wrapper') ;
	}
	$('div.table-wrapper').css({height: screen.availHeight - 448});
	setTimeout( ()=>{
		if (document.readyState == "complete") 
		{
			if (  $.trim(  $('#msg').val()   ) !='')
				alert($('#msg').val());
		}
	},200);
}

$(document).ready( ()=>{
	
	setInterval( ()=>{
		document.querySelector('.fa-wifi').style.color = navigator.onLine ?"green" :"red";
		document.querySelector('.fa-wifi').setAttribute( 'title',navigator.onLine ? "Connected" : " Disconnected");
	} , 3000);
	
});
