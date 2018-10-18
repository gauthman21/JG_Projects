package com.syntel.pojo.copy;

import java.util.ArrayList;
import java.util.List;

import com.syntel.dao.AreaPackageDAOImpl;


public class AreaPackage {

	public AreaPackage() {

	}

	private int AreaPackageID, AreaID, PackageID;
	private String PackageAvailability;

	@Override
	public String toString() {
		return "AreaPackage [AreaPackageID=" + AreaPackageID + ", AreaID="
				+ AreaID + ", PackageID=" + PackageID
				+ ", PackageAvailability=" + PackageAvailability + "]";
	}

	public AreaPackage(int areaPackageID, int areaID, int packageID,
			String packageAvailability) {
		super();
		AreaPackageID = areaPackageID;
		AreaID = areaID;
		PackageID = packageID;
		PackageAvailability = packageAvailability;
	}

	public int getAreaPackageID() {
		return AreaPackageID;
	}

	public void setAreaPackageID(int areaPackageID) {
		AreaPackageID = areaPackageID;
	}

	public int getAreaID() {
		return AreaID;
	}

	public void setAreaID(int areaID) {
		AreaID = areaID;
	}

	public int getPackageID() {
		return PackageID;
	}

	public void setPackageID(int packageID) {
		PackageID = packageID;
	}

	public String getPackageAvailability() {
		return PackageAvailability;
	}

	public void setPackageAvailability(String packageAvailability) {
		PackageAvailability = packageAvailability;
	}
	public List<Integer> getAreaIds(int id){
		List <Integer> ids = new ArrayList<Integer>();
		ids = new AreaPackageDAOImpl().getAreaIds(id);
		System.out.println(ids);
		return ids;
		
	}
	
}
