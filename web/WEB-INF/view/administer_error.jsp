<!--
Created by IntelliJ IDEA.
User: lishanshan
Date: 18/1/30
Time: 下午4:08
To change this template use File | Settings | File Templates.

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> -->
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Error</title>
    <link rel="stylesheet" href="css/solution.css">
    <script src="js/jquery.js"></script>
    <link rel="stylesheet" id="atos_css-css" href="https://atos.net/wp-content/themes/atos/style.css" type="text/css" media="screen">
    <!-- SEO keywords -->
    <meta name="keywords" content="React, React Primer, Reactprimer, React-Primer, Developer Tool, Javascript, ES6, Chrome, Developer, React Developer, Create React App, Learn programming, Visualization, React 16, React 15, React fiber, fiber, redux, react redux, router, react router, browser router, redux store, react visualizer, component visualizer, react component visualizer, react component"
    />
    <link rel="icon" href="favicon.png" type="image/png">
    <link rel="shortcut icon" href="favicon.ico" type="img/x-icon">
    <link href='https://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,800italic,700italic,600italic,400italic,300italic,800,700,600'
          rel='stylesheet' type='text/css'>
    <link href="css/error.css" rel="stylesheet" type="text/css">
    <link href="css/responsive.css" rel="stylesheet" type="text/css">
    <link href="css/animate.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

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
                    <img src="https://atos.net/wp-content/themes/atos/images/atos-logo-menu-bar.png" alt="Atos"></a>
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

        <div class="contain_header" id="header">
            <div class="container">

                <div class="panel-body">
                    <figure class="logo animated fadeInDown delay-07s">
                        <img src="img/warning-icon.png" alt="icon">
                    </figure>
                    <br>

                    <c:if test="${not empty errCode}">
                        <h1 class="animated fadeInDown delay-07s" style="color: red;">${errCode}</h1>
                    </c:if>
                    <c:if test="${empty errCode}">
                        <h1 class="animated fadeInDown delay-07s" style="color: red;">System Errors</h1>
                    </c:if>
                    <br>
                    <c:if test="${not empty errMsg}">
                        <h3 class="animated fadeInDown delay-07s" style="color: red;">${errMsg}</h3>
                    </c:if>
                    <br><br>
                    <a class="content_link animated fadeInUp delay-1s servicelink" style="background: red;" href="/administer_solution">Go Back</a>
                </div>

            </div>
        </div>
    </section>
</main>
</body>
</html>




