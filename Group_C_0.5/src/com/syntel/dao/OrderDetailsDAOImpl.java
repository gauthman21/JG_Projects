package com.syntel.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.syntel.connections.Connect;
import com.syntel.pojo.copy.OrderDetails;

public class OrderDetailsDAOImpl implements OrderDetailsDAO{

	@Override
	public List<OrderDetails> getAllOrderDetails() {
		try {
			String ins = "select * from OrderDetails";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			ResultSet result = st.executeQuery();
			List<OrderDetails> orderDetailslist = new ArrayList<>();
			while (result.next()) {
				OrderDetails thisOrderDetails = new OrderDetails(result.getInt(1), result.getInt(2), result.getInt(3));
				orderDetailslist.add(thisOrderDetails);
			}
			return orderDetailslist;
		} catch (Exception E) {
			E.printStackTrace();
		}
		return null;
	}

	@Override
	public OrderDetails getOrderDetails(int OrderDetailsID) {
		try {
			String ins = "select * from OrderDetails where OrderDetailsID = ?";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setInt(1, OrderDetailsID);
			ResultSet result = st.executeQuery();
			result.next();
			OrderDetails thisOrderDetails = new OrderDetails(result.getInt(1), result.getInt(2), result.getInt(3));
			return thisOrderDetails;
		} catch (Exception E) {
			E.printStackTrace();
		}
		return null;
	}

	@Override
	public void insertOrderDetails(OrderDetails orderDetails) {
		try {
			String ins = "insert into OrderDetails values(?,?,?)";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setInt(1, orderDetails.getOrderDetailsID());
			st.setInt(2, orderDetails.getOrderID());
			st.setInt(3, orderDetails.getPackageID());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateOrderDetails(OrderDetails orderDetails) {
		try {
			String ins = "update OrderDetails set OrderID=? , PackageID=? Where OrderDetailsID=?";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setInt(1, orderDetails.getOrderID());
			st.setInt(2, orderDetails.getPackageID());
			st.setInt(3, orderDetails.getOrderDetailsID());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteOrderDetails(OrderDetails orderDetails) {
		try {
			String ins = "delete from OrderDetails where OrderDetailsID = ?";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setInt(1, orderDetails.getOrderDetailsID());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteForOrder(int OrderID) {
		try {
			String ins = "delete from OrderDetails where OrderID = ?";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setInt(1, OrderID);
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
