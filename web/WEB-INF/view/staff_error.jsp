<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lishanshan
  Date: 18/2/9
  Time: 下午11:28
  To change this template use File | Settings | File Templates.
--%>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Search</title>
    <script src="js/jquery.js"></script>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <meta name="theme-color" content="#ffffff">
    <link rel="icon" type="image/png" href="img/ato_icon.png" sizes="200x200">
    <link rel="stylesheet" href="css/staff_search.css">
    <script src="js/staff_search.js?t=3" rel="script"></script>
    <link rel="stylesheet" href="css/add_solution.css">
    <link rel="stylesheet" id="js_composer_front-css" href="https://atos.net/wp-content/plugins/js_composer/assets/css/js_composer.min.css" type="text/css" media="all">
    <link rel="stylesheet" href="css/jquery-ui.css">
    <link rel="stylesheet" href="css/jquery-ui.theme.css">
    <link rel="stylesheet" href="css/jquery-ui.structure.css">
</head>
<body class="page_content page_home">
<header class="header" id="header-id">
    <div class="wrapper">
        <div class="header_pre cf">
            <ul class="header_pre-institutional">
                <li><p>Welcome ${name}&nbsp;&nbsp;(Role: <span id="role">${role}</span>)</p></li>
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
                    <li><a href="/administer_solution" id="Manage" style="visibility:hidden">Manage</a></li>
                    <li><a href="#">Home</a></li>
                    <li><a href="#">Search</a></li>
                </ul>
            </nav>
        </div>
    </div>

    <div class="search-box-wrapper">
        <form class="form-inline" method="post" action="search.do">
            <div class="form-group">
                <input type="text" id="search-box" class="form-control cf" placeholder="Type your keyword here" name="keyword">
            </div>
            <input type="submit" id="search-button" class="btn btn-primary" value="Search">
            <select class="form-control" id="search-select" name="tag">
                <option value="Default">Default</option>
                <option value="Content Title">Content Title</option>
                <option value="Author">Author</option>
                <option value="Customer">Customer</option>
                <option value="Type">External/Internal</option>
                <option value="Section Name">Section Name</option>
            </select>
        </form>
    </div>
</header>
<main role="main">
    <section class="section page">

        <header class="page_header-large">
            <div class="page_header-image cf" style="background-image: url(https://atos.net/wp-content/uploads/2016/07/Atos-Codex-1.jpg);">
            </div>
        </header>

        <div class="result-table-wrapper">
            <div class="col-sm-10 col-sm-offset-2" style="margin-top: 50px;">
                <div class="panel panel-danger">
                    <div class="panel-heading">
                        <c:if test="${not empty errCode}">
                            <h1  style="color : red;"><span class="glyphicon glyphicon-warning-sign"></span> ${errCode}</h1>
                        </c:if>
                        <c:if test="${empty errCode}">
                            <h1  style="color : red;">System Errors</h1>
                        </c:if>
                    </div>
                    <div class="panel-body">
                        <c:if test="${not empty errMsg}">
                            <h4 style="color : red;">${errMsg}</h4>
                        </c:if>
                    </div>
                </div>
            </div>

        </div>
    </section>
</main>
</body>
</html>
