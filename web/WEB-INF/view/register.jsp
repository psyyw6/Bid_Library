<!DOCTYPE html>
<html lang="en">
<head>
    <title>Register</title>
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
    <script src="js/register.js?t=2"></script>

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
                    Account Register
                </span>

            <form class="login100-form validate-form p-b-33 p-t-5">

                <div class="wrap-input100 validate-input" data-validate = "Username is invalid or already exist">
                    <input class="input100" type="text" name="username" id="username" placeholder="DAS ID">
                    <span class="focus-input100" data-placeholder="&#xe82a;"></span>
                </div>

                <div class="wrap-input100 validate-input" data-validate="Invalid password">
                    <input class="input100" type="password" name="password" id="password" placeholder="Password">
                    <span class="focus-input100" data-placeholder="&#xe80f;"></span>
                </div>

                <div class="wrap-input100 validate-input" data-validate="Two input password must be consistent">
                    <input class="input100" type="password" name="password" id="cf-password" placeholder="Confirm Password">
                    <span class="focus-input100" data-placeholder="&#xe80f;"></span>
                </div>

                <div class="wrap-input100 validate-input" data-validate="Invalid Email">
                    <input class="input100" type="email" name="email" id="email" placeholder="Email">
                    <span class="focus-input100" data-placeholder="&#xe818;"></span>
                </div>

                <div class="container-login100-form-btn m-t-32">
                    <input type="button" class="login100-form-btn" value="Register" onclick="register_user()">
                </div>
                <div class="text-center p-t-20">
                    <a class="txt2" href="/login">
                        Already have an account? Log in here
                    </a>
                </div>

            </form>
        </div>
    </div>
</div>
</body>
</html>
