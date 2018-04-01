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

function checkValid() {
    var filename = $("#file_name").html();
    var content_title = $("#Content_Title").val();
    var isExternal = $("#isExternal").val();
    var customer_name = $("#Customer").val();
    var expired_date = $("#expired_date").val();
    var textInput = $(".form-control");
    var ifDuplicate = false;
    textInput.each(function(){
        var text = $(this).val();
        if(text == ""){
            var error_info = $(this).parent().parent();
            $(error_info).addClass("has-error");
            $(this).parent().find('.help-block').css("display","block");
        }
    });

    $.ajax({
        url:"/checkDuplicateContent",
        data:{"content_title":content_title,"isExternal":isExternal},
        dataType:"json",
        type:"post",
        async:false,
        contentType:"application/x-www-form-urlencoded",
        success:function (data) {
            if(data[0].info == "true"){
                ifDuplicate = true;
            }
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
        }

    });

    if(ifDuplicate){
        var error_info = $("#Content_Title").parent().parent();
        $(error_info).addClass("has-error");
        $("#Content_Title").parent().find('#duplicate_hint').css("display","block");
        return false;
    }

    if(filename==""||content_title==""||customer_name==""||expired_date==""){
        return false;
    }
}

function removeHint(obj) {
    var error_info =  $(obj).parent().parent();
    $(error_info).removeClass('has-error');
    $(obj).parent().find('.help-block').css("display","none");
}

$(function () {
   $("#expired_date").datepicker({
       showOtherMonths: true,
       selectOtherMonths: true,
       showButtonPanel: true
   });
});




