package com.syntel.pojo.copy;

public class AreaEmployee {

	private int AreaEmployeeID, AreaID, EmployeeID;

	@Override
	public String toString() {
		return "AreaEmployee [AreaEmployeeID=" + AreaEmployeeID + ", AreaID="
				+ AreaID + ", EmployeeID=" + EmployeeID + "]";
	}

	public AreaEmployee() {
		
	}
	public AreaEmployee(int areaEmployeeID, int areaID, int employeeID) {
		super();
		AreaEmployeeID = areaEmployeeID;
		AreaID = areaID;
		EmployeeID = employeeID;
	}

	public int getAreaEmployeeID() {
		return AreaEmployeeID;
	}

	public void setAreaEmployeeID(int areaEmployeeID) {
		AreaEmployeeID = areaEmployeeID;
	}

	public int getAreaID() {
		return AreaID;
	}

	public void setAreaID(int areaID) {
		AreaID = areaID;
	}

	public int getEmployeeID() {
		return EmployeeID;
	}

	public void setEmployeeID(int employeeID) {
		EmployeeID = employeeID;
	}
	
	
}
