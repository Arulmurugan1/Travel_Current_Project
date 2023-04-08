
const statusColor = 
{ 
		WIP 				: "btn-danger" , 
		RAC					: "btn-primary" , 
		Confirmed 		 	: "btn-success" ,
		"Booking Status" 	: "btn-warning"
} , Info_Content = '.info-content' ;

function popupResult(result) 
{
	var info = result.split(',');
	with (document.booking) {

		if (info.length > 0) 
		{
			$('#booking_no').val(info[0]);
			$('#customer_id').val(info[1]);
			$('#fare').val(info[2]);

			$(document.booking).find('fieldset input').css({
				backgroundColor : "green",
				color:"yellow",
				cursor:"auto"
			});
		}
	}
}

$('fieldset input').css({
	backgroundColor : "grey",
	cursor:"not-allowed"
});

$('td a.user-detail').click( (e) => {

	$(Info_Content).show();

	const detail = $(e.target).find('#detail').text().split(',') ;

	for ( const i in detail) 
		$('#c' + i ).val(detail[i]);


});


function bookingStatusCall()
{
	if ( ADMIN_USER )
	{
		let bg_color , target ='tbody tr td button' ;

		$(target).removeClass();
		$(target).addClass('btn dropdown-toggle ');

		$(target).each( (index,element)=>
		{
			bg_color = statusColor[$.trim( $(element).text() )] ; 	

			if ( bg_color )
				$(element).addClass(bg_color);
			else
				$(element).addClass('btn-dark');
		});
	}
}


bookingStatusCall();

function successGridCall(element,status)
{
	if ( ADMIN_USER )
	{
		$(element).removeClass();
		$(element).addClass('btn dropdown-toggle '+statusColor[status]);
	}
}


$(document).ready(()=>{
	$('button').css({
		"border-radius" : "25px",
		padding : "5px"
	});

	$('a.status').click( (e)=> callAjax(e.target.getAttribute('status'),e.target.id) );

	$(this).mousedown( (e) =>{ 
		if( e.button === 0 && !$(Info_Content).has(e.target)[0] )
			$(Info_Content).hide(); 
	});

});
