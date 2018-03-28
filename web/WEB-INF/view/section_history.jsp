<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yutong
  Date: 2018/2/26
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>${section_name} History</title>
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
    <script src="js/content_action.js?t=3"></script>
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
                    <li><a href="/staff_search">Search</a></li>
                    <li><a href="#">Upgrade</a></li>
                    <li><a href="#">Logs</a></li>
                    <li><a href="/administer_solution">Contents</a></li>
                </ul>
            </nav>
        </div>
    </div>
</header>
</header>
<main role="main">
    <section class="section page">
        <header class="page_header-large">
            <div class="page_header-image cf" style="background-image: url(https://atos.net/wp-content/uploads/2016/06/About-us-1.png);">

            </div>
        </header>

        <div class="section page_button">
            <div class = "button-warpper">
                <a class="button-white" href="add_solution">Add New Solution</a>
            </div>
        </div>

        <div class="section page_main_content">
                <div class="table-title">
                    <h1 style="text-align: left; padding-bottom: 10px;">current contents</h1>
                </div>

                <div id="InUseBlock">
                    <span style="font-size: 20px;font-weight:bold;">Current In Use Version </span>&nbsp;
                    <input type="button" value="<-" class="btn btn-primary" onclick="showDialog2()">
                    &nbsp;&nbsp;&nbsp;
                    <span id="current_version" style="font-weight: bold">${InUseVersion}</span>
                    &nbsp;&nbsp;&nbsp;
                    <input type="button" value="->" class="btn btn-primary" onclick="showDialog3()">
                    <input type="hidden" id="c_title" value="${content_title}">
                    <input type="hidden" id="s_name" value="${section_name}">
                </div>

                <div class="table-warpper">
                    <table class="table table-striped">
                        <tbody>
                        <tr>
                            <th><b>Content Title<br></b></th>
                            <th><b>Section Name</b></th>
                            <th><b>Version<br></b></th>
                            <th class="button-th" colspan="2"><b>Action</b></th>
                            <th><b>In Use</b></th>
                        </tr>
                        <c:forEach var="section_list"  items = "${allSections}">
                            <form id="class_table">
                                <tr>
                                    <td>${section_list.title} <input type="hidden" id= "content_title" name="content_title" value="${section_list.title}"></td>
                                    <td>${section_list.section_name}<input type="hidden" id="section_name" name="section_name" value="${section_list.section_name}"></td>
                                    <td>${section_list.section_version} <input type="hidden" id="version" name="version" value="${section_list.section_version}"></td>
                                    <td class="button-td"><input type="submit" class="btn btn-info" id="button-blue" value="EDIT" onclick="this.form.action='/edit'" style="width: 85px"></td>
                                    <td class="button-td"><input type="button" class="btn btn-danger" id="button-red" value="DELETE" onclick="showDialog(this)"></td>
                                    <td class="InUse" style="text-transform: uppercase;">${section_list.inUse}</td>
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
                        <h4 class="modal-title" id="myModalLabel">Save</h4>
                    </div>
                    <div class="modal-body">
                        Are you sure to delete this content?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-danger" onclick="deleteSection()">Delete</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="rollbackModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">Save</h4>
                    </div>
                    <div class="modal-body">
                        Are you sure to roll back?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-info" onclick="RollBack()">Yes</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="forwardModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">Save</h4>
                    </div>
                    <div class="modal-body">
                        Are you sure to use next version?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-info" onclick="Forward()">Yes</button>
                    </div>
                </div>
            </div>
        </div>



</main>

</body>
</html>
