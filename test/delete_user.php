<?php
  $servername = "localhost";
  $username = "root";
  $password = "";
  $dbname = "serenada";

  $id=$_POST['id'];

  // Create connection
  $conn = new mysqli($servername, $username, $password, $dbname);
  // Check connection
  if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
  }

  $sql = "delete from t_user where id='$id'";

  if ($conn->query($sql) === TRUE) {
    echo "1";
  } else {
    echo "Error: " . $sql . "<br>" . $conn->error;
  }

  $conn->close();
 ?>
