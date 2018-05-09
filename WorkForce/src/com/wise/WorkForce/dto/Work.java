package com.wise.WorkForce.dto;

import java.util.Date;
import java.util.List;

public class Work {
	private int workId;
	private String description;
	private int subServiceTypeId;
	private int userId;
	private boolean accepted;
	public List<Bid> getBid() {
		return bid;
	}
	public void setBid(List<Bid> bid) {
		this.bid = bid;
	}
	private Date date;
	private SubServiceType subServiceType;
	private List<Bid> bid;
	private User user;
	private Address address;
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public int getWorkId() {
		return workId;
	}
	public void setWorkId(int workId) {
		this.workId = workId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getSubServiceTypeId() {
		return subServiceTypeId;
	}
	public void setSubServiceTypeId(int subServiceTypeId) {
		this.subServiceTypeId = subServiceTypeId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public boolean isAccepted() {
		return accepted;
	}
	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public SubServiceType getSubServiceType() {
		return subServiceType;
	}
	public void setSubServiceType(SubServiceType subServiceType) {
		this.subServiceType = subServiceType;
	}
	@Override
	public String toString() {
		return "Work [workId=" + workId + ", description=" + description + ", subServiceTypeId=" + subServiceTypeId
				+ ", userId=" + userId + ", accepted=" + accepted + ", date=" + date + ", subServiceType="
				+ subServiceType + ", bid=" + bid + ", user=" + user + ", address=" + address + "]";
	}
	
	
	
	
	
}
