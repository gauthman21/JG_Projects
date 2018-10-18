package main.model;

import java.util.ArrayList;

public class TotalVertAcc 
{
	
	private ArrayList<DropdownObj> vertDetails;
	private ArrayList<DropdownObj> accDetails;
	
	public TotalVertAcc(){}
	
	public TotalVertAcc(ArrayList<DropdownObj> vertDetails,
			ArrayList<DropdownObj> accDetails) {
		super();
		this.vertDetails = vertDetails;
		this.accDetails = accDetails;
	}

	public void addVertDetails(DropdownObj obj)
	{
		if (vertDetails == null)
			vertDetails = new ArrayList<DropdownObj>();
		
		vertDetails.add(obj);
	}
	
	public void addAccDetails(DropdownObj obj)
	{
		if (accDetails == null)
			accDetails = new ArrayList<DropdownObj>();
		
		accDetails.add(obj);
	}
	
	public ArrayList<DropdownObj> getVertDetails() {
		return vertDetails;
	}

	public void setVertDetails(ArrayList<DropdownObj> vertDetails) {
		this.vertDetails = vertDetails;
	}

	public ArrayList<DropdownObj> getAccDetails() {
		return accDetails;
	}

	public void setAccDetails(ArrayList<DropdownObj> accDetails) {
		this.accDetails = accDetails;
	}

	@Override
	public String toString() {
		return "TotalVertAcc [vertDetails=" + vertDetails + ", accDetails="
				+ accDetails + "]";
	}
}
