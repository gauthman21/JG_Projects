package main.model;

public class Skill 
{
	private String empId;
	private String skillName;
	private String key;
	
	public Skill(){}

	public Skill(String skillName, String key, String empId) {
		super();
		this.skillName = skillName;
		this.key = key;
		this.empId = empId;
	}

	
	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return "Skill [empId=" + empId + ", skillName=" + skillName + ", key="
				+ key + "]";
	}
}
