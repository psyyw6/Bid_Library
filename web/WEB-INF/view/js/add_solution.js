$(document).ready(function(){
    var file = $("#fileInput");
    var filename = $("#file_name");
    file.on('change',function(e){
         var name = e.currentTarget.files[0].name;
         filename.html(name);
    });
});

$(function () {
   $("#expired_date").datepicker({
       showOtherMonths: true,
       selectOtherMonths: true,
       showButtonPanel: true
   });
});




