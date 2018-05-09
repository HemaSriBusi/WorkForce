package com.wise.WorkForce.dto;

public class Force {
	private int forceId;
	private int subServiceTypeId;
	private int experience;
	private double costPerHour;
	private String comment;
	public int getForceId() {
		return forceId;
	}
	public void setForceId(int forceId) {
		this.forceId = forceId;
	}
	public int getSubServiceTypeId() {
		return subServiceTypeId;
	}
	public void setSubServiceTypeId(int subServiceTypeId) {
		this.subServiceTypeId = subServiceTypeId;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public double getCostPerHour() {
		return costPerHour;
	}
	public void setCostPerHour(double costPerHour) {
		this.costPerHour = costPerHour;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "Force [forceId=" + forceId + ", subServiceTypeId=" + subServiceTypeId + ", experience=" + experience
				+ ", costPerHour=" + costPerHour + ", comment=" + comment + ", getForceId()=" + getForceId()
				+ ", getSubServiceTypeId()=" + getSubServiceTypeId() + ", getExperience()=" + getExperience()
				+ ", getCostPerHour()=" + getCostPerHour() + ", getComment()=" + getComment() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
