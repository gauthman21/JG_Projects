package com.syntel.pojo.copy;

import java.util.ArrayList;
import java.util.List;

import com.syntel.dao.AreaPackageDAOImpl;
import com.syntel.dao.EmployeeDAOImpl;
import com.syntel.dao.PackageDAOImpl;
import com.syntel.dao.PackageDetailsDAOImpl;


public class Pack {
	
	public Pack(){
		
	}
	
	private int PackageID, Vegitarian;
	private String PackageName, Description, PictureLocation, MealTime;
	private double Price;
	
	private String locations[];
	private String items[];
	
	public String[] getItems() {
		return items;
	}

	public void setItems(String[] items) {
		this.items = items;
	}

	public String[] getLocations() {
		return locations;
	}

	public void setLocations(String[] locations) {
		this.locations = locations;
	}

	@Override
	public String toString() {
		return "Package [PackageID=" + PackageID + ", PackageName="
				+ PackageName + ", Description=" + Description
				+ ", PictureLocation=" + PictureLocation + ", MealTime="
				+ MealTime + ", Price=" + Price + ", Vegitarian=" + Vegitarian
				+ "]";
	}

	public int getPackageID() {
		return PackageID;
	}

	public void setPackageID(int packageID) {
		PackageID = packageID;
	}

	public String getPackageName() {
		return PackageName;
	}

	public void setPackageName(String packageName) {
		PackageName = packageName;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getPictureLocation() {
		return PictureLocation;
	}

	public void setPictureLocation(String pictureLocation) {
		PictureLocation = pictureLocation;
	}

	public String getMealTime() {
		return MealTime;
	}

	public void setMealTime(String mealTime) {
		MealTime = mealTime;
	}

	public double getPrice() {
		return Price;
	}

	public void setPrice(double price) {
		Price = price;
	}

	

	public int getVegitarian() {
		return Vegitarian;
	}

	public void setVegitarian(int vegitarian) {
		Vegitarian = vegitarian;

	}

	public Pack(int packageID, String packageName, String description,
			String pictureLocation, String mealTime, double price,
			int vegitarian) {
		super();
		PackageID = packageID;
		PackageName = packageName;
		Description = description;
		PictureLocation = pictureLocation;
		MealTime = mealTime;
		Price = price;
		Vegitarian = vegitarian;
	}
	
	public String insertPack() {
		Pack pack = new Pack(PackageID, PackageName, Description, PictureLocation, MealTime, Price, Vegitarian);
		new PackageDAOImpl().insertPack(pack, locations, items);
		return null;
	}
	public List<Pack> getPacks(){
		List<Pack> packlist = new ArrayList<>();
		packlist = new PackageDAOImpl().getAllPack();
		return packlist;
	}
	public Pack getItem(String id){
		int id1 = Integer.parseInt(id);
		Pack p = new PackageDAOImpl().getPack(id1);
		return p;
	}
	
	public void deletePack(String id){
		int id1 = Integer.parseInt(id);
		new AreaPackageDAOImpl().deleteAreaPackage(id1);
		new PackageDetailsDAOImpl().deletePackageDetails(id1);
		new PackageDAOImpl().deletePack(id1);
	}
	public String updatePack(){
		Pack pack = new Pack(PackageID, PackageName, Description, PictureLocation, MealTime, Price, Vegitarian);
		new PackageDAOImpl().updatePack(pack, locations, items);
		return null;
		
	}
	public int numOfPacks(){
		
		return new PackageDAOImpl().getTotalPacks();
	}

	
}
