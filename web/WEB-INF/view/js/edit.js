$(function() {
    $( "#dialog-confirm" ).dialog({
        autoOpen: false,
        resizable: false,
        height:200,
        modal: true,
        buttons: {
            "Save": function() {
                editSection();
                $( this ).dialog( "close" );
            },
            Cancel: function() {
                $( this ).dialog( "close" );
            }
        }
    });

    $("#opener").click(function(){
        $("#dialog-confirm").dialog("open");
    });
});

function editSection(){
    var content = $("#content").html();
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
                $(location).attr('href','success_upload');
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

