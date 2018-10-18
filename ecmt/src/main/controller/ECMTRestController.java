package main.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.syntel.ecmt.onStartup;

import main.DAO.Dao;
import main.constants.Constants;
import main.constants.Queries;
import main.model.*;

@RestController
public class ECMTRestController {
	@Autowired
	Dao dao;

	@RequestMapping(value = "/deleteComment/{key}", method = RequestMethod.GET)
	public ResponseEntity<Void> deleteComment(@PathVariable("key") String key)
			throws JAXBException {
		if (dao.deleteComment(key))
			return new ResponseEntity<Void>(HttpStatus.OK);
		else
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/databaseConn/{key}", method = RequestMethod.GET)
	public String switchDBConn(@PathVariable("key") String key) {
		if (dao.checkDatabaseCheckbox(key)) {
			System.out.println("true");
			return "true";
		} else {
			System.out.println("false");
			return "false";
		}
	}

	@RequestMapping(value = "/deleteSkill/{type}/{key}", method = RequestMethod.GET)
	public ResponseEntity<Void> deleteSkill(@PathVariable("type") String type,
			@PathVariable("key") String key) {
		if (dao.deleteSkill(key, type))
			return new ResponseEntity<Void>(HttpStatus.OK);
		else
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/getAccounts/{id}", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<DropdownObj>> getAccounts(
			@PathVariable("id") String id) {
		ArrayList<DropdownObj> acc = dao.getAccounts(id);

		if (acc != null)
			return new ResponseEntity<ArrayList<DropdownObj>>(acc,
					HttpStatus.OK);
		else
			return new ResponseEntity<ArrayList<DropdownObj>>(
					HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/getAccountSPOC/{id}", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Spoc>> getAccountSPOC(
			@PathVariable("id") String id) {
		ArrayList<Spoc> vs = dao.getAccountSPOC(id);

		if (vs != null)
			return new ResponseEntity<ArrayList<Spoc>>(vs, HttpStatus.OK);
		else
			return new ResponseEntity<ArrayList<Spoc>>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/getReport/{option}", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Employee>> getAllEmployees(
			@PathVariable("option") String option) {
		ArrayList<Employee> newEmps = null;

		if (option.equals("All Employees"))
			newEmps = dao.getAllEmployees();
		else
			newEmps = dao.getStatus(option);

		if (newEmps != null)
			return new ResponseEntity<ArrayList<Employee>>(newEmps,
					HttpStatus.OK);
		else
			return new ResponseEntity<ArrayList<Employee>>(
					HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/getEmployee/{id}", method = RequestMethod.GET)
	public ResponseEntity<Profile> getName(@PathVariable("id") String id) {
		Profile employee = dao.getEmployee(id);

		if (employee != null)
			return new ResponseEntity<Profile>(employee, HttpStatus.OK);
		else
			return new ResponseEntity<Profile>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/getReportTenure/{start}/{end}", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Employee>> getReportTenure(
			@PathVariable("start") String start, @PathVariable("end") String end)
			throws ParseException {
		ArrayList<Employee> employees = dao.getEmployeesByTenure(start, end);

		if (employees != null)
			return new ResponseEntity<ArrayList<Employee>>(employees,
					HttpStatus.OK);
		else
			return new ResponseEntity<ArrayList<Employee>>(
					HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/getReportSkill", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<ArrayList<Employee>> getSkillReport(
			@RequestBody String skill) {
		ArrayList<Employee> employees = dao.getEmployeesBySkill(skill);

		if (employees != null)
			return new ResponseEntity<ArrayList<Employee>>(employees,
					HttpStatus.OK);
		else
			return new ResponseEntity<ArrayList<Employee>>(
					HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/getVerticalAccount", method = RequestMethod.GET)
	public ResponseEntity<TotalVertAcc> getVerticalAccount() {
		TotalVertAcc vt = dao.getVerticalAccount();

		if (vt != null)
			return new ResponseEntity<TotalVertAcc>(vt, HttpStatus.OK);
		else
			return new ResponseEntity<TotalVertAcc>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/getVerticalSPOC/{id}", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Spoc>> getVerticalSPOC(
			@PathVariable("id") String id) {
		ArrayList<Spoc> vs = dao.getVerticalSPOC(id);

		if (vs != null)
			return new ResponseEntity<ArrayList<Spoc>>(vs, HttpStatus.OK);
		else
			return new ResponseEntity<ArrayList<Spoc>>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/addComment", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Comment> insertComment(@RequestBody Comment comment) {
		Comment newComment = dao.insertComment(comment);

		if (newComment != null)
			return new ResponseEntity<Comment>(newComment, HttpStatus.OK);
		else
			return new ResponseEntity<Comment>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/addEmployee", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Void> insertEmployee(@RequestBody Employee employee) {
		if (dao.insertEmployee(employee))
			return new ResponseEntity<Void>(HttpStatus.OK);
		else
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/addEmpVertical/{id}", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Void> insertEmpVertical(
			@RequestBody Vertical vertical, @PathVariable("id") String id) {
		if (dao.insertEmpVertical(vertical, id))
			return new ResponseEntity<Void>(HttpStatus.OK);
		else
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/addEmpAccount/{id}", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Void> insertEmpAccount(@RequestBody Account account,
			@PathVariable("id") String id) {
		if (dao.insertEmpAccount(account, id))
			return new ResponseEntity<Void>(HttpStatus.OK);
		else
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/addSkill/{type}", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Skill> insertSkill(@RequestBody Skill skill,
			@PathVariable("type") String type) {
		Skill newSkill = dao.insertSkill(skill, type);

		if (newSkill != null)
			return new ResponseEntity<Skill>(newSkill, HttpStatus.OK);
		else
			return new ResponseEntity<Skill>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/insertHistory/{empId}/{changes}", method = RequestMethod.POST)
	public ResponseEntity<History> insertHistory(
			@PathVariable("empId") String id,
			@PathVariable("changes") String changes) throws SQLException,
			ClassNotFoundException {
		if (dao.insertHistory(id, changes)) {
			History hist = new History();
			Class.forName("org.sqlite.JDBC");
			String dbURL = Constants.DBURL;
			Connection conn = DriverManager.getConnection(dbURL);
			PreparedStatement ps = conn
					.prepareStatement(Queries.SELECTHISTORYDETAILS);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Comment cmt = new Comment();

				cmt.setComment(rs.getString("comment"));
				cmt.setDate(rs.getString("date"));

				hist.addComment(cmt);
			}
			dao.connectionsClose(conn, ps, rs);

			return new ResponseEntity<History>(hist, HttpStatus.OK);
		} else
			return new ResponseEntity<History>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/updateAccount/{id}", method = RequestMethod.POST)
	public ResponseEntity<StringBuilder> updateAccount(
			@PathVariable("id") String id, @RequestBody Account account) {
		String history = dao.checkEmpAccountHistory(id, account);
		if (!history.equals(""))
			history += ", ";
		if (dao.updateAccount(account, id))
			return new ResponseEntity<StringBuilder>(
					new StringBuilder(history), HttpStatus.OK);
		else
			return new ResponseEntity<StringBuilder>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/updateComment", method = RequestMethod.POST)
	public ResponseEntity<Void> updateComment(@RequestBody Comment comment) {
		if (dao.updateComment(comment))
			return new ResponseEntity<Void>(HttpStatus.OK);
		else
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/updateEmployee", method = RequestMethod.POST)
	public ResponseEntity<StringBuilder> updateEmployee(
			@RequestBody Employee employee) throws JAXBException {
		String history = dao.checkEmpHistory(employee);
		if (!history.equals(""))
			history += ", ";

		if (dao.updateEmployee(employee))
			return new ResponseEntity<StringBuilder>(
					new StringBuilder(history), HttpStatus.OK);
		else
			return new ResponseEntity<StringBuilder>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/updateRM/{id}", method = RequestMethod.POST)
	public ResponseEntity<StringBuilder> updateRM(
			@PathVariable("id") String id, @RequestBody RM rm) {
		String history = dao.checkRMHistory(id, rm);
		if (!history.equals(""))
			history += ", ";
		if (dao.updateRM(rm, id))
			return new ResponseEntity<StringBuilder>(
					new StringBuilder(history), HttpStatus.OK);
		else
			return new ResponseEntity<StringBuilder>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/updateSkill/{type}", method = RequestMethod.POST)
	public ResponseEntity<Void> updateSkill(@RequestBody Skill skill,
			@PathVariable("type") String type) {
		if (dao.updateSkill(skill, type))
			return new ResponseEntity<Void>(HttpStatus.OK);
		else
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/updateVertical/{id}", method = RequestMethod.POST)
	public ResponseEntity<StringBuilder> updateVertical(
			@PathVariable("id") String id, @RequestBody Vertical vertical) {
		String history = dao.checkEmpVerticalHistory(id, vertical);
		if (!history.equals(""))
			history += ", ";
		if (dao.updateVertical(vertical, id))
			return new ResponseEntity<StringBuilder>(
					new StringBuilder(history), HttpStatus.OK);
		else
			return new ResponseEntity<StringBuilder>(HttpStatus.BAD_REQUEST);
	}
}
