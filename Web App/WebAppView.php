<link rel="icon" href="favicon.ico" type="image/icon" />
<?php
//ini_set('display_errors', 'On');
class appHTML{
	//Main form of web app and provides the iframe used for all output
	//Nav and title are fixed
	static function Main(){
		echo "<title>Otto - Summary</title>";
		echo "<link rel='stylesheet' type='text/css' href='stylesheet.css'/>";
		
		echo "<div id='container'>";
		echo "<div id='main'>";
		echo "<div id='static'>";
		echo "<h1>Otto - Summary</h1>";
		
		echo '<form method="get">';
		echo '<nav>';
			echo "<ul>";
  					echo '<li><a href="newController.php?resourceButton=true" target="iframe" class="btn-style">Resources In Use</a>';
 				echo '<ul>';
  					echo '<li><a href="newController.php?countButton=true" target="iframe" class="btn-style">Test Counts</a></li>';
  				echo '</ul>';
  					echo '</li>';
  					echo '<li><a href="newController.php?testProjectButton=true" target="iframe" class="btn-style">Test Project Status</a></li>';
  					echo '<li><a href="newController.php?prefButton=true" target="iframe" class="btn-style">User Preferences</a>';
  				echo '<ul>';
  					echo '<li><a href="newController.php?projectPrefButton=true" target="iframe" class="btn-style">Project Preferences</a></li>';
  				echo '</ul>';
  					echo '</li>';
  					echo '<li><a href="index.php?logViewerButton=true" target="iframe" class="btn-style">Log Viewer</a>';
  				echo '<ul>';
  					echo '<li><a href="reportViewer.php?reportViewerButton=true" target="iframe" class="btn-style">Report Viewer</a></li>';
				echo '</ul>';
					echo '</li>';
  					echo '<li><a href="http://10.127.64.50/Otto-Coding-Docs/" class="btn-style">Otto - Documentation</a></li>';
  			echo "</ul>";
  		echo '</nav>';

  		echo '<iframe class="ogiFrame" name="iframe" scrolling"yes"></iframe>';
  		
  		echo '</form>';
		echo "</div>";
		echo "</div>";	
		echo "</div>";
		
	}
	//Sends user to test count table
	static function testCounts(){
		
		return model::joinTest();
	}	
	//Sends users to resources table
	static function testResources(){
	
		return model::joinResources();
	}	
	//Outputs and populates project status table from db
	static function testProjectStatus(){
		
		$mysqli = newUtil::connect();
		
		$query = "SELECT test_project_status.Project_Name, test_project_status.Assigned, test_project_status.Priority, test_project_status.Status 
				  FROM test_projects.test_project_status";
		
		echo <<<_END
		<link rel='stylesheet' type='text/css' href='stylesheet.css' />
				
		<div id="tableContainer">
		<div id="testCounts">
		<fieldset class="overflow">
		<table class="testCountTable">
						
							<tr>
								<td> Test Project Name </td>
								<td> Assigned to </td>
								<td> Priority </td>
								<td> Status </td>
							</tr>
_END;
		if($stmt = $mysqli->query($query)){
							
			while($row = $stmt->fetch_assoc()) {	
			
				$user = str_replace("_", " ", $row['Assigned']);
				
						echo '<tr>';
						echo "<td> ".$row['Project_Name']." </td>";
						echo "<td> ".ucwords($user)." </td>";
						echo "<td> ".$row['Priority']." </td>";
							
					if($row['Status'] == "Completed"){
						
						echo "<td class='passBGC'> ".$row['Status']." </td>";
					}
					else if($row['Status'] == "Pending"){
						
						echo "<td class='pend_queBGC'> ".$row['Status']." </td>";
					}
					else if($row['Status'] == "In-Progress"){
						
						echo "<td class='runningBGC'> ".$row['Status']." </td>";
					}
					else if($row['Status'] == "Cancelled"){
						
						echo "<td class='failBGC'> ".$row['Status']." </td>";
					}
							echo '</tr>';
					}
		}
					echo <<<_END
						</table>
					</fieldset>
				</form>
			</div>
		</div>	
_END;
	}
	//Login screen for user preferences
	//Creates cookie for logged in user
	static function loginView(){
		
		if(!isset($_COOKIE['userid'])){
		
		echo "<link rel='stylesheet' type='text/css' href='stylesheet.css' />";
		echo <<<_END
		<div id="tableContainer">
		<div id="testCounts">
		<form method="get">
			<fieldset>
					<h3 class="login">Please enter your Username and Password</h3>
					Username: <input type="text" name="loginId0" size="20">
					Password: <input type="password" name="userPass0" size="20"><br>
				<br> <input class="btn-style" type="submit" name="loginButton" value="Submit">
				<br>
			</fieldset>
			</div>
			<br>
		</form>
_END;
			 
		
		
		
		}
		//allows users/admins to go between preferences without having to relogin everytime
		//users/admins will have to relogin every hour
		if(isset($_COOKIE['userid'])){
			$check = $_COOKIE['userid'];
			$nuser = $_COOKIE['username'];
		if($check >= 1000) {
			showUserForm($nuser);
		}
		else if($check > 0 && $check < 100) {
			showAdminForm();
		}
		}
		
	}
	//Login screen for project preferences
	//Creates cookie for user
	static function loginViewProject(){
	
		if(!isset($_COOKIE['userid'])){
		
			echo "<link rel='stylesheet' type='text/css' href='stylesheet.css' />";
			echo <<<_END
		<div id="tableContainer">
		<div id="testCounts">
		<form method="get">
			<fieldset>
					<h3 class="login">Please enter your Username and Password</h3>
					Username: <input type="text" name="loginId0" size="20">
					Password: <input type="password" name="userPass0" size="20"><br>
				<br> <input class="btn-style" type="submit" name="loginButtonProject" value="Submit">
				<br>
			</fieldset>
			</div>
			<br>
		</form>
_END;
		}
		//allows users/admins to go between preferences without having to relogin everytime
		//users/admins will have to relogin every hour
		if(isset($_COOKIE['userid'])){
			$check = $_COOKIE['userid'];
			$nuser = $_COOKIE['username'];
			if($check >= 1000) {
				showTestForm($nuser);
			}
			else if($check > 0 && $check < 100) {
				showTestAdminForm();
			}
		}
	}
}
?>