
<html>
<body>

<style>
table {
    border-collapse: collapse;
    border-spacing: 0;
    width: 100%;
    border: 1px solid #ddd;
}

th, td {
    text-align: left;
    padding: 16px;
}

tr:nth-child(even) {
    background-color: #f2f2f2
}


* {
    box-sizing: border-box;
}

body {
    margin: 0;
    font-family: Arial;
}

/* The grid: Three equal columns that floats next to each other */
.column {
    float: left;
    width: 33.33%;
    padding: 50px;
    text-align: center;
    font-size: 25px;
    cursor: pointer;
    color: white;
}

.containerTab {
    padding: 20px;
    color: white;
}

/* Clear floats after the columns */
.row:after {
    content: "";
    display: table;
    clear: both;
}

/* Closable button inside the container tab */
.closebtn {
    float: right;
    color: white;
    font-size: 35px;
    cursor: pointer;
}




</style>







<?php
error_reporting(E_ALL);
ini_set('display_errors', 1); ?>
<?php
session_start();
?>

<?php
error_reporting(E_ALL & ~E_NOTICE);

$id=$_GET["id"];
 
$db="pgsql:host=localhost;port=5432;dbname=movielens;user=jasil;password=test123";

  try{
	// create a PostgreSQL database connection
	$conn = new PDO($db);
 
	// display a message if connected to the PostgreSQL successfully
	if($conn){
		//echo "Connected to the database successfully!";
	}
}catch (PDOException $e){
	// report error message
	echo $e->getMessage();
}

echo "<b><center> welcome user".$id."</b></center>" ;


$first_q="SELECT movie_id,rating FROM ratings WHERE user_id=".$id;




$fetch_1=$conn->query($first_q); 

$movies_rated=array();
$best_movies=array();
$index_mov=0;


$gen_count=array(1,18,0);

for($i=1;$i<=19;$i++)
{
$gen_count[$i]=0;
}





if($fetch_1 === false){
		die("Error executing the query 1");
	}

while($row=$fetch_1->fetch(PDO::FETCH_ASSOC))
{
$mov_id=$row['movie_id'];
$mov_rating=$row['rating'];

if($mov_rating>3)
{

//echo "best movie is having id =".$mov_id."<br>";
$third_q="SELECT genre_id from genres_movies where movie_id=".$mov_id;
$fetch_3=$conn->query($third_q);

if($fetch_3===false)
{
die("Error executing the query 3");

}
$gen_id=$fetch_3->fetch(PDO::FETCH_ASSOC);

//echo "its genre id is ".$gen_id['genre_id'];

$i=$gen_id['genre_id'];

//echo "genre id-".$i;

$gen_count[$i]=$gen_count[$i]+1;

//echo "count is".$gen_count[$i]."<br>";





}







/*$

else
*/

/*
while($row_2=$fetch_2->fetch(PDO::FETCH_ASSOC))
{
$mov_name=$row_2['title'];
$movies_rated[]=array("name"=>$mov_id,"rating"=>$mov_rating);




echo "movie id is ".$mov_id." with rating ".$mov_rating." name is".$mov_name."<br>";

}






}

*/

//echo "movie id is ".$mov_id." with rating ".$mov_rating."<br>";

$second_q="SELECT title from movies where id=".$mov_id;

//echo $second_q."<br>";

$fetch_2=$conn->query($second_q);

if($fetch_2 === false){
		die("Error executing the query 2");

}


while($row_2=$fetch_2->fetch(PDO::FETCH_ASSOC))
{
$mov_name=$row_2['title'];
$movies_rated[]=array("name"=>$mov_name,"rating"=>$mov_rating);
//echo "movie id is ".$mov_id." with rating ".$mov_rating." name is".$mov_name."<br>";
//echo "movie title is".$mov_name."<br><br>";

}



}


  
  //echo '<button>Movies You Rated</button>';






//echo "<br>movies you rated<br>";

echo "<br>";

echo "<br>";
echo "<br>";

echo '<table >';
echo '<tr>';
echo '<th>Movie</th>';
echo '<th>Rating</th>';
echo '</tr>';


foreach ( $movies_rated as $movie ) {

  //echo '<dl style="margin-bottom: 1em;">';

 echo '<tr>';
  foreach ( $movie as $key => $value ) {
  
    //echo "<dt>$key</dt><dd>$value</dd>";
    echo '<th>'.$value.'</th>';
  }
 echo '</tr>';
  //echo '</dl>';
}


echo '</table>';







for($i=1;$i<=18;$i++)
{
$q_s="SELECT name FROM genres where id=".$i;
$fetch_4=$conn->query($q_s);
$gen_name=$fetch_4->fetch(PDO::FETCH_ASSOC);
//echo  $gen_name['name']."has count".$gen_count[$i];

}


echo "<br>Top three Rated genres<br>";

echo '<table style="width:100%">';
echo '<tr>';
echo '<th>Sl.no</th>';
echo '<th>Genres</th>';
echo '<th>Rating No</th>';
echo '</tr>';



for($j=1;$j<=3;$j++)
{


$max=1;
$i=2;

while($i<=18)
{
if($gen_count[$i]>$gen_count[$max])$max=$i;
$i++;
}

$q_s="SELECT name FROM genres where id=".$max;
$fetch_4=$conn->query($q_s);
$gen_name=$fetch_4->fetch(PDO::FETCH_ASSOC);
//echo  $gen_name['name'].$j." count".$gen_count[$max];

echo '<tr>';
echo '<th>'.$j.'</th>';
echo '<th>'.$gen_name['name'].'</th>';
echo '<th>'.$gen_count[$max].'</th>';
echo '</tr>';


$gen_count[$max]=0;


}

echo '</table>';





?>


</body>
</html>


  
  
  



