package com.syntel.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.syntel.connections.Connect;
import com.syntel.pojo.copy.Discounts;

public class DiscountsDAOImpl implements DiscountsDAO{

	@Override
	public List<Discounts> getAllDiscounts() {
		try {
			String ins = "select * from Discounts";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			ResultSet result = st.executeQuery();
			List<Discounts> discountlist = new ArrayList<>();
			while (result.next()) {
				Discounts thisDiscount = new Discounts(result.getInt(1), result.getDate(2).toString(), result.getInt(3), result.getInt(4), result.getString(5));
				discountlist.add(thisDiscount);
			}
			return discountlist;
		} catch (Exception E) {
			E.printStackTrace();
		}
		return null;
	}

	@Override
	public Discounts getDiscounts(int DiscountsID) {
		try {
			String ins = "select * from Discounts where DiscountID = ?";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setInt(1, DiscountsID);
			ResultSet result = st.executeQuery();
			result.next();
			Discounts thisDiscount = new Discounts(result.getInt(1), result.getDate(2).toString(), result.getInt(3), result.getInt(4), result.getString(5));
			return thisDiscount;
		} catch (Exception E) {
			E.printStackTrace();
		}
		return null;
	}

	@Override
	public void insertDiscounts(Discounts discount) {
		try {
			String ins = "insert into Discounts values(?,?,?,?,?)";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setInt(1, discount.getdiscountid());
			st.setDate(2, java.sql.Date.valueOf(discount.getdiscountdate()));
			st.setInt(3, discount.getpackageid());
			st.setInt(4, discount.getpercentoff());
			st.setString(5, discount.getextras());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateDiscounts(Discounts discount) {
		try {
			String ins = "update Discounts set DiscountDate=? , PackageID=? , PercentageOff=? , Extras=? Where DiscountID=?";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setDate(1, java.sql.Date.valueOf(discount.getdiscountdate()));
			st.setInt(2, discount.getpackageid());
			st.setInt(3, discount.getpercentoff());
			st.setString(4, discount.getextras());
			st.setInt(5, discount.getdiscountid());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteDiscounts(Discounts discount) {
		try {
			String ins = "delete from Discounts where DiscountID = ?";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setInt(1, discount.getdiscountid());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
