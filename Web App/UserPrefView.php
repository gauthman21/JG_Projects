<?php
//ini_set('display_errors', 'On');
echo "<link rel='stylesheet' type='text/css' href='stylesheet.css' />";

class UserPrefView {
	//Outputs change password form for users and allows them to fill out the information needed
	//to be sent to the controller for processing
	static function changePW($user)
	
	{
		echo <<<_END
		<form method="post" action="newController.php">
		<div id="tableContainer">
		<div id="testCounts">
		<h3 class="pref">User Preferences</h3>
			<fieldset>
				<legend> Change Password </legend>
						<p class="user">User: <select name="loginId">$user</p>
						<option value="$user">$user</option>
					</select><br>
					Old Password: <input type="password" name="userPass1" size="20"><br>
					New Password: <input type="password" name="userPass" size="20"><br>
					Confirm New Password: <input type="password" name="userPass2" size="20"><br>
				<br> <input class="btn-style" type="submit" name="ChangePWButtonUser" value="Submit">
				<br>
			</fieldset>
			<br>
			<input class="btn-style" type="submit" name="logout" value="Logout">
		</form>
_END;
	}
	}

?>