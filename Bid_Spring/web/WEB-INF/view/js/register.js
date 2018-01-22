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
