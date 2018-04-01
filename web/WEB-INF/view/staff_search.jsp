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
    <script src="js/jquery.js"></script>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <meta name="theme-color" content="#ffffff">
    <link rel="icon" type="image/png" href="img/ato_icon.png" sizes="200x200">
    <link rel="stylesheet" href="css/staff_search.css">
    <script src="js/staff_search.js?t=4" rel="script"></script>
    <link rel="stylesheet" href="css/add_solution.css">
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
                    <li><a href="/staff_search">Search</a></li>
                </ul>
            </nav>
        </div>
    </div>
    <div class="search-box-wrapper">
        <form class="form-inline" method="post" action="search.do">
            <div class="form-group">
                <input type="text" id="search-box" class="form-control cf" value="${keyword}" placeholder="Type your keyword here" name="keyword">
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


        <div class="search-result-wrapper">
            <div class="search-table-title">
                <h2 style="color:black">Result Contents:</h2>
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
                    <td>${content_list.content_title}</td><input type="hidden" id="content_title" value="${content_list.content_title}">
                    <td>${content_list.author}</td>
                    <td>${content_list.upload_date}</td>
                    <td>${content_list.customer}</td>
                    <td>${content_list.expired_date}</td><input type="hidden" id="index" value="${status.index}">
                    <td>${content_list.isExternal} <input type="hidden" id="type" value="${content_list.isExternal}"></td>
                    <td><input type="button" class="btn btn-info" value="Details" onclick="viewSection(this)"></td>
                </tr>
                    <c:set value="${'section'}${status.index}" var="option1"></c:set>
                    <tbody class="section_div">
                        <c:forEach var="section_list" items="${requestScope[option1]}">
                         <form>
                            <tr class="section-td">
                                <td colspan="3">${section_list.section_name}</td><input type="hidden" name="section_name" value="${section_list.section_name}">
                                <td colspan="3">Version: ${section_list.section_version}</td><input type="hidden" name="version" value="${section_list.section_version}">
                                <input type="hidden" name="content_title" value=${content_list.content_title}>
                                <input type="hidden" name="isExternal" value="${content_list.isExternal}">
                                <td colspan="2"><input type="submit" class="btn btn-primary button-view" value="View" onclick="this.form.action ='/userViewDetail.do'"></td>
                            </tr>
                         </form>
                        </c:forEach>
                    </tbody>
                </c:forEach>
            </table>
        </div>

        <div class="generate-button-wrapper">
            <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#template_area" aria-expanded="false" aria-controls="template_area">
                Select a template
            </button>
            <div class="collapse" id="template_area">
                <div class="well row">
                    <c:forEach var="template"  items = "${allTemplates}">
                    <div class="col-md-4" style="text-align: center">
                        <input type="radio" name="template_option" value="${template.template_name}">&nbsp;&nbsp;&nbsp;<img src="${template.cover_image}" alt="template1" class="img-rounded " width="150" height="200" >
                        <br><br>
                        <span class="template_name">${template.template_name}</span>
                    </div>
                    </c:forEach>

                </div>
            </div>
        </div>

        <div class="generate-button-wrapper">
            <input type="button" class="btn btn-primary" value="Generate" onclick="checkBoxTest()">
        </div>


    </section>
</main>
</body>
</html>