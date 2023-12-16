let isFormChanged = false , isFileChanged = false ;

$(document).ready( ()=>{
	
	if(document.editProfile)
		setProfileValues();
	
	$(document).mouseup(function (e) { 
            let container = $("#editProfile div.card-content"); 
            if(!container.is(e.target) && container.has(e.target).length === 0) 
            { 
                $('#editProfile div.card-content').css("top","-50%");
            } 
    }); 
	
	let imageInput = document.getElementById('imageFile');
	let imageSrc   = document.getElementById('img-profile');
	
	sessionStorage.setItem('Image',imageSrc.src);
	
	if( imageSrc && imageInput)
	{
		imageInput.addEventListener('change',()=>{
			imageSrc.src 	= URL.createObjectURL(imageInput.files[0]);
			sessionStorage.setItem('Image',imageSrc.src);
			isFileChanged	= true ;
		});
	}
	
	$(document.editProfile.elements).change(()=>{
		isFormChanged = true ;
	});
	
	$('.editProfileSubmit').click((e)=>{
		if(isFileChanged)
			callAjaxUpdate(document.editProfile);
		if(isFormChanged)
			updateUserInfo(document.editProfile,isFileChanged,imageSrc.src);
	})
	
	$('.loader-ajax').hide();
});

function editProfile()
{	
	let card = document.querySelector("#editProfile div.card-content");	
	
	if(card)
		card.style.top = "23%" ;
}


function setProfileValues()
{
	with(document.editProfile)
	{
		dob.value    = hdob.value;
		gender.value = hGender.value ;
	}
	
}