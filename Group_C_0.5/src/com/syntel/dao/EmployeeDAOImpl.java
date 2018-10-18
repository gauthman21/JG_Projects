package com.syntel.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.syntel.connections.Connect;
import com.syntel.pojo.copy.Employee;

public class EmployeeDAOImpl implements EmployeeDAO {

	@Override
	public String loginEmployee(String UserName) {
		String pWord = null;
		try (Connection con = Connect.getConnection()) {
			CallableStatement statement = con
					.prepareCall("{call EmployeeLogin(?, ?)}");
			statement.setString(1, UserName);
			statement.registerOutParameter(2, Types.VARCHAR);
			statement.execute();
			pWord = statement.getString(2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pWord;
	}

	@Override
	public List<Employee> getAllEmployee() {
		try {
			String ins = "select * from Employees";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			ResultSet result = st.executeQuery();
			List<Employee> employeelist = new ArrayList<>();
			while (result.next()) {
				Employee thisEmployee = new Employee(result.getInt(1),
						result.getString(2), result.getString(3),
						result.getString(4), result.getString(5),
						result.getString(6), result.getString(7),
						result.getString(8));
				employeelist.add(thisEmployee);
			}
			return employeelist;
		} catch (Exception E) {
			E.printStackTrace();
		}
		return null;
	}

	@Override
	public Employee getEmployee(int EmployeeID) {
		try {
			String ins = "select * from Employees where EmployeeID = ?";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setInt(1, EmployeeID);
			ResultSet result = st.executeQuery();
			result.next();
			Employee thisEmployee = new Employee(result.getInt(1),
					result.getString(2), result.getString(3),
					result.getString(4), result.getString(5),
					result.getString(6), result.getString(7),
					result.getString(8));
			return thisEmployee;
		} catch (Exception E) {
			E.printStackTrace();
		}
		return null;
	}

	@Override
	public void insertEmployee(Employee employee) {
		try {
			String ins = "insert into Employees values(0,?,?,?,?,?,?,?)";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setString(1, employee.getEfname());
			st.setString(2, employee.getElname());
			st.setString(3, employee.getEphone());
			st.setString(4, employee.getEemail());
			st.setString(5, employee.getEaccess());
			st.setString(6, employee.getEusername());
			st.setString(7, employee.getEpassword());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateEmployee(Employee employee) {
		try {
			String ins = "update Employees set EFName=? , ELName=? , EPhone=? , EEmail=? , EAccess=? , EUsername=? , EPassword=? Where EmployeeID=?";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setString(1, employee.getEfname());
			st.setString(2, employee.getElname());
			st.setString(3, employee.getEphone());
			st.setString(4, employee.getEemail());
			st.setString(5, employee.getEaccess());
			st.setString(6, employee.getEusername());
			st.setString(7, employee.getEpassword());
			st.setInt(8, employee.getEmployeeid());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteEmployee(Employee employee) {
		try {
			String ins = "delete from Employees where EmployeeID = ?";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setInt(1, employee.getEmployeeid());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Employee getEmployee(String UserName) {
		try {
			String ins = "select * from Employees where eusername = ?";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setString(1, UserName);
			ResultSet result = st.executeQuery();
			result.next();
			Employee thisEmployee = new Employee(result.getInt(1),
					result.getString(2), result.getString(3),
					result.getString(4), result.getString(5),
					result.getString(6), result.getString(7),
					result.getString(8));
			return thisEmployee;
		} catch (Exception E) {
			E.printStackTrace();
		}
		return null;
	}
	public int getTotalEmployees() {
		try {
			String ins = "select count(*) from Employees";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			ResultSet result = st.executeQuery();
			int totalemps = 0;
			while (result.next()) {
				totalemps = result.getInt(1);
			}
			return totalemps;
		} catch (Exception E) {
			E.printStackTrace();
		}
		return 0;
	}
	

}
