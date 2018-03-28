
var click_obj;

function showDialog(obj) {
    click_obj = $(obj);
    $("#myModal").modal({});
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