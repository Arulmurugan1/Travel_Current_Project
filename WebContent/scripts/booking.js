	function popupResult(result) 
	{
		var info = result.split(',');
		with (document.booking) {
			
			if (info.length > 0) 
			{
				$('#booking_no').val(info[0]);
				$('#customer_id').val(info[1]);
				$('#fare').val(info[2]);
				$('#booking_no').css({
					"backgroundColor" : "green",
					"color":"yellow",
					"cursor":"auto"
				});
				$('#customer_id').css({
					"backgroundColor" : "green",
					"color":"yellow",
					"cursor":"auto"
				});
				$('#fare').css({
					"backgroundColor" : "green",
					"color":"yellow",
					"cursor":"auto"
				});
			}
		}
	}
	
		$('#booking_no').css({
			"backgroundColor" : "grey",
			"cursor":"not-allowed"
		});
		$('#customer_id').css({
			"backgroundColor" : "grey",
			"cursor":"not-allowed"
		});
		$('#fare').css({
			"backgroundColor" : "grey",
			"cursor":"not-allowed"
		});

	function Open(arr) 
	{
		$('.info-content').show();
		
		for ( var i in arr) 
			$('#c' + i ).val(arr[i]);
	}
	function gridCall()
	{
		if ( ADMIN_USER )
		{
					$('tbody tr td button').removeClass();
					$('tbody tr td button').addClass('btn dropdown-toggle ');
					
					for ( var i=0;i < $('tbody tr td button').length ;i++)
					{
						if ( $('tbody tr td button')[i].innerText.trim() == "WIP"){
							$('tbody tr td button')[i].classList.add('btn-danger');
						}
						else if ( $('tbody tr td button')[i].innerText.trim() == "RAC"){
							$('tbody tr td button')[i].classList.add('btn-primary');
						}
						else if ( $('tbody tr td button')[i].innerText.trim() == "Confirmed"){
							$('tbody tr td button')[i].classList.add('btn-success');
						}
						else{
							$('tbody tr td button')[i].classList.add('btn-warning');
						}
					}
		}
	}
	
	gridCall();
	
	
	$(document).ready(()=>{
		$('button').css({
			"border-radius" : "25px",
			"padding" : "5px"
		});
		
		$('a.status').click((e)=>{
			callAjax(e.target.getAttribute('status'),e.target.id);
		});
		
		
	});