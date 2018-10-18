<?php
//ini_set('display_errors', 'On');
echo "<link rel='stylesheet' type='text/css' href='stylesheet.css' />";

	class testUserForm {
	//Outputs project status form and allows user to enter corresponding information to be sent
	//to the controller
	static function changeProjectStatus($user){

		$user = str_replace("_", " ", $user);
		$user = ucwords($user);
		
		$idArray = array();
		
		//$projectArray = array();
		//$projectArray = model::fillDropDown("test_project_status", "project_name", "ProjectID");
		$projectIDarray = model::fillDropDownTest("test_project_status", "project_name", "assigned", "projectid");
		$sizeof = sizeof($projectIDarray);

		echo <<<_END
		<form method="post" action="newController.php">
			<fieldset>
				<legend> Project Status </legend>
					Project: <select name='projectID'>
					<option value="Select"> --Select Project-- </option>
_END;
		
		for ($i = 0; $i < $sizeof; $i++) {
			$idArray[$i] = $projectIDarray[$i][0][0];
			
			if($projectIDarray[$i][0][1] == $user){
				
			echo "<option value=$idArray[$i]>".$projectIDarray[$i][0][2]."</option>";
		}
	}
		
		/*foreach($projectIDarray as $id => $project) { 
		 echo "<option value=$id>$project</option>";
			}*/
	
			echo <<<_END
			</select><br>
					Set Project Status: <select Name='pStatusFunction'>
						<option value="In-Progress"> In-Progress </option>
						<option value="Completed"> Completed </option>
						<option value="Pending"> Pending </option>
						<option value="Cancelled"> Cancelled </option>
					</select><br>
				<br> <input class="btn-style" type="submit" name="changePStatusButton" value="Submit"
				<br> <br>
			</fieldset>
					<br><input class="btn-style" type="submit" name="logout" value="Logout">
		</form>
		</div>
_END;
	}
	//Outputs user project preferences form and allows user to enter corresponding information to be sent
	//to the controller
		static function assignProjects($user){
			
			echo <<<_END
		<form method="post" action="newController.php">
		<div id="tableContainer">
		<div id="testCounts">
			<h3 class="pref">User Project Preferences</h3>
			<fieldset>
				<legend> Assign Project </legend>
				<select Name='userFunction'>
						<option value="Add"> Add </option>
				</select>
				Project Name: <input type="text" name="projectName" size="40"><br>
				<p class="user">Assigned To:<select name="assign"><option value="$user"> $user </option>
_END;
	foreach($userArray as $id => $user) {
		echo "<option value=$user>$user</option>";
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
						<option value="Completed">Completed</option>
						<option value="Cancelled">Cancelled</option>
						<option value="In-Progress">In-Progress</option>
						<option value="Pending">Pending</option>
					</select><br>
				<br> <input class="btn-style" type="submit" name="aProjectButtonUser" value="Submit"> <br>
			</fieldset>
	</form>
			
_END;
	}
	}
?>