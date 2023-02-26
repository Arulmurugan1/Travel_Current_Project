//let circularProgress = document.querySelector("#circular-progress"),
//progressValue = document.querySelector("#progress-value"),
//progressStartValue = 0,
//progressEndValue = 100,
//speed = 2;


//let progress = setInterval(() => {

//if ( document.readyState == 'loading' )
//{
//progressStartValue = 50;

//}
//if ( document.readyState == 'loaded' )
//{
//progressStartValue =100;
//}
//progressValue.textContent = progressStartValue+"%";
//circularProgress.style.background = `conic-gradient(#7d2ae8 ${progressStartValue*3.6}deg, #ededed 0deg)`;
//if (progressStartValue == progressEndValue) {
//clearInterval(progress);
//}
//}, speed);

//document.onreadystatechange = function() {
//if (document.readyState != "complete") {

//document.querySelector("body").style.visibility = "hidden";
//document.querySelector("#loader").style.visibility = "visible";
//} else {
//document.querySelector("#loader").style.display = "none";
//document.querySelector("body").style.visibility = "visible";
//}
//};

document.onreadystatechange = function() {
	if (document.readyState != "complete") 
	{
		document.querySelector("#load-content").style.visibility = "visible";
		if ( document.querySelector(".container") !=null )
		{
			document.querySelector(".container").style.opacity = "0.3";
		}
		if ( document.querySelector("#announcement") !=null )
		{
			document.querySelector("#announcement").style.opacity = "0.3";
		}
	} 
	else 
	{
		document.querySelector("#load-content").style.visibility = "hidden";
		if ( document.querySelector(".container") !=null )
		{
			document.querySelector(".container").style.opacity = "1";
		}
		if( document.querySelector("#announcement") !=null )
		{
			document.querySelector("#announcement").style.opacity = "1";
		}
		
			callAlert();
		
	}
};




