<?php
  $servername = "localhost";
  $username = "root";
  $password = "";
  $dbname = "serenada";

  $tugas=$_POST['tugas']/100;
  $kuis=$_POST['kuis']/100;
  $uts=$_POST['uts']/100;
  $uas=$_POST['uas']/100;

  // Create connection
  $conn = new mysqli($servername, $username, $password, $dbname);
  // Check connection
  if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
  }

  $sql = "update t_rules set tugas=$tugas,kuis=$kuis,uts=$uts,uas=$uas";

  if ($conn->query($sql) === TRUE) {
    echo "Update Rules Success";
  } else {
    echo "Error: " . $sql . "<br>" . $conn->error;
  }

  $conn->close();
 ?>
