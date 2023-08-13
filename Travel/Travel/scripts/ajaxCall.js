let request ,cities ;

$(document).ready(function() {

	//Stops the submit request
	$("#vehicle").submit(function(e){
		e.preventDefault();
	});
	
	// Vehicle models 
	$("#vehicle_model").change((e)=>{

			$.ajax({
				type: "POST",
				url: "Ajax",
				data: "vehicle_model="+$("#vehicle_model").val(),
				dataType: "json",
				success: function( data, textStatus, jqXHR)
				{
					if( data.length > 0 )
					{
						var values = data.sort();

						$("#vehicle_type").empty();
						$("#vehicle_type").append(new Option('',''));

						for ( var types of values )
						{
							$("#vehicle_type").append(new Option(types,types));
						}
					} 
				},


				error: function(jqXHR, textStatus, errorThrown)
				{
					alert("Something happened " + textStatus);
				},


				beforeSend:function(jqXHR, settings)
				{
					settings.data += "&dummyData=whatever";
				},
				complete:function(jqXHR, textStatus)
				{
					//called after finished response
				}

			});  
	});
});



//Ajax for vehicle Info Starts 
function sendInfo()  
{  
	if ( !$('#vehicle_no').prop('readonly') && $.trim( $("#vehicle_no").val() ).length == 4 )
	{
		$("#error").html("");

		let url="Ajax?vehicle_no="+$.trim( $("#vehicle_no").val() );

		createRequest();

		try
		{  
			request.onreadystatechange = getInfo;  
			request.open("POST",url,true);  
			request.send();  
		}
		catch(e)
		{
			alert("Unable to connect to server");
		}
	}
}  

function getInfo(){  

	if(request.readyState==4 && request.status == 200 )
	{  
		var val= (request.responseText).trim();  

		if ( val.length == 4)
		{
			$("#error").append(val + " Vehicle Already Exists");
			$('#vehicle_model').attr("disabled", true);
			$('#vehicle_type').attr("disabled", true);
			$('#vehicle_color').attr("disabled", true);
			$('#vehicle_no').focus();
		}
		else if ( val =="No Vehicle found" )
		{			
			$("#error").append ("No Vehicles Found");
			$('#vehicle_model').attr("disabled", false);
			$('#vehicle_type').attr("disabled", false);
			$('#vehicle_color').attr("disabled", false);
			$('#vehicle_no').attr("readonly", true);
			$('#vehicle_model').focus();
		}
		else
		{
			$("#error").append ("Exception Occured");
			$('#vehicle_model').attr("disabled", true);
			$('#vehicle_type').attr("disabled", true);
			$('#vehicle_color').attr("disabled", true);
			$('#vehicle_no').focus();
		}
	}  
}  

function callAjax(status,booking_no)
{
	var url = 'Ajax?status='+status+'&booking_no='+booking_no ;
	createRequest();
	try
	{  
		request.onreadystatechange=() => {
			if(request.readyState==4)
			{ 
				var val= (request.responseText).trim();

				if ( val == "success")
				{
					let element = '#'+booking_no+' button' ;

					$(element).text(status);

					successGridCall(element,status)
				}
				else if ( val == "exception" )
				{
					alert('Exception Occured');
				}
				else if (val == 'failed')
				{
					alert('Booking Status failed to update');
				}
			}
		};  
		request.open("GET",url,true);  
		request.send();  
	}
	catch(e)
	{
		alert("Unable to connect to server");
	}
}
//To update User Details 
function callAjaxUpdate(frm)
{
	$('.loader-ajax').show();
	createRequest();
	
	let url = `LoginInfo?mode=ajax&${ $(frm).serialize() }`;
	try{
		request.onreadystatechange= ()=> {
			if(request.readyState ==4 )
			{ 
				setTimeout( ()=>{
					$('.loader-ajax').hide();
					dialog(request);
				}, 3000);
			}
		}
		request.open("GET",url,true);  
		request.send(null);  
	}
	catch(e)
	{
		alert(e);
	}
}

function dialog(request)
{
	$('#editProfileDialog').dialog('close');
	$('#editProfileAfterDialog').empty();

	let response = JSON.parse( request.responseText ) ; 

	if ( response && response.status === "Success" )
	{
		$('#hdob').val(  response.dob );
		$('#hGender').val( response.gender );
	}

	$('#editProfileAfterDialog').text(response.status);
	$('#editProfileAfterDialog').dialog({
		autoOpen : false ,
		buttons : {
			OK : ()=>{
				$('#editProfileAfterDialog').dialog('close');
			}
		},
		position :{
			my : "center",
			at : "center"
		},
		closeonescape : true ,
		draggable : false ,
		modal : true ,
	});
	
	$('#editProfileAfterDialog').dialog('open');
}

function getLocationInfo(event)
{

//	Ajax method to fetch Locations 
	createRequest();

	try{
		request.onreadystatechange= ()=> {
			if(request.readyState ==4 )
			{ 
				if (request.responseText)
				{
					cities = request.responseText.split('\r\n');
					LOCATION_INFO = cities && cities.sort() ;
					getLocations(event);
				}
			}
		}
		request.open("GET","locations/Locations.txt",true);  
		request.send();  
	}
	catch(e)
	{
		alert(e);
	}
}

function createRequest()
{
	if(window.XMLHttpRequest)
	{  
		request=new XMLHttpRequest();  
	}  
	else if(window.ActiveXObject)
	{  
		request=new ActiveXObject("Microsoft.XMLHTTP");  
	}  
}


//Ajax Ends 