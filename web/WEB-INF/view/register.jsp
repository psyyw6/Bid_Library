<%--
  Created by IntelliJ IDEA.
  User: yutong
  Date: 17/12/10
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Registration</title>
    <meta name="description" content="A free html template with Sketch design made with Bootstrap">
    <meta name="keywords" content="free html template, bootstrap, ui kit, sass"/>
    <meta name="author" content="Codrops"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" type="image/png" href="img/ato_icon.png" sizes="200x200">
    <meta name="msapplication-TileColor" content="#66e0e5">
    <meta name="msapplication-TileImage" content="img/favicon/mstile-144x144.png">
    <meta name="msapplication-config" content="img/favicon/browserconfig.xml">
    <meta name="theme-color" content="#ffffff">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/register.css">
    <script src="js/jquery.js"></script>
    <script src="js/login.js" rel="script"></script>
    <script src="js/register.js" rel="script"></script>
</head>
<body>
<div class="catalogue">
    <div class="cal-image">
        <a href="#"><img src="img/list.png" width="90" height="65"></a>
    </div>
    <ul class="options">
        <li><a href="https://atos.net/en/">Home</a></li>
        <li><a href="#">Bid Library</a></li>
        <li><a href="/login">Log In</a></li>
        <li><a href="#">Contact Us</a></li>
    </ul>
</div>
<div class="form-warpper">
    <form class="register">
        <div class="logo-image">
            <img src="img/atos_logo_3.png" width="170" height="80">
        </div>
        <div class="customer_register">CUSTOMER REGISTRATION<br></div>
        <div class="hint1">
            <a class="hint_words" href="/login">Already have an account? Log in here</a>
        </div>
        <span class="input">
            <span id="username_hint">xxxxx</span><br>
            <span class="input_content"><img src="img/user.png" width="30" height="30"></span>
            <input class="input_field" type="text" name="username" id="username" placeholder="Username" onfocus="this.placeholder='';"onblur="if (this.placeholder == '') {this.placeholder = 'DAS ID';}" required="required"/>
        </span>
        <span class="input input_password_confirm">
            <span id="password_hint_one">xxxxx</span><br>
            <span class="input-content"><img src="img/password_logo.png" width="30" height="30"></span>
            <input class="input_field" type="password" name="password" id="password" placeholder="Password" onfocus="this.placeholder='';"onblur="if (this.placeholder == '') {this.placeholder = 'Password';}" required="required"
            />
        </span>
        <span class="input input_password_confirm">
            <span id="password_hint">password do not same</span><br>
            <span class="input-content"><img src="img/password_logo.png" width="30" height="30"></span>
            <input class="input_field" type="password" name = "cf_password" id="cf-password" placeholder="Confirm Password" onfocus="this.placeholder='';"onblur="if (this.placeholder == '') {this.placeholder = 'Confirm Password';}"
                   required="required" />

        </span>
        <span class="input">
            <span class="input-content"><img src="img/email_logo.png" width="30" height="30"></span>
            <input class="input_field" type="email" name="email" id="email" placeholder="Email" onfocus="this.placeholder='';"onblur="if (this.placeholder == '') {this.placeholder = 'Email';}" onclick="checkUsername()" required="required"/>
        </span>
        <input class="submit" value="Register" type="button" onclick="register_user()"/>
    </form>
</div>

</body>
</html>
