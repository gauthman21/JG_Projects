<?php

//Processes inputs from user and sends them to specific areas for processing 

//ini_set('display_errors', 'On');
echo "<link rel='stylesheet' type='text/css' href='stylesheet.css' />";

require_once("UserPrefView.php");
require_once("AdminUserPrefView.php");
require_once("newModel.php");
require_once("WebAppView.php");
require_once("ajax.php");
require_once("testAdminForm.php");
require_once("testUserForm.php");
require_once("newFrameDirectory.php");

	//grabs error message
	if(isset($_COOKIE['success'])){
		echo $_COOKIE['success'];
	}
	//goes to corresponding class depending on what submit button user clicked
	if(isset($_POST['ChangePWButtons'])) {
		processChangePW();
	}
	if(isset($_POST['aProjectButton'])) {
		assignProject();
	}
	if(isset($_POST['changePStatusButton'])) {
		changeStatus();
	}
	if (isset($_POST['userButton'])) {
		processUserEntry();
	}
	if (isset($_POST['userButtonRemove'])) {
		removeUser();
	}
	if (isset($_POST['logout'])) {
		logout();
	}
	if(isset($_POST['aProjectButtonUser'])) {
		assignProjectUser();
	}
	if(isset($_POST['ChangePWButtonUser'])) {
		processChangePWUser();
	}
	//changes path from directory to the virtual directory to view and download files
	if (isset($_GET['loc'])) {
		$path = $_GET['loc'];
		$path = str_replace("http://10.127.64.50/otto-logs/", "E:\\OttoLogs\\", $path);
		newDirectory::iframeDirect($path);
	}
	
	//Processes add user depending on whether or not they are an admin or regular user
	function processUserEntry() {
		
		$userFunction = $_POST['userFunction'];
		$userFunction0 = $_POST['userFunction0'];
		$userId = $_POST['userId'];
		$userName = $_POST['userName'];
		$loginId = $_POST['loginId'];
		$loginPass = $_POST['loginPass'];
		
		if($userFunction0 == "User" && $userName != null && $loginId != null && $loginPass != null){
			$userId = model::autoIncrement("pUser", "UserID", $userId, 1000);
			model::controlUser($userFunction, $userId, $userName, $loginId, $loginPass);
		}
		
		if($userFunction0 == "Admin" && $userName != null && $loginId != null && $loginPass != null){
			$userId = model::autoIncrement("pUser", "UserID", $userId, 1);
			model::controlUser($userFunction, $userId, $userName, $loginId, $loginPass);
		}

		if($userFunction = "Add" && $userId == null || $userName == null || $loginId == null || $loginPass == null){
			showAdminForm();
			echo "<p class='adminError'>*Invalid entry. Please try again.*</p>";
		}
		
		unset($_POST['userFunction']);
		unset($_POST['userFunction0']);
		unset($_POST['userId']);
		unset($_POST['userName']);
		unset($_POST['loginId']);
		unset($_POST['loginPass']);

	}
	//removes user from db
	function removeUser() {

		$userId = null;
		$userName = null;
		$loginPass = null;
		$loginId = $_POST['loginId'];
		$userFunction = $_POST['userFunction'];
		
		if($loginId == "Select"){
			
			showAdminForm();
			echo "<p class='adminError'>*Invalid entry. Please try again.*</p>";
		}
		if($userFunction == "Delete" && $loginId != "Select"){
			
			model::controlUser($userFunction, $userId, $userName, $loginId, $loginPass);
		}
	
		unset($_POST['userFunction']);
		unset($_POST['userId']);
		unset($_POST['userName']);
		unset($_POST['loginId']);
		unset($_POST['loginPass']);
	
	}
	//Processes db login credentials
	function processSQLEntry() {
		
		$addressY = $_POST['addressY'];
		$portY = $_POST['portY'];
		$usernameY = $_POST['usernameY'];
		$passwordY = $_POST['passwordY'];
		model::handleSQL($addressY, $usernameY, $passwordY, $portY);
	}
	//Processes admin or user login for user preferences
	function processLoginEntry() {
		
		$loginId0 = $_GET['loginId0'];
		$userPass0 = $_GET['userPass0'];
		
		//checks login to see if it is a user or admin
		$check = model::checkLogin($loginId0, $userPass0);
		setcookie("userid", $check, time()+3600, "/");
		//normal user id is over 1000
		if($check >= 1000) {
			setcookie("username", $loginId0, time()+3600, "/");
			showUserForm($loginId0);
		}
		//admin id is between 0 and 100
		else if($check > 0 && $check < 100) {
			showAdminForm();
		}
		else if($check == 0) {
			//removes cookies
			setcookie('username', '', time()-3600, '/');
			setcookie('userid', '', time()-3600, '/');
			appHTML::loginView();
			echo "<p class='invalidPW'>*Login ID or Password is invalid.* <br></p>";
		}
	}
	//Processes admin or user login for project preferences
	function processLoginEntryProject() {
	
		$loginId0 = $_GET['loginId0'];
		$userPass0 = $_GET['userPass0'];
		
		//checks login to see if it is a user or admin
		$check = model::checkLogin($loginId0, $userPass0);
		setcookie("userid", $check, time()+3600, "/");
		//normal user id is over 1000
		if($check >= 1000) {
			setcookie("username", $loginId0, time()+3600, "/");
			showTestForm($loginId0);
		}
		//admin id is between 0 and 100
		else if($check > 0 && $check < 100) {
			showTestAdminForm();
		}
		else if($check == 0) {
			//removes cookies
			setcookie('username', '', time()-3600, '/');
			setcookie('userid', '', time()-3600, '/');
			appHTML::loginViewProject();
			echo "<p class='invalidPW'>*Login ID or Password is invalid.* <br></p>";
		}
	}
	//Logs out current user and resets cookies
	function logout(){
		setcookie('username', '', time()-3600, '/');
		setcookie('userid', '', time()-3600, '/');
		header("Location: newController.php");
	}
	//Changes status on users project they are assigned to
	function changeStatus() {
		$projectID = $_POST['projectID'];
		$status = $_POST['pStatusFunction'];
		//needed for query purposes in model::changeProjectStatus
		$user = $_COOKIE['username'];
		
		if($projectID != "Select"){
		model::changeProjectStatus($projectID, $status, $user);
		}
		else{
			showTestForm($user);
			echo "<p class='userError'>*Invalid project selected.<br>Please try again.*</p>";
		}
	}
	//Allows the admin to assign projects to users
	function assignProject() {
		$projectName = $_POST['projectName'];
		$assignTo = $_POST['assign'];
		$function = $_POST['userFunction'];
		
		if(strlen($projectName) > 60){
			showTestAdminForm();
			echo "<p class='adminErrorProject'>*Project name exceeded maximum characters.*</p>";
			break;
		}
		
		if($assignTo == "Select"){
			showTestAdminForm();
			echo "<p class='adminErrorProject'>*Invalid entry. Please try again.*</p>";
			break;
		}
		if($projectName == "Select" && $function == "Delete"){
			showTestAdminForm();
			echo "<p class='adminErrorProject'>*Invalid entry. Please try again.*</p>";
			break;
		}
		
		$mysqli = newUtil::connect();
		$mysqlprep = "";
		$mysqlprep = "SELECT puser.username FROM test_projects.puser WHERE puser.userid = $assignTo;";
		
		if($stmt = $mysqli->query($mysqlprep)){
		while($row = $stmt->fetch_assoc()) {
				$assignTo = $row['username'];
			}
		}
		
		$pri = $_POST['priDrop'];
		$status = $_POST['statusDrop'];
		
		if($function == "Delete" && $projectName != "Select"){
			model::assignProjectAdmin($projectName, $assignTo, $pri, $status, $function);
		}
				
		if($projectName != null && $assignTo != null && $pri != null && $status != null){
			model::assignProjectAdmin($projectName, $assignTo, $pri, $status, $function);
		}

		if($projectName == null){
			showTestAdminForm();
			echo "<p class='adminErrorProject'>*Invalid entry. Please try again.*</p>";
		}
	}
	//Allows the user to assign themselves a project
	function assignProjectUser() {
		$projectName = $_POST['projectName'];
		$assignTo = $_POST['assign'];
	
		$mysqli = newUtil::connect();
		$mysqlprep = "";
		$mysqlprep = "SELECT puser.username FROM test_projects.puser WHERE puser.userid = $assignTo;";
	
		if($stmt = $mysqli->query($mysqlprep)){
			while($row = $stmt->fetch_assoc()) {
				$assignTo = $row['username'];
			}
		}
		$pri = $_POST['priDrop'];
		$status = $_POST['statusDrop'];
		$function = $_POST['userFunction'];
		
		if($projectName != null && $assignTo != null && $pri != null && $status != null){
			model::assignProjectAdmin($projectName, $assignTo, $pri, $status, $function);
			
		}
		else{
			showTestForm($assignTo);
			echo "<p class='userError'>*Forgot to enter a value. All values need<br>to be entered before submission.*</p>";
		}
	}
	//Allows the admin to change password for any user
	//no old pw needed
	function processChangePW() {
		$loginId1 = $_POST['loginId'];
		//$userPass1 = $_POST['userPass1']; //old pw location
		$userPassNew = $_POST['userPass'];
		$userPassNew1 = $_POST['userPass2'];
		
		if($loginId1 == "Select"){
			showAdminForm();
			echo "<p class='adminError'>*Invalid entry. Please try again.*</p>";
			break;
		}
		
		$mysqli = newUtil::connect();
		$mysqlprep = "";
		$mysqlprep = "SELECT puser.userlogin FROM test_projects.puser WHERE puser.userid = $loginId1;";
		
		if($stmt = $mysqli->query($mysqlprep)){
			while($row = $stmt->fetch_assoc()) {
				$loginId1 = $row['userlogin'];
			}
		}
		//checks login to see if it is a user or admin
		$check = model::checkLogin($loginId1, $userPass1);
		
		if($userPassNew == $userPassNew1){
			model::changePassword($loginId1, $userPassNew);
		}
		if($userPassNew != $userPassNew1){
			showAdminForm();
			echo "<p class='adminError'>*Passwords do not match.*</p>";
			break;
			}
		if($check > 0 && $check < 100 && $userPassNew == null || $userPassNew == null){
			showAdminForm();
			echo "<p class='adminError'>*Invalid entry please try again.*</p>";
			break;
		/*if($check == 0){
			showAdminForm();
			echo "<p class='adminError'>*Old Password was entered incorrectly.*</p>";
		}*/	
	}
}
	//Allows user to change there password
	function processChangePWUser() {
		$loginId1 = $_POST['loginId'];
		$userPass1 = $_POST['userPass1'];
		$userPassNew = $_POST['userPass'];
		$userPassNew1 = $_POST['userPass2'];
		//checks login to see if it is a user or admin
		$check = model::checkLogin($loginId1, $userPass1);
		
		if($check >= 1000 && $userPass1 == null || $userPassNew == null || $userPassNew1 == null){
			showUserForm($loginId1);
			echo "<p class='userErrorPW'>*Invalid entry please try again.*</p>";
			break;
		}
		if($userPassNew == $userPassNew1){
			model::changePassword($loginId1, $userPassNew);
			//sets cookie for success message output because logout() is being called afterwards
			setcookie("success", "<p class='success'>*You have successfully changed your password.*</p>", time()+1, "/");
			logout();
		}
		if($userPassNew != $userPassNew1){
			showUserForm($loginId1);
			echo "<p class='userErrorPW'>*New passwords do not match.*</p>";
			break;
		}
		if($check == 0){
			showUserForm($loginId1);
			echo "<p class='userErrorPW'>*Old Password was entered incorrectly.*</p>";
		}
	}
	//Sends user or admin to correct page depending on what they are doing
	function showTestAdminForm() {
		testAdminForm::assignProjects();
		testAdminForm::removeProjects();
	}
	function showTestForm($user) {
		testUserForm::assignProjects($user);
		testUserForm::changeProjectStatus($user);
	}
	function showAdminForm() {
		AdminUserPrefView::add_Users();
		AdminUserPrefView::removeUsers();
		AdminUserPrefView::changePW();
	}
	function showUserForm($user) {
		UserPrefView::changePW($user);
	}
	//Grabs input from user and sends them to corresponding page
	function testButtons() {
	
		if (isset($_GET['resourceButton'])) {
			appHTML::testResources();
		}
		else if (isset($_GET['testProjectButton'])) {
			appHTML::testProjectStatus();
		}
		else if (isset($_GET['countButton'])) {
			appHTML::testCounts();
		}
		else if (isset($_GET['prefButton'])) {
			appHTML::loginView();
		}
		else if(isset($_GET['loginButton'])) {
			processLoginEntry();
		}
		else if(isset($_GET['loginButtonProject'])) {
			processLoginEntryProject();
		}
		else if (isset($_GET['projectPrefButton'])) {
			appHTML::loginViewProject();
		}

}

?>