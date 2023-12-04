var ul = document.querySelector('.pagination ul') , pagination = document.querySelector('.pagination');

function element(totalPage, selectedPage)
{
	if ( selectedPage > 0 )
	{
		let li = '', beforePage = selectedPage - 1, afterPage = selectedPage + 1, li_active;

		li += `<li class="page-icon prev"><i class="fa fa-angle-left prev" aria-hidden="true"></i></li>`;    

		if (selectedPage > 2)
		{
			li += `<li class="num">1</li>`;

			if (selectedPage > 3)
			{
				li += `<li class="dots">..</li>`;
			}

		}
		
		if (totalPage === selectedPage)
		{
			afterPage = selectedPage;
			beforePage = beforePage - 2;
		}
		
		if (beforePage === 0 )
			beforePage = selectedPage;
		
		if (selectedPage === totalPage - 1)
			beforePage = beforePage - 1;
		
		if (selectedPage === 1)
			afterPage = afterPage + 2;
		else if (selectedPage === 2)
			afterPage = afterPage + 1;

		for (let pageLength = beforePage; pageLength <= afterPage; pageLength++)
		{
			if (selectedPage == pageLength) // if showpage == selectedPage current assign active
			{
				li_active = 'active';
			}
			else {
				li_active = "";
			}
			li += `<li class="num ${li_active}">${pageLength}</li>`
		}
		
		if (selectedPage < totalPage-1)
		{
			if (selectedPage < totalPage-2)
			{
				li += `<li class="dots">..</li>`;
			}

			li += `<li class="num">${totalPage}</li>`;   
		}

		li += `<li class="page-icon next li-page"><i class="fa fa-angle-right next" aria-hidden="true"></i></li>`;
		ul.innerHTML = li;

	}
}

$(document).ready(function()
{
	$(pagination).hide();

	$('#maxRows').change(function(){

		$(ul).empty();
		let maxRows = parseInt($(this).val()) , trnum = 0 ;

		let totalRows = $('table tbody tr').length;

		if ( maxRows > 0 )
		{

			$('table tr:gt(0):not( tr:lt('+maxRows+') ) ').hide();
			$(pagination).show();
		}
		else
		{
			$('table tr:gt(0) ').show();
			$(pagination).hide();
			return;
		}
		
		if ( totalRows > maxRows )
		{
			var pagenum = Math.ceil(totalRows/maxRows);
			element(pagenum, 1);
		}
		else
		{
			$(pagination).hide();
		}
	});	
	
});		

function dataCall(maxRows,pageNum)
{
	
		let startRow = maxRows * pageNum  , trIndex = 0;
		let endRow	 = startRow - maxRows ;
		
		$('table tr:gt(0)').each(function(){
			
			if( ++trIndex > startRow || trIndex <= endRow )
			{
				$(this).hide();
			}
			else
			{
				$(this).show();
			}
		});
}

function pagingCall(e,iconSelect)
{
	let maxRows = parseInt($('#maxRows').val()) ,totalRows = Math.ceil($('table tbody tr').length/maxRows) ,rowNumber,dir;

	if ( !iconSelect )
	{
		rowNumber  = parseInt( e.target.lastChild.textContent.trim() ) ;
		
		element( totalRows , rowNumber );
		dataCall( maxRows , rowNumber );
	}
	else
	{
	
		if( e.target.className.includes('prev') )
			dir = -1 ;
		else if ( e.target.className.includes('next') )
			dir = 1;
		
		rowNumber = parseInt( $('.pagination ul li.active').text().trim() ) + dir  ;
		
		if ( rowNumber > 0 && totalRows >= rowNumber)
		{
			element( totalRows , rowNumber );
			dataCall( maxRows , rowNumber );
		}
		
		
	}
}

$(document).click( (e)=>{
	
	if ( e.button === 0  )
	{
		if ( $(e.target).hasClass('prev') || $(e.target).hasClass('next') )
			pagingCall(e,true);
		else if ( $(e.target).parent()[0].id === 'ul-page' )
			pagingCall(e);
	}
});