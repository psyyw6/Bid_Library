$(document).ready(function(){
    var role = $("#role").text();
    if(role == "Admin"){
        $("#Manage").css("visibility","visible");
    }
});

function viewSection(obj){
    var target = $(".section_div");
    var index = $(obj).parents("tr").find("#index").val();
    $(target[index]).toggle(500);

}

function checkBoxTest(){
    var allContent = document.getElementsByName("select_box");
    var checkedContent = new Array();
    var nameList = new Array();
    var template_options = document.getElementsByName("template_option");
    var selected_template = "";
    for(var i = 0; i < template_options.length;i++){
        if(template_options[i].checked){
            selected_template = template_options[i].value;
        }
    }
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
    if(nameList.length == 0){
        alert("Please Select the Content!");
        return;
    }
    if(selected_template==""){
        alert("Please Select a Template!");
        return;
    }
    $.ajax({
        url:"/export_word",
        data:{list:nameList,"selected_template":selected_template},
        type:"post",
        dataType:"json",
        contentType:"application/x-www-form-urlencoded",
        success:function (data) {
            if(data[0].info=="true"){
                $(location).attr('href','success_generate');
            }
            else{
                console.log(data[0].info);
                // $(location).attr('href','generate_error');
            }
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
        }
    })
}