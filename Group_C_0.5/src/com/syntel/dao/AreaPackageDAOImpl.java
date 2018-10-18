package com.syntel.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.syntel.connections.Connect;
import com.syntel.pojo.copy.AreaPackage;

public class AreaPackageDAOImpl implements AreaPackageDAO{

	@Override
	public List<AreaPackage> getAllAreaPackage() {
		try {
			String ins = "select * from AreaPackages";
			PreparedStatement st = Connect.getConnection().prepareStatement(ins);
			ResultSet result = st.executeQuery();
			List<AreaPackage> areaPackagelist = new ArrayList<>();
			while (result.next()){
				AreaPackage thisArea = new AreaPackage(result.getInt(1), result.getInt(2), result.getInt(3), result.getString(4));
				areaPackagelist.add(thisArea);
			}
			return areaPackagelist;
		}catch (Exception E){
			E.printStackTrace();
		}
		return null;
	}

	@Override
	public AreaPackage getAreaPackage(int AreaPackageID) {
		try {
			String ins = "select * from AreaPackages where AreaPackagesID = ?";
			PreparedStatement st = Connect.getConnection().prepareStatement(ins);
			st.setInt(1, AreaPackageID);
			ResultSet result = st.executeQuery();
			result.next();
			AreaPackage thisAreaPackage = new AreaPackage(result.getInt(1), result.getInt(2), result.getInt(3), result.getString(4));
			return thisAreaPackage;
		}catch (Exception E){
			E.printStackTrace();
		}
		return null;
	}

	@Override
	public void insertAreaPackage(AreaPackage areaPackage) {
		try {
			String ins = "insert into AreaPackages values(?,?,?,?)";
			PreparedStatement st = Connect.getConnection().prepareStatement(ins);
			st.setInt(1, areaPackage.getAreaPackageID());
			st.setInt(2, areaPackage.getPackageID());
			st.setInt(3, areaPackage.getAreaID());
			st.setString(4, areaPackage.getPackageAvailability());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateAreaPackage(AreaPackage areaPackage) {
		try {
			String ins = "update AreaPackages set AreaID=?, PackageID=?, PackageAvaliability=? Where AreaPackagesID=?";
			PreparedStatement st = Connect.getConnection().prepareStatement(ins);
			st.setInt(1, areaPackage.getAreaID());
			st.setInt(2, areaPackage.getPackageID());
			st.setString(3, areaPackage.getPackageAvailability());
			st.setInt(4, areaPackage.getAreaPackageID());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteAreaPackage(AreaPackage areaPackage) {
		try {
			String ins = "delete from AreaPackages where AreaPackagesID = ?";
			PreparedStatement st = Connect.getConnection().prepareStatement(ins);
			st.setInt(1, areaPackage.getAreaPackageID());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public List<Integer> getAreaIds(int id){
		try {
			String ins = "select areaid from AreaPackages where PACKAGEID = ?";
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
	public void deleteAreaPackage(int id) {
		try {
			String ins = "delete from AreaPackages where packageid = ?";
			PreparedStatement st = Connect.getConnection().prepareStatement(ins);
			st.setInt(1, id);
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
