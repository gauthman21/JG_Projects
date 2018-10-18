package main.model;

import java.util.ArrayList;

public class Comments {

	private String commentID;

	private String empId;
	
	private ArrayList<Comment> comments;
	
	public Comments() {}
	
	public Comments(String commentID, String empId, ArrayList<Comment> comments) {
		super();
		this.commentID = commentID;
		this.empId = empId;
		this.comments = comments;
	}



	public void addComment(Comment comment)
	{
		if (comments == null)
		{
			comments = new ArrayList<Comment>();
		}
		
		comments.add(comment);
	}
	
	public ArrayList<Comment> getComments() {
		return comments;
	}
	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}
	public String getCommentID() {
		return commentID;
	}
	public void setCommentID(String commentID) {
		this.commentID = commentID;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}

	@Override
	public String toString() {
		return "Comments [commentID=" + commentID + ", empId=" + empId
				+ ", comments=" + comments + "]";
	}
}
