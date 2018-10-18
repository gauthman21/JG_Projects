package com.syntel.dao;

import java.util.List;

import com.syntel.pojo.copy.AreaEmployee;

public interface AreaEmployeeDAO {

	public List<AreaEmployee> getAllAreaEmployee();
	public AreaEmployee getAreaEmployee(int AreaEmployeeID);
	public void insertAreaEmployee(AreaEmployee areaEmployee);
	public void updateAreaEmployee(AreaEmployee areaEmployee);
	public void deleteAreaEmployee(AreaEmployee areaEmployee);
	
}
