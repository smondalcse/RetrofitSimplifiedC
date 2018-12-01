package com.nitolmotorsltd.retrofitsimplifiedc.PhpFIles;

public class PhpFiles {

    /*
    create a php project named "retrofitsimpliedc"
     */

    /*

    init.php
    ===========

    <?php
	$host = "localhost";
	$db_user = "root";
	$db_password = "";
	$db_name = "retrofitsimplifiedc";

	$con = mysqli_connect($host, $db_user, $db_password, $db_name);
	if($con){
		//echo "Connection Successfull";
	} else {
		echo "Connection not found";
	}
    ?>

     */

    /*

    createusers.php
    =================

    <?php

	require "init.php";

	$name = $_POST["name"];
	$email = $_POST["email"];
	$password = $_POST["password"];
	$address = $_POST["address"];

	$sql="INSERT INTO tblusers (name, email, password, address) VALUES ( '$name', '$email', '$password', '$address')";
	//echo $sql;

	if(mysqli_query($con, $sql)){

		$last_insert_id = mysqli_insert_id($con);

		echo json_encode(array(
			'success' => 1,
			'insert_id' => $last_insert_id
			));
	} else {
		echo json_encode(array(
			'success' => 0,
			'insert_id' => null
			));
	}

	mysqli_close($con);

    ?>

     */


    /*
    login.php
    =================

    <?php

	require "init.php";

	$email = $_POST["email"];
	$password = $_POST["password"];

	if ($con->connect_error) {
		echo json_encode(array(
			'success' => 0,
			'message' => 'Database connection Failed.'
			));
	} else {
		$sql = "SELECT * FROM tblusers where email = '" . $email . "' and password = '" . $password . "'";
		$result = $con->query($sql);

		if ($result->num_rows > 0) {
			$fetch = mysqli_query($con, $sql);

			while ($row = mysqli_fetch_array($fetch, MYSQL_ASSOC)) {
				$id = $row['id'];
				$name = $row['name'];
				$email = $row['email'];
				$password = $row['password'];
				$address = $row['address'];

				echo json_encode(array(
					'success' => 1,
					'message' => 'Login Success.',
					'id' => $id,
					'name' => $name,
					'email' => $email,
					'password' => $password,
					'address' => $address
				));

			}
		} else {
			echo json_encode(array(
				'success' => 0,
				'message' => 'Login Failed.'
			));
		}
	}

    ?>

     */
}
