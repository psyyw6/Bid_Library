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
    <title>Content</title>
    <meta name="author" content="Codrops"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" type="image/png" href="img/ato_icon.png" sizes="200x200">
    <meta name="msapplication-TileColor" content="#66e0e5">
    <meta name="msapplication-TileImage" content="img/favicon/mstile-144x144.png">
    <meta name="msapplication-config" content="img/favicon/browserconfig.xml">
    <meta name="theme-color" content="#ffffff">
    <link rel="stylesheet" href="css/add_solution.css">
    <script src="js/jquery.js"></script>
    <link rel="stylesheet" id="js_composer_front-css" href="https://atos.net/wp-content/plugins/js_composer/assets/css/js_composer.min.css" type="text/css" media="all">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
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
            <div class="page_header-image cf" style="background-image: url(https://atos.net/wp-content/uploads/2016/06/About-us-1.png);">

            </div>
        </header>
        <div class="section page_button">
            <div class = "button-warpper">
                <a class="button-white" href="add_solution">ADD NEW CONTENT</a>
            </div>
        </div>
        <div class="section page_main_content">
                <div class="table-title">
                    <h1 style="text-align: left; padding-bottom: 10px;">current contents</h1>
                </div>
            <div class="table-warpper">
                <table class="table table-striped">
                    <tbody>
                    <tr>
                        <th><b>Content Title<br></b></th>
                        <th><b>Author</b></th>
                        <th><b>Version<br></b></th>
                        <th><b>Upload Date</b></th>
                        <th><b>Customer</b></th>
                        <th><b>Expired Date</b></th>
                        <th><b>Flag</b></th>
                        <th class="button-th" colspan="3" width="50"><b>Action</b></th>
                    </tr>
                    <c:forEach var="content_list"  items = "${content_list}">
                        <form id="class_table">
                            <tr>
                                <td>${content_list.content_title} <input type="hidden" id= "content_title" name="content_title" value="${content_list.content_title}"></td>
                                <td>${content_list.author}</td>
                                <td>${content_list.version} <input type="hidden" id="version" name="version" value="${content_list.version}"></td>
                                <td>${content_list.upload_date}</td>
                                <td>${content_list.customer}</td>
                                <td>${content_list.expired_date}</td>
                                <td>${content_list.flag}</td>
                                <td class="button-td"><input type="submit" class="btn btn-info" id="button-blue" value="DETAILS" onclick="this.form.action='/admin_view_detail'"></td>
                                <td class="button-td"><input type="button" class="btn btn-danger" id="button-red" value="DELETE" onclick="showDialog(this)"></td>
                                <td class="button-td"><input type="submit" class="btn btn-warning" id="button-grey" value="HISTORY" onclick="this.form.action='/content_history'"></td>
                            </tr>
                        </form>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </section>
</main>
</body>
</html>
