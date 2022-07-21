

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
				url: "AjaxServlet",
				data: dataString,
				dataType: "json",
				success: function( data, textStatus, jqXHR)
				{
					if(data.success)
					{
						$("#vehicle_type").empty();
						$("#vehicle_type").append("<option value=' '></option>");
						for ( var s in data.vehicleModel )
						{
							$("#vehicle_type").append("<option value="+data.vehicleModel[s]+">"+data.vehicleModel[s]+"</option>");
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

var request;  
function sendInfo()  
{  
	if ( !$('#vehicle_no').prop('readonly') )
	{
		$("#error").html("");
		var vehicleNo = $("#vehicle_no").val(); 
		if ( vehicleNo.length == 4 )
		{ 
			var url="AjaxServlet?vehicle_no="+vehicleNo; 
			if(window.XMLHttpRequest)
			{  
				request=new XMLHttpRequest();  
			}  
			else if(window.ActiveXObject)
			{  
				request=new ActiveXObject("Microsoft.XMLHTTP");  
			}  

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
		else if ( val =="not_found" )
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



//Ajax Ends 