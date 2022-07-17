$(document).ready(function() {
 
    //Stops the submit request
    $("#vehicle").submit(function(e){
           e.preventDefault();
    });
    
    //checks for the button click event
    $("#vehicle_no").keyup(function(e){
           
            //get the form data and then serialize that
            dataString = $("#vehicle").serialize();
            
            //get the form data using another method 
            var vehicleNo = $("#vehicle_no").val(); 
            dataString = "vehicleNo=" + vehicleNo;
            
            if ( vehicleNo.length == 4)
            {
            
            //make the AJAX request, dataType is set to json
            //meaning we are expecting JSON data in response from the server
            $.ajax({
                type: "POST",
                url: "AjaxServlet",
                data: dataString,
                dataType: "json",
                
                //if received a response from the server
                success: function( data, textStatus, jqXHR) {
                    //our country code was correct so we have some information to display
                     if(data.success){
                         $("#error").html("");
                         $("#error").append(data.vehicleObj.no + " Vehicle Already Exists");
                         $('#vehicle_model').attr("disabled", true);
                         $('#vehicle_type').attr("disabled", true);
                         $('#vehicle_color').attr("disabled", true);
                         $('#vehicle_no').focus();
                     } 
                     //display error message
                     else {
                         $("#error").html("<div class=text-danger ><b> No vehicle Found</b></div>");
                         $('#vehicle_model').attr("disabled", false);
                         $('#vehicle_type').attr("disabled", false);
                         $('#vehicle_color').attr("disabled", false);
                         $('#vehicle_no').attr("readonly", true);
                     }
                },
                
                //If there was no resonse from the server
                error: function(jqXHR, textStatus, errorThrown){
                     console.log("Something happened " + textStatus);
                      $("#error").html("error Occured exception");//jqXHR.responseText
                },
                
                //capture the request before it was sent to server
                beforeSend: function(jqXHR, settings){
                    //adding some Dummy data to the request
                    settings.data += "&dummyData=whatever";
                    //disable the button until we get the response
                  //  $('#vehicle_no').attr("disabled", false);
                },
                
                //this is called after the response or error functions are finsihed
                //so that we can take some action
                complete: function(jqXHR, textStatus){
                   
                   // $('#vehicle_no').attr("disabled", false);
                }
      
            });  
            
            }
    });
 
});
