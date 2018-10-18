package com.syntel.dao;

import java.util.List;

import com.syntel.pojo.copy.OrderDetails;

public interface OrderDetailsDAO {
	
	public List<OrderDetails> getAllOrderDetails();
	public OrderDetails getOrderDetails(int OrderDetailsID);
	public void insertOrderDetails(OrderDetails orderDetails);
	public void updateOrderDetails(OrderDetails orderDetails);
	public void deleteOrderDetails(OrderDetails orderDetails);
	public void deleteForOrder(int OrderID);

}
