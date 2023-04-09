
	document.getElementById('search').addEventListener('input', function(e){
		search_table(this.value);
	});

function search_table(value)
{
	$('tbody tr').each(function(){
		var found = false ;
		$(this).each(function(){
			if( $(this).text().toLowerCase().indexOf(value.toLowerCase()) >= 0)
			{
				found = true ;
			}
		});
		if ( found )
		{
			$(this).show();
		}
		else{
			$(this).hide();
		}
	});
}

function search_table_specific(args)
{
	$('tbody tr:not(.d-none)').each( function(){
		let found = false ;
		$(this).each(function(){

			if( $(this).text().toLowerCase().indexOf(args.toLowerCase()) >= 0)
			{
				found = true ;
			}
		});
		if ( found )
		{
			$(this).removeClass('d-none');
		}
		else
		{
			$(this).addClass('d-none');
		}
	});
}