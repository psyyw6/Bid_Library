
var click_obj;
//click_obj = $(obj);
function showDialog(obj, name) {
    click_obj = $(obj);
    var role = click_obj.parents("tr").find("#role").val();
    if(role=="true" && name == "#myModal"){
        $("#adminModal").modal({});
        return false;
    }

    var username  = click_obj.parents("tr").find("#username").val();
    var login_user = $("#login_user").text();
    if(login_user == username){
        $("#delete_warning").modal({});

        return false;
    }
    $(name).modal({});
}

function Upgrade(){
    var username  = click_obj.parents("tr").find("#username").val();

    $.ajax({
        url:"upgradeUser.do",
        type:"post",
        data:{"username":username},
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


function Delete(){
    var username  = click_obj.parents("tr").find("#username").val();
    $.ajax({
        url:"deleteUser.do",
        type:"post",
        data:{"username":username},
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

