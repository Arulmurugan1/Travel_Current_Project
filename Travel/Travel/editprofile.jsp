<section>
	<img src="${sessionScope.ImagePath}/${sessionScope.user_id}.jpg" width="270" height=200 alt='${sessionScope.user}'>
	<form name=editProfile>
	<input type=hidden name=hGender id=hGender value ='${sessionScope.gender }'>
	<input type=hidden name=hdob id=hdob value ='${sessionScope.dob}'>
		<div class="row text-center">
			<input type="radio" class="ml-1 mt-2" name =gender id=male value='M'><label class="ml-1 mt-3" for=male>Male</label>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="radio" class="mt-2" name =gender id=female value='F'><label class="ml-1 mt-3" for=female>Female</label>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="radio" class="mt-2" name =gender id=transgender value='T'>
			<label class="ml-1 mt-3" for=transgender>Transgender</label><br>
			
			<input type="date" class="form-control m-2" name=dob id=dob placeholder="Enter Your dob.."><br>
			<input type="text" class="form-control m-2" name=status id=status disabled><br>
				<input class="form-control" type="file" id="image" name=image multiple>
		</div>
		</form>
</section>

<script>
document.editProfile.status.value = '${sessionScope.status}';

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
</script>