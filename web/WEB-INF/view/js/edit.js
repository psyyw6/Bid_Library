$(document).ready(function () {
    var original_content = editor.txt.html();
    $("#save-button").attr("disabled",true);
    $("#editor").keyup(function () {
        var content = editor.txt.html();
        original_content = original_content.trim();
        content = content.trim();
        if(content != original_content){
            $("#save-button").attr("disabled",false);
        }
        else{
            $("#save-button").attr("disabled",true);
        }

    });
    $("#editor").click(function () {
        var content = editor.txt.html();
        original_content = original_content.trim();
        content = content.trim();
        if(content != original_content){
            $("#save-button").attr("disabled",false);
        }
        else{
            $("#save-button").attr("disabled",true);
        }

    });

});

function editSection(){
    var content = editor.txt.html();
    var content_title = $("#content_title").val();
    var version = $("#version").val();
    var section_name = $("#section_name").val();

    $.ajax({
        url:"edit_upload.do",
        data:{"content_title":content_title,"section_name":section_name,"version":version,"content_detail":content},
        type:"post",
        dataType:"json",
        contentType:"application/x-www-form-urlencoded",
        success:function (data) {
            if(data[0].info == "true"){
                $(location).attr('href','admin_view_detail?content_title='+content_title);
            }
            else{
                $(location).attr('href','error');
            }
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
        }
    })
}

