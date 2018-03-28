$(document).ready(function() {
    $('.input100').each(function(){
        $(this).on('blur', function(){
            if($(this).val().trim() != "") {
                $(this).addClass('has-val');
            }
            else {
                $(this).removeClass('has-val');
            }
        })
    });

    $('.validate-form .input100').each(function(){
        $(this).focus(function(){
            hideValidate(this);
        });
    });

});

function showValidate(input) {
    var thisAlert = $(input).parent();

    $(thisAlert).addClass('alert-validate');
}

function hideValidate(input) {
    var thisAlert = $(input).parent();

    $(thisAlert).removeClass('alert-validate');
}
function validate (input) {
    if($(input).attr('type') == 'email' || $(input).attr('name') == 'email') {
        if($(input).val().trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
            return false;
        }
    }
    else {
        if($(input).val().trim() == ''){
            return false;
        }
    }
}

function register_user(){

    var username = $("#username").val();
    var pwd = $("#password").val();
    var cf_pwd = $("#cf-password").val();
    var input = $('.validate-input .input100');
    var check = true;
    var usernameCheck = false;
    for (var i = 0; i < input.length; i++) {
        if (validate(input[i]) == false) {
            showValidate(input[i]);
            check = false;
        }
    }
    if(check == false){
        return false;
    }
    if(cf_pwd!=pwd) {
        showValidate($("#cf-password"));
        return false;
    }
    var email = $("#email").val();
    $.ajax({
            url:"checkUser.do",
            data:{"username":username},
            type:"post",
            dataType:"json",
            async: false,
            success:function(data) {
                var response = data;
                if(response[0].info == "yes")
                {
                    showValidate($("#username"));
                    usernameCheck = false;
                }
                else{
                    usernameCheck = true;
                }
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                user_hint.html(XMLHttpRequest.status);
            }

        }
    );

    if(usernameCheck == false){
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
