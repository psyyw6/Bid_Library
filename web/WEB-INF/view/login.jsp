<!DOCTYPE html>
<html lang="en">
<head>
    <title>Log in</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="https://cdn.linearicons.com/free/1.0.0/icon-font.min.css">
    <link rel="icon" type="image/png" href="img/ato_icon.png" sizes="200x200">
    <meta name="msapplication-TileImage" content="img/favicon/mstile-144x144.png">
    <link rel="stylesheet" type="text/css" href="css/util.css">
    <link rel="stylesheet" type="text/css" href="css/login.css">
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <script src="js/jquery.js"></script>
    <script src="js/jquery-ui.js"></script>
    <script src="js/jquery-ui.min.js"></script>
    <script src="js/login.js?t=1"></script>

</head>
<body>

<div class="limiter">
    <div class="container-login100" style="background-image: url('https://atos.net/wp-content/uploads/2017/11/banni%C3%A8re-data-center-applications-and-transformation.png');">

        <div class="wrap-login100 p-t-30 p-b-50">
            <div class="logo animated fadeInDown delay-07s">
                <a href="https://atos.net/en">
                    <img src="img/atos_logo_3.png" alt="Atos">
                </a>
            </div>
            <span class="login100-form-title p-b-41">
                    Account Login
                </span>

            <form class="login100-form validate-form p-b-33 p-t-5">

                <div class="wrap-input100 validate-input" data-validate = "Enter username">
                    <input class="input100" type="text" id="username" name="username" placeholder="Username">
                    <span class="focus-input100" data-placeholder="&#xe82a;"></span>
                </div>

                <div class="wrap-input100 validate-input" data-validate="Enter password">
                    <input class="input100" type="password" id="password" name="password" placeholder="Password">
                    <span class="focus-input100" data-placeholder="&#xe80f;"></span>
                </div>

                <div class="container-login100-form-btn m-t-32">
                    <input type="button"  class="login100-form-btn" value="LOGIN" onclick="login()">
              </div>
                <div class="text-center p-t-20">
                    <a class="txt2" href="/register">
                        Not register? Create an account
                    </a>
                </div>

            </form>
        </div>
    </div>
</div>
</body>
</html>