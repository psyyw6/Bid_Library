<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: lishanshan
  Date: 18/1/30
  Time: 下午4:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Solution</title>
    <meta name="description" content="A free html template with Sketch design made with Bootstrap">
    <meta name="keywords" content="free html template, bootstrap, ui kit, sass"/>
    <meta name="author" content="Codrops"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" type="image/png" href="img/ato_icon.png" sizes="200x200">
    <meta name="msapplication-TileColor" content="#66e0e5">
    <meta name="msapplication-TileImage" content="img/favicon/mstile-144x144.png">
    <meta name="msapplication-config" content="img/favicon/browserconfig.xml">
    <meta name="theme-color" content="#ffffff">
    <link rel="stylesheet" href="css/solution.css">
    <script src="js/jquery.js"></script>
    <link rel="stylesheet" id="atos_css-css" href="https://atos.net/wp-content/themes/atos/style.css" type="text/css" media="screen">
    <link rel="stylesheet" id="js_composer_front-css" href="https://atos.net/wp-content/plugins/js_composer/assets/css/js_composer.min.css" type="text/css" media="all">
    <script type="text/javascript" src="https://atos.net/wp-content/plugins/sitepress-multilingual-cms/templates/language-switchers/legacy-dropdown/script.js"></script>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    <script type="text/javascript" src="https://atos.net/wp-includes/js/jquery/jquery-migrate.min.js"></script>
    <script type="text/javascript" src="https://atos.net/wp-content/themes/atos/js/lib.js"></script>

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
                    <img src="https://atos.net/wp-content/themes/atos/images/atos-logo-menu-bar.png" alt="Atos"></a>
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
            <div class="page_header-image cf" style="background-image: url(https://atos.net/wp-content/uploads/2016/06/About-us-1.png);">

            </div>
        </header>
        <div class="section page_button">
            <a class="button-white" style="border: solid 2px #0066a1;" href="add_solution">Add New Solution</a>
        </div>
        <div class="section page_main_content">
            <div class="grid-row">
                <div class="grid-col-12">
                    <h1 style="text-align: left; padding-bottom: 10px;">current solution documents</h1>
                    <table>
                        <tbody>
                        <tr>
                            <th width="200"><b>Content Title<br></b></th>
                            <th><b>Author</b></th>
                            <th><b>Version<br></b></th>
                            <th width="200"><b>Upload Date</b></th>
                            <th width="200"><b>Customer</b></th>
                            <th width="200"><b>Expired Date</b></th>
                            <th width="200"><b>Flag</b></th>
                            <th colspan="3"><b>Action</b></th>
                        </tr>
                        <c:forEach var="content_list"  items = "${content_list}">
                            <form id="class_table">
                            <tr>
                                <td>${content_list.content_title} <input type="hidden" name="content_title" value="${content_list.content_title}"></td>
                                <td>${content_list.author}</td>
                                <td>${content_list.version} <input type="hidden" name="version" value="${content_list.version}"></td>
                                <td>${content_list.upload_date}</td>
                                <td>${content_list.customer}</td>
                                <td>${content_list.expired_date}</td>
                                <td>${content_list.flag}</td>
                                <td><input type="submit" id="button-blue" value="DETAILS" onclick="javascript:this.form.action='/admin_view_detail'"></td>
                                <td><input type="submit" id="button-red" value="DELETE"></td>
                                <td><input type="submit" id="button-grey" value="HISTORY" onclick="javascript:this.form.action='/content_history'"></td>
                            </tr>
                            </form>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </section>
</main>
</body>
</html>
