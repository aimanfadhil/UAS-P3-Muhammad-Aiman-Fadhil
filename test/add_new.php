<?php
  $servername = "localhost";
  $username = "root";
  $password = "";
  $dbname = "serenada";

  $npm=$_POST['npm'];
  $nama=$_POST['nama'];
  $alamat=$_POST['alamat'];
  $mobile=$_POST['mobile'];
  $email=$_POST['email'];
  $uts=$_POST['uts'];
  $kuis=$_POST['kuis'];
  $uas=$_POST['uas'];
  $tugas=$_POST['tugas'];

  // Create connection
  $conn = new mysqli($servername, $username, $password, $dbname);
  // Check connection
  if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
  }

  $sql = "INSERT INTO t_user (id,nama, alamat, hp,email,tugas,kuis,uts,uas)
  VALUES ('$npm', '$nama', '$alamat','$mobile', '$email', $tugas,$kuis,$uts,$uas)";

  if ($conn->query($sql) === TRUE) {
    echo "1";
  } else {
    echo "Error: " . $sql . "<br>" . $conn->error;
  }

  $conn->close();
 ?>
