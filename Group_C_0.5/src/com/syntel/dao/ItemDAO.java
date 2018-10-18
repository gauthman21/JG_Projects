package com.syntel.dao;

import java.util.*;

import com.syntel.pojo.copy.Item;

public interface ItemDAO {
	
	public List<Item> getAllItems();
	public Item getItem(int ItemID);
	public void insertItem(Item item);
	public void updateItem(Item item);
	public void deleteItem(Item item);
}
