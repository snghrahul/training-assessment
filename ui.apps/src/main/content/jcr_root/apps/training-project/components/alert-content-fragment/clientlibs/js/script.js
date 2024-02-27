console.log("alert");

$(document).ready(function() {
var jsonData;
  $.ajax({
      type: "GET", 
      url: "/bin/getAllContentFragments",
      success: function(response) {
          // Handle the successful response from the servlet
          console.log(response);
          var jsonArray = JSON.parse(response);
          localStorage.setItem('jsonArray',JSON.stringify(jsonArray))
          // console.log("heyy data is ",localStorage.getItem('jsonArray'));
          // jsonData = JSON.parse(localStorage.getItem('jsonArray'))
          showdata(jsonArray);
         
          // You can update the UI or perform any action with the response data here
      },
      error: function(xhr, status, error) {
          // Handle errors
          console.error(xhr.responseText);
      }
  });

  $("#closeButton").on("click", function() {
    console.log("heyy");
    $("#alertCard").hide();
  });

  $('#dismissButton').click(function() {
    // console.log("heyyy", jsonData.shift());
    var dataString = localStorage.getItem('jsonArray');
    var dataArray = JSON.parse(dataString) || [];

    console.log("length is :", dataArray.length);
    if(dataArray.length >0){
        if(dataArray.length == 1){
        console.log("heyyy else")
      var div = document.getElementById('buttonsDiv');
      div.innerHTML = "No data to display";
      $("#buttons").hide();
      }
      dataArray.shift();
      localStorage.setItem('jsonArray',JSON.stringify(dataArray));
      var container = document.getElementById('contentFragmentData');
      var firstDiv = container.querySelector('div');
     
      if (firstDiv) {
          container.removeChild(firstDiv);
          console.log(dataArray.length);
      }
      showdata(dataArray)
    }else{
      
    }
    
     

});
});

function showdata(jsonData){
  var container = document.getElementById('contentFragmentData');
    container.innerHTML = '';
    jsonData.forEach(function(item) {
        var div = document.createElement('div');
        var titleElement = document.createElement('h2');
        var messageElement = document.createElement('p');
        titleElement.textContent = item.title;
        messageElement.textContent = item.message;

        div.appendChild(titleElement);
        div.appendChild(messageElement);
    
        container.appendChild(div);
    });
}





