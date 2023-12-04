$(document).ready( ()=>{
	
	if(document.editProfile)
		setProfileValues();
	
	$(document).mouseup(function (e) { 
            let container = $("#editProfile div.card"); 
            if(!container.is(e.target) && container.has(e.target).length === 0) 
            { 
                $('#editProfile div.card').css("top","-50%");
            } 
    }); 
	
	let imageInput = document.getElementById('imageFile');
	let imageSrc   = document.getElementById('img-profile');
	
	if( imageSrc && imageInput)
	{
		imageInput.addEventListener('change',()=>{
			imageSrc.src = URL.createObjectURL(imageInput.files[0]);
		});
	}
	
	$('.editProfileSubmit').click((e)=>{
		callAjaxUpdate(document.editProfile);
	})
	
	$('.loader-ajax').hide();
});

function editProfile()
{		
	$('#editProfile div.card').css("top","21%");
}


function setProfileValues()
{
	with(document.editProfile)
	{
		dob.value    = hdob.value;
		gender.value = hGender.value ;
	}
	
}