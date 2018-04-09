
var click_obj;

function showDialog(obj) {
    click_obj = $(obj);
    $("#changePasswordModal").modal({});
    alert("testtttttt" +obj);
}

function changePassword(){
    var username  = $("#username").val();
    var password = $("#newPassword").val();
    //alert("test");
    if(password == ""){
        var error_info = $("#newPassword").parent().parent();
        $(error_info).addClass("has-error");
        $("#newPassword").parent().find('.help-block').css("display","block");
        return false;
    }

    $.ajax({
        url:"changePassword.do",
        type:"post",
        data:{"username":username, "password":password},
        dataType:"json",
        contentType:"application/x-www-form-urlencoded",
        success:function (data) {
            if(data[0].info == "true"){
                window.location.href = 'upgrade';
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

function removeHint(obj) {
    var error_info =  $(obj).parent().parent();
    $(error_info).removeClass('has-error');
    $(obj).parent().find('.help-block').css("display","none");
}
//
// function testValid(){
//     var password = $("#newPassword").val();
//     var cf_password = $("#confirmPassword").val();
//     var textInput = $(".form-control");
//     var username  = $("#username").val();
//     textInput.each(function(){
//         var text = $(this).val();
//         if(text == ""){
//             var error_info = $(this).parent().parent();
//             $(error_info).addClass("has-error");
//             $(this).parent().find('.help-block').css("display","block");
//         }
//     });
//
//     if(cf_password != password){
//         var error_info = $("#confirmPassword").parent().parent();
//         $(error_info).addClass("has-error");
//         $("#confirmPassword").parent().find('#duplicate_hint').css("display","block");
//         return false;
//     }
//
//     if(password == ""|| cf_password == ""){
//         return false;
//     }
//
// }

