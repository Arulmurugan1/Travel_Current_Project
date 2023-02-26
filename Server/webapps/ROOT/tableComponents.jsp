<div class="d-flex justify-content-between ">
	<div class="my-auto mx-auto">
		<h4 class=text-white>Select No of Rows</h4>
		<div class=form-group>
			<select name=state id=maxRows class="form-control" style="width: 140%;">
				<option value=5000>Show All</option>
				<option value=5>5</option>
				<option value=10>10</option>
			</select>
		</div>
	</div>
	<div class="my-auto mx-auto">
		<div class="search-box">
    		<input type="text" name="search" id="search" class="search-txt" placeholder="Type To Search...">
		    	<div class="search-btn">
		        	<i class="fa fa-search" aria-hidden="true"></i>
		    	</div>
   		</div>
	</div>
	<div class="pagination my-auto mx-auto d-none">
		<ul></ul>
	</div>
</div>

<script src="scripts/Pagination.js"></script>
<script src="scripts/search.js"></script>