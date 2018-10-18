package main.model;

public class Account {

	private String empId;
	private Spoc spoc;
	private DropdownObj account;
	
	public Account() 
	{
		spoc = new Spoc();
		account = new DropdownObj();
	}

	public Account(String empId, Spoc spoc, DropdownObj account) {
		super();
		this.empId = empId;
		this.spoc = spoc;
		this.account = account;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public Spoc getSpoc() {
		return spoc;
	}

	public void setSpoc(Spoc spoc) {
		this.spoc = spoc;
	}

	public DropdownObj getAccount() {
		return account;
	}

	public void setAccount(DropdownObj account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "Account [empId=" + empId + ", spoc=" + spoc + ", account="
				+ account + "]";
	}
}
