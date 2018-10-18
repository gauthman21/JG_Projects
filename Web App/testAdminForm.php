<?php
//ini_set('display_errors', 'On');
echo "<link rel='stylesheet' type='text/css' href='stylesheet.css' />";

class testAdminForm {

	//Outputs admin project preferences form and allows user to enter corresponding information to be sent
	//to the controller
	static function assignProjects() {
	
	//grabs users from db
	$userArray = array();
	$userArray = model::fillDropDown("pUser", "userName","userID");

	echo <<<_END
		<form method="post" action="newController.php">
		<div id="tableContainer">
		<div id="testCounts">
			<h3 class="aref">Admin Project Preferences</h3>
			<fieldset>
				<legend> Assign Projects </legend>
				<select Name='userFunction'>
						<option value="Add"> Add </option>
				</select>
				Project Name: <input type="text" name="projectName" size="40"><br>
				Assign To: <select name="assign"><option value="Select"> --Select User-- </option>
_END;
	//populates drop down menu
	foreach($userArray as $id => $user) {
		echo "<option value=$id>$user</option>";
	}
		
	echo <<<_END
			</select><br>
				Priority: <select name='priDrop'>
						<option value="1"> 1 </option>
						<option value="2"> 2 </option>
						<option value="3"> 3 </option>
						<option value="4"> 4 </option>
						<option value="5"> 5 </option>
					</select><br>
				Status: <select name='statusDrop'>
						<option value="Pending">Pending</option>
					</select><br>
				<br> <input class="btn-style" type="submit" name="aProjectButton" value="Submit">
			</fieldset>
		</form>
_END;
}
		//Outputs remove projects form and allows user to enter corresponding information to be sent
		//to the controller
		static function removeProjects() {
		
		//grabs info from db
		$projectIDarray = model::fillDropDownTest("test_project_status", "project_name", "assigned", "projectid");
		$sizeof = sizeof($projectIDarray);
		
	echo <<<_END
		<form method="post" action="newController.php">
			<fieldset>
				<legend> Remove Projects </legend>
				<select Name='userFunction'>
						<option value="Delete"> Delete </option>
				</select>
				Project Name: <select name='projectName'>
					<option value="Select"> --User: Project-- </option>
_END;
					//fills drop down
					for ($i = 0; $i < $sizeof; $i++) {
  						$idArray[$i] = $projectIDarray[$i][0][0];
  						$user = str_replace("_", " ", $projectIDarray[$i][0][1]);
						$user = ucwords($user);
  						
						echo "<option value=$idArray[$i]>".$user.": ".$projectIDarray[$i][0][2]."</option>";
					}
					
			echo <<<_END
			</select><br>
				<br> <input class="btn-style" type="submit" name="aProjectButton" value="Submit">
			</fieldset>
					<br>
					<input class="btn-style" type="submit" name="logout" value="Logout">
		</form>
_END;
}
}
?>