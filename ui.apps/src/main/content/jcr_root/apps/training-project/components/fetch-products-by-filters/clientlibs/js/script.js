console.log("heyy");
$(document).ready(function() {

    // Event listener for #ascending button
    $('#lowToHighBtn').click(function() {
      filterProducts("ascending");
    });
  
    // Event listener for #descending button
    $('#highToLowBtn').click(function() {
      // Call the function to handle the AJAX call with the servlet URL for high to low sorting
      filterProducts("desceding");
    });
  
    // Event listener for #mostRecent button
    $('#mostRecentBtn').click(function() {
      filterProducts("mostRecent");
    });

    $('.filter-buttons button').click(function() {
        // Remove the 'active' class from all buttons
        $('.filter-buttons button').removeClass('active');
        // Add the 'active' class to the clicked button
        $(this).addClass('active');
        // Call the appropriate function based on the clicked button
        handleSorting(this.id);
      });
  });

  function filterProducts(sortType) {
    $.ajax({
        url: '/bin/products',
        type: 'GET',
        data: { sortType: sortType }, 
        success: function(response) {
           renderProducts(response);
          
        },
        error: function(xhr, status, error) {
            // Handle error response
            console.error('Error calling servlet:', error);
        }
    });
  }

  function renderProducts(data) {
    var source = $('#product-template').html();
    var template = Handlebars.compile(source);
    var html = template({ products: data });
    $('#productContainer').html(html);
    $('.carousel').carousel();
    
}




  