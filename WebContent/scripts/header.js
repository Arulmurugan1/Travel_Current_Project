$(document).ready( ()=>{
	
	setInterval( ()=>{
		if( document.querySelector('.fa-wifi') )
		{
			document.querySelector('.fa-wifi').style.color = navigator.onLine ?"green" :"red";
			document.querySelector('.fa-wifi').innerText 	= navigator.onLine ?"ONLINE" :"OFFLINE";
			document.querySelector('.fa-wifi').setAttribute( 'title',navigator.onLine ? "Connected" : " Disconnected");
		}
		
	} , 1000);
	
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