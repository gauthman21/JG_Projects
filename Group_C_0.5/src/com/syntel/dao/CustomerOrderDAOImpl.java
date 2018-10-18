package com.syntel.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.syntel.connections.Connect;
import com.syntel.pojo.copy.CustomerOrder;

public class CustomerOrderDAOImpl implements CustomerOrderDAO {

	@Override
	public List<CustomerOrder> getAllCustomerOrder() {
		try {
			String ins = "select * from CustOrder";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			ResultSet result = st.executeQuery();
			List<CustomerOrder> custOrderlist = new ArrayList<>();
			while (result.next()) {
				CustomerOrder thisCustOrder = new CustomerOrder(
						result.getInt(1), result.getString(2),
						result.getString(3), result.getString(4),
						result.getString(5), result.getInt(6),
						result.getString(7), result.getInt(8),
						result.getInt(9), result.getString(10),
						result.getString(11));
				custOrderlist.add(thisCustOrder);
			}
			return custOrderlist;
		} catch (Exception E) {
			E.printStackTrace();
		}
		return null;
	}

	@Override
	public CustomerOrder getCustomerOrderbyID(int CustomerOrderID) {
		try {
			String ins = "select * from CustOrder where OrderID = ?";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setInt(1, CustomerOrderID);
			ResultSet result = st.executeQuery();
			result.next();
			CustomerOrder thisCustOrder = new CustomerOrder(result.getInt(1),
					result.getDate(2).toString(), result.getString(3), result.getDate(4).toString(),
					result.getString(5), result.getInt(6), result.getString(7),
					result.getInt(8), result.getInt(9), result.getString(10),
					result.getString(11));
			return thisCustOrder;
		} catch (Exception E) {
			E.printStackTrace();
		}
		return null;
	}

	@Override
	public void insertCustomerOrder(CustomerOrder customerOrder) {
		try {
			String ins = "insert into CustOrder values(?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setInt(1, customerOrder.getOrderid());
			st.setDate(2, java.sql.Date.valueOf(customerOrder.getOdate()));
			st.setString(3, customerOrder.getOtime());
			st.setDate(4, java.sql.Date.valueOf(customerOrder.getOenddate()));
			st.setString(5, customerOrder.getOdays());
			st.setInt(6, customerOrder.getArea());
			st.setString(7, customerOrder.getOaddress());
			st.setInt(8, customerOrder.getCid());
			st.setInt(9, customerOrder.getPayid());
			st.setString(10, customerOrder.getPaymentstatus());
			st.setString(11, customerOrder.getOrderstatus());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateCustomerOrder(CustomerOrder customerOrder) {
		try {
			String ins = "update CustOrder set ODate=? , OTime=? , OEndDate=? , ODays=? , Area=? , OAddress=? , CID=? , PayID=? , PaymentStatus=? , OrderStatus=? Where OrderID=?";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setDate(1, java.sql.Date.valueOf(customerOrder.getOdate()));
			st.setString(2, customerOrder.getOtime());
			st.setDate(3, java.sql.Date.valueOf(customerOrder.getOenddate()));
			st.setString(4, customerOrder.getOdays());
			st.setInt(5, customerOrder.getArea());
			st.setString(6, customerOrder.getOaddress());
			st.setInt(7, customerOrder.getCid());
			st.setInt(8, customerOrder.getPayid());
			st.setString(9, customerOrder.getPaymentstatus());
			st.setString(10, customerOrder.getOrderstatus());
			st.setInt(11, customerOrder.getOrderid());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteCustomerOrder(CustomerOrder customerOrder) {
		try {
			String ins = "delete from CustOrder where OrderID = ?";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setInt(1, customerOrder.getOrderid());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<CustomerOrder> searchCustomerOrderbyStatus(String status) {
		try {
			String ins = "select * from CustOrder Where PaymentStatus=?";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setString(1, status);
			ResultSet result = st.executeQuery();
			List<CustomerOrder> custOrderlist = new ArrayList<>();
			while (result.next()) {
				CustomerOrder thisCustOrder = new CustomerOrder(
						result.getInt(1), result.getString(2),
						result.getString(3), result.getString(4),
						result.getString(5), result.getInt(6),
						result.getString(7), result.getInt(8),
						result.getInt(9), result.getString(10),
						result.getString(11));
				custOrderlist.add(thisCustOrder);
			}
			return custOrderlist;
		} catch (Exception E) {
			E.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CustomerOrder> searchCustomerOrderbyDate(java.sql.Date date) {
		try {
			String ins = "select * from CustOrder Where OAddress=?";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setDate(1, date);
			ResultSet result = st.executeQuery();
			List<CustomerOrder> custOrderlist = new ArrayList<>();
			while (result.next()) {
				CustomerOrder thisCustOrder = new CustomerOrder(
						result.getInt(1), result.getString(2),
						result.getString(3), result.getString(4),
						result.getString(5), result.getInt(6),
						result.getString(7), result.getInt(8),
						result.getInt(9), result.getString(10),
						result.getString(11));
				custOrderlist.add(thisCustOrder);
			}
			return custOrderlist;
		} catch (Exception E) {
			E.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CustomerOrder> searchCustomerOrderbyArea(String area) {
		try {
			String ins = "select * from CustOrder Where Area=?";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setString(1, area);
			ResultSet result = st.executeQuery();
			List<CustomerOrder> custOrderlist = new ArrayList<>();
			while (result.next()) {
				CustomerOrder thisCustOrder = new CustomerOrder(
						result.getInt(1), result.getString(2),
						result.getString(3), result.getString(4),
						result.getString(5), result.getInt(6),
						result.getString(7), result.getInt(8),
						result.getInt(9), result.getString(10),
						result.getString(11));
				custOrderlist.add(thisCustOrder);
			}
			return custOrderlist;
		} catch (Exception E) {
			E.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CustomerOrder> searchCustomerOrderbyCustomerID(int customerID) {
		try {
			String ins = "select * from CustOrder Where CID=?";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setInt(1, customerID);
			ResultSet result = st.executeQuery();
			List<CustomerOrder> custOrderlist = new ArrayList<>();
			while (result.next()) {
				CustomerOrder thisCustOrder = new CustomerOrder(
						result.getInt(1), result.getString(2),
						result.getString(3), result.getString(4),
						result.getString(5), result.getInt(6),
						result.getString(7), result.getInt(8),
						result.getInt(9), result.getString(10),
						result.getString(11));
				custOrderlist.add(thisCustOrder);
			}
			return custOrderlist;
		} catch (Exception E) {
			E.printStackTrace();
		}
		return null;
	}

	public int getTotalOrders() {
		try {
			String ins = "select count(*) from custorder";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			ResultSet result = st.executeQuery();
			int totalorders = 0;
			while (result.next()) {
				totalorders = result.getInt(1);
			}
			return totalorders;
		} catch (Exception E) {
			E.printStackTrace();
		}
		return 0;
	}

}
