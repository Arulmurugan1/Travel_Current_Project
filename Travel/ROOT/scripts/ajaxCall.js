const ajaxWait = 2000;

$(document).ready(function () {

	//Stops the submit request
	$("#vehicle").submit(function (e) {
		e.preventDefault();
	});

	// Vehicle models 
	$("#vehicle_model").change(() => {
		ajaxLoader(true);
		createRequest();

		let url = "Ajax?vehicle_model=" + $("#vehicle_model").val() + "&mode=getVehicles";

		try {
			request.onreadystatechange = () => {
				if (request.readyState == 4) {
					if (request.responseText) {
						setCarModel(JSON.parse(request.responseText));
					}
				}
			};

			request.open("POST", url, true);
			request.send();
		}
		catch (e) {
			alert(e);
		}
		finally {
			setTimeout(() => {
				ajaxLoader(false);
			}, ajaxWait);
		}

		/*$.ajax({
			type: "POST",
			url: "Ajax",
			data: "vehicle_model="+$("#vehicle_model").val()+"&mode=getVehicles",
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

		});  */
	});
});

function setCarModel(data) {
	if (data.length > 0) {
		data = data.sort();

		$("#vehicle_type").empty();
		$("#vehicle_type").append(new Option('', '', true, true));

		for (var types of data)
			$("#vehicle_type").append(new Option(types, types));
	}
}


function callAjaxUserAccess(userId, Access) {

	let datum = `user=${userId}&id=${Access}`;

	ajaxLoader(true);

	createRequest();

	try {
		request.onreadystatechange = () => {
			if (request.readyState == 4) {
				if (request.responseText) {
					let data = JSON.parse(request.responseText);

					if (data)
					{
							let css = data.status == "Approved" ? "btn-success" : "btn-danger";
							let approval = data.status == "Approved" ? "Y" : "N";

							$('#' + userId).removeClass();
							$('#' + userId).addClass('btn ' + css);
							$('#' + userId).val(data.status);
							$('#' + userId).click(() => {
								callAjaxUserAccess(userId, approval);
							});
							$('#accessStatus').val(data.status);
					}
				}
			}
		}
		request.open("GET", "Ajax?mode=updateUserAccess&" + datum, true);
		request.send();
	}
	catch (e) {
		alert(e);
	}
	finally {
		setTimeout(() => {
			ajaxLoader(false);
		}, ajaxWait);
	}
}

//Ajax for vehicle Info Starts 
function sendInfo() {
	if (!$('#vehicle_no').prop('readonly') && $("#vehicle_no").val().trim().length == 4)
	{
		ajaxLoader(true);

		$("#error").html("");
		let url = "Ajax?mode=getVehicleStatus&vehicle_no=" + $.trim($("#vehicle_no").val());
		createRequest();

		try {
			request.onreadystatechange = getInfo;
			request.open("POST", url, true);
			request.send();
		}
		catch (e) {
			alert("Unable to connect to server");
		}
		finally {
			setTimeout(() => {
				ajaxLoader(false);
			}, ajaxWait);
		}
	}
	}

function getInfo() {

	if (request.readyState == 4 && request.status == 200) {
		var val = (request.responseText).trim();

		if (val.length == 4) {
			$("#error").append(val + " Vehicle Already Exists");
			$('#vehicle_model').attr("disabled", true);
			$('#vehicle_type').attr("disabled", true);
			$('#vehicle_color').attr("disabled", true);
			$('#vehicle_no').focus();
		}
		else if (val == "No Vehicle found") {
			$("#error").append("No Vehicles Found");
			$('#vehicle_model').attr("disabled", false);
			$('#vehicle_type').attr("disabled", false);
			$('#vehicle_color').attr("disabled", false);
			$('#vehicle_no').attr("readonly", true);
			$('#vehicle_model').focus();
		}
		else {
			$("#error").append("Exception Occured");
			$('#vehicle_model').attr("disabled", true);
			$('#vehicle_type').attr("disabled", true);
			$('#vehicle_color').attr("disabled", true);
			$('#vehicle_no').focus();
		}
	}
}

function callAjax(status, booking_no) {
	var url = 'Ajax?status=' + status + '&booking_no=' + booking_no;
	ajaxLoader(true);
	createRequest();
	try
	{

		request.onreadystatechange = () => {
			if (request.readyState == 4) {
				var val = (request.responseText).trim();

				if (val == "success") {
					let element = '#' + booking_no + ' button';

					$(element).text(status);

					successGridCall(element, status)
				}
				else if (val == "exception") {
					alert('Exception Occured');
				}
				else if (val == 'failed') {
					alert('Booking Status failed to update');
				}
			}
		};
		request.open("GET", url, true);
		request.send();
	}
	catch (e)
	{
		alert("Unable to connect to server");
	}
	finally
	{
		setTimeout(() => {
			ajaxLoader(false);
		}, ajaxWait);
	}
}

function getLocationInfo(event) {

	//	Ajax method to fetch Locations 
	createRequest();

	try {
		request.onreadystatechange = () => {
			if (request.readyState == 4) {
				if (request.responseText) {
					let cities = request.responseText.split('\r\n');
					LOCATION_INFO = cities && cities.sort();
					getLocations(event);
				}
			}
		}
		request.open("GET", "locations/Locations.txt", true);
		request.send();
	}
	catch (e) {
		alert(e);
	}
}

function createRequest() {
	if (window.XMLHttpRequest) {
		request = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		request = new ActiveXObject("Microsoft.XMLHTTP");
	}
}



//To update User Details 
function callAjaxUpdate(frm) {
	ajaxLoader(true);
	let formData = new FormData(frm);

	try {
		$.ajax({
			type: "POST",
			url: "upload.jsp",
			data: formData,
			processData: false,
			contentType: false,
			success: function (data) {
				if (data.trim() != '')
					alert(data.trim())
			},
			error: function (jqXHR, textStatus, errorThrown) {
				alert("Something happened >> " + textStatus);
				console.log(errorThrown);
			},

		});

	}
	catch (e) {
		alert(e);
	}
	finally {
		setTimeout(() => { ajaxLoader(false); }, ajaxWait);
	}
}

function updateUserInfo(frm, isFileChanged, ObjectURL) {

	ajaxLoader(true);

	let formData = getFormData(frm, isFileChanged) + "&objectUrl=" + ObjectURL;

	try {

		$.ajax({
			type: "POST",
			url: "Ajax?mode=userProfileSubmit&isFileChanged=" + isFileChanged + formData,
			processData: false,
			contentType: false,
			success: function (data) {
				if (data.trim() != '')
					alert(data.trim())
			},
			error: function (jqXHR, textStatus, errorThrown) {
				alert("Something happened >> " + textStatus);
				console.log(errorThrown);
			},
		});

	}
	catch (e) {
		alert(e);

	}
	finally {
		setTimeout(() => { ajaxLoader(false); }, ajaxWait);
	}

}