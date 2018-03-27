$(document).ready(function () {
   var InUseText = $(".InUse");
   InUseText.each(function (i,item) {
       var text = $(this).text();
       if(text == "true"){
           $(this).css("color","green");
       }
       if(text == "false"){
           $(this).css("color","red");
       }
   })
});

var click_obj;

function showDialog(obj) {
    click_obj = $(obj);
    $("#myModal").modal({});
}

function showDialog2() {
    $("#rollbackModal").modal({});
}

function showDialog3() {
    $("#forwardModal").modal({});
}


function deleteContent(){
    var content_title = click_obj.parents("tr").find("#content_title").val();
    $.ajax({
        url:"delete_content.do",
        type:"post",
        data:{"content_title":content_title},
        dataType:"json",
        contentType:"application/x-www-form-urlencoded",
        success:function (data) {
            if(data[0].info == "true"){
                window.location.href = 'administer_solution';
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

function  deleteSection() {
    var content_title = click_obj.parents("tr").find("#content_title").val();
    var section_name = click_obj.parents("tr").find("#section_name").val();
    var version = click_obj.parents("tr").find("#version").val();
    $.ajax({
        url:"delete_section.do",
        type:"post",
        data:{"content_title":content_title,"section_name":section_name,"version":version},dataType:"json",
        contentType:"application/x-www-form-urlencoded",
        success:function (data) {
            if(data[0].info == "true"){
                window.location.href = 'section_history?content_title='+content_title+"&section_name="+section_name;
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

function RollBack(){
    var content_title = $("#c_title").val();
    var section_name = $("#s_name").val();
    $.ajax({
        url:"rollback.do",
        type:"post",
        data:{"content_title":content_title,"section_name":section_name},
        dataType:"json",
        contentType:"application/x-www-form-urlencoded",
        success:function (data) {
            if(data[0].info == "true"){
                window.location.href = 'section_history?content_title='+content_title+"&section_name="+section_name;
            }
            else{
                alert("Can not roll back from version 1");
            }
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
        }

    })

}

function Forward(){
    var content_title = $("#c_title").val();
    var section_name = $("#s_name").val();
    $.ajax({
        url:"forward.do",
        type:"post",
        data:{"content_title":content_title,"section_name":section_name},
        dataType:"json",
        contentType:"application/x-www-form-urlencoded",
        success:function (data) {
            if(data[0].info == "true"){
                window.location.href = 'section_history?content_title='+content_title+"&section_name="+section_name;
            }
            else{
                alert("Already the latest version");
            }
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
        }

    })

}