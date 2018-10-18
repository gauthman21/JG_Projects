<?php
require_once("newUtil.php");
require_once("newModel.php");
//require_once("util.php");
require_once("WebAppView.php");

/*
	Main class called by IIS
	NOTE: editted php.ini mail server

 	LOCATION of php.ini: C:\Program Files (x86)\iis express\PHP\v5.6\php.ini 
 	can also be located in the PHP Manager in IIS
 
	[mail function]
	; For Win32 only.
	; http://php.net/smtp
	SMTP = smtp.equallogic.com
	; http://php.net/smtp-port
	smtp_port = 25
*/

appHTML::Main();



?>