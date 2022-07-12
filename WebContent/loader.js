let circularProgress = document.querySelector("#circular-progress"),
	progressValue = document.querySelector("#progress-value"),
	progressStartValue = 0,
	progressEndValue = 100,
	speed = 2;


let progress = setInterval(() => {
	progressStartValue++;

	progressValue.textContent = ` ${progressStartValue}%`;
	circularProgress.style.background = `conic-gradient(#7d2ae8 ${progressStartValue*3.6}deg, #ededed 0deg)`;
	if (progressStartValue == progressEndValue) {
		clearInterval(progress);
	}
}, speed);

		document.onreadystatechange = function() {
			if (document.readyState !== "complete") {

				document.querySelector("body").style.visibility = "hidden";
				document.querySelector("#loader").style.visibility = "visible";
			} else {
				document.querySelector("#loader").style.display = "none";
				document.querySelector("body").style.visibility = "visible";
			}
		};
	