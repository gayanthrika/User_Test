<?php
	require_once 'db_connect.php';
	extract($_POST);
	$qry = $conn->query("SELECT * FROM users WHERE username = '$username' and  password = '$password'") or die(msqli_error());
	$login = $qry->fetch_array();

	if($qry->num_rows > 0){
		echo true;
		session_start();
		$_SESSION['login_id'] = $login['id'];
	}

	//$conn->close();
	?>