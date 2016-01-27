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

  $query = "select id,nama,email,alamat,hp,jk,tugas,kuis,uts,uas,
    ((tugas*(select tugas from t_rules))+(kuis*(select kuis from t_rules))+(uts*(select uts from t_rules))+(uas*(select uas from t_rules))) as gpa
   from t_user order by id asc";

  $json = array();
	$result = mysqli_query ($con, $query);
	while($row = mysqli_fetch_array ($result))
  {
		$bus = array(
			'id' => $row['id'],
			'nama' => $row['nama'],
      'email' => $row['email'],
			'alamat' => $row['alamat'],
			'hp' => $row['hp'],
			'jk' => $row['jk'],
			'tugas' => $row['tugas'],
			'kuis' => $row['kuis'],
			'uts' => $row['uts'],
			'uas' => $row['uas'],
			'gpa' => $row['gpa']
		);
		array_push($json, $bus);
	}


	$newbus = array(
		'kontak' => $json
	);

  $newJsonString = json_encode($newbus);
  file_put_contents('kontak.json', $newJsonString);

  mysqli_close($con);
 ?>
