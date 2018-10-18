<?php
//ini_set('display_errors', 'On');
echo "<link rel='stylesheet' type='text/css' href='stylesheet.css' />";

class AdminUserPrefView {
	//Outputs admin user preferences form and allows user to enter corresponding information to be sent
	//to the controller
	static function add_Users()
	{
		echo <<<_END
		<form method="post" action="newController.php">
			<div id="tableContainer">
			<div id="testCounts">
				<h3 class="aref">Admin User Preferences</h3>
				<fieldset>
				<legend> Add Users </legend>
					<select Name='userFunction'>
						<option value="Add"> Add </option>
					</select>
					Type of User: <select Name='userFunction0'>
						<option value="Admin"> Admin </option>
						<option value="User"> User </option>
					</select><br>
					User name: <input type="text" name="userName" size="20"><br>
					Login ID: <input type="text" name="loginId" size="20"><br>
					Login Password: <input type="password" name="loginPass" size="20"><br>
				<br> <input class="btn-style" type="submit" name="userButton" value="Submit"> <br>
			</fieldset>
		</form>
_END;
	}
	//Outputs remove users and allows user to enter corresponding information to be sent
	//to the controller
	static function removeUsers()
	{
		$userArray = array();
		$userArray = model::fillDropDown("pUser", "userlogin","userID");
		
		echo <<<_END
		<form method="post" action="newController.php">
				<fieldset>
				<legend> Remove Users </legend>
					<select Name='userFunction'>
						<option value="Delete"> Delete </option>
					</select>
					<select name="loginId"><option value="Select" name="loginId"> --Select User-- </option>
_END;
	foreach($userArray as $id => $user) {
		echo "<option value=$user>$user</option>";
	}
		
	echo <<<_END
			</select><br>
				<br> <input class="btn-style" type="submit" name="userButtonRemove" value="Submit"> <br>
			</fieldset>
		</form>
_END;
	}
	//Outputs change password form and allows user to enter corresponding information to be sent
	//to the controller
	static function changePW()
	{
		
		$userArray = array();
		$userArray = model::fillDropDown("pUser", "userName","userID");
		
		echo <<<_END
		<form method="post" action="newController.php">
			<fieldset>
				<legend> Change Password </legend>
					Username: <select name='loginId'><option value="Select"> --Select User-- </option>
_END;
			foreach($userArray as $id => $user) { 
			 echo "<option value=$id>$user</option>\n";
			}
			
			echo <<<_END
			</select><br>
					<!--Old Password: <input type="password" name="userPass1" size="20"><br>-->
					New Password: <input type="password" name="userPass" size="20"><br>
					Confirm New Password: <input type="password" name="userPass2" size="20"><br>
				<br> <input class="btn-style" type="submit" name="ChangePWButtons" value="Submit">
			
			</fieldset>
					<br>
					<input class="btn-style" type="submit" name="logout" value="Logout">
		</form>
_END;
	
	}
}
?>