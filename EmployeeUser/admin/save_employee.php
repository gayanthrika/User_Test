<?php
	include 'db_connect.php';
	extract($_POST);
	if(empty($id)){
		$i= 1;
		
		while($i == 1){
		
			$e_num=date('Y') .'-'. mt_rand(1,9999);
			// $e_num="1234";
			$chk  = $conn->query("SELECT * FROM employee where employee_no = '$e_num' ")->num_rows;
			
			if($chk <= 0){
				$i = 0;
			}
		}
		//echo "INSERT INTO `employee` VALUES('', '$e_num','$firstname', '$middlename', '$lastname', '$department', '$position')";
		//exit;
		$save=$conn->query("INSERT INTO employee (employee_no,firstname,middlename,lastname,position,department) VALUES('$e_num','$firstname', '$middlename', '$lastname', '$department', '$position')");
		
		if($save){
			echo json_encode(array('status'=>1,'msg'=>"Data successfully Saved"));
		}		
	}

	else {
		
		$save=$conn->query("UPDATE employee set firstname='$firstname',middlename='$middlename',lastname='$lastname',position='$position',department='$department' where id = $id ") or die(mysqli_error());
		
		if($save){
			//echo json_encode(array('msg'=> "HI"));
			//exit();
			echo json_encode(array('status'=>1,'msg'=>"Data successfully Updated"));
		}
	}	

$conn->close();
//*[@id="new_user"]/text()
//*[@id="new_user"]
//*[@id="table"]/tbody/tr[1]/td[4]/center/button[1]
//*[@id="user-frm"]/div[2]/button