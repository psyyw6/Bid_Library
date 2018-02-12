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
    <meta name="description" content="A free html template with Sketch design made with Bootstrap">
    <meta name="keywords" content="free html template, bootstrap, ui kit, sass"/>
    <meta name="author" content="Codrops"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" type="image/png" href="img/ato_icon.png" sizes="200x200">
    <meta name="msapplication-TileColor" content="#66e0e5">
    <meta name="msapplication-TileImage" content="img/favicon/mstile-144x144.png">
    <meta name="msapplication-config" content="img/favicon/browserconfig.xml">
    <meta name="theme-color" content="#ffffff">
    <link rel="stylesheet" href="css/solution.css">
    <script src="js/jquery.js"></script>
    <script src="js/login.js" rel="script"></script>
    <script src="js/register.js" rel="script"></script>
    <!--from Atos website-->
    <link rel="stylesheet" id="atos_css-css" href="https://atos.net/wp-content/themes/atos/style.css" type="text/css" media="screen">
    <link rel="stylesheet" id="js_composer_front-css" href="https://atos.net/wp-content/plugins/js_composer/assets/css/js_composer.min.css" type="text/css" media="all">
    <script type="text/javascript" src="https://atos.net/wp-content/plugins/sitepress-multilingual-cms/templates/language-switchers/legacy-dropdown/script.js"></script>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    <script type="text/javascript" src="https://atos.net/wp-includes/js/jquery/jquery-migrate.min.js"></script>
    <script type="text/javascript" src="https://atos.net/wp-content/themes/atos/js/lib.js"></script>

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
    <section class="section page">
        <header class="page_header-large">
            <div class="page_header-image cf search_header-image" style="background-image:url(https://atos.net/wp-content/uploads/2016/06/Scientific-Community-banner.jpg);">
                <div class="page_header-grad">
                    <div class="grid-row">
                        <div class= "grid-col-7">
                            <input style="border-radius: 10px" type="text" name="mySearch">
                        </div>
                        <div class ="grid-offset-6 search_header-tag">
                            <select>
                                <option>tag 1</option>
                                <option>tag 2</option>
                                <option>tag 3</option>
                            </select>
                            <button class="search-button">search</button>
                        </div>
                    </div>
                </div>
                <div class="page_content-grad">
                    <div class="grid-row">
                        <div class="grid-col-12" style="margin:0">
                            <h1 id="search_content-title">Search Result</h1>
                            <table class="search_content-table">
                                <tbody>
                                <tr>
                                    <th><b>Solution Title<br></b></th>
                                    <th><b>Heading Title</b></th>
                                    <th><b>Internal/External<br></b></th>
                                    <th><b>Creator DAS ID</b></th>
                                    <th><b>Version</b></th>
                                    <th><b>Modified Date</b></th>
                                    <th><b>Customer Name</b></th>
                                    <th><b>Content</b></th>
                                </tr>
                                <tr>
                                    <td>Title one</td>
                                    <td>Heading one</td>
                                    <td>Internal</td>
                                    <td>'DAS ID'</td>
                                    <td>1.0</td>
                                    <td>06/01/2017</td>
                                    <td>Bob</td>
                                    <td>The corresponding heading solution details</td>
                                </tr>
                                <tr>
                                    <td>Title two</td>
                                    <td>Heading two</td>
                                    <td>External</td>
                                    <td>'DAS ID'</td>
                                    <td>2.0</td>
                                    <td>07/02/2018</td>
                                    <td>Jam</td>
                                    <td>The corresponding heading solution details</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="page_footer-grad">
                    <div class="grid-row">
                        <div class="grid-offset-4">
                            <div class="grid-col-4 ">
                                <p style="color: black; padding-top: 10px;">Generate the document:</p>
                            </div>
                            <div class="grid-col-4 search_footer-tag">
                                <select>
                                    <option>tag 1</option>
                                    <option>tag 2</option>
                                    <option>tag 3</option>
                                </select>
                            </div>
                            <div class="grid-col-4">
                                <button class="search-button" style="margin: 0 0 0 50px ">Generate</button>
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
