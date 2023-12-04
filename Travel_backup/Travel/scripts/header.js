$(document).ready( ()=>{
	
	setInterval( ()=>{
		if( document.querySelector('.fa-wifi') )
		{
			document.querySelector('.fa-wifi').style.color = navigator.onLine ?"green" :"red";
			document.querySelector('.fa-wifi').innerText 	= navigator.onLine ?"Connected" :"Disconnected";
			document.querySelector('.fa-wifi').setAttribute( 'title',navigator.onLine ? "Connected" : " Disconnected");
		}
		
	} , 1000);
	
	let imageInput = document.getElementById('imageFile');
	let imageSrc   = document.getElementById('img-profile');
	
	if( imageSrc && imageInput)
	{
		imageInput.addEventListener('change',()=>{
			imageSrc.src = URL.createObjectURL(imageInput.files[0]);
		});
	}
	
});


$('#editProfileDialog').hide();
$('.loader-ajax').hide();

function editProfile()
{
			setProfileValues();
			
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