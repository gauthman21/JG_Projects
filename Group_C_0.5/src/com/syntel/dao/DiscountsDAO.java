package com.syntel.dao;

import java.util.List;

import com.syntel.pojo.copy.Discounts;

public interface DiscountsDAO {

	public List<Discounts> getAllDiscounts();
	public Discounts getDiscounts(int DiscountsID);
	public void insertDiscounts(Discounts discount);
	public void updateDiscounts(Discounts discount);
	public void deleteDiscounts(Discounts discount);
	
}
