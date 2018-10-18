package com.syntel.dao;

import java.util.List;

import com.syntel.pojo.copy.AreaPackage;

public interface AreaPackageDAO {

	public List<AreaPackage> getAllAreaPackage();
	public AreaPackage getAreaPackage(int AreaPackageID);
	public void insertAreaPackage(AreaPackage areaPackage);
	public void updateAreaPackage(AreaPackage areaPackage);
	public void deleteAreaPackage(AreaPackage areaPackage);
	
}
