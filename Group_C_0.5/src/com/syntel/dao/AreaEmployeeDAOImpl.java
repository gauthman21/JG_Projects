package com.syntel.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.syntel.connections.Connect;
import com.syntel.pojo.copy.AreaEmployee;

public class AreaEmployeeDAOImpl implements AreaEmployeeDAO{

	@Override
	public List<AreaEmployee> getAllAreaEmployee() {
		try {
			String ins = "select * from AreaEmployee";
			PreparedStatement st = Connect.getConnection().prepareStatement(ins);
			ResultSet result = st.executeQuery();
			List<AreaEmployee> areaEmployeelist = new ArrayList<>();
			while (result.next()){
				AreaEmployee thisAreaEmployee = new AreaEmployee(result.getInt(1), result.getInt(2), result.getInt(3));
				areaEmployeelist.add(thisAreaEmployee);
			}
			return areaEmployeelist;
		}catch (Exception E){
			E.printStackTrace();
		}
		return null;
	}

	@Override
	public AreaEmployee getAreaEmployee(int AreaEmployeeID) {
		try {
			String ins = "select * from AreaEmployee where AreaEmployeeID = ?";
			PreparedStatement st = Connect.getConnection().prepareStatement(ins);
			st.setInt(1, AreaEmployeeID);
			ResultSet result = st.executeQuery();
			result.next();
			AreaEmployee thisAreaEmployee = new AreaEmployee(result.getInt(1), result.getInt(2), result.getInt(3));
			return thisAreaEmployee;
		}catch (Exception E){
			E.printStackTrace();
		}
		return null;
	}

	@Override
	public void insertAreaEmployee(AreaEmployee areaEmployee) {
		try {
			String ins = "insert into AreaEmployee values(?,?,?)";
			PreparedStatement st = Connect.getConnection().prepareStatement(ins);
			st.setInt(1, areaEmployee.getAreaEmployeeID());
			st.setInt(2, areaEmployee.getAreaID());
			st.setInt(3, areaEmployee.getEmployeeID());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateAreaEmployee(AreaEmployee areaEmployee) {
		try {
			String ins = "update AreaEmployee set AreaID=?, EmployeeID=? Where AreaEmployeeID=?";
			PreparedStatement st = Connect.getConnection().prepareStatement(ins);
			st.setInt(1, areaEmployee.getAreaID());
			st.setInt(2, areaEmployee.getEmployeeID());
			st.setInt(3, areaEmployee.getAreaEmployeeID());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteAreaEmployee(AreaEmployee areaEmployee) {
		try {
			String ins = "delete from AreaEmployee where AreaEmployeeID = ?";
			PreparedStatement st = Connect.getConnection().prepareStatement(ins);
			st.setInt(1, areaEmployee.getAreaEmployeeID());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
