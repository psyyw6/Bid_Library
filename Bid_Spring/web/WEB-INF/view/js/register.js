$(document).ready(function() {
    $("#email,#username").click(function () {
        var password = $("#password").val();
        var cf_password = $('#cf-password').val();

        if(password!="") {
            if(password!=cf_password) {
                $("#password_hint").css("visibility","visible");
            }
            else{
                $("#password_hint").css("visibility","hidden");
            }
        }
        else{
            $("#password_hint").css("visibility","hidden");
        }
    });


});

function checkUsername() {
    var username = $("#username").val();
    var user_hint  = $("#username_hint");
    $.ajax({
            url:"checkUser.do",
            data:{"username":username},
            type:"post",
            dataType:"json",
            success:function(data) {
                var response = data;
                if(response[0].info == "yes")
                {
                    user_hint.html("Duplicate username!");
                }
                else{
                    user_hint.html("");
                }


            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                user_hint.html(XMLHttpRequest.status);
            }

        }
    )
}

