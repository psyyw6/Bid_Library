<%--
  Created by IntelliJ IDEA.
  User: yutong
  Date: 2018/3/5
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: yutong
  Date: 08/02/2018
  Time: 00:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Solution</title>
    <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
    <link href="css/froala_editor.min.css" rel="stylesheet" type="text/css">
    <link rel="icon" type="image/png" href="img/ato_icon.png" sizes="200x200">
    <script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
    <script src="js/froala_editor.min.js"></script>
    <!--[if lt IE 9]>
    <script src="js/froala_editor_ie8.min.js"></script>
    <![endif]-->
    <script src="js/plugins/tables.min.js"></script>
    <script src="js/plugins/lists.min.js"></script>
    <script src="js/plugins/colors.min.js"></script>
    <script src="js/plugins/media_manager.min.js"></script>
    <script src="js/plugins/font_family.min.js"></script>
    <script src="js/plugins/font_size.min.js"></script>
    <script src="js/plugins/block_styles.min.js"></script>
    <script src="js/plugins/video.min.js"></script>
    <script>
        $(function(){
            $('#edit').editable({inlineMode: false, alwaysBlank: true})
        });
    </script>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="css/add_solution.css">
    <link rel="stylesheet" type="text/css" href="css/edit.css">
    <script src="js/jquery-ui.js"></script>
    <script src="js/jquery-ui.min.js"></script>
    <link rel="stylesheet" id="js_composer_front-css" href="https://atos.net/wp-content/plugins/js_composer/assets/css/js_composer.min.css" type="text/css" media="all">
    <style>
        #main-menu{
            margin-left: 70%;
        }

    </style>
</head>
<body>
<header class="header" id="header-id">
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
                <ul class="header_main-menu" id="main-menu">
                    <li><a href="#">Home</a></li>
                    <li><a href="#">Search</a></li>
                </ul>
            </nav>
        </div>
    </div>
</header>
<main role="main">
    <section class="section page">
        <header class="page_header-large">
            <div class="page_header-image cf" style="background-image: url(https://atos.net/wp-content/uploads/2017/02/atos-lights-map-na.jpg);">
            </div>
        </header>
        <div class = "title_header" id="title-header">
            <p class = "title_word">${content_title}: ${section_name}</p><input type="hidden" id="content_title" value="${content_title}">
            <p class= "title_word">Version: ${version}</p><input type="hidden" id="section_name" value="${section_name}">
            <input type="hidden" id="version" value="${version}">
        </div>
    </section>

    <section id="editor">
        <form class="form-horizontal" enctype="multipart/form-data">
            <div id='edit' style="margin-top: 30px;">
                <div id="content">
                    <p>${content}</p>
                </div>
            </div>
            <br>
            </div>
        </form>
    </section>


</main>
</body>





</html>