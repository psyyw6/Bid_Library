<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lishanshan
  Date: 18/2/9
  Time: 下午11:28
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
    <script src="js/staff_search.js" rel="script"></script>
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

        <div class="search-box-wrapper">
            <form class="form-inline" id="search-form" method="post" action="search.do">
                <div class="form-group">
                    <input type="text" id="search-box" class="form-control cf" placeholder="Type your keyword here" name="keyword">
                </div>
                <input type="submit" id="search-button" class="btn btn-primary" value="Search">
                <select class="form-control" id="search-select" name="tag">
                    <option>Default</option>
                    <option>Content Title</option>
                    <option>Author</option>
                    <option>External/Internal</option>
                    <option>Section Name</option>
                </select>
            </form>
        </div>

        <div class="search-result-wrapper">
            <div class="search-table-title">
                <h2 style="color:white">Result Contents:</h2>
            </div>
        </div>
        <div class="result-table-wrapper">
            <table class="table">
                <tr id="tr-th">
                    <th><b>Select</b></th>
                    <th><b>Content Title<br></b></th>
                    <th><b>Author</b></th>
                    <th><b>Upload Date</b></th>
                    <th><b>Customer</b></th>
                    <th><b>Expired Date</b></th>
                    <th><b>Type</b></th>
                    <th><b>Details</b></th>
                </tr>
                <c:forEach var="content_list"  items = "${content_list}" varStatus="status">
                <tr class="tr-td">
                    <td><input type="checkbox" name="select_box"></td>
                    <td>${content_list.content_title}</td>
                    <td>${content_list.author}</td>
                    <td>${content_list.upload_date}</td>
                    <td>${content_list.customer}</td>
                    <td>${content_list.expired_date}</td><input type="hidden" id="index" value="${status.index}">
                    <td>${content_list.isExternal}</td>
                    <td><input type="button" class="btn btn-info" value="Details" onclick="viewSection(this)"></td>
                </tr>
                    <c:set value="${'section'}${status.index}" var="option1"></c:set>
                    <tbody class="section_div">
                        <c:forEach var="section_list" items="${requestScope[option1]}">
                        <tr class="section-td">
                            <td colspan="3">${section_list.section_name}</td>
                            <td colspan="3">Version: ${section_list.section_version}</td>
                            <td colspan="2"><input type="button" class="btn btn-primary button-view" value="View"></td>
                        </tr>
                        </c:forEach>
                    </tbody>>
                </c:forEach>
            </table>
        </div>
        <div class="generate-button-wrapper">
            <input type="button" class="btn btn-primary" value="Generate">
        </div>

    </section>
</main>
</body>
</html>
