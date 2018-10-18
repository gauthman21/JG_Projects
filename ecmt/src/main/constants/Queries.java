package main.constants;

public class Queries 
{
	
	//SELECT QUERIES- GETTING DATA FROM THE TABLES IN THE DATABASE AS NEEDED
	
	public static final String SELECTALLEMPLOYEES = "SELECT e.empID, e.name AS EMP_NAME,status,"
					+ "wtr,a.name AS Account,workLocation as wl,currentLocation as cl,homeLocation as hl,"
					+ "e.phone,e.email FROM EMPLOYEE e JOIN ACCOUNT JOIN AID a JOIN SID s "
					+ "ON e.empID = ACCOUNT.empID AND ACCOUNT.aid = a.aid AND a.aid = s.aid "
					+ "GROUP BY e.empID";
	
	public static final String SELECTALLPREVIOUSEMPLOYEES = "SELECT * FROM PREVIOUSEMPLOYEE";
	
	public static final String SELECTPREVIOUSEMPLOYEE = "SELECT * FROM PREVIOUSEMPLOYEE WHERE empID=?";
	
	
	public static final String SELECTSTATUSEXTRATYPE = "SELECT e.empID, e.name AS EMP_NAME,status,"
			+ "wtr,a.name AS Account,workLocation as wl,currentLocation as cl,homeLocation as hl,"
			+ "e.phone,e.email FROM EMPLOYEE e JOIN ACCOUNT JOIN AID a JOIN SID s "
			+ "ON e.empID = ACCOUNT.empID AND ACCOUNT.aid = a.aid AND a.aid = s.aid "
			+ "WHERE status LIKE ? OR status LIKE ? "
			+ "GROUP BY e.empID";

	public static final String SELECTSTATUSTYPE="SELECT e.empID, e.name AS EMP_NAME,status,"
			+ "wtr,a.name AS Account,workLocation as wl,currentLocation as cl,homeLocation as hl,"
			+ "e.phone,e.email FROM EMPLOYEE e JOIN ACCOUNT JOIN AID a JOIN SID s "
			+ "ON e.empID = ACCOUNT.empID AND ACCOUNT.aid = a.aid AND a.aid = s.aid "
			+ "WHERE status LIKE ? "
			+ "GROUP BY e.empID";
	
	public static final String SELECTNONBILLABLESTATUS = "SELECT e.empID, e.name AS EMP_NAME,status,"
			+ "wtr,a.name AS Account,workLocation as wl,currentLocation as cl,homeLocation as hl,"
			+ "e.phone,e.email FROM EMPLOYEE e JOIN ACCOUNT JOIN AID a JOIN SID s "
			+ "ON e.empID = ACCOUNT.empID AND ACCOUNT.aid = a.aid AND a.aid = s.aid "
			+ "WHERE status NOT LIKE ? GROUP BY e.empID";

	public static final String SELECTVERTICALINFORMATION = "SELECT VID.Name AS VERTICAL,SID.name AS "
					+ "VERT_SPOC_NAME,VID.vid AS ID,SID.phone AS VERT_SPOC_PHONE,SID.email AS "
					+ "VERT_SPOC_EMAIL FROM EMPLOYEE JOIN VERTICAL JOIN VID JOIN SID ON "
					+ "EMPLOYEE.empID = VERTICAL.empID AND VERTICAL.vid = VID.vid AND "
					+ "VID.vid=SID.vid WHERE EMPLOYEE.empID=? AND EMPLOYEE.vsid=SID.sid";
		
	public static final String SELECTACCOUNTINFORMATION = "SELECT AID.Name AS ACCOUNT,SID.name AS "
					+ "ACC_SPOC_NAME,AID.aid AS ID,SID.phone AS ACC_SPOC_PHONE,SID.email AS "
					+ "ACC_SPOC_EMAIL FROM EMPLOYEE JOIN ACCOUNT JOIN AID JOIN SID ON "
					+ "EMPLOYEE.empID = ACCOUNT.empID AND ACCOUNT.aid = AID.aid AND "
					+ "AID.aid=SID.aid WHERE EMPLOYEE.empID=? AND EMPLOYEE.asid= SID.sid";
	
	public static final String SELECTEMPLOYEEINFORMATION = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE.empID =?";
	
	public static final String SELECTHISTORYDETAILS = "SELECT date,comment FROM HISTORY,EMPLOYEE ON "
					+ "EMPLOYEE.empID = HISTORY.empID WHERE EMPLOYEE.empID =?";
	
	public static final String SELECTCOMMENTS = "SELECT date,comment,key FROM COMMENT,EMPLOYEE ON "
					+ "EMPLOYEE.empID = COMMENT.empID WHERE EMPLOYEE.empID =?";
	
	public static final String SELECTEMPSKILLS = "SELECT skillName,key FROM SKILLS,EMPLOYEE ON "
					+ "EMPLOYEE.empID = SKILLS.empID WHERE EMPLOYEE.empID =?";
	
	public static final String SELECTTRAINEDSKILLS = "SELECT skillName,key FROM TRAINING,EMPLOYEE ON "
					+ "EMPLOYEE.empID = TRAINING.empID WHERE EMPLOYEE.empID =?";
	
	public static final String SELECTSKILL = "SELECT e.empID, e.name AS EMP_NAME,status,wtr,a.name AS Account,workLocation AS wl,currentLocation AS cl,homeLocation AS hl,e.phone,e.email "+
	"FROM EMPLOYEE e JOIN ACCOUNT JOIN AID a JOIN Skills s JOIN TRAINING t "+
	"ON e.empID = ACCOUNT.empID AND ACCOUNT.aid = a.aid AND e.empID = s.empId AND e.empID = t.empID "+
	"WHERE e.empID=ACCOUNT.empID AND ACCOUNT.aid=a.aid AND e.empID=s.empID AND s.skillName LIKE ? OR t.skillName LIKE ? GROUP BY e.empID";
	
	public static final String SELECTTENURE = "SELECT e.empID, e.name AS EMP_NAME,status,wtr,a.name AS Account,workLocation as wl,"+
	"currentLocation as cl,homeLocation as hl,e.phone,e.email FROM EMPLOYEE e JOIN ACCOUNT JOIN AID a JOIN SID s "+
	"ON e.empID = ACCOUNT.empID AND ACCOUNT.aid = a.aid AND a.aid = s.aid WHERE (SELECT julianday('now')-julianday(tenure)) between ? AND ? GROUP BY e.empID";
	
	public static final String SELECTSKILLKEY ="SELECT key FROM EMPLOYEE e,SKILLS s ON e.empID=s.empID WHERE e.empID = ? AND s.skillName=?";
	
	public static final String SELECTALLVERTICALS = "SELECT * FROM VID";
	
	//DELETE QUERIES- REMOVE FROM DATABASE
	
	public static final String SELECTALLACCOUNTS ="SELECT * FROM AID";
	
	public static final String SELECTALLVERTSPOCS ="SELECT * FROM SID WHERE vid=?";
	
	public static final String SELECTALLACCSPOCS = "SELECT * FROM SID WHERE aid=?";
	
	public static final String SELECTALLACCOUNTSBYVERTICAL="SELECT * FROM AID WHERE AID.vid=?";
	
	//INSERT QUERIES- INSERTING DATA INTO DATABASE
	public static final String INSERTEMPLOYEE = "INSERT INTO EMPLOYEE(empID,name,status,DateofJoining,tenure,workLocation,currentLocation,"+
	"homeLocation,phone,email,wtr)VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	
	public static final String INSERTACCOUNT = "INSERT INTO ACCOUNT(empID,aid) VALUES (?,?)";
	
	public static final String INSERTVERTICAL = "INSERT INTO VERTICAL(empID,vid) VALUES (?,?)";
	
	public static final String INSERTHISTORY = "INSERT INTO HISTORY(empID,comment,date) VALUES (?,?,Date('now'))";

	public static final String INSERTCOMMENT = "INSERT INTO COMMENT(empID,comment,date) VALUES (?,?,Date('now'))";
	
	public static final String INSERTSKILL = "INSERT INTO SKILLS(empID,skillName) VALUES(?,?)";
	
	public static final String INSERTTRAININGSKILL = "INSERT INTO TRAINING(empID,skillName) VALUES(?,?)";
	
	public static final String INSERTNEWEMPHIST = "INSERT INTO HISTORY(empID,comment,date) VALUES(?,Created new Employee,Date('now'))";
	
	public static final String INSERTPREVIOUSEMPLOYEE = "INSERT INTO PreviousEmployee('empID','name','status','DateofJoining','DateofLeaving','phone','email','wtr','workingLocation','homeLocation','vertical','account','rmName','rmPhone','rmEmail','training','reason','reasonComments','comments')VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	//DELETE QUERIES- REMOVE FROM DATABASE

	public static final String DELETECOMMENT = "DELETE FROM Comment WHERE key = ?";
	
	public static final String DELETESKILL = "DELETE FROM Skills WHERE key = ?";
	
	public static final String DELETETRAININGSKILL = "DELETE FROM Training WHERE key = ?";
	
	public static final String DELETEEMPHISTORY = "DELETE FROM History WHERE empID = ?";
	
	public static final String DELETEEMPVERTICAL = "DELETE FROM VERTICAL WHERE empID = ?";
	
	public static final String DELETEEMPACCOUNT = "DELETE FROM Account WHERE empID = ?";
	
	public static final String DELETEEMPCOMMENTS = "DELETE FROM Comment WHERE empID = ?";
	
	public static final String DELETEEMPSKILLS = "DELETE FROM Skills WHERE empID = ?";
	
	public static final String DELETEEMPTRAINING = "DELETE FROM Training WHERE empID = ?";
	
	public static final String DELETEEMPLOYEE = "DELETE FROM Employee WHERE empID = ?";
	
	
	//UPDATE QUERIES- UPDATE FIELDS IN THE DATABASE
	
	public static final String UPDATECOMMENT = "UPDATE COMMENT SET comment =?,date=? WHERE key=?";
	
	public static final String UPDATESKILL = "UPDATE SKILLS SET skillName = ? WHERE key=?";
	
	public static final String UPDATETRAININGSKILL = "UPDATE TRAINING SET skillName = ? WHERE key=?";
	
	public static final String UPDATEMPLOYEEDETAILS ="UPDATE EMPLOYEE SET empID=?,name=?,status=?,DateofJoining=?,tenure=?,"+
	"workLocation=?,currentLocation=?,homeLocation=?,phone=?,email=?,wtr=? WHERE empID=?";
	
	public static final String UPDATEEMPLOYEERM ="UPDATE EMPLOYEE SET reportingManager=?,reportingManagerPhone=?,reportingManagerEmail=? WHERE empID=?";
		
	public static final String UPDATEEMPLOYEEVERTICAL = "UPDATE EMPLOYEE SET vsid=? WHERE empID=?";
	
	public static final String UPDATEVERTICALTABLE = "UPDATE VERTICAL SET vid=? WHERE empID=?";
	
	public static final String UPDATEEMPLOYEEACCOUNT = "UPDATE EMPLOYEE SET asid=? WHERE empID=?";
	
	public static final String UPDATEACCOUNTTABLE="UPDATE ACCOUNT SET aid=? WHERE empID=?";
	
	public static final String UPDATESPOC ="UPDATE EMPLOYEE SET vsid=?,asid=? WHERE empID=?";
	
	public static final String UPDATEBULKSPOC="UPDATE EMPLOYEE SET vsid=? WHERE EMPLOYEE.vsid=?";
	
}