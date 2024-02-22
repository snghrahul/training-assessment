// Function to fetch data from the server and render it
function fetchDataAndRender() {
  fetch("/bin/getAllContentFragments")
      .then(response => {
          if (!response.ok) {
              throw new Error("Network response was not ok");
          }
          return response.json();
      })
      .then(data => {
          console.log("Data fetched successfully:", data);
          renderData(data);
          saveDataToLocalStorage(data);
      })
      .catch(error => {
          console.error("Error occurred while fetching data:", error);
          alert("Error occurred while fetching data. Please try again later.");
      });
}

$.ajax({
  type: "GET",
  url: "/bin/getAllContentFragments",
  success: function(response) {
      console.log(JSON.parse(response));
      var responseData = JSON.parse(response);
      console.log(responseData);
      displayContentData(responseData);
      saveDataToLocalStorage(responseData);
  },
  error: function() {
      alert("Error occurred while fetching data.");
  }
});
// Your existing displayContentData function
function displayContentData(data) {
  var container = document.getElementById("contentFragmentData");
  
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

// Function to save data to localStorage
function saveDataToLocalStorage(data) {
  localStorage.setItem('alertData', JSON.stringify(data));
}

// Function to retrieve data from localStorage
function getDataFromLocalStorage() {
  var storedData = localStorage.getItem('alertData');
  return storedData ? JSON.parse(storedData) : null;
}

// Function to remove the topmost data from localStorage
function removeTopMostDataFromLocalStorage() {
  var storedData = getDataFromLocalStorage();
  if (storedData && storedData.length > 0) {
      storedData.shift(); // Remove the first item
      localStorage.setItem('alertData', JSON.stringify(storedData));
      displayContentData(storedData); // Re-display the remaining data
  } else {
      console.log("No data found in localStorage.");
  }
}


$("#dismissButton").on("click", function() {
  removeTopMostDataFromLocalStorage();
});

// Event listener for close button
$("#closeButton").on("click", function() {
  console.log("heyy");
  $("#alertCard").hide();
});

// On page load
document.addEventListener("DOMContentLoaded", function() {
  var storedData = getDataFromLocalStorage();
  if (storedData) {
      displayContentData(storedData);
  } else {
      fetchDataAndRender();
  }
});
