package com.syntel.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.syntel.connections.Connect;
import com.syntel.pojo.copy.AreaPackage;
import com.syntel.pojo.copy.Pack;
import com.syntel.pojo.copy.PackageDetails;

public class PackageDAOImpl implements PackageDAO {

	@Override
	public List<Pack> getAllPack() {
		try {
			String ins = "select * from Pack";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			ResultSet result = st.executeQuery();
			List<Pack> packlist = new ArrayList<>();
			while (result.next()) {
				Pack thisPack = new Pack(result.getInt(1), result.getString(2),
						result.getString(3), result.getString(4),
						result.getString(5), result.getDouble(6),
						result.getInt(7));
				packlist.add(thisPack);
			}
			return packlist;
		} catch (Exception E) {
			E.printStackTrace();
		}
		return null;
	}

	@Override
	public Pack getPack(int PackID) {
		try {
			String ins = "select * from Pack where PackageID = ?";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setInt(1, PackID);
			ResultSet result = st.executeQuery();
			result.next();
			Pack thisPack = new Pack(result.getInt(1), result.getString(2),
					result.getString(3), result.getString(4),
					result.getString(5), result.getDouble(6), result.getInt(7));
			return thisPack;
		} catch (Exception E) {
			E.printStackTrace();
		}
		return null;
	}

	//@Override
	public void insertPack(Pack pack, String locations[], String items[]) {
		try {
			String ins = "insert into Pack values(?,?,?,?,?,?,?)";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setInt(1, pack.getPackageID());
			st.setString(2, pack.getPackageName());
			st.setString(3, pack.getDescription());
			st.setString(4, pack.getPictureLocation());
			st.setString(5, pack.getMealTime());
			st.setDouble(6, pack.getPrice());
			st.setInt(7, pack.getVegitarian());
			st.executeUpdate(); 
			String ins1 = "select packseq.nextval from dual";
			PreparedStatement st1 = Connect.getConnection()
					.prepareStatement(ins1);
			ResultSet id = st1.executeQuery();
			int id1 = 0;
			while (id.next()) {
				id1 = id.getInt(1)-1;
			}
			for (int i = 0; i < items.length; i++) {
				PackageDetails packageDetails = new PackageDetails(0, id1, Integer.parseInt(items[i]));
				new PackageDetailsDAOImpl().insertPackageDetails(packageDetails);
				
			}
			
			for (int i = 0; i < locations.length; i++) {
				AreaPackage areaPackage = new AreaPackage(0, Integer.parseInt(locations[i]), id1, "Available");
				new AreaPackageDAOImpl().insertAreaPackage(areaPackage);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void updatePack(Pack pack, String locations[], String items[]) {
		try {
			String ins = "update Pack set PackageName=? , Description=? , PictureLocation=? , MealTime=? , Price=? , Vegitarian=? Where PackageID=?";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setString(1, pack.getPackageName());
			st.setString(2, pack.getDescription());
			st.setString(3, pack.getPictureLocation());
			st.setString(4, pack.getMealTime());
			st.setDouble(5, pack.getPrice());
			st.setInt(6, pack.getVegitarian());
			st.setInt(7, pack.getPackageID());
			st.executeUpdate();
			//System.out.println("Updated pack--------");
			try{
				new AreaPackageDAOImpl().deleteAreaPackage(pack.getPackageID());
				System.out.println("Area Pack deleted--------");
				new PackageDetailsDAOImpl().deletePackageDetails(pack.getPackageID());
				//System.out.println("Pack detail deleted--------");
			}catch (Exception e) {
				e.printStackTrace();
			}
			if(items.length > 0){
				for (int i = 0; i < items.length; i++) {
					PackageDetails packageDetails = new PackageDetails(0, pack.getPackageID(), Integer.parseInt(items[i]));
					new PackageDetailsDAOImpl().insertPackageDetails(packageDetails);	
				}
				//System.out.println("Pack detail added--------");
				
			}
			if(locations.length>0){
				
				for (int i = 0; i < locations.length; i++) {
					AreaPackage areaPackage = new AreaPackage(0, Integer.parseInt(locations[i]), pack.getPackageID(), "Available");
					new AreaPackageDAOImpl().insertAreaPackage(areaPackage);
				
				}
				//System.out.println("area pack added--------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deletePack(Pack pack) {
		try {
			String ins = "delete from Pack where PackageID = ?";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setInt(1, pack.getPackageID());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void deletePack(int id) {
		try {
			
			String ins = "delete from Pack where PackageID = ?";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setInt(1, id);
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public int getTotalPacks() {
		try {
			String ins = "select count(*) from Pack";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			ResultSet result = st.executeQuery();
			int totalpacks = 0;
			while (result.next()) {
				totalpacks = result.getInt(1);
			}
			return totalpacks;
		} catch (Exception E) {
			E.printStackTrace();
		}
		return 0;
	}

	

}
