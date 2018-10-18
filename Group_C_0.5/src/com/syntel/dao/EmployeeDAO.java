package com.syntel.dao;

import java.util.List;

import com.syntel.pojo.copy.Employee;

public interface EmployeeDAO{
	
	public List<Employee> getAllEmployee();
	public Employee getEmployee(int EmployeeID);
	public void insertEmployee(Employee employee);
	public void updateEmployee(Employee employee);
	public void deleteEmployee(Employee employee);
	public String loginEmployee(String UserName);

}
