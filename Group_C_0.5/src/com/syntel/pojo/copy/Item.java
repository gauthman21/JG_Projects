package com.syntel.pojo.copy;

import java.util.ArrayList;
import java.util.List;

import com.syntel.dao.ItemDAOImpl;

public class Item {
	public Item() {
		
	}
	private int ItemID;
	private String ItemName, Description, ItemCategroy;
	@Override
	public String toString() {
		return "Item [ItemID=" + ItemID + ", ItemName=" + ItemName
				+ ", Description=" + Description + ", ItemCategroy="
				+ ItemCategroy + "]";
	}
	public Item(int itemID, String itemName, String itemCategroy, String description
			) {
		super();
		ItemID = itemID;
		ItemName = itemName;
		Description = description;
		ItemCategroy = itemCategroy;
	}
	public int getItemID() {
		return ItemID;
	}
	public void setItemID(int itemID) {
		
		ItemID = itemID;
	}
	public String getItemName() {
		return ItemName;
	}
	public void setItemName(String itemName) {
		ItemName = itemName;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getItemCategroy() {
		return ItemCategroy;
	}
	public void setItemCategroy(String itemCategroy) {
		ItemCategroy = itemCategroy;
	}
	
	public void insertItem() {
		Item item = new Item(ItemID, ItemName, ItemCategroy, Description);
		new ItemDAOImpl().insertItem(item);
	}
	public void deleteItem(String id){
		int id1 = Integer.parseInt(id);
		new ItemDAOImpl().deleteItem(id1);
	}
	public Item getItem(String id){
		int id1 = Integer.parseInt(id);
		Item i = new ItemDAOImpl().getItem(id1);
		return i;
	}
	public void updateItem(){
		Item item = new Item(ItemID, ItemName, ItemCategroy, Description);
		new ItemDAOImpl().updateItem(item);
	}
	
	public List<Item> getItems(){
		List<Item> itemlist = new ArrayList<>();
		itemlist = new ItemDAOImpl().getAllItems();
		return itemlist;
	}

	
}
