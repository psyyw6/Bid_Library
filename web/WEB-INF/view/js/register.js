$(document).ready(function() {
    $("#email,#username").click(function () {
        var password = $("#password").val();
        var cf_password = $('#cf-password').val();
        if(password == "") {
            $("#password_hint_one").css("visibility","visible");
            $("#password_hint_one").html("Password can not be empty");
        }
        if(password!="") {
            $("#password_hint_one").css("visibility","hidden");
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
    if(username == "") {
        $("#username_hint").css("visibility","visible");
        user_hint.html("Username can not be empty");
        return false;
    }
    else{
        user_hint.html("xxxxx");
        $("#username_hint").css("visibility","hidden");
    }
    $.ajax({
            url:"checkUser.do",
            data:{"username":username},
            type:"post",
            dataType:"json",
            success:function(data) {
                var response = data;
                if(response[0].info == "yes")
                {
                    $("#username_hint").css("visibility","visible");
                    user_hint.html("Duplicate username!");
                }
                else{
                    $("#username_hint").css("visibility","hidden");
                    user_hint.html("xxxxxxx");
                }
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                user_hint.html(XMLHttpRequest.status);
            }

        }
    )
}

function register_user(){
    var username = $("#username").val();
    var pwd = $("#password").val();
    var cf_pwd = $("#cf-password").val();
    if(cf_pwd!=pwd) {
        alert("fail to register");
        $(location).attr('href','register');
        return false;
    }
    var email = $("#email").val();
    if(username == ""||pwd==""||email=="") {
        return false;
    }

    $.ajax({
        url:"reg.do",
        data:{
            "username":username,
            "pwd":pwd,
            "email":email
        },
        type:"post",
        dataType:"json",
        contentType:"application/x-www-form-urlencoded",
        success:function(data){
            var response = data;
            if(response[0].info == "true"){
                alert("register successful");
                $(location).attr('href','login');
            }
            else{
                alert("register failed");
                $(location).attr('href','register');
            }
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
           alert(XMLHttpRequest.status);
        }

    })
}
