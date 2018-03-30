<%--
  Created by IntelliJ IDEA.
  User: yutong
  Date: 08/02/2018
  Time: 00:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Solution</title>
    <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
    <link rel="icon" type="image/png" href="img/ato_icon.png" sizes="200x200">
    <script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="css/add_solution.css">
    <link rel="stylesheet" type="text/css" href="css/edit.css">
    <script src="js/jquery-ui.js"></script>
    <script src="js/jquery-ui.min.js"></script>
    <script src="js/edit.js?t=2"></script>
    <link rel="stylesheet" id="js_composer_front-css" href="https://atos.net/wp-content/plugins/js_composer/assets/css/js_composer.min.css" type="text/css" media="all">
</head>
<body>
<header class="header">
    <div class="wrapper">
        <div class="header_pre cf">
            <ul class="header_pre-institutional">
                <li><p>Welcome ${name} </p></li>
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
            <div class="page_header-image cf" style="background-image: url(https://atos.net/wp-content/uploads/2017/02/atos-lights-map-na.jpg);">
            </div>
        </header>
        <div class = "title_header" id="title-header">
            <p class = "title_word">${content_title}: ${section_name}</p><input type="hidden" id="content_title" value="${content_title}">
            <p class= "title_word">Version: ${version}</p><input type="hidden" id="section_name" value="${section_name}">
                                                            <input type="hidden" id="version" value="${version}">
        </div>
    </section>

    <section id="editor">
        <form class="form-horizontal" enctype="multipart/form-data">
        <div id='edit' style="margin-top: 30px;">
            ${content}
        </div>
        <br>
        <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                        <input type="button" value="Save" class="btn btn-primary" id="save-button" data-toggle="modal" data-target="#myModal"/>
                </div>
        </div>
        </form>
    </section>

    <div class="modal fade" id="myModal" tabindex="9999" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Save</h4>
                </div>
                <div class="modal-body">
                    Are you sure to save the changes?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" onclick="editSection()">Save changes</button>
                </div>
            </div>
        </div>
    </div>
</main>

<script type="text/javascript" src="js/wangEditor.min.js"></script>

<script type="text/javascript">
    var E = window.wangEditor;
    var editor = new E('#edit');

    editor.customConfig.uploadFileName = 'file';
    editor.customConfig.uploadImgServer = '/upload';
    editor.customConfig.uploadImgMaxSize = 10 * 1024 * 1024;
    editor.customConfig.zIndex = 100;
    editor.customConfig.uploadImgHooks = {
        success: function (xhr, editor, result) {
        },
        fail:function (xhr, editor,result) {

        }
    };
    editor.customConfig.menus = [
        'head',
        'bold',
        'italic',
        'underline',
        'strikeThrough',
        'list',
        'justify',
        'image',
        'table',
        'undo',
        'redo'
    ]
    editor.customConfig.lang = {
        '设置标题': 'Title',
        '正文': 'p',
        '链接文字': 'link text',
        '链接': 'link',
        '上传图片': 'Upload image',
        '上传': 'Upload',
        '创建': 'Init',
        '网络图片':'Internet Image',
        '图片':'Image',
        '插入表格':'Insert Table',
        '插入':'Insert',
        '字号':'Font-size',
        '字体':'Font-style',
        '文字颜色': 'Font-color',
        '设置列表':'List-setting',
        '对齐方式':'Text-align',
        '有序列表':'Ordered',
        '无序列表':'Unordered',
        '靠左':'Left',
        '靠右':'Right',
        '居中':'Center'
        // 还可自定添加更多
    };
    editor.customConfig.fontNames = [
        'Arial',
        'Tahoma',
        'Verdana'
    ]
    editor.create();
</script>

</body>





</html>