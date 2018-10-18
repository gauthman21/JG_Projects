<?php
//ini_set('display_errors', 'On');
echo "<link rel='stylesheet' type='text/css' href='stylesheet.css' />";

require_once("newUtil.php");
require_once("WebAppView.php");
require_once("util.php");
require_once("newController.php");
require_once("AdminUserPrefView.php");
require_once("testAdminForm.php");
require_once("testUserForm.php");

	class model{
		//Sets array with all user databases 
		static function databaseArray(){
		
			$mysqli = util::dataConnect();
			$databases = mysql_query("SHOW DATABASES");
			
			$i = 0;
			
		while ($row = mysql_fetch_assoc($databases)) {
    		
			$databaseArray[$i] = $row['Database'];
			$i++;
			}
			return array_values($databaseArray);
		}
		//Sets table array with only the needed tables
		static function tableArray(){
			
			$tableArray = array("ottobrain_arrays", "ottobrain_spartans", "ottobrain_vms", "queue", "requests");
			return $tableArray;
		}
		//Sets column array with only the needed columns
		static function columnArray(){
			
			$columnArray = array("Status", "job_status", "request_status", "array_owner", "owner", "job_id", "request_user", "job_test", "request_task", "Use_ID", "request_number", "Name", "array_name", "Appliance");
			return $columnArray;
		}
	//Queries test information from the database and outputs the information into a table
	static function joinTest(){
		
		$databaseArray = model::databaseArray();
		$tableArray = model::tableArray();
		$columnArray = model::columnArray();
		$userArray = array();
		
		$user = '';
		
		echo <<<_END
		<link rel='stylesheet' type='text/css' href='stylesheet.css' />

		<div id="tableContainer">
		<div id="testCounts">
		<fieldset>
		<table class="testCountTable">
		<tr>
		<td class="tableTitles"> Database Owner </td>
		<td class="passBGC"> Passed </td>
		<td class="runningBGC"> Running </td>
		<td class="pend_queBGC"> Queued/Pending </td>	
		<td class="failBGC"> Failed </td>		
		</tr>
_END;
		//creates the test counts table	
		for($i = 1; $i < sizeof($databaseArray); $i++){
			
			$ofrunTests = 0;
			$ofqpTests = 0;
			$ofpassTests = 0;
			$offailTests = 0;
			
					$query = "SELECT ".$tableArray[4].".".$columnArray[6].", ".$tableArray[3].".".$columnArray[5].", ".$tableArray[4].".".$columnArray[2].", ".$tableArray[3].".".$columnArray[1].", ".$tableArray[3].".".$columnArray[7].", ".$tableArray[4].".".$columnArray[8]."
					  FROM ".$databaseArray[$i].".".$tableArray[4]." 
					  LEFT JOIN ".$databaseArray[$i].".".$tableArray[3]." 
					  ON ".$tableArray[4].".".$columnArray[5]." = ".$tableArray[3].".".$columnArray[5]."
				      ORDER BY ".$tableArray[4].".".$columnArray[6];
				
				if($stmt = mysql_query($query)){
		
					while ($row = mysql_fetch_array($stmt, MYSQL_ASSOC)) {						
						
						if($row[$columnArray[7]] != "Dummy1"){
					
						if($row[$columnArray[2]] == "RUNNING"){
						
							$ofrunTests++;
						}
								
						if($row[$columnArray[2]] == "PENDING" || $row[$columnArray[2]] == "QUEUED"){				

							$ofqpTests++;
						}
										
						if($row[$columnArray[1]] == "pass" || $row[$columnArray[1]] == "succeed"){
						
							$ofpassTests++;
						}
				
						if($row[$columnArray[1]] == "fail"){

							$offailTests++;
						}				
						
						$user = str_replace("_", " ", $row[$columnArray[6]]);
						$userArray[$i] = $user;
						
					}
				}	
			}
			
			if(isset($userArray[$i])){
			
				echo '<tr>';
				echo "<td> ".ucwords($user)." </td>";
			
			if($ofpassTests != 0){
				
				echo "<td class='passBGC'> ".$ofpassTests." Passed </td>";
			}
			if($ofpassTests == 0){
			
				echo "<td></td>";
			}
			
			if($ofrunTests != 0){
					
				echo "<td class='runningBGC'> ".$ofrunTests." Running </td>";
			}
			if($ofrunTests == 0){
				
				echo "<td></td>";
			}
			
			if($ofqpTests != 0){
				
				echo "<td class='pend_queBGC'> ".$ofqpTests." Queued/Pending </td>";
			}
			
			if($ofqpTests == 0){
			
				echo "<td></td>";
			}

			if($offailTests != 0){
				
				echo "<td class='failBGC'> ".$offailTests." Failed </td>";
			}
			
			if($offailTests == 0){
			
				echo "<td></td>";
			}
			}
		}
		echo '</tr>';
		echo <<<_END
		</table>
		</div>
		</div>
_END;
	}
	//Queries resource information from the databases and outputs it to a table
	static function joinResources(){
	
		$databaseArray = model::databaseArray();
		$tableArray = model::tableArray();

		echo "<link rel='stylesheet' type='text/css' href='stylesheet.css' />";
	
		echo <<<_END
		<div id="tableContainer">
		<div id="testCounts">
		<fieldset class="overflow">
		<table class="testCountTable">
		<tr>
		<td id="tableTitles"> Resource Owner </td>
		<td id="tableTitles"> Resource </td>
		<td id="tableTitles"> Requested Task </td>
		</tr>

_END;

		//creates the resources table
		for($i = 4; $i < sizeof($databaseArray); $i++){

			$ofrunTests = 0;
			$ofqpTests = 0;
			$ofpassTests = 0;
			$offailTests = 0;

					$query = "(SELECT array_name, array_owner, requests.request_task FROM ".$databaseArray[$i].".".$tableArray[0]." LEFT JOIN ".$databaseArray[$i].".".$tableArray[4]." ON ottobrain_arrays.Use_ID = requests.request_number WHERE requests.request_task != 'null')
							   UNION
							  (SELECT Name, owner, requests.request_task FROM ".$databaseArray[$i].".".$tableArray[2]." LEFT JOIN ".$databaseArray[$i].".".$tableArray[4]." ON ottobrain_vms.Use_ID = requests.request_number WHERE requests.request_task != 'null')
							   UNION
							  (SELECT appliance, owner, requests.request_task FROM ".$databaseArray[$i].".".$tableArray[1]." LEFT JOIN ".$databaseArray[$i].".".$tableArray[4]." ON ottobrain_spartans.Use_ID = requests.request_number WHERE requests.request_task != 'null')";
			
			if($stmt = mysql_query($query)){
				//$stmt = mysql_query($query) or die($query."<br/><br/>".mysql_error());
	
				while ($row = mysql_fetch_array($stmt, MYSQL_ASSOC)) {	
				
				if(isset($row['array_name']) && isset($row['array_owner']) && isset($row['request_task'])){
					if(model::startsWith($row['array_name'], "s")){
						
						$user = str_replace("_", " ", $row['array_owner']);
							
						echo "<td>".ucwords($user)."</td>";
						echo "<td class='arrays'>".$row['array_name']."</td>";
						echo "<td>".$row['request_task']."</td>";
						echo '</tr>';
						
					}
					if(model::startsWith($row['array_name'], "O")){
						
						$user = str_replace("_", " ", $row['array_owner']);
							
						echo "<td>".ucwords($user)."</td>";
						echo "<td class='vms'>".$row['array_name']."</td>";
						echo "<td>".$row['request_task']."</td>";
						echo '</tr>';
						
					}
					if(model::startsWith($row['array_name'], "m")){
						
						$user = str_replace("_", " ", $row['array_owner']);
							
						echo "<td>".ucwords($user)."</td>";
						echo "<td class='spartans'>".$row['array_name']."</td>";
						echo "<td>".$row['request_task']."</td>";
						echo '</tr>';
						
					}
					$el = substr($row['array_name'], -3, 1);
					$int = substr($row['array_name'], -2);
					$int = (int)$int;
					
					if($el == "L" && is_int($int) && $int > 50){
					
						$user = str_replace("_", " ", $row['array_owner']);
							
						echo "<td>".ucwords($user)."</td>";
						echo "<td class='arrays'>".$row['array_name']."</td>";
						echo "<td>".$row['request_task']."</td>";
						echo '</tr>';
				
					}
						
					if(model::startsWith($row['array_name'], "DESL") && $int < 50){
							
						$user = str_replace("_", " ", $row['array_owner']);
							
						echo "<td>".ucwords($user)."</td>";
						echo "<td class='vms'>".$row['array_name']."</td>";
						echo "<td>".$row['request_task']."</td>";
						echo '</tr>';
					
					}
					
					if(model::startsWith($row['array_name'], "DESL-")){
							
						$user = str_replace("_", " ", $row['array_owner']);
							
						echo "<td>".ucwords($user)."</td>";
						echo "<td class='vms'>".$row['array_name']."</td>";
						echo "<td>".$row['request_task']."</td>";
						echo '</tr>';
							
					}
					
				}
			}
		}
	}
	
		echo <<<_END
				
		</table>
		</fieldset>
		</div>
		</div>
		<fieldset class="key">
		<legend>Key</legend>
		<table>
		<tr><td class="arrays">Arrays</td></tr>
		<tr><td class="spartans">Spartans</td></tr>
		<tr><td class="vms">VMs</td></tr>
		</table>
		</fieldset>		
		
_END;
	}
	//Inserts assigned project info into the local db
	static function assignProjectAdmin($Project_Name, $Assigned, $Priority, $Status, $function) {
		$mysqli = newUtil::connect();
		$mysqlprep = "";
		
		//admin email addreses
		$to  = 'john_j_breen@Dell.com' . ', ';
		$to .= 'chris_trautman@Dell.com';// . ', ';
		//$to .= 'justin_gauthier@Dell.com';
		
		$headers  = 'MIME-Version: 1.0' . "\r\n";
		$headers .= 'Content-type: text/html; charset=iso-8859-1' . "\r\n";
		$headers .= 'From: ottoUpdate@Dell.com';
		
		//removes _ with " " and capitalizes first and last name if needed 
		$newuser = $Assigned;
		if (strpos($Assigned, '_') !== false){
			$Assigned = str_replace("_", " ", $Assigned);
			$Assigned = ucwords($Assigned);
		}
		
		if($function == "Add"){
			
			//preps email message
			$message = "<p><strong>".$Assigned."</strong> just got assigned <strong>".$Project_Name."</strong>.<br>Priority level: <strong>".$Priority."</strong>.<br>Status: <strong>".$Status."</strong>.";
			
			$mysqlprep = "INSERT INTO test_projects.test_project_status(Project_Name, Assigned, Priority, Status) 
					  VALUES (".'"'.$Project_Name.'"'.", ".'"'.$Assigned.'"'.", ".'"'.$Priority.'"'.", ".'"'.$Status.'"'.");";
		
		if ($stmt = $mysqli->prepare($mysqlprep)){
			$stmt->execute();
			$user = str_replace("_", " ", $Assigned);
			
			//sends email to admins
			mail($to, 'New Project', $message, $headers);
			echo "<p class='success'>*You have successfully assigned $Project_Name to ".ucwords($user).".*</p>";
		}
		}
		
		else if($function == "Delete"){
			
			$query = "SELECT test_project_status.project_name, test_project_status.assigned FROM test_projects.test_project_status WHERE test_project_status.projectid = ".$Project_Name.";";
			
			$stmt = $mysqli->query($query);
			
			while($row = $stmt->fetch_assoc()) {
				$newName = $row['project_name'];
				$Assigned = $row['assigned'];
				break;
			}
			//preps email message
			$message = "<p><strong>".$Assigned."</strong>: <strong>".$newName."</strong> just got removed.</p>";
			
			$mysqlprep = "DELETE FROM test_projects.test_project_status WHERE test_project_status.projectID = ".'"'.$Project_Name.'"'.";";
			
			if ($stmt = $mysqli->prepare($mysqlprep)){
				$stmt->execute();
				//sends email to admins
				mail($to, 'Removed Project', $message, $headers);
				echo "<p class='success'>*You have successfully removed the project.*</p>";
			}
		}
		
		if(!$stmt) {
			printf("prepare() failed: (%s) %s", $mysqli->errno, $mysqli->error);
		}
		else {

			//close statement
			$stmt->close();
		}
	}
	//Removes the old password and adds the new one into the db
	static function changePassword($userid, $newPassword) {
		$mysqli = newUtil::connect();
		$mysqlprep = "";

				$mysqlprep = "UPDATE test_projects.puser SET puser.userpass = ".'"'.$newPassword.'"'." WHERE puser.userid = $userid;";
				
		if ($stmt = $mysqli->prepare($mysqlprep)){		
			$stmt->execute();			
			echo "<p class='success'>*You have successfully changed your password.*</p>";
		}
		if(!$stmt) {
			$mysqlprep = "UPDATE test_projects.puser SET puser.userpass = ".'"'.$newPassword.'"'." WHERE puser.userlogin = ".'"'.$userid.'"'.";";
			
			$stmt = $mysqli->prepare($mysqlprep);
			$stmt->execute();
			echo "<p class='success'>*You have successfully changed your password.*</p>";
			//printf("prepare() failed: (%s) %s", $mysqli->errno, $mysqli->error);
		}
		else {
			//close statement
			
			$stmt->close();
		}
	}
	//Changes the project status in the db
	static function changeProjectStatus($ProjectID, $Status, $user) {
		$mysqli = newUtil::connect();
		$mysqlprep = "";
		
		//admin email addreses
		$to  = 'john_j_breen@Dell.com' . ', ';
		$to .= 'chris_trautman@Dell.com';// . ', ';
		//$to .= 'justin_gauthier@Dell.com';
		
		$headers  = 'MIME-Version: 1.0' . "\r\n";
		$headers .= 'Content-type: text/html; charset=iso-8859-1' . "\r\n";
		$headers .= 'From: ottoUpdate@Dell.com';
		
		$query = "SELECT test_project_status.status FROM test_projects.test_project_status WHERE test_project_status.projectid = $ProjectID;";
		
		$stmt = $mysqli->query($query);
		
			while($row = $stmt->fetch_assoc()) {
				$oldStatus = $row['status'];
				break;
			}
		//removes _ from user for email message
		$user = str_replace("_", " ", $user);
		$user = ucwords($user);
		
		//preps email message
		$message = "<p><strong>".$user."</strong> changed their project status from <strong>".$oldStatus."</strong> to <strong>".$Status."</strong>.</p>";
		//echo "<p class='success'>*You have successfully changed the project status.*</p>";
		
		$mysqlprep = "UPDATE test_projects.test_project_status SET test_project_status.status = ".'"'.$Status.'"'." WHERE test_project_status.projectid = $ProjectID;";
		
		if($stmt = $mysqli->prepare($mysqlprep)){
			$stmt->execute();
			
			//emails admins
			mail($to, 'Project Status Change', $message, $headers);
			echo "<p class='success'>*You have successfully changed the project status.*</p>";
		}
		if(!$stmt) {
			printf("prepare() failed: (%s) %s", $mysqli->errno, $mysqli->error);
		}
		else {

			//close statement
			$stmt->close();
		}
	}
	//Inserts or deletes users from db
	static function controlUser($function, $userID, $userName, $loginId, $loginPass) {
		$mysqli = newUtil::connect();
		$mysqlprep = "";
		
			if($function == "Add"){
				
				$mysqlprep = "INSERT INTO test_projects.pUser (puser.UserID,puser.UserLogin,puser.UserPass,puser.UserName)
									  VALUES ($userID, ".'"'.$loginId.'"'.", ".'"'.$loginPass.'"'.", ".'"'.$userName.'"'.");";
				
				$stmt = $mysqli->prepare($mysqlprep);
				$stmt->execute();
				echo "<p class='success'>*You have successfully added $userName.*</p>";
				break;
			}
			if($function == "Delete"){
				
				$mysqlprep = "DELETE FROM test_projects.puser WHERE puser.userlogin = ".'"'.$loginId.'"'.";";
				
				$stmt = $mysqli->prepare($mysqlprep);
				$stmt->execute();
				
				//removes _ for db query purposes
				$user = str_replace("_", " ", $loginId);
				
				$mysqlprep = "DELETE FROM test_projects.test_project_status WHERE test_project_status.assigned = ".'"'.ucwords($user).'"'.";";
				
				echo "<p class='success'>*You have successfully removed ".ucwords($user).".*</p>";
				
				$stmt = $mysqli->prepare($mysqlprep);
				$stmt->execute();
				break;
		}
	}
	//Fills dynamic dropdown menus in user preferences locations
	function fillDropDown($table, $field, $iD) {
		$queryArray = array();
		$mysqli = newUtil::connect();
		$query = "SELECT ".$iD.",".$field." FROM ".$table." ORDER BY ".$field." ASC";
		$stmt = $mysqli->query($query);
		if ($stmt->num_rows > 0) {
			while($row = $stmt->fetch_assoc()) {
				$queryArrayID = $row[$iD];
				$queryArrayField = $row[$field];
				$queryArray[$queryArrayID] = $queryArrayField;
			}
		}
		return $queryArray;
	}
	//Fills dynamic dropdown menus in the project preferences
	function fillDropDownTest($table, $project_name, $assigned, $projectID) {
		
		$i = 0;
		
		$queryArray = array();
		$mysqli = newUtil::connect();
		$query = "SELECT ".$project_name.",".$assigned.", ".$projectID." FROM ".$table." ORDER BY ".$projectID." ASC";
		$stmt = $mysqli->query($query);
		if ($stmt->num_rows > 0) {
			while($row = $stmt->fetch_assoc()) {
				$queryArrayName = $row[$project_name];
				$queryArrayField = $row[$assigned];
				$queryArrayID = $row[$projectID];
				$queryArray[$i] = array(array($queryArrayID, "$queryArrayField", "$queryArrayName"));
				$i++;
			}
		}
		return array_values($queryArray);
	}
	//Checks user login in db and returns whether or not they are a user or admin
	function checkLogin($userLogin, $userPass) {
		
		$userID = 0;
		$mysqli = newUtil::connect();
		$query = "SELECT UserID,UserLogin,UserPass FROM pUser
								WHERE (UserLogin=?) && (UserPass=?)";
		$stmt = $mysqli->prepare($query);
		$stmt->bind_param("ss", $userLogin, $userPass);
		//execute prepare statement
		$stmt->execute();
		if( ! $stmt) {
			$stmt->close();
		}
		else {
			$stmt->bind_result($userIDValue,$userLogin,$userPass);
			$stmt->fetch();
			if($userIDValue != 0) {
				$userID = $userIDValue;
			}
			$stmt->close();
		}
		return $userID;
	}
	//Auto increments user or admin IDs in db 
	//allows IDs to be reused if one was removed
	function autoIncrement($table, $field, $idVariable, $startPosition) {
		$queryArray = array();
		$mysqli = newUtil::connect();
		$query = "SELECT ".$field." FROM ".$table;
		$stmt = $mysqli->query($query);
		if ($stmt->num_rows > 0) {
			$i = 0;
			while($row = $stmt->fetch_assoc()) {
				$queryArray[$i] = $row[$field];
				$i++;
			}
		}
		if(!$stmt) {
			printf("prepare() failed: (%s) %s", $mysqli->errno, $mysqli->error);
		}
		if($idVariable == "") {
			$idVariable = (int) $idVariable;
			$idVariable = $startPosition;
			foreach($queryArray as $value) {
				if($idVariable == $value) {
					$idVariable = $idVariable + 1;
				}
			}
		}
		else {
			$idVariable = (int) $idVariable;
			foreach($queryArray as $value) {
				if($idVariable == $value) {
					echo $field." value ".$idVariable." already exists... please choose a different value. Ignore this message for Delete.<br>";
				}
			}
		}
		//close statement
		$stmt->close();
		return $idVariable;
	}
	//Searches string for the beginning character
	function startsWith($haystack, $needle)
	{
		$length = strlen($needle);
		return (substr($haystack, 0, $length) === $needle);
	}
	
}
?>