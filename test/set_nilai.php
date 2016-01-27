<?php
  $servername = "localhost";
  $username = "root";
  $password = "";
  $dbname = "serenada";

  $id=$_POST['id'];
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

  $sql = "update t_user set tugas=$tugas,kuis=$kuis,uts=$uts,uas=$uas where id='$id'";

  if ($conn->query($sql) === TRUE) {
    echo "1";
  } else {
    echo "Error: " . $sql . "<br>" . $conn->error;
  }

  $conn->close();
 ?>
