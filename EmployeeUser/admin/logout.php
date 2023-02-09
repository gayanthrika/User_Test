<?php
	session_start();
	
	//session_unset('login_id');
	unset($_SESSION['login_id']);
	session_destroy();
	header('location:index.php');

?>