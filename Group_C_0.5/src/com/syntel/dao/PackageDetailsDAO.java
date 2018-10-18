package com.syntel.dao;

import java.util.List;

import com.syntel.pojo.copy.PackageDetails;

public interface PackageDetailsDAO {
	
	public List<PackageDetails> getAllPackageDetails();
	public PackageDetails getPackageDetails(int PackageDetailsID);
	public void insertPackageDetails(PackageDetails packageDetails);
	public void updatePackageDetails(PackageDetails packageDetails);
	public void deletePackageDetails(PackageDetails packageDetails);

}
