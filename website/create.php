<?php


error_reporting(E_ALL);
ini_set('display_errors', 1);

   //$db =  pg_connect("host=localhost dbname=movielens user=jasil password=test123");
   
$db="pgsql:host=localhost;port=5432;dbname=movielens;user=jasil;password=test123";



   try{
	// create a PostgreSQL database connection
	$conn = new PDO($db);
 
	// display a message if connected to the PostgreSQL successfully
	if($conn){
		echo "Connected to the database successfully!";
	}
}catch (PDOException $e){
	// report error message
	echo $e->getMessage();
}


$table_query="CREATE TABLE IF NOT EXISTS authenticate( user_id INTEGER PRIMARY KEY UNIQUE NOT NULL REFERENCES users(id), user_name VARCHAR(255) UNIQUE NOT NULL, password VARCHAR(255) NOT NULL, type INTEGER )";








$r=$conn->exec($table_query);


if($r !== false){
echo "<br>table is created<br>";
}
else{
echo "<br>table is not created<br>";
}


$user_count="select count(id) from users";
$u_count=$conn->query($user_count); 

	if($u_count === false){
		echo "Error executing the query";
	}

//echo "the number of user is".$u_count."<br>";

$plz_count=$u_count->fetch(PDO::FETCH_ASSOC);

echo "<br>the number of user is".$plz_count['count']."<br>";

$user_list="select id,zip_code from users";

$u_row=$conn->query($user_list);


if($u_row === false){
		echo "Error executing the query";
	}


$front='A';
$back='V';
$us="user";



while($row = $u_row->fetch(PDO::FETCH_ASSOC))
{
echo "<br>".$row['id']."<br>";
echo "<br>".$row['zip_code']."<br>";

$user_id=$row['id'];


$username=$us.$user_id;

$password=$front.$row['zip_code'].$back;

echo "user id is".$user_id."<br>user name is".$username."<br>password is".$password."<br>";



$b="INSERT INTO authenticate VALUES(".$user_id.",'".$username."','".$password."',0)";

echo $b;


$u_insert=$conn->query($b);

if($u_insert === false){
		echo "Error executing insert query";
	}
else
{
echo "executed successfully<br>";
}


$front++;
$back++;


}

$u_row->commit();








/*
  public function createTables() {
    
        $sqlList =  "";
                      
        $ad= $this->pdo->exec($sqlList);;

          echo "hello"
*/




?>
