package main.model;

public class Employee {

	private String empId;
	private String oldEmpId;
	private String name;
	private String status;
	private String tenure;
	private String phone;
	private String email;
	private String doj;
	private String wl;
	private String hl;
	private String cl;
	private String wtr;

	public Employee() {
	}

	public Employee(String empId, String oldEmpId, String name, String status,
			String tenure, String phone, String email, String doj, String wl,
			String hl, String cl, String wtr) {
		super();
		this.empId = empId;
		this.oldEmpId = oldEmpId;
		this.name = name;
		this.status = status;
		this.tenure = tenure;
		this.phone = phone;
		this.email = email;
		this.doj = doj;
		this.wl = wl;
		this.hl = hl;
		this.cl = cl;
		this.wtr = wtr;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getOldEmpId() {
		return oldEmpId;
	}

	public void setOldEmpId(String oldEmpId) {
		this.oldEmpId = oldEmpId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDoj() {
		return doj;
	}

	public void setDoj(String doj) {
		this.doj = doj;
	}

	public String getWl() {
		return wl;
	}

	public void setWl(String wl) {
		this.wl = wl;
	}

	public String getHl() {
		return hl;
	}

	public void setHl(String hl) {
		this.hl = hl;
	}

	public String getCl() {
		return cl;
	}

	public void setCl(String cl) {
		this.cl = cl;
	}

	public String getWtr() {
		return wtr;
	}

	public void setWtr(String wtr) {
		this.wtr = wtr;
	}

	public void setTenure(String tenure) {
		this.tenure = tenure;
	}

	public String getTenure() {
		return tenure;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", oldEmpId=" + oldEmpId
				+ ", name=" + name + ", status=" + status + ", tenure="
				+ tenure + ", phone=" + phone + ", email=" + email + ", doj="
				+ doj + ", wl=" + wl + ", hl=" + hl + ", cl=" + cl + ", wtr="
				+ wtr + "]";
	}
}
