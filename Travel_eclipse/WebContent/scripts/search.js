
	document.getElementById('search').addEventListener('input', function(e){
		search_table(this.value);
	});

function search_table(value)
{
	$('tbody tr').each(function(){

		if( $(this).text().toLowerCase().indexOf(value.toLowerCase()) >= 0)
		{
			$(this).show();
		}
		else{
			$(this).hide();
		}
	});
}

function search_table_specific(args,target)
{
	$('tbody tr').show();
	
	if ( args.trim() != '')
	{
			
		$('tbody tr').each( function(){
			
			const data = JSON.parse( $(this).attr('data') ) ;
			
			if( data && data[target] === args )
			{
				$(this).show();
			}
			else
			{
				$(this).hide();
			}

		});
	}
}