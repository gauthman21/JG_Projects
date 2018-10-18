<html>
<head>
<link rel='stylesheet' type='text/css' href='stylesheet.css'/>
</head>
<body>
<!-- Back button for folder directory -->
<a href="#" onclick="goBack();return false;" target="iframe" class="left-arrow" >Back</a>
<?php
	require_once("logUtil.php");
	//Outputs information from testdb to corresponding table
	function grabItem($item){
		
	$i = 0;	
	$j = 0;
	$k = 0;
		
	$mysql = log::connect(); //connects to correct db
		
		$query = "SELECT testinfo.test_name, testinfo.average_run_time, testinfo.longest_run_time, testinfo.shortest_run_time, testexecutionhistory.time_completed, testexecutionhistory. arrays_used, testexecutionhistory.vms_used, testexecutionhistory.spartans_used, testexecutionhistory.log_location 
			      FROM testdb.testinfo 
				  LEFT JOIN testdb.testexecutionhistory 
				  ON testinfo.test_id = testexecutionhistory.test_id 
				  ORDER BY UNIX_TIMESTAMP(testexecutionhistory.time_completed) DESC;";
		
		if($stmt = mysql_query($query)){
			//$stmt = mysql_query($query) or die($query."<br/><br/>".mysql_error()); //for testing purposes only
			echo "<table class='testNameTable'>";
			while ($row = mysql_fetch_array($stmt, MYSQL_ASSOC)) {
				
				if(basename($item) == $row['test_name']){
					//table is only created once
					if(!$i++){
								
						echo "<tr>";
						echo "<td>Test Name</td>";
						echo "<td>Average Runtime</td>";
						echo "<td>Shortest Runtime</td>";
						echo "<td>Longest Runtime</td>";
						echo "</tr>";
						echo "<tr>";
						echo "<td>".$row['test_name']."</td>";
						echo "<td>".$row['average_run_time']."</td>";
						echo "<td>".$row['shortest_run_time']."</td>";
						echo "<td>".$row['longest_run_time']."</td>";
						echo "</tr>";
						echo "</table>";
						echo "<br><br>";
						echo "<table class='timeTable'>";
						echo "<tr>";
						echo "<td>Time Completed</td>";
						echo "<td>Arrays Used</td>";
						echo "<td>VMs Used</td>";
						echo "<td>Spartans Used</td>";
						echo "</tr>";
						
						//javascript hides the folder directories when the log viewer table is created
						echo "<script>";
						echo "$(document).ready(function(){";
						echo "$('a.folder').hide();";
						echo "$('h2.marginTitle').hide();";
						echo "});";
						echo "</script>";
					}
						if($j++){
							
							//backs time completed with corresponding url for log files and send it to the newFrameDirectory via $_GET
							//outputs after $j > 0 
							$logLocation = "http://10.127.64.50/otto-logs/".$row['log_location'];
							echo "<form method='get'>";
							echo "<tr>";
							echo "<td><a href=\"newController.php?loc={$logLocation}\" id='blackAhref' target='iframe0';>".$row['time_completed']."</a></td>";
							echo "<td>",(is_null($row['arrays_used']) ? "NULL" : $row['arrays_used']),"</td>";
							echo "<td>",(is_null($row['vms_used']) ? "NULL" : $row['vms_used']),"</td>";
							echo "<td>",(is_null($row['spartans_used']) ? "NULL" : $row['spartans_used']),"</td>";
							echo "</tr>";
							echo "</form>";
						}	
					//creates new iframe only once to accommodate the file output
					if (!$k++)echo '<iframe id="iframe0" name="iframe0" scrolling="no"></iframe>';
				}	
			}
			echo "</table>";
		}
	}
?>
<script>
function goBack() {
    window.history.back();
}
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<?php

  // Explore the files via a web interface.
  $script = basename(__FILE__); // the name of this script
  
  // Sets directory and allows for new directory to be added to path
  if($path = "E:\\OttoLogs\\"){
  	
  	$path .= $_REQUEST['path'];
  	
  	grabItem($path);
  }
  
  $directories = array();
  $files = array();

  // Check we are focused on a dir
  if (is_dir($path)) {
    
  	chdir($path); // Focus on the dir
   
   if ($handle = opendir('.')) {
      while (($item = readdir($handle)) !== false) {
        // Loop through current directory and divide files and directories
        if(is_dir($item)){
        
          array_push($directories, realpath($item));
        }
        else
        {
          array_push($files, ($item));
        }
   }
   closedir($handle); // Close the directory handle
   }
    else {
      echo "<p class=\"error\">Directory handle could not be obtained.</p>";
    }
  }
  else
  {
    echo "<p class=\"error\">Path is not a directory</p>";
  }

  // There are now two arrays that contians the contents of the path.

  // List the directories as browsable navigation
  echo '<div id="directoryDiv">';
  echo "<h2 class='marginTitle'>Directories</h2>";
  echo "<ul>";
  
  foreach( $directories as $directory ){
 	
    // Removes physical direcory from string
  	$directory = str_replace("E:\\OttoLogs\\", "", $directory);
   	
    if($directory != "AutomaticExecution" && $directory != "DrvFirmwareLogs" && $directory != "EmailServer" && $directory != "LiveMemDumps" && $directory != "LvlogUpload" && $directory != "NDR_Tests" && $directory != "OldUpgradetestResults" && $directory != "Otto-Summary" && $directory != "PatchkitTests" && $directory != "Sanity" && $directory != "Unittest_Suite" && $directory != "E:\\OttoLogs" && $directory != "E:\\"){

       	echo ($directory != $path) ? "<li><a href=\"{$script}?path={$directory}\" class='folder'>+ {$directory}</a></li><br>" : "";
    }
  }
  echo "</ul>";
  echo "</div>";
?>
<br>
</body>
</html>