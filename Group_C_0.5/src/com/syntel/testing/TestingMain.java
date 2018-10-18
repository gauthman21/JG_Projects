package com.syntel.testing;

import java.util.List;

import com.syntel.dao.*;
import com.syntel.pojo.copy.Area;

public class TestingMain {

	public static void main(String[] args) {

		List<Area> a = new AreaDAOImpl().getAllArea();

		for(Area i : a){
			System.out.println(i.toString());
		}
		
	}

}
