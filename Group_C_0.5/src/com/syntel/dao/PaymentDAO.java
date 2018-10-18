package com.syntel.dao;

import java.util.List;

import com.syntel.pojo.copy.Payment;

public interface PaymentDAO {
	
	public List<Payment> getAllPayment();
	public Payment getPayment(int PaymentID);
	public void insertPayment(Payment payment);
	public void updatePayment(Payment payment);
	public void deletePayment(Payment payment);

}
