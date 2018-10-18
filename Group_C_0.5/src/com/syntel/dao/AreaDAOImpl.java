package com.syntel.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.syntel.connections.Connect;
import com.syntel.pojo.copy.Area;

public class AreaDAOImpl{

	//implements AreaDAO 
	//@Override
	public List<Area> getAllArea() {
		try {
			String ins = "select * from Areas";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			ResultSet result = st.executeQuery();
			List<Area> arealist = new ArrayList<>();
			while (result.next()) {
				Area thisArea = new Area(result.getInt(1), result.getString(2), result.getString(3));
				arealist.add(thisArea);
			}
			return arealist;
		} catch (Exception E) {
			E.printStackTrace();
		}
		return null;
	}

//	@Override
	public Area getArea(int AreaID) {
		try {
			String ins = "select * from Areas where AreaID = ?";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setInt(1, AreaID);
			ResultSet result = st.executeQuery();
			result.next();
			Area thisArea = new Area(result.getInt(1), result.getString(2), result.getString(3));
			return thisArea;
		} catch (Exception E) {
			E.printStackTrace();
		}
		return null;
	}

	//@Override
	public void insertArea(Area area) {
		try {
			String ins = "insert into Areas values(?,?,?)";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setInt(1, area.getAreaid());
			st.setString(2, area.getLocation());
			st.setString(3, area.getZipcode());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//@Override
	public void updateArea(Area area) {
		try {
			String ins = "update Areas set Loc=? , Zip=? Where AreaID=?";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setString(1, area.getLocation());
			st.setString(2, area.getZipcode());
			st.setInt(3, area.getAreaid());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//@Override
	public void deleteArea(Area area) {
		try {
			String ins = "delete from Areas where AreaID = ?";
			PreparedStatement st = Connect.getConnection()
					.prepareStatement(ins);
			st.setInt(1, area.getAreaid());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Facilitates the deletion process by deleting relevant child tables
		
		public void deleteRelevantAreaInfo(int id) {
			try {
				String ins = "delete from AreaEmployee where AreaID = ?";
				PreparedStatement st = Connect.getConnection()
						.prepareStatement(ins);
				st.setInt(1, id);
				st.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				String ins = "delete from AreaPackages where AreaID = ?";
				PreparedStatement st = Connect.getConnection()
						.prepareStatement(ins);
				st.setInt(1, id);
				st.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}
	
	

}
