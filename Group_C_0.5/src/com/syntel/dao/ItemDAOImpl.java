package com.syntel.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.syntel.connections.Connect;
import com.syntel.pojo.copy.Item;

public class ItemDAOImpl implements ItemDAO{

	@Override
	public List<Item> getAllItems() {
		try {
			String ins = "select * from Items";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			ResultSet result = st.executeQuery();
			List<Item> itemlist = new ArrayList<>();
			while (result.next()) {
				Item thisItem = new Item(result.getInt(1), result.getString(2), result.getString(3), result.getString(4));
				itemlist.add(thisItem);
			}
			return itemlist;
		} catch (Exception E) {
			E.printStackTrace();
		}
		return null;
	}

	@Override
	public Item getItem(int ItemID) {
		try {
			String ins = "select * from Items where ItemID = ?";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setInt(1, ItemID);
			ResultSet result = st.executeQuery();
			result.next();
			Item thisItem = new Item(result.getInt(1), result.getString(2), result.getString(3), result.getString(4));
			return thisItem;
		} catch (Exception E) {
			E.printStackTrace();
		}
		return null;
	}

	@Override
	public void insertItem(Item item) {
		try {
			String ins = "insert into Items values(?,?,?,?)";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setInt(1, item.getItemID());
			
			st.setString(2, item.getItemName());
			st.setString(3, item.getItemCategroy());
			st.setString(4, item.getDescription());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateItem(Item item) {
		try {
			String ins = "update Items set ItemName=? , ItemCategory=? , Description=? Where ItemID=?";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setString(1, item.getItemName());
			st.setString(2, item.getItemCategroy());
			st.setString(3, item.getDescription());
			st.setInt(4, item.getItemID());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteItem(Item item) {
		try {
			String ins = "delete from Items where ItemID = ?";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setInt(1, item.getItemID());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteItem(int id) {
		try {
			
			String ins = "delete from Items where ItemID = ?";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setInt(1, id);
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
