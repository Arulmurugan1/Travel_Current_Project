<section>
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
		const Gender			= hGender.value;
		
		if (  Gender == "M" )
		{
			document.getElementById("male").checked = true ;
		}
		else if ( Gender == "F" )
		{
			document.getElementById("female").checked = true ;
		}
		else if ( Gender == "" )
		{
		
		}
		else
		{
			document.getElementById("transgender").checked = true ;
		}
		
	}
	if($('#dialog'))
		$('#dialog').dialog('close');
	
}
</script>