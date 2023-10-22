
const tabs = {
		Bookings  :{redirect :'Booking', classList : "fa fa-book"},
		Customers :{redirect :'Customer',classList : "fa fa-user"},
		Vehicles  :{redirect :'Vehicle',classList : "fa fa-bus"},
		Drivers	  :{redirect :'Driver',classList : "fa fa-user"},
		Routes    :{redirect :'Route',classList : "fa fa-road"},
		
		Users	  :{redirect :'LoginInfo',classList : "fa fa-user"},
		
}

function buildHomeTabs()
{
	for( const tab in tabs)
	{
		if ( tab === 'Users' )
		{
			if(!ADMIN_USER)
			{
				continue;
			}
		}
		
		$('form#center div.row').append(tabContent(tab));

	}

}
$(document).ready( ()=> { buildHomeTabs(); });

function tabContent(tab)
{
	let div = "<div class='col-sm-12 col-md-6 col-lg-4' >";
	let a	= `<a class='home-button' onclick="Submit('${tabs[tab].redirect}')">`;
	let i	= `<i class='${tabs[tab].classList}' aria-hidden="true"></i><br>${tab}</a></div>`;
	
	return div+a+i;
}
function Submit(servlet) 
{
	with (document.home) {
		action = servlet;
		submit();
	}
}

