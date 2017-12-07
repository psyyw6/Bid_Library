<%--
  Created by IntelliJ IDEA.
  User: yutong
  Date: 17/12/7
  Time: 13:47
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
    <link rel="icon" type="image/png" href="img/favicon/ato_icon.png" sizes="200x200">
    <link rel="manifest" href="img/favicon/manifest.json">
    <link rel="mask-icon" href="img/favicon/safari-pinned-tab.svg" color="#5bbad5">
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
        <li><a href="index.jsp">Bid Library</a></li>
        <li><a href="login.jsp">Log In</a></li>
        <li><a href="#">Contact Us</a></li>
    </ul>
</div>

<form class = log_in action = "login.jsp" method="get">
    <div class="logo-image">
        <img src="img/Atos_logo.png" width="250" height="150">
    </div>
    <br>
    <div class="customer_login">CUSTOMER LOG IN</div><br>
    <div class="hint1">
        <a class="hint_words" href="#">Haven't register yet? Please register here</a>
    </div>
    <span class="input input--haruki">
                    <label class="input__label input__label--haruki" for="input-1">
						<span class="input__label-content "><img src="img/user.png" width="40" height="40"></span>
					</label>
					<input class="input__field input__field--username" type="text" id="input-1" placeholder="Username" onfocus="this.placeholder='';"onblur="if (this.placeholder == '') {this.placeholder = 'Username';}"/>
            </span>
    <span class="input input--password">
                <label class="input__label input__label--haruki" for="input-1">
                    <span class="input__label-content"><img src="img/password_logo.png" width="40" height="40"></span>
                </label>
                <input class="input__field input__field--username" type="password" placeholder="Password" onfocus="this.placeholder='';"onblur="if (this.placeholder == '') {this.placeholder = 'Password';}"/>
            </span>
    <span class="input input--submit">
                <input class="input__field input__submit" type="submit" value="Log In">
            </span>
</form>
</body>

</html>

