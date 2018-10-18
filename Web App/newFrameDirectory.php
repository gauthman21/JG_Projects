<html>
<head>
<link rel='stylesheet' type='text/css' href='stylesheet.css'/>
</head>
<body>
<?php

// This class is called when the user selects a specific date from the log viewer table

class newDirectory{
	function iframeDirect($path){
		
  // Explore the files via a web interface.
  $script = basename(__FILE__); // the name of this script
  
  // Sets directory and allows for new directory to be added to path
  	
  	$path .= $_REQUEST['path'];
  	
  
  
  $directories = array();
  $files = array();

  // Check we are focused on a dir
  if (is_dir($path)) {
    
  	chdir($path); // Focus on the dir
   
   if ($handle = opendir('.')) {
      while (($item = readdir($handle)) !== false) {
        // Loop through current directory and seperates files and directories
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
  echo "<h2 class='h2Files'>Files</h2>";
  echo "<ul>";
  
  foreach( $files as $file ){
    // Comment the next line out if you wish see hidden files while browsing
    if(preg_match("/^\./", $file) || $file == $script): continue; endif; // This line will hide all invisible files.
	// Outputs only .log and .txt files
    if(substr($file, -4) == ".log" || substr($file, -4) == ".txt" && substr($file, -8) != "None.txt"){
    
    	// Changes path from server directory to online directory to view/download file
    	$path0 = str_replace("\\","/",$path);
    	$newpath = str_replace("E:/OttoLogs","http://10.127.64.50/otto-logs/",$path0);
    	
    	echo "<div id='inline'>";
    	echo "<form method='get'>";  	
    	echo "<p class='file0'>$file</p><br>";
    	echo "<li class='center0'><a href='".$newpath."/".$file."' class='download' target='_blank'> &nbsp*View* </a><a href='".$newpath."/".$file."' class='download' download> *Download* </a></li><br>";
    	echo "</form>";
    	echo "</div>";
    }
  }
  echo "</ul>";
	}
}
?>
</body>
</html>