<%--
  Created by IntelliJ IDEA.
  User: lishanshan
  Date: 18/3/11
  Time: 下午9:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Search</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <meta name="theme-color" content="#ffffff">
    <link rel="icon" type="image/png" href="img/ato_icon.png" sizes="200x200">
    <link rel="stylesheet" href="css/staff_search.css">
    <script src="js/jquery.js"></script>
    <script src="js/login.js" rel="script"></script>
    <script src="js/register.js" rel="script"></script>
    <link rel="stylesheet" href="css/jquery-ui.css">
    <link rel="stylesheet" href="css/jquery-ui.theme.css">
    <link rel="stylesheet" href="css/jquery-ui.structure.css">
</head>
<body class="page_content page_home">
<header class="header">
    <div class="wrapper">
        <div class="header_pre cf">
            <ul class="header_pre-institutional">
                <li><p>Welcome your DIS is 'employee DIS' </p></li>
            </ul>
            <ul class="header_pre-external">
                <li><a href="#">Logout</a>
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
                    <li><a href="#">Home</a></li>
                    <li style="text-decoration: underline;"><a href="#">Search</a></li>
                </ul>
            </nav>
        </div>
    </div>
</header>
<main role="main">
    <section class="page">
        <header class="page-large">
            <div class="page-image" style="background-image:url(https://atos.net/wp-content/uploads/2016/06/Scientific-Community-banner.jpg);">
                <div class="page_header-grad">
                    <form class="form-inline" action="/search.do" method="get">
                        <div class="col-sm-offset-1 col-sm-3">
                            <select class="form-control" name = "search_area" style="width: 100%">
                                <option value="everything">Everything</option>
                                <option value="Customer">Customer Name</option>
                                <option value="Section_Detail"> Section Detail</option>
                                <option value="Section_Name"> Section Name</option>
                                <option value="Title"> Content Title</option>
                            </select>
                        </div>
                        <div class="col-sm-8">
                            <div class="input-group" style="width: 95%">
                                <input type="text" name="keyword" class="form-control" placeholder="Search">
                                <div class="input-group-btn">
                                    <button class="btn btn-primary" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>


                <div class="page_content-grad">
                    <div class="row">
                        <div class="col-sm-12" style="margin:20px 0">
                            <div class="panel panel-danger">
                                <div class="panel-heading">
                                    <c:if test="${not empty errCode}">
                                        <h1  style="color : darkred;"><span class="glyphicon glyphicon-warning-sign"></span> ${errCode}</h1>
                                    </c:if>
                                    <c:if test="${empty errCode}">
                                        <h1  style="color : darkred;">System Errors</h1>
                                    </c:if>
                                </div>
                                <div class="panel-body">
                                    <c:if test="${not empty errMsg}">
                                        <h2  style="color : darkred;">${errMsg}</h2>
                                    </c:if>
                                </div>
                                <button type="button" id="button-green" onclick="window.location.href='/staff_search'">Go back</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>

    </section>
</main>
</body>
</html>
