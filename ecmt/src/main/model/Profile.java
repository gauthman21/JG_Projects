package main.model;

public class Profile {
	private Employee employee;
	private Account account;
	private Vertical vertical;
	private History history;
	private Comments comments;
	private EmpSkills skills;
	private TrainedEmpSkills trainings;
	private RM rm;
	private boolean isUpdated;

	public Profile() {
	}

	public RM getRm() {
		return rm;
	}

	public void setRm(RM rm) {
		this.rm = rm;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Vertical getVertical() {
		return vertical;
	}

	public void setVertical(Vertical vertical) {
		this.vertical = vertical;
	}

	public History getHistory() {
		return history;
	}

	public void setHistory(History history) {
		this.history = history;
	}

	public Comments getComments() {
		return comments;
	}

	public void setComments(Comments comments) {
		this.comments = comments;
	}

	public EmpSkills getSkills() {
		return skills;
	}

	public void setSkills(EmpSkills empSkills) {
		this.skills = empSkills;
	}

	public TrainedEmpSkills getTrainings() {
		return trainings;
	}

	public void setTrainings(TrainedEmpSkills trainings) {
		this.trainings = trainings;
	}

	@Override
	public String toString() {
		return "Profile [employee=" + employee + ", account=" + account
				+ ", vertical=" + vertical + ", history=" + history
				+ ", comments=" + comments + ", skills=" + skills
				+ ", trainings=" + trainings + ", rm=" + rm + "]";
	}

	public boolean isUpdated() {
		return isUpdated;
	}

	public void setUpdated(boolean isUpdated) {
		this.isUpdated = isUpdated;
	}

}
