<?php

$servername = "localhost";
$username = "root";
$password = "";
$dbname = "serenada";

$id=$_POST["id"];
$nama=$_POST["nama"];
$alamat=$_POST["alamat"];
$tgl_lahir=$_POST["tgl_lahir"];

$con=mysqli_connect($servername,$username,$password,$dbname);

  // Check connection
  if (mysqli_connect_errno())
  {
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }

  $query = "select tugas,uts,uas,kuis from t_rules";

  $json = array();
	$result = mysqli_query ($con, $query);
	while($row = mysqli_fetch_array ($result))
  {
		$bus = array(
			'tugas' => $row['tugas'],
			'kuis' => $row['kuis'],
			'uts' => $row['uts'],
			'uas' => $row['uas']
		);
		array_push($json, $bus);
	}

  echo json_encode($json);
  mysqli_close($con);
 ?>
