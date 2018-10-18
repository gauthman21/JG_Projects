package com.syntel.pojo.copy;

import java.util.List;
import com.syntel.dao.PaymentDAOImpl;

public class Payment {
	public Payment(){}

	private int paymentid, customerid;
	private String paymenttype;
	@Override
	public String toString() {
		return "Payment [PaymentID=" + paymentid + ", CustomerID=" + customerid
				+ ", PaymentType=" + paymenttype + "]";
	}
	public Payment(int paymentID, String paymentType, int customerID) {
		super();
		paymentid = paymentID;
		customerid = customerID;
		paymenttype = paymentType;
	}
	
	public List<Payment> getAll(){
		return new PaymentDAOImpl().getAllPayment();
	}
	public void updatePayment(){
			Payment p = new Payment(this.paymentid, this.paymenttype, this.customerid);
			new PaymentDAOImpl().updatePayment(p);
	}
	public void deletePayment(int id){
		Payment p = new PaymentDAOImpl().getPayment(id);
		new PaymentDAOImpl().deletePayment(p);
	}
	public Payment getPayment(int id){
		return new PaymentDAOImpl().getPayment(id);
	}
	public int getPaymentid() {
		return paymentid;
	}
	public void setPaymentid(int paymentid) {
		this.paymentid = paymentid;
	}
	public int getCustomerid() {
		return customerid;
	}
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}
	public String getPaymenttype() {
		return paymenttype;
	}
	public void setPaymenttype(String paymenttype) {
		this.paymenttype = paymenttype;
	}
}
