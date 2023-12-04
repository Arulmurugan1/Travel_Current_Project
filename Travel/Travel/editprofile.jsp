<link rel="stylesheet" href="<%=request.getContextPath() %>/styleSheet/Profile/editProfile.css<%=session.getAttribute("CACHE_VERSION") %>" type="text/css">

<section>

<form name=editProfile id=editProfile method="post" enctype="multipart/form-data">

	<input type=hidden name=hGender id=hGender value ='${sessionScope.gender }'>
	<input type=hidden name=hdob id=hdob value ='${sessionScope.dob}'>
	
	 <div class="card">
        <div class="imageBox">
            <img src="<%=request.getContextPath() %>/${sessionScope.ImagePath}" id=img-profile alt='#${sessionScope.user}'>
            <div class="round">
                <span class="camera"><i class="fa fa-camera" aria-hidden="true"></i></span>
                <input type="file" id="imageFile" name=imageFile>
            </div>
        </div>
        
        <div class="info">
            <div class="userDetails">
                <h4>${sessionScope.user}<br>${sessionScope.role}</h4>
                <div class="data row">
                    <div>Gender<BR>
	                    <span>
	                    	<select class=" form-select form-control ml-1 mt-2" name =gender id=gender>
	                    		<option value =""></option>
	                    		<option value ="M">Male</option>
	                    		<option value ="F">Female</option>
	                    		<option value ="T">Transgender</option>
	                    	</select>
	                    </span>
                    </div>
                    <div>Date Of Birth<BR><span><input type="date" class="form-control m-2" name=dob id=dob placeholder="Enter Your dob.."></span></div>
                    <div>Age<BR><span> ${sessionScope.age}</span></div>
                </div>
                <div class="status">
                    <input type="text" class="form-control m-2" name=status id=status disabled>
                </div>
                <div class="prf-button">
                    <button type=button class="editProfileSubmit">Change</button>
                </div>
            </div>
        </div>
    </div>

</form>
</section>

<script>
document.editProfile.status.value = '${sessionScope.status}';
</script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="<%=request.getContextPath() %>/scripts/profile/editProfile.js<%=session.getAttribute("CACHE_VERSION") %>" type="text/javascript"></script>
