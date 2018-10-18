package com.syntel.pojo.copy;

import java.util.ArrayList;
import java.util.List;

import com.syntel.dao.PackageDetailsDAOImpl;


public class PackageDetails {
	
	
	public PackageDetails(){
		
	}
	private int PackageDetailsID, PackageID, ItemID;

	
	
	@Override
	public String toString() {
		return "PackageDetails [PackageDetailsID=" + PackageDetailsID
				+ ", PackageID=" + PackageID + ", ItemID=" + ItemID + "]";
	}

	public PackageDetails(int packageDetailsID, int packageID, int itemID) {
		super();
		PackageDetailsID = packageDetailsID;
		PackageID = packageID;
		ItemID = itemID;
	}

	public int getPackageDetailsID() {
		return PackageDetailsID;
	}

	public void setPackageDetailsID(int packageDetailsID) {
		PackageDetailsID = packageDetailsID;
	}

	public int getPackageID() {
		return PackageID;
	}

	public void setPackageID(int packageID) {
		PackageID = packageID;
	}

	public int getItemID() {
		return ItemID;
	}

	public void setItemID(int itemID) {
		ItemID = itemID;
	}
	
	public List<String> getItemName(int id){
		List<String> names = new ArrayList<String>();
		names = new PackageDetailsDAOImpl().getItemNames(id); 
		return names;
		
	}
	
	public List<Integer> getItemIds(int id){
		List <Integer> ids = new ArrayList<Integer>();
		ids = new PackageDetailsDAOImpl().getItemIds(id);
		return ids;
		
	}

}
