var originalPath = "";
$(document).ready(function() {
    console.log("java ");
    $('#projectDropdown').change(function() {
      var selectedProject = $(this).val();
      originalPath += selectedProject + "/";
      console.log(selectedProject);
      $.ajax({
        url: '/bin/getorphanassets',
        type: 'GET',
        data: { selectedProject: selectedProject },
        success: function(response) {
            getChilds(response);
        },
        error: function(xhr, status, error) {
          console.error('Request failed. Status:', status);
        }
      });
    });
    $("#fetchDataButton").on('click',function(){
      window.location.href="/apps/training-project/components/custom-screen-2.html?path=" + originalPath;
      console.log("/apps/training-project/components/custom-screen-2.html" + originalPath);
     })
  });

  
  function getChilds(response){
    var checkboxContainer = $('#checkboxContainer');
    checkboxContainer.empty(); 
    var selectElement = $('<select id="pagesDropdown" name="pages"></select>');
    response.childNodes.forEach(function(item) {
        var option = $('<option></option>');
        option.val(item); 
        option.text(item); 
        selectElement.append(option); 
    });
    selectElement.change(function() {
     
      var selectedValue = $(this).val();
      originalPath += selectedValue + "/";
      console.log('Selected value:', originalPath);
  });
    
    checkboxContainer.append(selectElement);
  }





 


 
  



  
