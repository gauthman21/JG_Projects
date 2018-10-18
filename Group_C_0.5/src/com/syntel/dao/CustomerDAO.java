package com.syntel.dao;

import java.util.List;

import com.syntel.pojo.copy.Customer;

public interface CustomerDAO {

	public List<Customer> getAllCustomer();
	public Customer getCustomer(int CustomerID);
	public void insertCustomer(Customer customer);
	public void updateCust(Customer customer);
	public void deleteItem(Customer customer);
	public String loginCustomer(String UserName);

	

}
