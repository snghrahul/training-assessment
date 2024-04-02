console.log("custom-tool-2")
$(document).ready(function() {
    var currentURL = window.location.href;
    var url = new URL(currentURL);
    var path = url.searchParams.get('path');
      
      $.ajax({
          url: '/bin/getdata',
          type: 'GET',
          data: {path :path },
          success: function(response) {
           
              console.log("Response from servlet:",response);
              displayResponse(response);
          },
          error: function(xhr, status, error) {
              console.error('Request failed. Status:', status);
          }
      });
      $('#deleteButton').click(function() {
   
        var assetPath = "/content/dam/example/image.jpg"; // Replace with the actual asset path
        $.ajax({
            url: '/bin/remove',
            type: 'POST',
            data: { path:path },
            success: function(response) {
                // Handle success response
                console.log("Asset deleted successfully",response);
                // Optionally, perform additional actions after deletion
            },
            error: function(xhr, status, error) {
                // Handle error response
                console.error('Failed to delete asset. Status:', status);
            }
        });
    });
});

function displayResponse(response) {
   
    var responseContainer = $('#responseContainer');
    responseContainer.empty();
    $.each(response, function(key, value) {
       
        var listItem = $('<li></li>').text(key + '    : ' + value);
        responseContainer.append(listItem);
    });
}






