

$.ajax({
  type: "GET",
  url: "/bin/getAllContentFragments",
  success: function(response) {
      console.log(JSON.parse(response));
      var responseData = JSON.parse(response);
      console.log(responseData);
      displayContentData(responseData);
  },
  error: function() {
      alert("Error occurred while fetching data.");
  }
});
// Your existing displayContentData function
function displayContentData(data) {
  var container = document.getElementById("contentFragmentData");
  container.innerHTML= '';
  
  data.forEach((element) => {
      var title = element.title;
      var message = element.message;

      var itemContainer = document.createElement('div');
      var titleElement = document.createElement('h3');
      var messageElement = document.createElement('p');

      titleElement.textContent = title;
      messageElement.textContent = message;

      itemContainer.appendChild(titleElement);
      itemContainer.appendChild(messageElement);
      container.appendChild(itemContainer);
  });
}

$("#dismissButton").on("click", function() {
 
});

$("#closeButton").on("click", function() {
  console.log("heyy");
  $("#alertCard").hide();
});


