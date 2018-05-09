package com.wise.WorkForce.dto;

public class Feedback {
	private int givenById;
	private int givenToId;
	private String feedbackType;
	private String comments;
	private int rating;
	public int getGivenById() {
		return givenById;
	}
	public void setGivenById(int givenById) {
		this.givenById = givenById;
	}
	@Override
	public String toString() {
		return "Feedback [givenById=" + givenById + ", givenToId=" + givenToId + ", feedbackType=" + feedbackType
				+ ", comments=" + comments + ", rating=" + rating + ", getGivenById()=" + getGivenById()
				+ ", getGivenToId()=" + getGivenToId() + ", getFeedbackType()=" + getFeedbackType() + ", getComments()="
				+ getComments() + ", getRating()=" + getRating() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	public int getGivenToId() {
		return givenToId;
	}
	public void setGivenToId(int givenToId) {
		this.givenToId = givenToId;
	}
	public String getFeedbackType() {
		return feedbackType;
	}
	public void setFeedbackType(String feedbackType) {
		this.feedbackType = feedbackType;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	
}
