package com.syntel.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.syntel.connections.Connect;
import com.syntel.pojo.copy.PackageDetails;

public class PackageDetailsDAOImpl implements PackageDetailsDAO{

	@Override
	public List<PackageDetails> getAllPackageDetails() {
		try {
			String ins = "select * from PackageDetails";         
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			ResultSet result = st.executeQuery();
			List<PackageDetails> packageDetailslist = new ArrayList<>();
			while (result.next()) { 
				PackageDetails thisPackageDetails = new PackageDetails(result.getInt(1), result.getInt(2), result.getInt(3));
				packageDetailslist.add(thisPackageDetails);
			}
			return packageDetailslist;
		} catch (Exception E) {
			E.printStackTrace();
		}
		return null;
	}

	@Override
	public PackageDetails getPackageDetails(int PackageDetailsID) {
		try {
			String ins = "select * from PackageDetails where PackageDetailsID = ?";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setInt(1, PackageDetailsID);
			ResultSet result = st.executeQuery();
			result.next();
			PackageDetails thisPackageDetails = new PackageDetails(result.getInt(1), result.getInt(2), result.getInt(3));
			return thisPackageDetails;
		} catch (Exception E) {
			E.printStackTrace();
		}
		return null;
	}

	@Override
	public void insertPackageDetails(PackageDetails packageDetails) {
		try {
			String ins = "insert into PackageDetails values(?,?,?)";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setInt(1, packageDetails.getPackageDetailsID());
			st.setInt(2, packageDetails.getPackageID());
			st.setInt(3, packageDetails.getItemID());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updatePackageDetails(PackageDetails packageDetails) {
		try {
			String ins = "update PackageDetails set PackageID=? , ItemID=? Where PackageDetailsID=?";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setInt(1, packageDetails.getPackageID());
			st.setInt(2, packageDetails.getItemID());
			st.setInt(3, packageDetails.getPackageDetailsID());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	@Override
	public void deletePackageDetails(PackageDetails packageDetails) {
		try {
			String ins = "delete from PackageDetails where PackageDetailsID = ?";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setInt(1, packageDetails.getPackageDetailsID());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<String> getItemNames(int id) {
		try {
			String ins = "select itemname from ITEMS where ITEMID in (select itemid from PACKAGEDETAILS where PACKAGEID = ?)";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setInt(1, id);
			ResultSet result = st.executeQuery();
			List<String> names = new ArrayList<String>();
			while (result.next()) {
				names.add(result.getString(1));
			}
			
			return names;
		} catch (Exception E) {
			E.printStackTrace();
		}
		
		
		return null;
	}
	
	public List<Integer> getItemIds(int id){
		
		try {
			String ins = "select itemid from PACKAGEDETAILS where PACKAGEID = ?";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setInt(1, id);
			ResultSet result = st.executeQuery();
			List<Integer> ids = new ArrayList<Integer>();
			while (result.next()) {
				ids.add(result.getInt(1));
			}
			
			return ids;
		} catch (Exception E) {
			E.printStackTrace();
		}
		return null;
		
	}
	
	public void deletePackageDetails(int id) {
		try {
			String ins = "delete from PackageDetails where packageid = ?";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setInt(1, id);
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
