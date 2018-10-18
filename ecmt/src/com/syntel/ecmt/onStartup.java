package com.syntel.ecmt;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.bind.JAXBException;

import main.DAO.Dao;
import main.constants.Constants;
import main.model.Employee;
import main.model.Profile;
import main.utils.PersistCache;

public class onStartup {
	
	private static ArrayList<Profile> holdAllProfilesFromCache = new ArrayList<Profile>();
	private static Dao myDaoObj = new Dao();
	private static File file = new File(Constants.cacheFilePath);
	
	public ArrayList<Profile> grabEmpsFromCache(){
		
		ArrayList<Employee> allEmployees = myDaoObj.getAllEmployees();

		for (int empList = 0; empList < allEmployees.size(); empList++) {

			Profile empProfile = myDaoObj.getEmployee(allEmployees.get(
					empList).getEmpId());
			holdAllProfilesFromCache.add(empProfile);
		}
		
		return holdAllProfilesFromCache;
	}
	
	public static void createArrayListOfEmp() throws ClassNotFoundException,
			SQLException, JAXBException {

		if(!file.exists()){
			new PersistCache().createCacheFile(Constants.cacheFilePath);
		}
		
		if (Dao.getConnection() != null) {
			
			ArrayList<Employee> allEmployees = myDaoObj.getAllEmployees();

			for (int empList = 0; empList < allEmployees.size(); empList++) {

				Profile empProfile = myDaoObj.getEmployee(allEmployees.get(
						empList).getEmpId());
				holdAllProfilesFromCache.add(empProfile);

			}
			
		} else {
			
			Boolean cacheFileCheck = PersistCache
					.checkIfFileExists(Constants.cacheBackupFilePathAfterSession);
			if (cacheFileCheck.TRUE) {

				ArrayList<Profile> allProfilesFromCacheList = PersistCache
						.readFullCache(Constants.cacheBackupFilePathAfterSession);
				holdAllProfilesFromCache = allProfilesFromCacheList;
				Boolean cacheUpdateStatus = PersistCache.updateCacheFile(
						Constants.cacheFilePath, allProfilesFromCacheList);
				System.out.println("Cache updated " + cacheUpdateStatus);

				for (int cacheProfileSize = 0; cacheProfileSize < allProfilesFromCacheList
						.size(); cacheProfileSize++)
					System.out.println("Profiles in Cache read are: "
							+ allProfilesFromCacheList.get(cacheProfileSize));
			}
		}
	}
	
	public static void updateCacheFileWithProfilesFromDatabase() throws JAXBException{
		
		Boolean cacheUpdateStatus = PersistCache.updateCacheFile(
				Constants.cacheFilePath, holdAllProfilesFromCache);
		Boolean cacheBackupFileUpdateStatus = PersistCache.updateCacheFile(
				Constants.cacheBackupFilePath, holdAllProfilesFromCache);
		System.out.println("Cache updated " + cacheUpdateStatus);
		System.out.println("Cache updated " + cacheBackupFileUpdateStatus);
	}
	
	public static void readFromCacheFile(){
		
		ArrayList<Profile> allProfilesFromCacheList = PersistCache
				.readFullCache(Constants.cacheFilePath);

		for (int cacheProfileSize = 0; cacheProfileSize < allProfilesFromCacheList
				.size(); cacheProfileSize++)
			System.out.println("Profiles in Cache read are: "
					+ allProfilesFromCacheList.get(cacheProfileSize));
	}
	
	public static void fetchSpecificProfileFromCache(){
		
		Profile getSpecificProfile = PersistCache.getProfile("1234567",
				Constants.cacheFilePath);

		if (getSpecificProfile != null) {
			System.out.println("Profile from cache is: "
					+ getSpecificProfile.toString());
		} else {
			System.out.println("No Match found for the provided emp ID");
		}
	}
	
	public static Profile fetchProfileObjectById(){
		
		Profile getSpecificProfile = PersistCache.getProfile("1234567",
				Constants.cacheFilePath);

		if (getSpecificProfile != null) {
			System.out.println("Profile from cache is: "
					+ getSpecificProfile.toString());
			return getSpecificProfile;
		} else {
			System.out.println("No Match found for the provided emp ID");
		}
		return getSpecificProfile;
	}
	
	public static void updateCache() throws JAXBException{
		
		// 5) Update the Profile Object --------------
		Profile getSpecificProfile = fetchProfileObjectById();
		getSpecificProfile.getEmployee().setPhone("(999)777-5555");

				// 6) Update the Cache --------------
				Boolean cacheUpdateProfileInCache = PersistCache
						.updateCacheFileWithSpecificProfile(Constants.cacheFilePath,
								getSpecificProfile, "1234567");
	}
	
	public static void updateDB() throws JAXBException, ClassNotFoundException, SQLException{
		
		if(Dao.getConnection() != null){
			ArrayList<Profile> allProfilesFromCacheList = PersistCache
					.readFullCache(Constants.cacheFilePath);

			for (int cacheProfileSize = 0; cacheProfileSize < allProfilesFromCacheList
					.size(); cacheProfileSize++) {

				Profile tmpProfile = (Profile) allProfilesFromCacheList
						.get(cacheProfileSize);

				if (tmpProfile.isUpdated())
					myDaoObj.updateEmployee(tmpProfile.getEmployee());

			}
		}else{
			ArrayList<Profile> allProfilesFromCacheList = PersistCache
					.readFullCache(Constants.cacheFilePath);
			Boolean cacheUpdateStatusAfterDBCheck = PersistCache.updateCacheFile(
					Constants.cacheBackupFilePathAfterSession, allProfilesFromCacheList);
			System.out.println("Cache updated " + cacheUpdateStatusAfterDBCheck);
		}
	}
}