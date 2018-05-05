
<HTML>
<BODY>
<?php
error_reporting(E_ALL);
ini_set('display_errors', 1); ?>

//?php
//session_start();
//?

<?php

$passwrd=$_GET["passwrd"];

$name= $_GET["name"];

echo "name in variable".$name ."<br>";
echo "password in variable".$passwrd."<br>";

$query="SELECT * FROM authenticate WHERE user_name='".$name."' AND password='".$passwrd."'";
//$_SESSION["user_name"]=$name;
//$_SESSION["password"]=$passwrd;
//echo $query;


 //$host        = "host = 127.0.0.1";
   //$port        = "port = 5432";
   //$dbname      = "dbname = movielens";
   //$credentials = "user = jasil password=test123";

//echo "am also here\n";

   $db =  pg_connect("host=localhost dbname=movielens user=jasil password=test123");

//echo "am here";

   if(!$db) {
        
      echo "Error : Unable to open database<br>";
   } else {

        // header('Location:https://www.youtube.com/watch?v=QYTmOVeVj24');
     // echo "Opened database successfully<br>";
   }


$result = pg_query($db,$query);


if(!$result)
{
echo "An error occurred...\n";
exit();
}
else
{
$row = pg_fetch_row($result);
if(pg_num_rows($result)==0) 
{

/*
echo "<script>
alert('username or password wrong')
window.location.href='http://localhost/pro.html';
</script>"; 
//header('Location:http://localhost/pro.html');

die();
*/
}
else
{
$id=$row[0];
header('Location:http://localhost/success_login.php?id='.$id);
 //header('Location:https://www.youtube.com/watch?v=QYTmOVeVj24');
echo "id is".$row[0]."user name is".$row[1]."password is".$row[2]."type is".$row[3]."<br>";
echo "session id is".$_SESSION["id"]."<br>";
//echo "session user name".$_SESSION["user_name"]."<br> session password is ".$_SESSION["password"]."<br>session id is".$_SESSION["id"]."<br>";


}

}



     //echo"helllo\n";

  // $query="


?>
</BODY>
</HTML>
