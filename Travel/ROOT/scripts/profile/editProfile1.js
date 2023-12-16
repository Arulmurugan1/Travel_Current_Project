$(document).ready( ()=>{
	
	if(document.editProfile)
		setProfileValues();
	
	let imageInput = document.getElementById('imageFile');
	let imageSrc   = document.getElementById('img-profile');
	
	if( imageSrc && imageInput)
	{
		imageInput.addEventListener('change',()=>{
			imageSrc.src = URL.createObjectURL(imageInput.files[0]);
		});
	}
	
	$('#editProfileDialog').hide();
	$('.loader-ajax').hide();
});

function editProfile()
{		
	$('#editProfileDialog').dialog({
		autoOpen : false ,
		buttons : {
			Update  : ()=> {
				callAjaxUpdate(document.editProfile) ;
			},
			OK : ()=>{
				setProfileValues();
				$('#editProfileDialog').dialog('close');
			}
		},
		
		title : " Edit / Update Profile",
		position :{
			my : "center",
			at : "center"
		},
			
		closeonescape : true ,
		draggable : false ,
		modal : true ,
	});
	$('#editProfileDialog').dialog('open');
}


function setProfileValues()
{
	with(document.editProfile)
	{
		dob.value    = hdob.value;
		
		switch (hGender.value) 
		{
			case "M" :
				document.getElementById("male").checked = true ;
			break;
			
			case "F" :
				document.getElementById("female").checked = true ;
			break;
			
			case "" :
				break;
			
			default:
				document.getElementById("transgender").checked = true ;
		}
		
	}
	if($('#dialog'))
		$('#dialog').dialog('close');
	
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