package main.model;

import java.util.ArrayList;


public class TrainedEmpSkills {
	
	
	private String empId;
	
	private ArrayList<Skill> skills;
	
	
	public TrainedEmpSkills() {}
	
	public TrainedEmpSkills(String empId,
			ArrayList<Skill> skills) {
		super();
		this.empId = empId;
		this.skills = skills;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public ArrayList<Skill> getSkills() {
		return skills;
	}

	public void setSkills(ArrayList<Skill> skills) {
		this.skills = skills;
	}

	@Override
	public String toString() {
		return "TrainedEmpSkills [empId=" + empId + ", skills=" + skills + "]";
	}
}