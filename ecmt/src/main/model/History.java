package main.model;

import java.util.ArrayList;

public class History {

	private String historyID;

	private String empId;
	
	private ArrayList<Comment> comments;
	
	public History() {}
	
	
	
	public History(String historyID, String empId, ArrayList<Comment> comments) {
		super();
		this.historyID = historyID;
		this.empId = empId;
		this.comments = comments;
	}

	
	public void addComment(Comment comment)
	{
		if (comments == null)
		{
			comments = new ArrayList<Comment>();
		}
		
		this.comments.add(comment);
	}
	
	public ArrayList<Comment> getComments() {
		return comments;
	}
	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}
	public String getHistoryID() {
		return historyID;
	}
	public void setHistoryID(String historyID) {
		this.historyID = historyID;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}



	@Override
	public String toString() {
		return "History [historyID=" + historyID + ", empId=" + empId
				+ ", comments=" + comments + "]";
	}
}
