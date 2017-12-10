<?php
/**
 * Created by PhpStorm.
 * User: yutong
 * Date: 17/12/7
 * Time: 21:07
 */
$dbms='mysql';
$host='localhost';
$db_name='bid_library';
$user='root';
$password='dhqxiao';
$dsn="$dbms:host=$host;dbname=$db_name";

try{
    $dbh = new PDO($dsn,$user,$password);
//    echo "Connect succeed<br/>";
}
catch (PDOException $e)
{
    die ("Error!: " . $e->getMessage() . "<br/>");
}
$db = new PDO($dsn, $user, $password, array(PDO::ATTR_PERSISTENT => true));
?>