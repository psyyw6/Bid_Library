$(document).ready(function(){
    var file = $("#fileInput");
    var filename = $("#file_name");
    var info_hint = $("#file-info-hint");
    var strRegx = /.ftl$/;
    var upload_button = $("#upload_button");
    var name;
    file.on('change',function(e){
        name = e.currentTarget.files[0].name;
        filename.html(name);
        if(!strRegx.test(name)){
            info_hint.css("display","inline");

        }else{
            info_hint.css("display","none");
        }
    });

    var image = $("#imageInput");
    var image_name = $("#image_name");
    var info_hint2 = $("#image-info-hint");
    var strRegx2 = /(.jpg|.png|.gif|.ps|.jpeg)$/;
    var name2;
    image.on('change',function(e){
        name2 = e.currentTarget.files[0].name;
        image_name.html(name2);
        if(!strRegx2.test(name2)){
            info_hint2.css("display","inline");

        }else{
            info_hint2.css("display","none");
        }
    });

});

function testValid() {
    var filename = $("#file_name").html();
    var image_name = $("#image_name").html();
    var template_name = $("#Template_Name").val();
    var src_prefix_location = $("#DocSrcPrefixLocation").val();
    var next_part_id = $("#NextPartId").val();
    var doc_src_parent = $("#DocSrcParent").val();
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
        url:"/checkDuplicateTemplate",
        data:{"template_name":template_name},
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
        var error_info = $("#Template_Name").parent().parent();
        $(error_info).addClass("has-error");
        $("#Template_Name").parent().find('#duplicate_hint').css("display","block");
        return false;
    }

    if(filename == ""||image_name==""||template_name==""||src_prefix_location==""||next_part_id==""||doc_src_parent==""){
        return false;
    }

}

function removeHint(obj) {
    var error_info =  $(obj).parent().parent();
    $(error_info).removeClass('has-error');
    $(obj).parent().find('.help-block').css("display","none");
}

function modify_testValid() {
    var template_name = $("#Template_Name").val();
    var src_prefix_location = $("#DocSrcPrefixLocation").val();
    var next_part_id = $("#NextPartId").val();
    var doc_src_parent = $("#DocSrcParent").val();
    var textInput = $(".form-control")
    textInput.each(function(){
        var text = $(this).val();
        if(text == ""){
            var error_info = $(this).parent().parent();
            $(error_info).addClass("has-error");
            $(this).parent().find('.help-block').css("display","block");
        }
    });
    if(template_name==""||src_prefix_location==""||next_part_id==""||doc_src_parent==""){
        return false;
    }

}