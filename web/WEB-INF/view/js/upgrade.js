
var click_obj;
//click_obj = $(obj);
function showDialog(obj, name) {
    click_obj = $(obj);
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
    alert("name: "+username);
    $.ajax({
        url:"deleteUser.do",
        type:"post",
        data:{"username":username},
        dataType:"json",
        contentType:"application/x-www-form-urlencoded",
        success:function (data) {
            alert(data[0].info + " " +username);
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

