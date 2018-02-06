<%--
  Created by IntelliJ IDEA.
  User: Joe Drayton
  Date: 06/02/2018
  Time: 13:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Modify Solution</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" type="image/png" href="img/ato_icon.png" sizes="200x200">
    <meta name="msapplication-TileColor" content="#66e0e5">
    <meta name="msapplication-TileImage" content="img/favicon/mstile-144x144.png">
    <meta name="msapplication-config" content="img/favicon/browserconfig.xml">
    <meta name="theme-color" content="#ffffff">
    <link rel="stylesheet" type="text/css" href="css/modify.css">
    <script src="js/jquery.js"></script>
    <script src="js/add_solution.js"></script>
    <link rel="stylesheet" href="css/jquery-ui.css">
    <link rel="stylesheet" href="css/jquery-ui.theme.css">
    <link rel="stylesheet" href="css/jquery-ui.structure.css">
    <!--<link rel="stylesheet" id="atos_css-css" href="https://atos.net/wp-content/themes/atos/style.css" type="text/css" media="screen">-->
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
                    <li><a href="#">Proposal</a></li>
                    <li><a href="#">Request</a></li>
                    <li><a href="#">Events</a></li>
                    <li><a href="/administer_solution">Solution</a></li>
                </ul>
            </nav>
        </div>
    </div>
</header>
<main role="main">
    <section class="section page">
        <header class="page_header-large">
            <div class="page_header-image cf">
            </div>
        </header>
        <div class = "title_header">
            <p class = "title_word">Modify Solution</p>
        </div>
    </section>
    <section class = "add_form">
        <form class = "mod_solution_form" action="upload.do" method="post" enctype="multipart/form-data">

                <span class="form_label">
                    <span class="input_label">Modify solution:&nbsp;</span>
                    <input class="input_button" type = "button" value = "Browse..." onclick="fileInput.click()">
                    <input id = "fileInput" type = "file" name = "myfile" onchange="processFiles(this.files)" style = "display:none">
                </span>
            <span class="form_label">
                    <span class="input_label">Version:&nbsp;</span>
                    <input type="text" class="text_input" id="solution_title">
                </span>
            <span class="upload">
                    <input id="upload_button" type="submit" value="UPLOAD">
                </span>
        </form>
    </section>
</main>
</body>
</html>
