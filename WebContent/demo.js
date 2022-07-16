



function fareClick()
{
	with(document.bookingInsert)
	{
		if(pickup_from.value == 'Chennai' || drop_at.value == 'Chennai')
		{
			if( drop_at.value =='Salem' || pickup_from.value =='Salem')
			{
				hFare.value = '900';
			}
			if( drop_at.value =='Madurai' || pickup_from.value =='Madurai')
			{
				hFare.value = '1200';
			}
			if( drop_at.value =='Kanyakumari' || pickup_from.value =='Kanyakumari')
			{
				hFare.value = '3500';
			}   	
			if( drop_at.value =='Coimbatore' || pickup_from.value =='Coimbatore')
			{
				hFare.value = '2300';
			}
			if( drop_at.value =='Erode' || pickup_from.value =='Erode')
			{
				hFare.value = '1700';
			}
			if( drop_at.value =='Vellore' || pickup_from.value =='Vellore')
			{
				hFare.value = '600';
			}
		}
		else if(pickup_from.value == 'Salem' || drop_at.value == 'Salem')
		{
			if( drop_at.value =='Madurai' || pickup_from.value =='Madurai')
			{
				hFare.value = '1000';
			}
			if( drop_at.value =='Vellore' || pickup_from.value =='Vellore')
			{
				hFare.value = '500';
			}
			if( drop_at.value =='Kanyakumari' || pickup_from.value =='Kanyakumari')
			{
				hFare.value = '3000';
			}   	
			if( drop_at.value =='Coimbatore' || pickup_from.value =='Coimbatore')
			{
				hFare.value = '2000';
			}
			if( drop_at.value =='Erode' || pickup_from.value =='Erode')
			{
				hFare.value = '400';
			}

		}
		else if(pickup_from.value == 'Madurai' || drop_at.value == 'Madurai')
		{
			if( drop_at.value =='Erode' || pickup_from.value =='Erode')
			{
				hFare.value = '900';
			}
			if( drop_at.value =='Vellore' || pickup_from.value =='Vellore')
			{
				hFare.value = '1600';
			}
			if( drop_at.value =='Kanyakumari' || pickup_from.value =='Kanyakumari')
			{
				hFare.value = '1800';
			}   	
			if( drop_at.value =='Coimbatore' || pickup_from.value =='Coimbatore')
			{
				hFare.value = '2200';
			}
		}
		else if(pickup_from.value == 'Coimbatore' || drop_at.value == 'Coimbatore')
		{
			if( drop_at.value =='Vellore' || pickup_from.value =='Vellore')
			{
				hFare.value = '1500';
			}
			if( drop_at.value =='Kanyakumari' || pickup_from.value =='Kanyakumari')
			{
				hFare.value = '3000';
			}   	
			if( drop_at.value =='Erode' || pickup_from.value =='Erode')
			{
				hFare.value = '600';
			}

		}
		else if(pickup_from.value == 'Kanyakumari' || drop_at.value == 'Kanyakumari')
		{

			if( drop_at.value =='Vellore' || pickup_from.value =='Vellore')
			{
				hFare.value = '2300';
			}

			if( drop_at.value =='Erode' || pickup_from.value =='Erode')
			{
				hFare.value = '1600';
			}

		}
		else if(pickup_from.value == 'Erode' || drop_at.value == 'Erode')
		{
			if( drop_at.value =='Vellore' || pickup_from.value =='Vellore')
			{
				hFare.value = '900';
			}

		}
		if (pickup_from.value == '' || drop_at.value == '')
		{
			hFare.value ='';
		}
		if (pickup_from.value == drop_at.value)
		{
			hFare.value 			='';
			drop_at.value		='';
		}
		fare.value =hFare.value;
	}
}


function selectValue(no,start,end)
{
	var select = document.getElementById('uvehicle_no').selectedIndex;
	var option = document.getElementById('uvehicle_no').options;
	
	
}

function routeClik()
{
	if (document.route.ustart.value == document.route.uend.value)
	{
		document.route.uend.value		='';
	}	
}



function emailClick()
{

	
	document.bookingInsert.email.value =  document.bookingInsert.customer_name.value + '@gmail.com';
	
	
	
}


function query()
{
	if ( document.bookingInsert.pickup_from.value !="")
		document.bookingInsert.hStatus.value   = "I";

	submitOnChange();
}

function submitOnChange()
{
	with(document.bookingInsert)
	{
	action ="CommonServlet";
	submit();
	}
}





