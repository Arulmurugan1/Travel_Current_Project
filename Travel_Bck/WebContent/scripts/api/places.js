
      

  
//  <!-- Google Maps JavaScript library -->
//    <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBGiRYxvi_UrhY59idX7-y_-Wev5w-NS_Q&libraries=places&v=weekly&callback=initAutocomplete"></script>
   
    
let autocomplete,address1Field;

function initAutocomplete() {
	
  address1Field = document.querySelector("#autocomplete");

  autocomplete = new google.maps.places.Autocomplete(address1Field, {
    componentRestrictions: { country: ["us", "ca" , "in"] },
    fields: ["address_components", "geometry"],
    types: ["address"],
  });
  address1Field.focus();
  
  autocomplete.addListener("place_changed", fillInAddress);
}

function fillInAddress() {
  // Get the place details from the autocomplete object.
  const place = autocomplete.getPlace();
  
  let address1 = "";
  

  for (const component of place.address_components) {
   
    const componentType = component.types[0];

    switch (componentType) {
      case "street_number": {
        address1 = `${component.long_name} ${address1}`;
        break;
      }

      case "route": {
        address1 += component.short_name;
        break;
      }

      case "postal_code": {
    	  address1 += `${component.long_name}${postcode}`;
        break;
      }

      case "postal_code_suffix": {
    	address1 += `${postcode}-${component.long_name}`;
        break;
      }
      case "locality":
//        document.querySelector("#locality").value = component.long_name;
        break;
      case "administrative_area_level_1": {
//        document.querySelector("#state").value = component.short_name;
        break;
      }
      case "country":
//        document.querySelector("#country").value = component.long_name;
        break;
    }
  }

  address1Field.value = address1;
//  postalField.value = postcode;
//  address2Field.focus();
}

window.initAutocomplete = initAutocomplete;