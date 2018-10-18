package main.model;

public class Comment 
{
	private String commentId;
	private String empId;
	private String date;
	private String comment;
	
	public Comment(){}
	
	
	public Comment(String date, String comment) {
		super();
		this.date = date;
		this.comment = comment;
	}
	
	public Comment(String empId, String date, String comment, String commentId) {
		super();
		this.empId = empId;
		this.date = date;
		this.comment = comment;
		this.commentId = commentId;
	}

	
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}


	public String getEmpId() {
		return empId;
	}


	public void setEmpId(String empId) {
		this.empId = empId;
	}


	@Override
	public String toString() {
		return "Comment [empId=" + empId + ", date=" + date + ", comment="
				+ comment + ", commentId=" + commentId + "]";
	}
	
}
