package main.utils;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;

import javax.xml.bind.JAXBException;

import main.constants.Constants;
import main.model.Profile;

public class PersistCache {

	private static File persistsFile = null;

	public static void createCacheFile(String filePath) {
		;
		System.out.println("Going to create a chache File for ECMT.");

		// Create a file Object to Persists
		try {

			if (persistsFile.exists()) {
				persistsFile.delete();
			}

			persistsFile = new File(filePath);
			Boolean fileCreated = persistsFile.createNewFile();

			System.out.println("File Created in location: "
					+ persistsFile.getPath() + " and " + fileCreated);

			if (persistsFile.length() > 0) {
				PrintWriter persistFileWriter = new PrintWriter(persistsFile);
				persistFileWriter.print("");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Update the file

	public static Boolean updateCacheFile(String cacheFilePath,
			ArrayList<Profile> contents) throws JAXBException {

		System.out
				.println("Going to update the overall cache via updateCacheFile.");
		Boolean updateStatus = true;

		System.out.println("In updateCacheFile: Employee Details ---  "
				+ contents.toString());

		try {

			XMLEncoder updateCache = new XMLEncoder(new BufferedOutputStream(
					new FileOutputStream(cacheFilePath)));
			updateCache.writeObject(contents);
			updateCache.close();

		} catch (IOException cacheUpdateException) {
			updateStatus = false;
			System.out.println(cacheUpdateException);
		}

		return updateStatus;
	}

	// Update specific profile
	public static Boolean updateCacheFileWithSpecificProfile(
			String cacheFilePath, Profile profileToBeUpdated, String empId)
			throws JAXBException {

		System.out
				.println("Going to update specific Profile in Cache via updateCacheFileWithSpecificProfile.");
		Boolean specificProfileUpdateStatus = false;
		Profile tmpEmpProfile = null;
		ArrayList<Profile> contents = readFullCache(cacheFilePath);

		for (int cacheProfileSize = 0; cacheProfileSize < contents.size(); cacheProfileSize++) {
			tmpEmpProfile = (Profile) contents.get(cacheProfileSize);

			if (tmpEmpProfile.getEmployee().getEmpId()
					.equals(profileToBeUpdated.getEmployee().getEmpId())) {
				contents.remove(cacheProfileSize);
				profileToBeUpdated.setUpdated(true);
				contents.add(profileToBeUpdated);

				// Update the contents to cache file
				Boolean cacheFileStatus = updateCacheFile(cacheFilePath,
						contents);

				if (cacheFileStatus)
					specificProfileUpdateStatus = true;
				else
					specificProfileUpdateStatus = false;
			} else {
				specificProfileUpdateStatus = false;
			}
		}

		return specificProfileUpdateStatus;
	}

	// Read from Cache
	public static ArrayList readFullCache(String cacheFilePath) {

		System.out.println("Going to read full cache via readFullCache.");
		ArrayList<Profile> allProfilesFromCacheList = null;

		System.out.println("Reading Cache details");

		try {
			XMLDecoder e = new XMLDecoder(new BufferedInputStream(
					new FileInputStream(cacheFilePath)));

			allProfilesFromCacheList = (ArrayList<Profile>) e.readObject();
			e.close();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}

		return allProfilesFromCacheList;
	}

	// Read specific Profile from Cache
	public static Profile getProfile(String profileEmpId, String cacheFilePath) {

		System.out.println("Going to read specific profile via getProfile.");
		Profile empProfile = null;

		ArrayList<Profile> getAllProfiles = readFullCache(cacheFilePath);

		for (int cacheProfileSize = 0; cacheProfileSize < getAllProfiles.size(); cacheProfileSize++) {
			empProfile = (Profile) getAllProfiles.get(cacheProfileSize);

			if (empProfile.getEmployee().getEmpId().equals(profileEmpId)) {
				return empProfile;
			}

			if (empProfile.getEmployee().getStatus().equals(profileEmpId)) {
				return empProfile;
			}
			if (empProfile.getSkills().getSkills().toString()
					.contains(profileEmpId)) {
				return empProfile;
			}
		}

		return empProfile;
	}

	// Delete the file
	public static Boolean deleteCacheFile(String cacheFilePath)
			throws JAXBException {

		System.out.println("Going to remove all contents of the cache.");
		ArrayList<Profile> contents = null;

		updateCacheFile(cacheFilePath, contents);
		return null;
	}

	public static Boolean checkIfFileExists(String cacheFilePath) {

		persistsFile = new File(cacheFilePath);

		if (persistsFile.exists()) {
			return true;
		} else {
			return false;
		}
	}
}
