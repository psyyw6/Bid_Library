$(document).ready(function(){
    var file = $("#fileInput");
    var filename = $("#file_name");
    var info_hint = $("#file-info-hint");
    var strRegx = /.txt$/;
    var upload_button = $("#upload_button");
    file.on('change',function(e){
         var name = e.currentTarget.files[0].name;
         filename.html(name);
         if(!strRegx.test(name)){
            info_hint.css("display","inline");
            upload_button.attr("disabled",true);

         }else{
             info_hint.css("display","none");
             upload_button.attr("disabled",false);
         }
    });
});

$(function () {
   $("#expired_date").datepicker({
       showOtherMonths: true,
       selectOtherMonths: true,
       showButtonPanel: true
   });
});




