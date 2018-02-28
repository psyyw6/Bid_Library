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
                                    <form class="form-inline" action="/search.do" method="post">
                                        <div class="col-sm-offset-1 col-sm-3">
                                            <select class="form-control" style="width: 100%">
                                                <option>tag 1</option>
                                                <option>tag 2</option>
                                                <option>tag 3</option>
                                            </select>
                                        </div>
                                        <div class="col-sm-8">
                                            <div class="input-group" style="width: 95%">
                                                <input type="text" class="form-control" placeholder="Search">
                                                <div class="input-group-btn">
                                                    <button class="btn btn-primary" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>


                                <div class="page_content-grad">
                                    <div class="row">
                                        <div class="col-sm-12" style="margin:0">
                                            <h1>Search Result</h1>
                                            <table class="table table-striped">
                                                <tbody>
                                                <tr>
                                    <th><b>Content Title<br></b></th>
                                    <%--<th><b>Section Name<br></b></th>--%>
                                    <th><b>Author</b></th>
                                    <th><b>Internal/External<br></b></th>
                                    <th><b>Creator DAS ID</b></th>
                                    <th><b>Version</b></th>
                                    <th><b>Modified Date</b></th>
                                    <th><b>Customer Name</b></th>
                                    <th colspan="3"><b>Action</b></th>
                                </tr>
                                <c:forEach var="solution_list"  items = "${solution_list}">
                                    <form id="class_table">
                                        <tr>
                                            <td>${solution_list.content_title} <input type="hidden" name="content_title" value="${solution_list.content_title}"></td>
                                            <%--<td>${solution_list.section_name} <input type="hidden" name="section_name" value="${solution_list.section_name}"></td>--%>
                                            <td>${solution_list.author} <input type="hidden" name="heading" value="${solution_list.author}"></td>
                                            <td>${solution_list.external}</td>
                                            <td>${solution_list.version}</td>
                                            <td>${solution_list.upload_date}</td>
                                            <td>${solution_list.customer}</td>
                                            <td>${solution_list.expired_date}</td>
                                            <td><input type="submit" id="button-blue" value="EDIT" onclick="javascript:this.form.action='/edit'"></td>
                                            <td><input type="submit" id="button-red" value="DELETE"></td>
                                            <td><input type="submit" id="button-grey" value="EXPORT" onclick="javascript:this.form.action='/export_word'"></td>
                                        </tr>
                                    </form>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </header>

    </section>
</main>
</body>
</html>
