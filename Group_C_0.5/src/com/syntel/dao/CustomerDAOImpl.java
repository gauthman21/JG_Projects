package com.syntel.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.syntel.connections.Connect;
import com.syntel.pojo.copy.Customer;

public class CustomerDAOImpl implements CustomerDAO {

	@Override
	public String loginCustomer(String UserName) {
		String pWord = null;
		try (Connection con = Connect.getConnection()) {
			CallableStatement statement = con
					.prepareCall("{call CustomerLogin(?, ?)}");
			statement.setString(1, UserName);
			statement.registerOutParameter(2, Types.VARCHAR);
			statement.execute();
			pWord = statement.getString(2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pWord;
	}

	@Override
	public List<Customer> getAllCustomer() {
		try {
			String ins = "select * from Customer";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			ResultSet result = st.executeQuery();
			List<Customer> customerlist = new ArrayList<>();
			while (result.next()) {
				Customer thisCustomer = new Customer(result.getInt(1),
						result.getString(2), result.getString(3),
						result.getString(4), result.getString(5),
						result.getString(6), result.getString(7),
						result.getString(8));
				customerlist.add(thisCustomer);
			}
			return customerlist;
		} catch (Exception E) {
			E.printStackTrace();
		}
		return null;
	}

	@Override
	public Customer getCustomer(int CustomerID) {
		try {
			String ins = "select * from Customer where CustomerID = ?";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setInt(1, CustomerID);
			ResultSet result = st.executeQuery();
			result.next();
			Customer thisCustomer = new Customer(result.getInt(1),
					result.getString(2), result.getString(3),
					result.getString(4), result.getString(5),
					result.getString(6), result.getString(7),
					result.getString(8));
			return thisCustomer;
		} catch (Exception E) {
			E.printStackTrace();
		}
		return null;
	}

	@Override
	public void insertCustomer(Customer customer) {
		try {
			String ins = "insert into Customer values(?,?,?,?,?,?,?,?)";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setInt(1, customer.getcustomerid());
			st.setString(2, customer.getcfname());
			st.setString(3, customer.getclname());
			st.setString(4, customer.getcphone());
			st.setString(5, customer.getcemail());
			st.setString(6, customer.getstatus());
			st.setString(7, customer.getcusername());
			st.setString(8, customer.getcpassword());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateCust(Customer customer) {
		try {
			String ins = "update Customer set CFName=? , CLName=? , CPhone=? , CEmail=? , Status=? , CUsername=? , CPassword=? Where CustomerID=?";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setString(1, customer.getcfname());
			st.setString(2, customer.getclname());
			st.setString(3, customer.getcphone());
			st.setString(4, customer.getcemail());
			st.setString(5, customer.getstatus());
			st.setString(6, customer.getcusername());
			st.setString(7, customer.getcpassword());
			st.setInt(8, customer.getcustomerid());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteItem(Customer customer) {
		try {
			String ins = "delete from Customer where CustomerID = ?";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setInt(1, customer.getcustomerid());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getTotalOCustomers() {
		try {
			String ins = "select count(*) from Customer";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			ResultSet result = st.executeQuery();
			int totalCustomers = 0;
			while (result.next()) {
				totalCustomers = result.getInt(1);
			}
			
			return totalCustomers;
		} catch (Exception E) {
			E.printStackTrace();
		}
		return 0;
	}

}
