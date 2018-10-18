<html lang="en">
<head>
<!-- imports for jquery calender -->
  <meta charset="utf-8">
  <link rel='stylesheet' type='text/css' href='stylesheet.css' />
  <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script>
  $(function() {
    $( "#datepicker" ).datepicker();
    $( "#datepicker0" ).datepicker();
  });
  </script>
</head>
<body>
<div id="tableContainer">
<div id="testCounts">
<form method="post">
		<fieldset class="fieldsetDate">
			<legend>Report Viewer</legend>
				<p>Start Date: <input type="text" name="startDate" id="datepicker" value="<?php echo date("m/d/Y", strtotime('-1 week'));?>">MM/DD/YYYY</p><br><br><br>
				<p>End Date: <input type="text" name="endDate" id="datepicker0" value="<?php echo date("m/d/Y");?>">MM/DD/YYYY</p><br><br><br>
				<input class="btn-style" type="submit" name="reportButton" value="Submit">
		</fieldset>
	</form>
</div>
</div>
<?php 
	
	require_once("logUtil.php");
	
	if (isset($_POST['reportButton'])) {
		rViewer();
	}
	if(isset($_GET['error'])){
		echo "<p class='reportError'>".$_GET['error']."</p>";
	}
		//grabs information from testdb and outputs it into a table
		//html logs are created are backed with url for that specific file
		function rViewer(){
			
			$k = 0;
			
			$startDate = strtotime($_POST['startDate']);
			$endDate = strtotime($_POST['endDate']);
			
			if($startDate >= $endDate || $startDate == null || $endDate == null){
				$error = urlencode("*You have entered an incorrect value. <br>Please try again*");
				header('Location: reportViewer.php?error='.$error);
			}
			
			$mysql = log::connect();
		
			$query = "SELECT testrunreporthistory.date_executed, testrunreporthistory.report_location 
  					  FROM testdb.testrunreporthistory
  					  ORDER BY testrunreporthistory.date_executed;";
			
			if($stmt = mysql_query($query)){
				//$stmt = mysql_query($query) or die($query."<br/><br/>".mysql_error()); //query testing purposes only
				echo "<p class='startEndDate'>".date("m/d/Y", $startDate)." - ".date("m/d/Y", $endDate)."</p>";
				echo '<table class="htmlTable">';
				echo "<tr>";
				echo "<td>Date Executed</td>";
				echo "<td>Report Location</td>";
				echo "</tr>";
				
				while ($row = mysql_fetch_array($stmt, MYSQL_ASSOC)) {

					$dateExecution = str_replace("-", "/", $row['date_executed']);
					$dateExecution = strtotime($dateExecution);
					
					if($dateExecution >= $startDate && $dateExecution <= $endDate){
						
						$reportLocation = "http://10.127.64.50/otto-logs/".$row['report_location'];
						
							echo "<tr>";
							echo "<td>".$row['date_executed']."</td>";
							echo "<td><a href=\"{$reportLocation}\" id='blackAhref' target='_blank';>".basename($row['report_location'])."</a></td>";
							echo "</tr>";
					}
			
		}
		echo '</table>';
	}
}
?>
</body>
</html>