package com.syntel.dao;

import java.util.List;

import com.syntel.pojo.copy.Pack;

public interface PackageDAO {

	public List<Pack> getAllPack();
	public Pack getPack(int PackID);
	public void insertPack(Pack pack, String locations[], String items[]);
	public void updatePack(Pack pack, String locations[], String items[]);
	public void deletePack(Pack pack);
	
}
