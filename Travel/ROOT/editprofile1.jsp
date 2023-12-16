<section>
	<img src="<%=request.getContextPath() %>/${sessionScope.ImagePath}" class="clearfix rounded-circle" id=img-profile style="object-fit :cover;object-position:center;" width="270" height=200 alt='${sessionScope.user}'>
	
	<form name=editProfile id=editProfile method="post" enctype="multipart/form-data">
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
				<input class="form-control" type="file" id="imageFile" name=imageFile>
		</div>
		</form>
</section>

<script>
document.editProfile.status.value = '${sessionScope.status}';
</script>