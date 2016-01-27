<?php
  $servername = "localhost";
  $username = "root";
  $password = "";
  $dbname = "serenada";

  $nama=$_POST['nama'];
  $alamat=$_POST['alamat'];
  $tgl_lahir=$_POST['tgl_lahir'];

  // Create connection
  $conn = new mysqli($servername, $username, $password, $dbname);
  // Check connection
  if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
  }

  $sql = "INSERT INTO t_user (nama, alamat, tgl_lahir)
  VALUES ('$nama', '$alamat', '$tgl_lahir')";

  if ($conn->query($sql) === TRUE) {
    echo "1";
  } else {
    echo "Error: " . $sql . "<br>" . $conn->error;
  }

  $conn->close();
 ?>
