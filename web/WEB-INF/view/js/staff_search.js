function viewSection(obj){
    var target = $(".section_div");
    var index = $(obj).parents("tr").find("#index").val();
    $(target[index]).toggle(500);
}

function checkBoxTest(){
    var allContent = document.getElementsByName("select_box");
    var checkedContent = new Array();
    var nameList = new Array();
    var name = "";
    for(var i = 0;i < allContent.length;i++)
    {
        if(allContent[i].checked){
            checkedContent.push(allContent[i]);
        }
    }
    for(var i = 0;i<checkedContent.length;i++){
        name = $(checkedContent[i]).parents("tr").find("#content_title").val();
        nameList.push(name);
    }
    $.ajax({
        url:"/export_word",
        data:{list:nameList},
        type:"post",
        dataType:"json",
        contentType:"application/x-www-form-urlencoded",
        success:function (data) {
            if(data[0].info == "true"){
                $(location).attr('href','success_generate');
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