package com.syntel.pojo.copy;

import java.util.List;

import com.syntel.dao.EmployeeDAOImpl;

public class Employee {
	public Employee() {
	}
	private int employeeid;
	private String efname, elname, ephone, eemail, eaccess, eusername, epassword;
		
	public Employee(int employeeid, String efname, String elname,
			String ephone, String eemail, String eaccess, String eusername,
			String epassword) {
		super();
		this.employeeid = employeeid;
		this.efname = efname;
		this.elname = elname;
		this.ephone = ephone;
		this.eemail = eemail;
		this.eaccess = eaccess;
		this.eusername = eusername;
		this.epassword = epassword;
	}


	public int getEmployeeid() {
		return employeeid;
	}



	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}



	public String getEfname() {
		return efname;
	}



	public void setEfname(String efname) {
		this.efname = efname;
	}



	public String getElname() {
		return elname;
	}



	public void setElname(String elname) {
		this.elname = elname;
	}



	public String getEphone() {
		return ephone;
	}



	public void setEphone(String ephone) {
		this.ephone = ephone;
	}



	public String getEemail() {
		return eemail;
	}



	public void setEemail(String eemail) {
		this.eemail = eemail;
	}



	public String getEaccess() {
		return eaccess;
	}



	public void setEaccess(String eaccess) {
		this.eaccess = eaccess;
	}



	public String getEusername() {
		return eusername;
	}



	public void setEusername(String eusername) {
		this.eusername = eusername;
	}



	public String getEpassword() {
		return epassword;
	}



	public void setEpassword(String epassword) {
		this.epassword = epassword;
	}

	public Employee getEmployee(int id){
		return new EmployeeDAOImpl().getEmployee(id);
	}

	public void addEmployee(){
		Employee e = new Employee(this.employeeid, this.efname, this.elname,
			this.ephone, this.eemail, this.eaccess, this.eusername,
			this.epassword);
		new EmployeeDAOImpl().insertEmployee(e);
	}

	public List<Employee> getAll(){
		return new EmployeeDAOImpl().getAllEmployee();
	}
	
	public void deleteEmployee(int id){
		Employee e = new EmployeeDAOImpl().getEmployee(id);
		System.out.println("Delete----------");
		new EmployeeDAOImpl().deleteEmployee(e);
	}
	
	public void updateEmployee(){
		Employee e = new Employee(this.employeeid, this.efname, this.elname,
				this.ephone, this.eemail, this.eaccess, this.eusername,
				this.epassword);
		new EmployeeDAOImpl().updateEmployee(e);
	}

	public String validateAdmin(){
		
		return new EmployeeDAOImpl().loginEmployee(eusername);
	}

	public Employee getEmployee(){
		Employee e = new EmployeeDAOImpl().getEmployee(eusername);
		return e;
	}
	
	@Override
	public String toString() {
		return "Employee [employeeid=" + employeeid + ", efname=" + efname
				+ ", elname=" + elname + ", ephone=" + ephone + ", eemail="
				+ eemail + ", eaccess=" + eaccess + ", eusername=" + eusername
				+ ", epassword=" + epassword + "]";
	}
	
	public int numOfEmployees(){
		
		return new EmployeeDAOImpl().getTotalEmployees();
	}

}
