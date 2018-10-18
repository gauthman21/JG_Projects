package com.syntel.dao;

import java.util.List;

import com.syntel.pojo.copy.Area;

public interface AreaDAO {

	public List<Area> getAllArea();
	public Area getArea(int AreaID);
	public void insertArea(Area area);
	public void updateArea(Area area);
	public void deleteArea(Area area);
	public void deleteRelevantAreaInfo(int AreaID);
}
