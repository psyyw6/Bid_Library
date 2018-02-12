<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 31/01/2018
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
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
    <link rel="stylesheet" href="css/search.css">
    <script src="js/jquery.js"></script>
    <script src="js/login.js"></script>
</head>

<body>
<div class="header">
        <p class="welcomeHeader">Welcome! Your DIS is (Insert employee DIS number)</p>
        <button class="logoutButton">Logout</button>
</div>
<div class="title">
    <div class="catalogue">
        <div class="cal-image">
            <a href="#" onclick="showList()"><img src="img/list.png" width="90" height="65"></a>
        </div>
        <ul class="options">
            <li><a href="https://atos.net/en/">Home</a></li>
            <li><a href="/login">Log In</a></li>
            <li><a href="#">Contact Us</a></li>
        </ul>
    </div>
    <div class = titleName>
        <h1>Search</h1>
    </div>
    <div class = logoImage>
        <img src="img/Atos_logo.png" width="140" height="80">
    </div>
</div>
<form class="searchForm">
    <select name="tags" id="tags">
        <option value="">--Select a tag--</option>
        <option value="tag 1">tag 1</option>
        <option value="tag 2">tag 2</option>
        <option value="tag 3">tag 3</option>
        <option value="tag 4">tag 4</option>
        <option value="tag 5">tag 5</option>
        <option value="tag 6">tag 6</option>
    </select>
    <input type="text" placeholder="Search..." name="searchBar" id = "searchBar" onfocus="this.placeholder='';"onblur="if (this.placeholder == '') {this.placeholder = 'Search...';}">
    <button type="submit" id = "submitButton">Enter</button>
</form>
</body>

</html>

