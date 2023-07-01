var LOCATION_INFO ;

function getLocations(event)
{
	const inputValue = event.target.value ;

	$('fieldset ul').empty();

	if( inputValue && inputValue != "" )
	{
		for ( const loc of LOCATION_INFO )
		{
			if( loc.toUpperCase().startsWith( inputValue.toUpperCase() ) )
			{
				let li = document.createElement('li');
				
				li.setAttribute('value',loc);

				li.innerHTML = "<strong>"+loc.substr(0,inputValue.length)+"</strong>"+loc.substr(inputValue.length);

				document.getElementsByTagName('ul')[0].appendChild(li);
			}
		}
	}
}

function selectLocation(event)
{
	document.getElementById('city').value = event.target.getAttribute('value') ;
	$('.location ul').empty();
}

function submitCall(success)
{
	if (success)
	{
		document.driver.action ="Driver";
		document.driver.submit();
	}
	return;
}

document.getElementById('city').addEventListener('input',(e) => { $('div .location').show(); if( !LOCATION_INFO ) getLocationInfo(e); else getLocations(e); } );
document.querySelector('.location ul').addEventListener('click',(e) => selectLocation(e));

$(document).mouseup( (e)=> {
	if( e.button == 0 && e.target.id != 'city'  )
		$('div .location').hide() 
		
} );
