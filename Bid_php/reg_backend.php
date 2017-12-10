<?php
/**
 * Created by PhpStorm.
 * User: yutong
 * Date: 17/12/7
 * Time: 21:18
 */
include("connect_db.php");

function test_input($data)
{
    $data = trim($data);
    $data = stripslashes($data);
    $data = htmlspecialchars($data);
    return $data;
}

if($_SERVER["REQUEST_METHOD"]=='POST') {
    $username = test_input($_POST['username']);
    $password = test_input($_POST['password']);
    $email = test_input($_POST['email']);
    $department = test_input($_POST['department']);
}

try{
    $password = md5($password);
    $query = 'INSERT INTO Users VALUES (:Username, :Password, :Email, :Department, :Role)';
    $sth = $db->prepare($query);
    $sth->bindParam(':Username',$username);
    $sth->bindParam(':Password',$password);
    $sth->bindParam(':Email',$email);
    $sth->bindParam(':Department',$department);
    $sth->bindValue(':Role',"Normal");
    $sth->execute();
    header('Location: login.php');
}
catch(Exception $e)
{
    header("Location: register.php");
}
?>