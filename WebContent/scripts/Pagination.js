var ul = document.querySelector('.pagination ul');

function element(totalPage, page)
{
	let li = '', beforePage = page - 1, afterPage = page + 1, li_active;

	if (totalPage === page)
	{
		afterPage = page;
	}
	if (beforePage === 0 )
	{   
		beforePage = page;
	}
	if (page > 1) // if value > 1 show the prev menu
	{
		li += `<li class="page-icon prev" onclick="element(${totalPage},${page-1});dataCall(parseInt($('#maxRows').val()),${page-1});"><span><i class="fa fa-angle-left" aria-hidden="true"></i></span></li>`;    
	}
	else
	{
		li += `<li class="page-icon prev"><span><i class="fa fa-angle-left" aria-hidden="true"></i></span></li>`;    
	}
	if (page > 2)
	{
		li += `<li class="num" onclick="element(${totalPage},1);dataCall(parseInt($('#maxRows').val()),1);"><span>1</span></li>`;
		if (page > 3)
		{
			li += `<li class="dots"><span>..</span></li>`;
		}

	}

	if (totalPage === page)
		beforePage = beforePage - 2;
	else if (page === totalPage - 1)
		beforePage = beforePage - 1;

	if (page === 1)
		afterPage = afterPage + 2;
	else if (page === 2)
		afterPage = afterPage + 1;

	for (let pageLength = beforePage; pageLength <= afterPage; pageLength++)
	{
		if (page == pageLength) // if showpage == page current assign active
		{
			li_active = 'active';
		}
		else {
			li_active = "";
		}
		li += `<li class="num ${li_active}" onclick="element(${totalPage},${pageLength});dataCall(parseInt($('#maxRows').val()),${pageLength});"><span>${pageLength}</span></li>`
	}
	if (page < totalPage-1)
	{
		if (page < totalPage-2)
		{
			li += `<li class="dots"><span>..</span></li>`;
		}
		li += `<li class="num" onclick="element(${totalPage},${totalPage});dataCall(parseInt($('#maxRows').val()),${totalPage});"><span>${totalPage}</span></li>`;   
	}

	if (page < totalPage) 
		li += `<li class="page-icon next" onclick="element(${totalPage},${page+1});dataCall(parseInt($('#maxRows').val()),${page+1});" ><span> <i class="fa fa-angle-right" aria-hidden="true"></i></span></li>`;
	else
		li += `<li class="page-icon next"><span> <i class="fa fa-angle-right" aria-hidden="true"></i></span></li>`;
	ul.innerHTML = li;
}

$(document).ready(function()
{
			$('#maxRows').on('change',function(){
				$('.pagination').removeClass('d-none');
				$('.pagination ul').empty();
				var trnum = 0 ;
				var maxRows = parseInt($(this).val());
				var totalRows = $('table tbody tr').length;
				$('table tr:gt(0) ').each(function(){
					trnum++;
					if( trnum > maxRows)
					{
						$(this).hide();
					}
					if (trnum <= maxRows)
					{
						$(this).show();
					}
				});
				if ( totalRows > maxRows )
				{
					var pagenum = Math.ceil(totalRows/maxRows);
					element(pagenum, 1);
				}
				else
				{
					$('.pagination').addClass('d-none');
				}
			})
		});		
function dataCall(maxRows,pageNum)
{
		var trIndex = 0;
		$('table tr:gt(0)').each(function(){
			trIndex++;
			if( trIndex > (maxRows*pageNum) || trIndex <= ( (maxRows*pageNum) -maxRows))
			{
				$(this).hide();
			}
			else
			{
				$(this).show();
			}
		});
}