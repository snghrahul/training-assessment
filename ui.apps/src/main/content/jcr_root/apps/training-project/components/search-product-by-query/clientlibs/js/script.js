console.log("search...")


function sendQueryToServlet(query) {

  $.ajax({
    type: "GET",
    url: "/bin/search", 
    data: { query: query },
    success: function(response) {
      console.log('Response from servlet:', response);
      $("#dataContainer").empty();
      var dataArray = response.substring(1,response.length-1).split(', ');
      console.log(response);
      console.log("no data is ",dataArray);
      dataArray.forEach(element => {
        var listItem = $('<div class="response-item"></div>');
        listItem.text(element);

        $("#dataContainer").append(listItem)
        
      });

    },
    error: function(xhr, status, error) {
      console.error('Error sending query:', error);
    }
  });
}

$(document).ready(function() {
$('#searchInput').on('input', function() {
  console.log("heyy");
 
  var query = $(this).val();
  if (query.length >= 3) {
 
    sendQueryToServlet(query);
  }
});
});








