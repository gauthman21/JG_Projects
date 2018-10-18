package main.DAO;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBException;

import org.springframework.stereotype.Service;
import org.sqlite.SQLiteConfig;

import com.syntel.ecmt.onStartup;

import main.constants.Constants;
import main.constants.Queries;
import main.model.*;
import main.utils.PersistCache;

@Service
public class Dao {
	Employee emp = new Employee();
	Profile pro = new Profile();
	private String DBswitch = "off";

	private void closePrepareConnection(Connection con, PreparedStatement ps)
			throws SQLException {
		ps.close();
		con.close();
	}

	public void connectionsClose(Connection conn, PreparedStatement ps,
			ResultSet rs) throws SQLException {
		rs.close();
		closePrepareConnection(conn, ps);
	}

	private void connectionsClose(Connection conn, Statement stmt, ResultSet rs)
			throws SQLException {
		rs.close();
		stmt.close();
		conn.close();
	}

	public boolean checkDatabaseCheckbox(String checkboxVal) {

		System.out.println("<p>testing 1 - 2 - 3</p>");
		return checkboxVal.equals("true");
	}

	public boolean deleteComment(String key) throws JAXBException {

		Connection con;
		PreparedStatement ps;
		boolean bool = true;

		try {

			if (DBswitch != "off") {

				con = getConnection();

				con.setAutoCommit(false);

				ps = con.prepareStatement(Queries.DELETECOMMENT);
				ps.setString(1, key);

				ps.executeUpdate();
				con.commit();

				closePrepareConnection(con, ps);

			} else {

			}

		} catch (ClassNotFoundException e1) {
			System.out.println("ClassNotFoundException");
			e1.printStackTrace();
			bool = false;
		} catch (SQLException ex) {
			System.out.println("SQLException");
			ex.printStackTrace();
			bool = false;
		}
		return bool;
	}

	public boolean deleteSkill(String key, String type) {
		Connection con;
		PreparedStatement ps;
		boolean result = true;

		try {
			con = getConnection();
			con.setAutoCommit(false);

			if (type.equals("skill"))
				ps = con.prepareStatement(Queries.DELETESKILL);
			else
				ps = con.prepareStatement(Queries.DELETETRAININGSKILL);

			ps.setString(1, key);

			ps.executeUpdate();
			con.commit();

			closePrepareConnection(con, ps);

		} catch (ClassNotFoundException e1) {
			System.out.println("ClassNotFoundException");
			e1.printStackTrace();
			result = false;
		} catch (SQLException ex) {
			System.out.println("SQLException");
			ex.printStackTrace();
			result = false;
		}
		return result;
	}

	public ArrayList<DropdownObj> getAccounts(String id) {
		ArrayList<DropdownObj> acc = new ArrayList<DropdownObj>();

		Connection conn;
		ResultSet rs;
		Statement stmt = null;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);

			acc.add(new DropdownObj("0", "Not Assigned"));

			if (!id.equals("0")) {

				stmt = conn.createStatement();
				rs = stmt.executeQuery("SELECT * FROM AID WHERE AID.vid=" + id);

				while (rs.next()) {
					DropdownObj obj = new DropdownObj();
					obj.setId(rs.getString("aid"));
					obj.setName(rs.getString("Name"));

					acc.add(obj);
				}
				connectionsClose(conn, stmt, rs);
			}

		} catch (ClassNotFoundException e1) {
			System.out.println("ClassNotFoundException");
			e1.printStackTrace();
		} catch (SQLException ex) {
			System.out.println("SQLException");
			ex.printStackTrace();
		}

		return acc;
	}

	public ArrayList<Spoc> getAccountSPOC(String id) {
		ArrayList<Spoc> result = new ArrayList<Spoc>();

		Connection conn;
		ResultSet rs;
		Statement stmt = null;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);

			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM SID WHERE aid=" + id);

			while (rs.next()) {
				Spoc spoc = new Spoc();

				spoc.setId(rs.getString("sid"));
				spoc.setName(rs.getString("name"));
				spoc.setPhone(rs.getString("phone"));
				spoc.setEmail(rs.getString("email"));

				result.add(spoc);
			}

			connectionsClose(conn, stmt, rs);

		} catch (ClassNotFoundException e1) {
			System.out.println("ClassNotFoundException");
			e1.printStackTrace();
		} catch (SQLException ex) {
			System.out.println("SQLException");
			ex.printStackTrace();
		}

		return result;
	}

	public ArrayList<Employee> getAllEmployees() {
		Connection conn;
		Statement stmt;
		String dbURL;

		ArrayList<Employee> employees = new ArrayList<Employee>();

		try {

			if (DBswitch != "off") {

				Class.forName("org.sqlite.JDBC");
				dbURL = Constants.DBURL;
				conn = DriverManager.getConnection(dbURL);
				conn.setAutoCommit(false);

				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(Queries.SELECTALLEMPLOYEES);

				while (rs.next())
					setEmpDetails(employees, rs);

				connectionsClose(conn, stmt, rs);

			} else {

				ArrayList<Profile> allProfilesFromCacheList = PersistCache
						.readFullCache(Constants.cacheFilePath);

				for (int cacheProfileSize = 0; cacheProfileSize < allProfilesFromCacheList
						.size(); cacheProfileSize++) {

					pro = (Profile) allProfilesFromCacheList
							.get(cacheProfileSize);
					pro.setEmployee(pro.getEmployee());
					employees.add(pro.getEmployee());
				}
				return employees;
			}
		} catch (ClassNotFoundException e1) {
			System.out.println("ClassNotFoundException");
			e1.printStackTrace();
		} catch (SQLException ex) {
			System.out.println("SQLException");
			ex.printStackTrace();
		}
		return employees;
	}

	public static Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Connection con = null;
		Class.forName("org.sqlite.JDBC");
		String dbURL = Constants.DBURL;
		try {
			SQLiteConfig config = new SQLiteConfig();
			config.enforceForeignKeys(true);
			con = DriverManager.getConnection(dbURL, config.toProperties());
			con.setAutoCommit(false);

		} catch (SQLException ex) {
			System.out.println(ex);
		}
		// con.createStatement().execute("PRAGMA foreign_keys = ON");
		return con;
	}

	public Profile getEmployee(String empId) {
		Connection conn;
		PreparedStatement ps;

		Account account = new Account();
		Comments comments = new Comments();
		Employee employee = new Employee();
		EmpSkills empSkills = new EmpSkills();
		History history = new History();
		TrainedEmpSkills trainedEmpSkills = new TrainedEmpSkills();
		Vertical vertical = new Vertical();
		Profile profile = new Profile();
		RM rm = new RM();

		try {
			if (DBswitch != "off") {

				conn = getConnection();

				conn.setAutoCommit(false);
				ResultSet rs;

				// fetching Vertical info
				ps = conn.prepareStatement(Queries.SELECTVERTICALINFORMATION);
				ps.setString(1, empId);
				rs = ps.executeQuery();

				if (rs.next()) {
					vertical.getVertical().setName(rs.getString("VERTICAL"));
					vertical.getSpoc().setName(rs.getString("VERT_SPOC_NAME"));
					vertical.getSpoc()
							.setPhone(rs.getString("VERT_SPOC_PHONE"));
					vertical.getSpoc()
							.setEmail(rs.getString("VERT_SPOC_EMAIL"));
					vertical.getVertical().setId(rs.getString("ID"));
				}

				// fetching Account info
				ps = conn.prepareStatement(Queries.SELECTACCOUNTINFORMATION);
				ps.setString(1, empId);
				rs = ps.executeQuery();

				if (rs.next()) {
					account.getAccount().setName(rs.getString("ACCOUNT"));
					account.getSpoc().setName(rs.getString("ACC_SPOC_NAME"));
					account.getSpoc().setPhone(rs.getString("ACC_SPOC_PHONE"));
					account.getSpoc().setEmail(rs.getString("ACC_SPOC_EMAIL"));
					account.getAccount().setId(rs.getString("ID"));
				}

				// fetching Employee info
				ps = conn.prepareStatement(Queries.SELECTEMPLOYEEINFORMATION);
				ps.setString(1, empId);
				rs = ps.executeQuery();

				if (rs.next()) {
					employee.setEmpId(rs.getString("empID"));
					employee.setName(rs.getString("name"));
					employee.setStatus(rs.getString("status"));
					employee.setPhone(rs.getString("phone"));
					employee.setDoj(rs.getString("DateofJoining"));
					employee.setCl(rs.getString("currentLocation"));
					employee.setHl(rs.getString("homeLocation"));
					employee.setWl(rs.getString("workLocation"));
					employee.setEmail(rs.getString("email"));
					employee.setWtr(rs.getString("wtr"));
					employee.setTenure(rs.getString("tenure"));
					rm.setRm(rs.getString("reportingManager"));
					rm.setPhone(rs.getString("reportingManagerPhone"));
					rm.setEmail(rs.getString("reportingManagerEmail"));
					vertical.getSpoc().setId(rs.getString("vsid"));
					account.getSpoc().setId(rs.getString("asid"));
				}

				getHistory(empId, conn, ps, rs, history);

				// fetching Comments info
				ps = conn.prepareStatement(Queries.SELECTCOMMENTS);
				ps.setString(1, empId);
				rs = ps.executeQuery();

				while (rs.next()) {
					Comment cmt = new Comment();

					cmt.setComment(rs.getString("comment"));
					cmt.setDate(rs.getString("date"));
					cmt.setCommentId(rs.getString("key"));

					comments.addComment(cmt);
				}

				// fetching EmpSkills info
				ps = conn.prepareStatement(Queries.SELECTEMPSKILLS);
				ps.setString(1, empId);
				rs = ps.executeQuery();
				ArrayList<Skill> skills = new ArrayList<Skill>();

				while (rs.next()) {
					Skill skill = new Skill();
					skill.setKey(rs.getString("key"));
					skill.setSkillName(rs.getString("skillName"));

					skills.add(skill);
				}
				empSkills.setSkills(skills);
				empSkills.setEmpId(empId);

				// setting TrainedEmpSkills info
				ps = conn.prepareStatement(Queries.SELECTTRAINEDSKILLS);
				ps.setString(1, empId);
				rs = ps.executeQuery();
				ArrayList<Skill> trainedSkills = new ArrayList<Skill>();

				while (rs.next()) {
					Skill skill = new Skill();
					skill.setKey(rs.getString("key"));
					skill.setSkillName(rs.getString("skillName"));

					trainedSkills.add(skill);
				}
				trainedEmpSkills.setSkills(trainedSkills);
				trainedEmpSkills.setEmpId(empId);

				profile.setAccount(account);
				profile.setVertical(vertical);
				profile.setEmployee(employee);
				profile.setHistory(history);
				profile.setComments(comments);
				profile.setSkills(empSkills);
				profile.setTrainings(trainedEmpSkills);
				profile.setRm(rm);

				connectionsClose(conn, ps, rs);

			} else {

				Profile getSpecificProfile = PersistCache.getProfile(empId,
						Constants.cacheFilePath);

				if (getSpecificProfile != null) {
					System.out.println("Profile from cache is: "
							+ getSpecificProfile.toString());
				}

				profile.setAccount(getSpecificProfile.getAccount());
				profile.setVertical(getSpecificProfile.getVertical());
				profile.setEmployee(getSpecificProfile.getEmployee());
				profile.setHistory(getSpecificProfile.getHistory());
				profile.setComments(getSpecificProfile.getComments());
				profile.setSkills(getSpecificProfile.getSkills());
				profile.setTrainings(getSpecificProfile.getTrainings());
				profile.setRm(getSpecificProfile.getRm());

				return profile;
			}

		} catch (ClassNotFoundException e1) {
			System.out.println("ClassNotFoundException");
			e1.printStackTrace();
		} catch (SQLException ex) {
			System.out.println("SQLException");
			ex.printStackTrace();
		}

		return profile;
	}

	public void getHistory(String empId, Connection conn, PreparedStatement ps,
			ResultSet rs, History history) throws SQLException {
		// fetching History info
		ps = conn.prepareStatement(Queries.SELECTHISTORYDETAILS);
		ps.setString(1, empId);
		rs = ps.executeQuery();

		while (rs.next()) {
			Comment cmt = new Comment();

			cmt.setComment(rs.getString("comment"));
			cmt.setDate(rs.getString("date"));

			history.addComment(cmt);
		}
	}

	public ArrayList<Employee> getEmployeesBySkill(String skill) {
		Connection conn;
		PreparedStatement ps;

		ArrayList<Employee> employees = new ArrayList<Employee>();
		skill = "%" + skill + "%";

		try {
			if (DBswitch != "off") {

				conn = getConnection();
				conn.setAutoCommit(false);

				ps = conn.prepareStatement(Queries.SELECTSKILL);
				ps.setString(1, skill);
				ps.setString(2, skill);
				ResultSet rs = ps.executeQuery();

				while (rs.next())
					setEmpDetails(employees, rs);

				connectionsClose(conn, ps, rs);

			} else {

				skill = skill.replace("%", "");
				skill = skill.substring(0, 1).toUpperCase()
						+ skill.substring(1);
				Profile getSpecificProfile;

				getSpecificProfile = PersistCache.getProfile(skill,
						Constants.cacheFilePath);

				pro.setEmployee(getSpecificProfile.getEmployee());
				pro.setSkills(getSpecificProfile.getSkills());

				if (getSpecificProfile.getSkills().getSkills().toString()
						.contains(skill)) {

					employees.add(pro.getEmployee());
				}
				return employees;
			}

		} catch (ClassNotFoundException e1) {
			System.out.println("ClassNotFoundException");
			e1.printStackTrace();
		} catch (SQLException ex) {
			System.out.println("SQLException");
			ex.printStackTrace();
		}
		return employees;
	}

	public ArrayList<Employee> getEmployeesByTenure(String start, String end)
			throws ParseException {
		Connection conn;
		PreparedStatement ps;

		ArrayList<Employee> employees = new ArrayList<Employee>();

		try {

			if (DBswitch != "off") {

				conn = getConnection();
				conn.setAutoCommit(false);

				ps = conn.prepareStatement(Queries.SELECTTENURE);
				ps.setInt(1, Integer.valueOf(start));
				ps.setInt(2, Integer.valueOf(end));
				ResultSet rs = ps.executeQuery();

				while (rs.next())
					setEmpDetails(employees, rs);

				connectionsClose(conn, ps, rs);

			} else {

				Profile empProfile;

				Calendar cal1 = new GregorianCalendar();
				Calendar cal2 = new GregorianCalendar();
				SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
				DateFormat dateFormat = myFormat;

				int days;
				int intStart = Integer.parseInt(start);
				int intEnd = Integer.parseInt(end);

				Date date = new Date();
				Date st;
				Date e = myFormat.parse(dateFormat.format(date));

				cal2.setTime(e);

				ArrayList<Profile> allProfilesFromCacheList = PersistCache
						.readFullCache(Constants.cacheFilePath);

				for (int i = 0; i < allProfilesFromCacheList.size(); i++) {

					empProfile = (Profile) allProfilesFromCacheList.get(i);
					pro.setEmployee(empProfile.getEmployee());
					st = myFormat.parse(pro.getEmployee().getTenure());
					cal1.setTime(st);
					days = daysBetween(cal1.getTime(), cal2.getTime());
					System.out.println("Days= "
							+ daysBetween(cal1.getTime(), cal2.getTime()));

					if (intStart < days && intEnd >= days) {

						employees.add(pro.getEmployee());
					}
				}
			}

		} catch (ClassNotFoundException e1) {
			System.out.println("ClassNotFoundException");
			e1.printStackTrace();
		} catch (SQLException ex) {
			System.out.println("SQLException");
			ex.printStackTrace();
		}
		return employees;
	}

	public int daysBetween(Date d1, Date d2) {

		return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	}

	public ArrayList<Employee> getStatus(String option) {
		String status = "%" + option + "%";
		String training = "%training%";
		String si = "%si%";
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		ArrayList<Employee> employees = new ArrayList<Employee>();

		try {

			if (DBswitch != "off") {

				conn = getConnection();
				conn.setAutoCommit(false);

				if (option.equalsIgnoreCase("bench")) {
					ps = conn.prepareStatement(Queries.SELECTSTATUSEXTRATYPE);
					ps.setString(1, status);
					ps.setString(2, training);
					rs = ps.executeQuery();
					while (rs.next()) {
						setEmpDetails(employees, rs);
					}
					connectionsClose(conn, ps, rs);
				} else if (option.equalsIgnoreCase("buffer")) {
					ps = conn.prepareStatement(Queries.SELECTSTATUSEXTRATYPE);
					ps.setString(1, status);
					ps.setString(2, si);
					rs = ps.executeQuery();
					while (rs.next()) {
						setEmpDetails(employees, rs);
					}
					connectionsClose(conn, ps, rs);
				} else if (option.equalsIgnoreCase("non-billable")) {
					String bill = "%billable%";
					ps = conn.prepareStatement(Queries.SELECTNONBILLABLESTATUS);
					ps.setString(1, bill);
					rs = ps.executeQuery();
					while (rs.next()) {
						setEmpDetails(employees, rs);
					}
					connectionsClose(conn, ps, rs);
				} else {
					ps = conn.prepareStatement(Queries.SELECTSTATUSTYPE);
					ps.setString(1, status);
					rs = ps.executeQuery();
					while (rs.next()) {
						setEmpDetails(employees, rs);
					}
					connectionsClose(conn, ps, rs);
				}
			} else {

				Profile getSpecificProfile;

				getSpecificProfile = PersistCache.getProfile(option,
						Constants.cacheFilePath);

				pro.setEmployee(getSpecificProfile.getEmployee());

				if (option.equalsIgnoreCase("bench")
						&& emp.getStatus() == option) {

					employees.add(pro.getEmployee());
				} else if (option.equalsIgnoreCase("buffer")
						&& emp.getStatus() == option) {

					employees.add(pro.getEmployee());
				} else if (option.equalsIgnoreCase("non-billable")
						&& emp.getStatus() == option) {

					employees.add(pro.getEmployee());
				} else {

					employees.add(pro.getEmployee());
				}
			}

		} catch (ClassNotFoundException e1) {
			System.out.println("ClassNotFoundException");
			e1.printStackTrace();
		} catch (SQLException ex) {
			System.out.println("SQLException");
			ex.printStackTrace();
		}
		return employees;
	}

	public TotalVertAcc getVerticalAccount() {
		TotalVertAcc result = new TotalVertAcc();

		Connection conn;
		PreparedStatement ps;
		ResultSet rs;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(Queries.SELECTALLVERTICALS);
			rs = ps.executeQuery();

			while (rs.next()) {
				DropdownObj vert = new DropdownObj();

				vert.setId(rs.getString("vid"));
				vert.setName(rs.getString("Name"));

				result.addVertDetails(vert);
			}

			ps = conn.prepareStatement(Queries.SELECTALLACCOUNTS);
			rs = ps.executeQuery();

			while (rs.next()) {
				DropdownObj acc = new DropdownObj();

				acc.setId(rs.getString("aid"));
				acc.setName(rs.getString("Name"));

				result.addAccDetails(acc);
			}

			connectionsClose(conn, ps, rs);

		} catch (ClassNotFoundException e1) {
			System.out.println("ClassNotFoundException");
			e1.printStackTrace();
		} catch (SQLException ex) {
			System.out.println("SQLException");
			ex.printStackTrace();
		}
		return result;
	}

	public ArrayList<Spoc> getVerticalSPOC(String id) {
		ArrayList<Spoc> result = new ArrayList<Spoc>();

		Connection conn;
		ResultSet rs;
		Statement stmt = null;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);

			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM SID WHERE vid=" + id);

			while (rs.next()) {
				Spoc spoc = new Spoc();

				spoc.setId(rs.getString("sid"));
				spoc.setName(rs.getString("name"));
				spoc.setPhone(rs.getString("phone"));
				spoc.setEmail(rs.getString("email"));

				result.add(spoc);
			}

			connectionsClose(conn, stmt, rs);

		} catch (ClassNotFoundException e1) {
			System.out.println("ClassNotFoundException");
			e1.printStackTrace();
		} catch (SQLException ex) {
			System.out.println("SQLException");
			ex.printStackTrace();
		}
		return result;
	}

	public Comment insertComment(Comment comment) {
		Connection conn;
		PreparedStatement ps;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(Queries.INSERTCOMMENT);
			ps.setString(1, comment.getEmpId());
			ps.setString(2, comment.getComment());

			ps.executeUpdate();
			conn.commit();

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date cal = new Date();
			String date = dateFormat.format(cal);
			comment.setDate(date);

			closePrepareConnection(conn, ps);
		} catch (ClassNotFoundException e1) {
			System.out.println("ClassNotFoundException");
			e1.printStackTrace();
		} catch (SQLException ex) {
			System.out.println("SQLException");
			ex.printStackTrace();
		}
		return comment;
	}

	public boolean insertEmployee(Employee employee) {
		Connection con;
		PreparedStatement ps;
		scrubDate(employee.getTenure(), employee);
		scrubDOJDate(employee.getDoj(), employee);
		boolean bool = true;
		try {
			// INSERT INTO
			// EMPLOYEE(empID,name,status,DateofJoining,tenure,workLocation,currentLocation,homeLocation,
			// phone,email,wtr)VALUES(?,?,?,?,?,?,?,?,?,?,?)

			if(DBswitch != "off"){
			
			con = getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(Queries.INSERTEMPLOYEE);
			ps.setString(1, employee.getEmpId());
			ps.setString(2, employee.getName());
			ps.setString(3, employee.getStatus());
			ps.setString(4, employee.getDoj());
			ps.setString(5, employee.getTenure());
			ps.setString(6, employee.getWl());
			ps.setString(7, employee.getCl());
			ps.setString(8, employee.getHl());
			ps.setString(9, employee.getPhone());
			ps.setString(10, employee.getEmail());
			ps.setString(11, employee.getWtr());
			ps.executeUpdate();
			closePrepareConnection(con, ps);
			
			} else {
				
				
			}

		} catch (ClassNotFoundException e1) {
			System.out.println("ClassNotFoundException");
			e1.printStackTrace();
			bool = false;
		} catch (SQLException ex) {
			System.out.println("SQLException");
			ex.printStackTrace();
			bool = false;
		}
		return bool;
	}

	public boolean insertEmpVertical(Vertical vertical, String id) {
		Connection con;
		PreparedStatement ps;
		boolean bool = true;
		try {
			con = getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(Queries.INSERTVERTICAL);
			ps.setString(1, id);
			ps.setString(2, vertical.getVertical().getId());
			ps.executeUpdate();

			ps = con.prepareStatement(Queries.UPDATEEMPLOYEEVERTICAL);
			ps.setString(1, vertical.getSpoc().getId());
			ps.setString(2, id);
			ps.executeUpdate();
			closePrepareConnection(con, ps);

		} catch (ClassNotFoundException e1) {
			System.out.println("ClassNotFoundException");
			e1.printStackTrace();
			bool = false;
		} catch (SQLException ex) {
			System.out.println("SQLException");
			ex.printStackTrace();
			bool = false;
		}
		return bool;
	}

	public boolean insertEmpAccount(Account account, String id) {
		Connection con;
		PreparedStatement ps;
		boolean bool = true;
		try {
			con = getConnection();
			con.setAutoCommit(false);

			ps = con.prepareStatement(Queries.INSERTACCOUNT);
			ps.setString(1, id);
			ps.setString(2, account.getAccount().getId());
			ps.executeUpdate();

			ps = con.prepareStatement(Queries.UPDATEEMPLOYEEACCOUNT);
			ps.setString(1, account.getSpoc().getId());
			ps.setString(2, id);
			ps.executeUpdate();
			closePrepareConnection(con, ps);

		} catch (ClassNotFoundException e1) {
			System.out.println("ClassNotFoundException");
			e1.printStackTrace();
			bool = false;
		} catch (SQLException ex) {
			System.out.println("SQLException");
			ex.printStackTrace();
			bool = false;
		}
		return bool;
	}

	public boolean insertHistory(String id, String changes) {
		Connection con;
		PreparedStatement ps;
		boolean bool = true;
		try {
			con = getConnection();
			con.setAutoCommit(false);

			ps = con.prepareStatement(Queries.INSERTHISTORY);
			ps.setString(1, id);
			ps.setString(2, changes);
			ps.executeUpdate();
			closePrepareConnection(con, ps);

		} catch (ClassNotFoundException e1) {
			System.out.println("ClassNotFoundException");
			e1.printStackTrace();
			bool = false;
		} catch (SQLException ex) {
			System.out.println("SQLException");
			ex.printStackTrace();
			bool = false;
		}
		return bool;
	}

	public Skill insertSkill(Skill skill, String type) {
		Connection con;
		PreparedStatement ps;

		try {
			con = getConnection();
			con.setAutoCommit(false);

			if (type.equals("skill"))
				ps = con.prepareStatement(Queries.INSERTSKILL);
			else
				ps = con.prepareStatement(Queries.INSERTTRAININGSKILL);

			ps.setString(1, skill.getEmpId());
			ps.setString(2, skill.getSkillName());

			ps.executeUpdate();
			con.commit();

			closePrepareConnection(con, ps);

		} catch (ClassNotFoundException e1) {
			System.out.println("ClassNotFoundException");
			e1.printStackTrace();
		} catch (SQLException ex) {
			System.out.println("SQLException");
			ex.printStackTrace();
		}
		return skill;
	}

	public boolean updateAccount(Account account, String empId) {
		Connection conn;
		PreparedStatement ps;
		boolean result = true;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(Queries.UPDATEACCOUNTTABLE);
			ps.setString(1, account.getAccount().getId());
			ps.setString(2, empId);

			ps.executeUpdate();

			ps = conn.prepareStatement(Queries.UPDATEEMPLOYEEACCOUNT);
			ps.setString(1, account.getSpoc().getId());
			ps.setString(2, empId);

			ps.executeUpdate();
			conn.commit();

			closePrepareConnection(conn, ps);

		} catch (ClassNotFoundException e1) {
			System.out.println("ClassNotFoundException");
			e1.printStackTrace();
			result = false;
		} catch (SQLException ex) {
			System.out.println("SQLException");
			ex.printStackTrace();
			result = false;
		}
		return result;
	}

	public boolean updateComment(Comment comment) {
		Connection conn;
		PreparedStatement ps;
		boolean bool = true;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			String newDateString = null;
			try {
				date = dateFormat.parse(comment.getDate());
				newDateString = dateFormat.format(date);
				comment.setDate(newDateString);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			ps = conn.prepareStatement(Queries.UPDATECOMMENT);
			ps.setString(1, comment.getComment());
			ps.setString(2, comment.getDate());
			ps.setString(3, comment.getCommentId());

			ps.executeUpdate();
			conn.commit();

			closePrepareConnection(conn, ps);

		} catch (ClassNotFoundException e1) {
			System.out.println("ClassNotFoundException");
			e1.printStackTrace();
			bool = false;
		} catch (SQLException ex) {
			System.out.println("SQLException");
			ex.printStackTrace();
			bool = false;
		}
		return bool;
	}

	public boolean updateEmployee(Employee employee) throws JAXBException {
		Connection conn;
		PreparedStatement ps;
		boolean result = true;

		try {

			if (DBswitch != "off") {

				conn = getConnection();
				conn.setAutoCommit(false);

				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = null;
				String newDateString = null;
				try {
					date = dateFormat.parse(employee.getDoj());
					newDateString = dateFormat.format(date);
					employee.setDoj(newDateString);

					date = dateFormat.parse(employee.getTenure());
					newDateString = dateFormat.format(date);
					employee.setTenure(newDateString);
				} catch (ParseException e) {
					e.printStackTrace();
				}

				ps = conn.prepareStatement(Queries.UPDATEMPLOYEEDETAILS);
				ps.setString(1, employee.getEmpId());
				ps.setString(2, employee.getName());
				ps.setString(3, employee.getStatus());
				ps.setString(4, employee.getDoj());
				ps.setString(5, employee.getTenure());
				ps.setString(6, employee.getWl());
				ps.setString(7, employee.getCl());
				ps.setString(8, employee.getHl());
				ps.setString(9, employee.getPhone());
				ps.setString(10, employee.getEmail());
				ps.setString(11, employee.getWtr());
				ps.setString(12, employee.getEmpId());

				ps.executeUpdate();
				conn.commit();

				closePrepareConnection(conn, ps);

			} else {

				Profile getSpecificProfile = PersistCache.getProfile(pro
						.getEmployee().getEmpId(), Constants.cacheFilePath);

				getSpecificProfile.setEmployee(pro.getEmployee());

				Boolean cacheUpdateProfileInCache = PersistCache
						.updateCacheFileWithSpecificProfile(
								Constants.cacheFilePath, getSpecificProfile,
								pro.getEmployee().getEmpId());

				return cacheUpdateProfileInCache;
			}

		} catch (ClassNotFoundException e1) {
			System.out.println("ClassNotFoundException");
			e1.printStackTrace();
			result = false;
		} catch (SQLException ex) {
			System.out.println("SQLException");
			ex.printStackTrace();
			result = false;
		}
		return result;
	}

	public boolean updateRM(RM rm, String empId) {
		Connection conn;
		PreparedStatement ps;
		boolean result = true;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(Queries.UPDATEEMPLOYEERM);
			ps.setString(1, rm.getRm());
			ps.setString(2, rm.getPhone());
			ps.setString(3, rm.getEmail());
			ps.setString(4, empId);

			ps.executeUpdate();
			conn.commit();

			closePrepareConnection(conn, ps);

		} catch (ClassNotFoundException e1) {
			System.out.println("ClassNotFoundException");
			e1.printStackTrace();
			result = false;
		} catch (SQLException ex) {
			System.out.println("SQLException");
			ex.printStackTrace();
			result = false;
		}
		return result;
	}

	public boolean updateSkill(Skill skill, String type) {
		Connection con;
		PreparedStatement ps;
		boolean result = true;

		try {
			con = getConnection();
			con.setAutoCommit(false);

			if (type.equals("skill"))
				ps = con.prepareStatement(Queries.UPDATESKILL);
			else
				ps = con.prepareStatement(Queries.UPDATETRAININGSKILL);

			ps.setString(1, skill.getSkillName());
			ps.setString(2, skill.getKey());

			ps.executeUpdate();
			con.commit();

			closePrepareConnection(con, ps);

		} catch (ClassNotFoundException e1) {
			System.out.println("ClassNotFoundException");
			e1.printStackTrace();
			result = false;
		} catch (SQLException ex) {
			System.out.println("SQLException");
			ex.printStackTrace();
			result = false;
		}
		return result;
	}

	public boolean updateVertical(Vertical vertical, String empId) {
		Connection conn;
		PreparedStatement ps;
		Statement stmt;
		boolean result = true;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);

			stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE VERTICAL SET vid="
					+ vertical.getVertical().getId() + " WHERE empID=" + empId);

			ps = conn.prepareStatement(Queries.UPDATEEMPLOYEEVERTICAL);
			ps.setString(1, vertical.getSpoc().getId());
			ps.setString(2, empId);

			ps.executeUpdate();

			closePrepareConnection(conn, ps);

		} catch (ClassNotFoundException e1) {
			System.out.println("ClassNotFoundException");
			e1.printStackTrace();
			result = false;
		} catch (SQLException ex) {
			System.out.println("SQLException");
			ex.printStackTrace();
			result = false;
		}
		return result;
	}

	private void fetchOldAccDetails(String id, Account acc) {
		Connection conn;
		PreparedStatement ps;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);

			ResultSet rs;

			// fetching Vertical info
			ps = conn.prepareStatement(Queries.SELECTACCOUNTINFORMATION);
			ps.setString(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				acc.getAccount().setName(rs.getString("ACCOUNT"));
				acc.getSpoc().setName(rs.getString("ACC_SPOC_NAME"));
				acc.getSpoc().setPhone(rs.getString("ACC_SPOC_PHONE"));
				acc.getSpoc().setEmail(rs.getString("ACC_SPOC_EMAIL"));
				acc.getAccount().setId(rs.getString("ID"));
			}

			connectionsClose(conn, ps, rs);

		} catch (ClassNotFoundException e1) {
			System.out.println("ClassNotFoundException");
			e1.printStackTrace();
		} catch (SQLException ex) {
			System.out.println("SQLException");
			ex.printStackTrace();
		}

	}

	private void fetchOldEmpDetails(String id, Employee emp) {
		Connection conn;
		PreparedStatement ps;
		try {
			if (DBswitch != "off") {

				conn = getConnection();
				conn.setAutoCommit(false);

				ResultSet rs;
				// fetching Employee info
				ps = conn.prepareStatement(Queries.SELECTEMPLOYEEINFORMATION);
				ps.setString(1, id);
				rs = ps.executeQuery();

				emp.setEmpId(rs.getString("empID"));
				emp.setName(rs.getString("name"));
				emp.setStatus(rs.getString("status"));
				emp.setPhone(rs.getString("phone"));
				emp.setDoj(rs.getString("DateofJoining"));
				emp.setCl(rs.getString("currentLocation"));
				emp.setHl(rs.getString("homeLocation"));
				emp.setWl(rs.getString("workLocation"));
				emp.setEmail(rs.getString("email"));
				emp.setWtr(rs.getString("wtr"));
				emp.setTenure(rs.getString("tenure"));

				rs.close();
				ps.close();
				conn.close();

			} else {

				Profile getSpecificProfile = PersistCache.getProfile(
						emp.getEmpId(), Constants.cacheFilePath);

				pro.setEmployee(getSpecificProfile.getEmployee());
			}

		} catch (ClassNotFoundException e1) {
			System.out.println("ClassNotFoundException");
			e1.printStackTrace();
		} catch (SQLException ex) {
			System.out.println("SQLException");
			ex.printStackTrace();
		}
	}

	private void fetchOldVertDetails(String id, Vertical vert) {
		Connection conn;
		PreparedStatement ps;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);

			ResultSet rs;

			// fetching Vertical info
			ps = conn.prepareStatement(Queries.SELECTVERTICALINFORMATION);
			ps.setString(1, id);
			rs = ps.executeQuery();

			vert.getVertical().setName(rs.getString("VERTICAL"));
			vert.getSpoc().setName(rs.getString("VERT_SPOC_NAME"));
			vert.getSpoc().setPhone(rs.getString("VERT_SPOC_PHONE"));
			vert.getSpoc().setEmail(rs.getString("VERT_SPOC_EMAIL"));
			vert.getVertical().setId(rs.getString("ID"));

			connectionsClose(conn, ps, rs);

		} catch (ClassNotFoundException e1) {
			System.out.println("ClassNotFoundException");
			e1.printStackTrace();
		} catch (SQLException ex) {
			System.out.println("SQLException");
			ex.printStackTrace();
		}
	}

	private void fetchOldRmDetails(String id, RM rm) {
		Connection conn;
		PreparedStatement ps;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);

			ResultSet rs;

			// fetching Vertical info
			ps = conn.prepareStatement(Queries.SELECTEMPLOYEEINFORMATION);
			ps.setString(1, id);
			rs = ps.executeQuery();

			rm.setRm(rs.getString("reportingManager"));
			rm.setPhone(rs.getString("reportingManagerPhone"));
			rm.setEmail(rs.getString("reportingManagerEmail"));

			connectionsClose(conn, ps, rs);

		} catch (ClassNotFoundException e1) {
			System.out.println("ClassNotFoundException");
			e1.printStackTrace();
		} catch (SQLException ex) {
			System.out.println("SQLException");
			ex.printStackTrace();
		}

	}

	public String checkRMHistory(String id, RM rm) {
		String changes = "";
		ArrayList<String> listOfChanges = new ArrayList<String>();
		RM oldRm = new RM();
		fetchOldRmDetails(id, oldRm);
		if (oldRm.getRm() != null) {
			if (!oldRm.getRm().equalsIgnoreCase(rm.getRm()))
				listOfChanges.add("RM name(" + oldRm.getRm() + " to "
						+ rm.getRm() + ")");
			if (oldRm.getPhone() == null
					|| !oldRm.getPhone().equalsIgnoreCase(rm.getPhone()))
				listOfChanges.add("RM phone(" + oldRm.getPhone() + " to "
						+ rm.getPhone() + ")");
		}
		changes += listOfChanges.toString().replace("[", "").replace("]", "");
		return changes;
	}

	public String checkEmpHistory(Employee employee) {
		String changes = "";
		scrubDate(employee.getTenure(), employee);
		scrubDOJDate(employee.getDoj(), employee);
		ArrayList<String> listOfChanges = new ArrayList<String>();
		Employee emp = new Employee();
		fetchOldEmpDetails(employee.getEmpId(), emp);

		if (!employee.getEmpId().equalsIgnoreCase(emp.getEmpId()))
			listOfChanges.add("Employee ID");
		if (!employee.getName().equalsIgnoreCase(emp.getName()))
			listOfChanges.add("Name(" + emp.getName() + " to "
					+ employee.getName() + ")");
		if (!employee.getStatus().equalsIgnoreCase(emp.getStatus()))
			listOfChanges.add("Status(" + emp.getStatus() + " to "
					+ employee.getStatus() + ")");
		if (!employee.getTenure().equalsIgnoreCase(emp.getTenure()))
			listOfChanges.add("Tenure");
		if (!employee.getDoj().equalsIgnoreCase(emp.getDoj()))
			listOfChanges.add("Date of Joining");
		if (!employee.getPhone().equalsIgnoreCase(emp.getPhone()))
			listOfChanges.add("Phone");
		if (!employee.getEmail().equalsIgnoreCase(emp.getEmail()))
			listOfChanges.add("E-mail");
		if (!employee.getWtr().equalsIgnoreCase(emp.getWtr()))
			listOfChanges.add("Willing to Relocate(" + emp.getWtr() + " to "
					+ employee.getWtr() + ")");
		if (!employee.getWl().equalsIgnoreCase(emp.getWl()))
			listOfChanges.add("Work Location");
		if (!employee.getCl().equalsIgnoreCase(emp.getCl()))
			listOfChanges.add("Current Location");
		if (!employee.getHl().equalsIgnoreCase(emp.getHl()))
			listOfChanges.add("Home Location");
		changes += listOfChanges.toString().replace("[", "").replace("]", "");
		return changes;
	}

	public String checkEmpVerticalHistory(String emp, Vertical vertical) {
		String changes = "";
		ArrayList<String> listOfChanges = new ArrayList<String>();
		Vertical vert = new Vertical();

		fetchOldVertDetails(emp, vert);
		if (vert.getVertical().getName() == null
				|| !vert.getVertical().getName()
						.equalsIgnoreCase(vertical.getVertical().getName()))
			listOfChanges.add("Vertical(" + vert.getVertical().getName()
					+ " to " + vertical.getVertical().getName() + ")");
		if (vert.getSpoc().getName() == null
				|| !vert.getSpoc().getName()
						.equalsIgnoreCase(vertical.getSpoc().getName()))
			listOfChanges.add("Vertical SPOC(" + vert.getSpoc().getName()
					+ " to " + vertical.getSpoc().getName() + ")");
		changes += listOfChanges.toString().replace("[", "").replace("]", "");
		return changes;
	}

	public String checkEmpAccountHistory(String emp, Account account) {
		String changes = "";
		ArrayList<String> listOfChanges = new ArrayList<String>();
		Account acc = new Account();

		fetchOldAccDetails(emp, acc);
		if (acc.getAccount().getName() == null
				|| !acc.getAccount().getName()
						.equalsIgnoreCase(account.getAccount().getName()))
			listOfChanges.add("Account(" + acc.getAccount().getName() + " to "
					+ account.getAccount().getName() + ")");
		if (acc.getSpoc().getName() == null
				|| !acc.getSpoc().getName()
						.equalsIgnoreCase(account.getSpoc().getName()))
			listOfChanges.add("Account SPOC(" + acc.getSpoc().getName()
					+ " to " + account.getSpoc().getName() + ")");
		changes += listOfChanges.toString().replace("[", "").replace("]", "");
		return changes;
	}

	private void scrubDate(String tenure, Employee employee) {
		String[] parts = tenure.split("T");
		System.out.println(tenure);
		employee.setTenure(parts[0]);

	}

	private void scrubDOJDate(String doj, Employee employee) {
		String[] parts = doj.split("T");
		employee.setDoj(parts[0]);

	}

	private void setEmpDetails(ArrayList<Employee> employees, ResultSet rs)
			throws SQLException {
		Employee e = new Employee();

		e.setEmpId(rs.getString("empId"));
		e.setName(rs.getString("EMP_NAME"));
		e.setStatus(rs.getString("status"));
		e.setPhone(rs.getString("phone"));
		e.setEmail(rs.getString("email"));
		e.setWl(rs.getString("wl"));
		e.setHl(rs.getString("hl"));
		e.setCl(rs.getString("cl"));
		e.setWtr(rs.getString("wtr"));

		employees.add(e);
	}

}