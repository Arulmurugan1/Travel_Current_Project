var LOCATION_INFO ;

function getLocations(event)
{
	const inputValue = event.target.value ;

	let locations = LOCATION_INFO.sort();

	$('fieldset ul').empty();

	if( inputValue && inputValue != "" )
	{
		for ( const loc of locations)
		{
			if( loc.toUpperCase().startsWith( inputValue.toUpperCase() ) )
			{
				let li = document.createElement('li');
				
				li.setAttribute('value',loc);

				li.innerHTML = "<b>"+loc.substr(0,inputValue.length)+"</b>"+loc.substr(inputValue.length);

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

document.getElementById('city').addEventListener('input',(e) => { if( !LOCATION_INFO ) getLocationInfo(e); else getLocations(e); } );
document.querySelector('.location ul').addEventListener('click',(e) => selectLocation(e));
