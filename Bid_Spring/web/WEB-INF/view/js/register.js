function checkPassword() {
    var password = document.getElementById("password");
    var cf_password = document.getElementById("cf-password");
    if (password.value != cf_password.value) {
        document.getElementById("password_hint").innerHTML = "xxxx";
    }
    else
    {
        document.getElementById("password_hint").innerHTML = "";
    }
}