package com.syntel.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.syntel.connections.Connect;
import com.syntel.pojo.copy.Payment;

public class PaymentDAOImpl implements PaymentDAO{

	@Override
	public List<Payment> getAllPayment() {
		try {
			String ins = "select * from Payment";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			ResultSet result = st.executeQuery();
			List<Payment> paymentlist = new ArrayList<>();
			while (result.next()) {
				Payment thisPayment = new Payment(result.getInt(1), result.getString(2), result.getInt(3));
				paymentlist.add(thisPayment);
			}
			return paymentlist;
		} catch (Exception E) {
			E.printStackTrace();
		}
		return null;
	}

	@Override
	public Payment getPayment(int PaymentID) {
		try {
			String ins = "select * from Payment where PaymentID = ?";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setInt(1, PaymentID);
			ResultSet result = st.executeQuery();
			result.next();
			Payment thisPayment = new Payment(result.getInt(1), result.getString(2), result.getInt(3));
			return thisPayment;
		} catch (Exception E) {
			E.printStackTrace();
		}
		return null;
	}

	@Override
	public void insertPayment(Payment payment) {
		try {
			String ins = "insert into Payment values(?,?,?)";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setInt(1, payment.getPaymentid());
			st.setString(2, payment.getPaymenttype());
			st.setInt(3, payment.getCustomerid());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void updatePayment(Payment payment) {
		try {
			String ins = "update Payment set PaymentType=? , CustID=? Where PaymentID=?";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setString(1, payment.getPaymenttype());
			st.setInt(2, payment.getCustomerid());
			st.setInt(3, payment.getPaymentid());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deletePayment(Payment payment) {
		try {
			String ins = "delete from Payment where PaymentID = ?";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setInt(1, payment.getPaymentid());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
