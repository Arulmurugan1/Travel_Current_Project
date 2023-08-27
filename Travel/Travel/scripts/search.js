
if(document.getElementById('search'))
{
		document.getElementById('search').addEventListener('input', function(){
		search_table(this.value);
	});
}
	

function search_table(value)
{
	$('tbody tr').each(function()
	{

		if( $(this).text().toLowerCase().indexOf(value.toLowerCase()) >= 0)
		{
			$(this).show();
		}
		else{
			$(this).hide();
		}
	});
}

function search_table_specific()
{
	$('tbody tr').show();

	$('tbody tr').each(function() {
		let found 		= false;
		let start 		= $('#start').val().trim();
		let end 		= $('#end').val().trim();
		let tdStart 	= $(this).find('td#data-start') ;
		let tdEnd   	= $(this).find('td#data-end') ;
		let tdStartText = tdStart.text().trim();
		let tdEndText	= tdEnd.text().trim();
		

		if ( start.trim() != '' && end.trim() != '') 
		{
			if (tdStart && tdEnd && tdStartText === start && tdEndText === end) 
			{
					found = true;
			}
		}
		else if (end.trim() != '')
		{
			if (tdEnd && tdEndText === end) 
			{
					found = true;
			}
		}
		else if (start.trim() != '') 
		{
			if (tdStart && tdStartText === start) 
			{
					found = true;
			}
		}

		if (found)
			$(this).show();
		else
			$(this).hide();
	});
}