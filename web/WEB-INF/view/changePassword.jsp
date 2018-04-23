<%--
  Created by IntelliJ IDEA.
  User: lishanshan
  Date: 18/4/8
  Time: 上午10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Change Password</title>
    <link rel="icon" type="image/png" href="img/ato_icon.png" sizes="200x200">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <meta name="theme-color" content="#ffffff">
    <link rel="stylesheet" type="text/css" href="css/add_solution.css">
    <script src="js/jquery.js"></script>
    <script src="js/change_password.js?t=6"></script>
    <link rel="stylesheet" href="css/jquery-ui.css">
    <link rel="stylesheet" href="css/jquery-ui.theme.css">
    <link rel="stylesheet" href="css/jquery-ui.structure.css">
    <link rel="stylesheet" id="js_composer_front-css" href="https://atos.net/wp-content/plugins/js_composer/assets/css/js_composer.min.css" type="text/css" media="all">
    <script src="js/jquery-ui.js"></script>
    <script src="js/jquery-ui.min.js"></script>
</head>
<body class="page_content page_home">
<header class="header">
    <div class="wrapper">
        <div class="header_pre cf">
            <ul class="header_pre-institutional">
                <li><p>Welcome ${name}</p></li>
            </ul>
            <ul class="header_pre-external">
                <li><a href="/logout">Logout</a>
                </li>
            </ul>
        </div>
        <div class="header_main cf">
            <div class="header_main-logo">
                <a href="https://atos.net/en">
                    <img height="38px" src="https://atos.net/wp-content/themes/atos/images/atos-logo-menu-bar.png" alt="Atos"></a>
            </div>
            <nav class="header_main-nav">
                <ul class="header_main-menu">
                    <li><a href="/staff_search">Search</a></li>
                    <li><a href="/upgrade">Upgrade</a></li>
                    <li><a href="template">Templates</a></li>
                    <li><a href="/download_log">Log</a></li>
                    <li><a href="/administer_solution">Contents</a></li>
                </ul>
            </nav>
        </div>
    </div>
</header>
<main role="main">
    <section class="section page">
        <header class="page_header-large">
            <div class="page_header-image cf"  style="background-image: url(https://atos.net/wp-content/uploads/2017/02/atos-lights-map-na.jpg)">
            </div>
        </header>
        <div class = "title_header">
            <p class = "title_word">Change Password</p>
        </div>
    </section>
    <section class="upload_form">
        <form class="form-horizontal">
            <div class="form-group">
                <label for="username" class="col-sm-2 control-label">User</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="username" name="username" value = "${username}" placeholder="Username" disabled>
                </div>
            </div>
            <div class="form-group">
                <label for="newPassword" class="col-sm-2 control-label">New Password</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" id="newPassword" name="newPassword" placeholder="New Password" onclick="removeHint(this)">
                    <span class="help-block" style="display: none">Password can not be empty!</span>
                </div>
            </div>
            <div class="form-group">
            <label for="confirmPassword" class="col-sm-2 control-label">Confirm Password</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Confirm Password" onclick="removeHint(this)">
                <span class="help-block" style="display: none">Password can not be empty!</span>
                <span class="help-block" id="duplicate_info" style="display: none">Two passwords must be the constant!</span>
            </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="button" id="upload_button" class="btn btn-primary" value="Change" onclick="changePassword()">
                </div>
            </div>
        </form>
    </section>
</main>




</body>
</html>

