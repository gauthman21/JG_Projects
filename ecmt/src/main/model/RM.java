package main.model;

public class RM 
{
	
	private String rm;
	private String phone;
	private String email;
	
	public RM(){}

	public RM(String rm, String phone, String email) {
		super();
		this.rm = rm;
		this.phone = phone;
		this.email = email;
	}

	public String getRm() {
		return rm;
	}

	public void setRm(String rm) {
		this.rm = rm;
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

	@Override
	public String toString() {
		return "RM [rm=" + rm + ", phone=" + phone + ", email=" + email + "]";
	}
}
