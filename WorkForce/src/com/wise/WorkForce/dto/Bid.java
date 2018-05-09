package com.wise.WorkForce.dto;

public class Bid {
	private int forceId;
	private int bidAmount;
	private int workId;
	private User user;
	private Boolean accepted;
	
	public Boolean getAccepted() {
		return accepted;
	}
	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getBidAmount() {
		return bidAmount;
	}
	public void setBidAmount(int bidAmount) {
		this.bidAmount = bidAmount;
	}
	public int getWorkId() {
		return workId;
	}
	public void setWorkId(int workId) {
		this.workId = workId;
	}
	public int getForceId() {
		return forceId;
	}
	public void setForceId(int forceId) {
		this.forceId = forceId;
	}
	@Override
	public String toString() {
		return "Bid [forceId=" + forceId + ", bidAmount=" + bidAmount + ", workId=" + workId + ", user=" + user
				+ ", accepted=" + accepted + "]";
	}
	
	
	
}
