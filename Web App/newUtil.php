<?php
class util {
	//Connects to otto-sqlserver databases
	static function dataConnect() {
		
		//config.txt provides connection info
		$textFile = file_get_contents("config.txt");
		$textArray = explode(",", $textFile);

		$connectionInfo = mysql_connect( $textArray[0], $textArray[1], $textArray[2]);
		$databases = mysql_query("SHOW DATABASES");;

	if( $connectionInfo == false )
		{
			echo "Could not connect.\n";
			die("Connection failed: " . $connectionInfo);
		}
		
		return $connectionInfo;
	}
}
?>