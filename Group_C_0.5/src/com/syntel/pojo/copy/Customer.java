package com.syntel.pojo.copy;

import java.util.List;

import com.syntel.dao.CustomerDAOImpl;
import com.syntel.dao.CustomerOrderDAOImpl;

public class Customer {

	public Customer() {

	}

	private int customerid;
	private String cfname, clname, cphone, cemail, status, cusername,
			cpassword;

	@Override
	public String toString() {
		return "Customer [customerid=" + customerid + ", cfname=" + cfname
				+ ", clname=" + clname + ", cphone=" + cphone + ", cemail="
				+ cemail + ", status=" + status + ", cusername=" + cusername
				+ ", cpassword=" + cpassword + "]";
	}

	public Customer(int customerid, String cfname, String clname,
			String cphone, String cemail, String status, String cusername,
			String cpassword) {
		super();
		this.customerid = customerid;
		this.cfname = cfname;
		this.clname = clname;
		this.cphone = cphone;
		this.cemail = cemail;
		this.status = status;
		this.cusername = cusername;
		this.cpassword = cpassword;
	}

	public int getcustomerid() {
		return customerid;
	}

	public void setcustomerid(int customerid) {
		this.customerid = customerid;
	}

	public String getcfname() {
		return cfname;
	}

	public void setcfname(String cfname) {
		this.cfname = cfname;
	}

	public String getclname() {
		return clname;
	}

	public void setclname(String clname) {
		this.clname = clname;
	}

	public String getcphone() {
		return cphone;
	}

	public void setcphone(String cphone) {
		this.cphone = cphone;
	}

	public String getcemail() {
		return cemail;
	}

	public void setcemail(String cemail) {
		this.cemail = cemail;
	}

	public String getstatus() {
		return status;
	}

	public void setstatus(String status) {
		this.status = status;
	}

	public String getcusername() {
		return cusername;
	}

	public void setcusername(String cusername) {
		this.cusername = cusername;
	}

	public String getcpassword() {
		return cpassword;
	}

	public void setcpassword(String cpassword) {
		this.cpassword = cpassword;
	}
	public List<Customer> getAll(){
		return new CustomerDAOImpl().getAllCustomer();
	}
	public void deleteCustomer(int id){
		new CustomerDAOImpl().deleteItem(new CustomerDAOImpl().getCustomer(id));
	}
	public void updateCustomer(){
		Customer c = new Customer(this.customerid, this.cfname, this.clname, this.cphone, this.cemail, this.status, this.cusername, this.cpassword);
		System.out.println(c.toString());
		new CustomerDAOImpl().updateCust(c);
	}
	public Customer getCustomer(int id){
		return new CustomerDAOImpl().getCustomer(id);
	}
	public int numOfCustomers(){
		int totalcustomers = new CustomerDAOImpl().getTotalOCustomers();
		return totalcustomers;
	}

}
