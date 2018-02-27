var click_obj;

function showDialog(obj) {
    click_obj = obj;
    $("#myModal").modal({});
}

function deleteContent() {
    var version = click_obj.parents("tr").find("#version").val();
    var content_title = $(obj).parents("tr").find("#content_title").val();
    $.ajax({
        url:"delete_content.do",
        type:"post",
        data:{"content_title":content_title,"version":version},
        dataType:"json",
        contentType:"application/x-www-form-urlencoded",
        success:function (data) {
            if(data[0].info == "true"){
                window.location.href = 'content_history?content_title='+content_title;
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
