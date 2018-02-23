<%--
  Created by IntelliJ IDEA.
  User: yutong
  Date: 17/12/10
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Log in</title>
    <meta name="description" content="A free html template with Sketch design made with Bootstrap">
    <meta name="keywords" content="free html template, bootstrap, ui kit, sass"/>
    <meta name="author" content="Codrops"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" type="image/png" href="img/ato_icon.png" sizes="200x200">
    <meta name="msapplication-TileColor" content="#66e0e5">
    <meta name="msapplication-TileImage" content="img/favicon/mstile-144x144.png">
    <meta name="msapplication-config" content="img/favicon/browserconfig.xml">
    <meta name="theme-color" content="#ffffff">
    <link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet" href="css/style.css">
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
        <li><a href="#">Bid Library</a></li>
        <li><a href="/login">Log In</a></li>
        <li><a href="#">Contact Us</a></li>
    </ul>
</div>

<form class = log_in>
    <div class="logo-image">
        <img src="img/atos_logo_3.png" width="170" height="80">
    </div>
    <br>
    <div class="customer_login">CUSTOMER LOG IN</div><br>
    <div class="hint1">
        <a class="hint_words" href="/register">Haven't register yet? Please register here</a>
    </div>
    <span class="input input--haruki">
                    <label class="input__label input__label--haruki">
						<span class="input__label-content "><img src="img/user.png" width="30" height="30"></span>
					</label>
					<input class="input__field input__field--username" type="text" name = "username" id="username" placeholder="DAS ID" onfocus="this.placeholder='';"onblur="if (this.placeholder == '') {this.placeholder = 'DAS ID';}"/>
    </span>
    <span class="input input--password">
                <label class="input__label input__label--haruki">
                    <span class="input__label-content"><img src="img/password_logo.png" width="30" height="30"></span>
                </label>
                <input class="input__field input__field--username" type="password" name="password" id="password" placeholder="Password" onfocus="this.placeholder='';"onblur="if (this.placeholder == '') {this.placeholder = 'Password';}"/>
    </span>
    <span class="input input--submit">
                <input class="input__field input__submit" type="button" value="Log In" onclick="login()">
    </span>
</form>
</body>

</html>

