package com.syntel.dao;

import java.util.List;

import com.syntel.pojo.copy.CustomerOrder;

public interface CustomerOrderDAO {

	public List<CustomerOrder> getAllCustomerOrder();
	public CustomerOrder getCustomerOrderbyID(int CustomerOrderID);
	public void insertCustomerOrder(CustomerOrder customerOrder);
	public void updateCustomerOrder(CustomerOrder customerOrder);
	public void deleteCustomerOrder(CustomerOrder customerOrder);
	public List<CustomerOrder> searchCustomerOrderbyStatus(String status);
	public List<CustomerOrder> searchCustomerOrderbyDate(java.sql.Date date);
	public List<CustomerOrder> searchCustomerOrderbyArea(String area);
	public List<CustomerOrder> searchCustomerOrderbyCustomerID(int customerID);
}
