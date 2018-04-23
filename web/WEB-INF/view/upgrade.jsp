<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yutong
  Date: 2018/3/27
  Time: 16:50
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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script src="js/upgrade.js"></script>
    <style>
        th{
            text-align: center !important;
            color: white;
            background-color: #197FBA;
        }

        td{
            padding-top: 20px !important;
            padding-bottom: 20px;!important;
            text-align: center;
            font-size: 20px;
        }
    </style>
</head>
<body class="page_content page_home">
<header class="header">
    <div class="wrapper">
        <div class="header_pre cf">
            <ul class="header_pre-institutional">
                <li><p>Welcome <a href="/changePassword?username=${name}" id="login_user">${name}</a></p></li>
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
                    <li><a href="/staff_search">Search</a></li>
                    <li><a href="/upgrade">Upgrade</a></li>
                    <li><a href="/template">Templates</a></li>
                    <li><a href="/download_log">Log</a></li>
                    <li><a href="/administer_solution">Contents</a></li>
                </ul>
            </nav>
        </div>
    </div>
</header>

<section class="section page">
    <header class="page_header-large">
        <div class="page_header-image cf" style="background-image: url(https://atos.net/wp-content/uploads/2016/06/About-us-1.png);">

        </div>
    </header>
    <div class="section page_button">
        <div class = "button-warpper">
            <a class="button-white" href="add_solution">ADD NEW CONTENT</a>
        </div>
        <div class="section page_main_content">
            <div class="table-title">
                <h1 style="text-align: left; padding-bottom: 10px;">current users</h1>
            </div>
        </div>
        <div class="user-table-warpper">
            <table class="table table-striped" id="myTable">
                <thead>
                <tr>
                    <th><b>Username</b></th>
                    <th><b>Email</b></th>
                    <th><b>Role</b></th>
                    <th class="button-th" colspan="3"><b>Action</b></th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="user_list"  items = "${user_list}">
                    <form id="class_table">
                        <tr>
                            <td>${user_list.name} <input type="hidden" id="username" name="username" value="${user_list.name}"></td>
                            <td>${user_list.email}</td>
                            <td>${user_list.role_string} <input type="hidden" id="role" name="role" value="${user_list.role}"></td>
                            <td class="button-td"><input type="button" class="btn btn-primary" id="button-upgrade" value="UPGRADE" onclick="showDialog(this, '#myModal')"></td>
                            <td class="button-td"><input type="submit" class="btn btn-info" id="button-change" value="CHANGE PASSWORD" onclick="this.form.action='/changePassword'"></td>
                            <td class="button-td"><input type="button" class="btn btn-danger" id="button-delete" value="DELETE" onclick="showDialog(this, '#deleteModal')"></td>
                        </tr>
                    </form>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</section>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Upgrade</h4>
            </div>
            <div class="modal-body">
                Are you sure to make this user an administrator?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-info" onclick="Upgrade()">Yes</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="deleteModalLabel">Delete</h4>
            </div>
            <div class="modal-body">
                Are you sure to delete the user?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-danger" onclick="Delete()">Yes</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="adminModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="adminModalLabel">Upgrade</h4>
            </div>
            <div class="modal-body">
                Can not upgrade an administrator.
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="delete_warning" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="delete_warningLabel">Delete</h4>
            </div>
            <div class="modal-body">
                You can not delete yourself.
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>


</body>
</html>
