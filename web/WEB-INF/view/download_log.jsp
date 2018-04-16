<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yutong
  Date: 2018/3/30
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Download Log</title>
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
    <script src="js/content_action.js?t=1"></script>

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
                    <li><a href="/upgrade">Upgrade</a></li>
                    <li><a href="template">Templates</a></li>
                    <li><a href="#">Log</a></li>
                    <li><a href="/administer_solution">Contents</a></li>
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
                <h1 style="text-align: left; padding-bottom: 10px;">Download Logs</h1>
            </div>
            <div class="table-warpper">
                <table class="table table-striped" id="myTable">
                    <thead id="headers">
                    <tr>
                        <th><b>Download ID<br></b></th>
                        <th><b>File Name</b></th>
                        <th><b>Selected Contents</b></th>
                        <th><b>Template</b></th>
                        <th><b>Download Time</b></th>
                        <th><b>User</b></th>
                        <th class="button-th" width="50"><b>Action</b></th>
                    </tr>
                    </thead>

                    <tbody id="body">
                    <c:forEach var="logs"  items = "${logs}">
                        <form id="class_table">
                            <tr>
                                <td>${logs.download_id} <input type="hidden" id="download_id" name="download_id" value="${logs.download_id}"></td>
                                <td>${logs.download_name} <input type="hidden" id="file_name" name="file_name" value="${logs.download_name}"></td>
                                <td>${logs.selected_contents}</td>
                                <td>${logs.selected_template}</td>
                                <td>${logs.download_time}</td>
                                <td>${logs.user}</td>
                                <td class="button-td"><input type="button" class="btn btn-danger" id="button-red" value="DELETE" onclick="showDialog(this)"></td>
                            </tr>
                        </form>
                    </c:forEach>
                    </tbody>

                </table>
            </div>
            <div class="form-group" id="export">
                <div class="col-sm-offset-1 col-sm-10">
                    <a class="btn btn-primary" data-type="csv" href="javascript:;">Export</a>
                </div>
            </div>
            <script src="js/Blob.js"></script>
            <script src="js/FileSaver.js"></script>
            <script src="js/tableExport.js"></script>
            <script>
                var $exportLink = document.getElementById('export');
                $exportLink.addEventListener('click', function(e){
                    e.preventDefault();
                    if(e.target.nodeName === "A"){
                        tableExport('myTable', 'DownloadLog', e.target.getAttribute('data-type'));
                    }
                }, false);
            </script>
        </div>
    </section>
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Delete</h4>
                </div>
                <div class="modal-body">
                    Are you sure to delete this generated file?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-danger" onclick="deleteDoc()">Delete</button>
                </div>
            </div>
        </div>
    </div>
</main>
</body>
</html>
