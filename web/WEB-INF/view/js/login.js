$(document).ready(function () {
    $(".cal-image").click(function () {
        if ($(".options").css("visibility") == "hidden") {
            $(".options").css("visibility", "visible");
        }
        else {
            $(".options").css("visibility","hidden");
        }

    });

});

function login() {
    var username = $("#username").val();
    var password = $("#password").val();
    $.ajax({
        url:"login.do",
        data:{"name":username,"password":password},
        type:"post",
        dataType:"json",
        contentType:"application/x-www-form-urlencoded",
        success:function (data) {
            if(data[0].info == "true"){
                $(location).attr('href','administer_solution');
            }
            else{
               alert("username or password is not correct");
            }
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
        }
        })
}