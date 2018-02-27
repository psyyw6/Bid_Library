<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yutong
  Date: 2018/2/23
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
 <head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <title>Content Detail</title>
      <meta name="author" content="Codrops"/>
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="icon" type="image/png" href="img/ato_icon.png" sizes="200x200">
      <meta name="msapplication-TileColor" content="#66e0e5">
      <meta name="msapplication-TileImage" content="img/favicon/mstile-144x144.png">
      <meta name="msapplication-config" content="img/favicon/browserconfig.xml">
      <meta name="theme-color" content="#ffffff">
      <link rel="stylesheet" href="css/add_solution.css">
     <link rel="stylesheet" href="css/view_detail.css">
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
        <div class="section page_main_content">
                <div class="table-title">
                    <h1 style="text-align: left; padding-bottom: 10px;">${content_title} Sections &nbsp;&nbsp;&nbsp;Version: ${version}</h1>
                </div>
                <div class="table-warpper">
                    <table class="table table-stripe">
                        <tbody>
                        <tr>
                            <th><b>Content Title<br></b></th>
                            <th><b>Section Name</b></th>
                            <th></th>
                            <th><b>Action</b></th>
                        </tr>
                        <c:forEach var="section_list"  items = "${section_list}">
                            <form id="class_table">
                                <tr>
                                    <td>${section_list.title} <input type="hidden" name="content_title" value="${section_list.title}"></td>
                                    <td>${section_list.section_name}<input type="hidden" name="section_name" value="${section_list.section_name}"></td>
                                    <td><input type="hidden" name="version" value="${version}"></td>
                                    <td class="button-td"><input type="submit" class="btn btn-primary"id="button-blue" value="EDIT" onclick="javascript:this.form.action='/edit'"></td>
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
