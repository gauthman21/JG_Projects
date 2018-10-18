package com.syntel.pojo.copy;

import java.util.ArrayList;
import java.util.List;

import com.syntel.dao.AreaDAOImpl;
import com.syntel.dao.CustomerOrderDAOImpl;
import com.syntel.dao.ItemDAOImpl;

//import com.syntel.dao.AreaDAOImpl;

public class Area {

	public Area() {

	}
	private int areaid;
	private String location, zipcode;

	
	public Area(int areaid, String location, String zipcode) {
		this.areaid = areaid;
		this.location = location;
		this.zipcode = zipcode;
	}

	public int getAreaid() {
		return areaid;
	}
	
	public void setAreaid(int areaid) {
		this.areaid = areaid;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public void addArea() {
		Area a = new Area(this.areaid, this.location, this.zipcode);
		new AreaDAOImpl().insertArea(a);
	}
	
	public String test(){
		return (areaid + location + zipcode);
	}
	
	public List<Area> getAll(){
		return new AreaDAOImpl().getAllArea();
	}
	
	public void deleteArea(int id){
		new AreaDAOImpl().deleteRelevantAreaInfo(id);
		List<CustomerOrder> colist = new CustomerOrderDAOImpl().getAllCustomerOrder();
		for(CustomerOrder coitem : colist){
			if(coitem.getArea()==id){
				coitem.deleteOrder(coitem.getOrderid());
			}
		}
		new AreaDAOImpl().deleteArea(new AreaDAOImpl().getArea(id));		
	}
	public Area getArea(int id){
		return new AreaDAOImpl().getArea(id);
	}
	public void updateArea(){
		Area a = new Area(this.areaid, this.location, this.zipcode);
		new AreaDAOImpl().updateArea(a);
	}
	public List<Area> getAreas(){
		List<Area> arealist = new ArrayList<>();
		arealist = new AreaDAOImpl().getAllArea();
		return arealist;

	}
	
}
