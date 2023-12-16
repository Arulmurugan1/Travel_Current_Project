$(document).ready( ()=>{
	setInterval( ()=>{
		if( document.querySelector('.fa-wifi') )
		{
			document.querySelector('.fa-wifi').style.color = navigator.onLine ?"green" :"red";
			document.querySelector('.fa-wifi').innerText 	= navigator.onLine ?"Connected" :"Disconnected";
			document.querySelector('.fa-wifi').setAttribute( 'title',navigator.onLine ? "Connected" : " Disconnected");
		}
		
	} , 1000);
	
});