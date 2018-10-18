<?php

	class log {
	//Logs into testdb db
	static function connect() {
		//newConfig.txt provides connection info
		$textFile = file_get_contents("newConfig.txt");
		$textArray = explode(",", $textFile);
		$connection = mysql_connect($textArray[1], $textArray[0], $textArray[2], "testdb");
		//check connection
		if($connection->connect_error){
			echo "Failed to connect to MYSQL";
			die($connection->connect_error);
		}
		return $connection;
	}
}
		
?>