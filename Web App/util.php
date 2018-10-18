<?php

	class newUtil {
	//Logs into test projects db
	static function connect() {
		//newConfig.txt provides connection info
		$textFile = file_get_contents("newConfig.txt");
		$textArray = explode(",", $textFile);
		$connection = new mysqli($textArray[1], $textArray[0], $textArray[2], "test_projects");
		//check connection
		if($connection->connect_error){
			echo "Failed to connect to MYSQL";
			die($connection->connect_error);
		}
		return $connection;
	}
}
		
?>