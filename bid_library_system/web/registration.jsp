<%--
  Created by IntelliJ IDEA.
  User: yutong
  Date: 17/12/7
  Time: 16:17
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
</head>
<body>
<div class="catalogue">
    <div class="cal-image">
        <a href="#" onclick="showList()"><img src="img/list.png" width="90" height="65"></a>
    </div>
    <ul class="options">
        <li><a href="https://atos.net/en/">Home</a></li>
        <li><a href="index.jsp">Bid Library</a></li>
        <li><a href="login.jsp">Log In</a></li>
        <li><a href="#">Contact Us</a></li>
    </ul>
</div>
<div class="form-warpper">
    <form class="register">
        <div class="logo-image">
            <img src="img/Atos_logo.png" width="170" height="100">
        </div>
        <div class="custome_register">CUSTOMER REGISTRATION<br></div>
        <div class="hint1">
            <a class="hint_words" href="login.jsp">Already have an acount? Log in here</a>
        </div>
        <span class="input">
            <span class="input_content"><img src="img/user.png" width="30" height="30"></span>
            <input class="input_field" type="text" placeholder="Username" onfocus="this.placeholder='';"onblur="if (this.placeholder == '') {this.placeholder = 'Username';}"/>
        </span>
        <span class="input">
            <span class="input-content"><img src="img/password_logo.png" width="30" height="30"></span>
            <input class="input_field" type="text" placeholder="Password" onfocus="this.placeholder='';"onblur="if (this.placeholder == '') {this.placeholder = 'Password';}"/>
        </span>
        <span class="input">
            <span class="input-content"><img src="img/password_logo.png" width="30" height="30"></span>
            <input class="input_field" type="text" placeholder="Confirm Password" onfocus="this.placeholder='';"onblur="if (this.placeholder == '') {this.placeholder = 'Confirm Password';}"/>
        </span>
        <span class="input">
            <span class="input-content"><img src="img/email_logo.png" width="30" height="30"></span>
            <input class="input_field" type="text" placeholder="Email" onfocus="this.placeholder='';"onblur="if (this.placeholder == '') {this.placeholder = 'Email';}"/>
        </span>
        <span class="input">
            <span class="input-content"><img src="img/department_logo.png" width="30" height="30"></span>
            <input class="input_field" type="text" placeholder="Department" onfocus="this.placeholder='';"onblur="if (this.placeholder == '') {this.placeholder = 'Department';}"/>
        </span>
        <input class="submit" value="Register" type="submit"/>
    </form>
</div>
</body>
</html>
