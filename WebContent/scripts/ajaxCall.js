var request ;

$(document).ready(function() {

	//Stops the submit request
	$("#vehicle").submit(function(e){
		e.preventDefault();
	});

	$("#vehicle_model").change(function(e){
		
		dataString = "vehicle_model="+$("#vehicle_model").val();
		if ( dataString != "" )
		{
			$.ajax({
				type: "POST",
				url: "Ajax",
				data: dataString,
				dataType: "json",
				success: function( data, textStatus, jqXHR)
				{
					if( data.length > 0 )
					{
						var values = data.sort();
							
						$("#vehicle_type").empty();
						$("#vehicle_type").append("<option value=' '></option>");
						for ( var s in values )
						{
							$("#vehicle_type").append("<option value="+values[s]+">"+values[s]+"</option>");
						}
					} 
				},


				error: function(jqXHR, textStatus, errorThrown)
				{
					console.log("Something happened " + textStatus);
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

		}
	});
});



//Ajax for vehicle Info Starts 
function sendInfo()  
{  
	if ( !$('#vehicle_no').prop('readonly') )
	{
		$("#error").html("");
		var vehicleNo = $("#vehicle_no").val(); 
		if ( vehicleNo.length == 4 )
		{ 
			var url="Ajax?vehicle_no="+vehicleNo; 
			createRequest();
			try
			{  
				request.onreadystatechange=getInfo;  
				request.open("POST",url,true);  
				request.send();  
			}
			catch(e)
			{
				alert("Unable to connect to server");
			}
		}
	}
}  

function getInfo(){  
	if(request.readyState==4)
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
		else if ( val.trim() =="No Vehicle found" )
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
			$("#error").append ("Error exception Occured");
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
					$('#'+booking_no+' button')[0].innerText = status;
					gridCall();
				}
				else if ( val == "exception" )
				{
					alert('Exception Occured ');
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
// To update User Details 
function callAjaxUpdate(frm)
{
	createRequest();
	try{
		request.onreadystatechange= ()=> {
			if(request.readyState ==4 )
			{ 
				$('#dialog').empty();
				$('#dialog').text((request.responseText).trim());
				$('#dialog').dialog({
					autoOpen : false ,
					buttons : {
						OK : ()=>{
							$('#dialog').dialog('close');
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
			}
		}
		request.open("GET","LoginInfo?mode=ajax&"+$(frm).serialize(),true);  
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