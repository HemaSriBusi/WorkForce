package com.wise.WorkForce.dto;

import java.util.List;

public class ServiceType {
	private int serviceTypeId;
	private String name;
	private String icon;
	private boolean active;
	private List<SubServiceType> subServiceType;
	public List<SubServiceType> getSubServiceType() {
		return subServiceType;
	}
	public void setSubServiceType(List<SubServiceType> subServiceType) {
		this.subServiceType = subServiceType;
	}
	public int getServiceTypeId() {
		return serviceTypeId;
	}
	public void setServiceTypeId(int serviceTypeId) {
		this.serviceTypeId = serviceTypeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "ServiceType [serviceTypeId=" + serviceTypeId + ", name=" + name + ", icon=" + icon + ", active="
				+ active + ", getServiceTypeId()=" + getServiceTypeId() + ", getName()=" + getName() + ", getIcon()="
				+ getIcon() + ", isActive()=" + isActive() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	
}
