package com.wise.WorkForce.dto;

import java.util.List;

public class SubServiceType {
	private int subServiceTypeId;
	private String name;
	private int ServiceTypeId;
	private boolean active;
	private String Icon;
	List<SubServiceType> subServiceType;
	public List<SubServiceType> getSubServiceType() {
		return subServiceType;
	}
	public void setSubServiceType(List<SubServiceType> subServiceType) {
		this.subServiceType = subServiceType;
	}
	public String getIcon() {
		return Icon;
	}
	public void setIcon(String icon) {
		Icon = icon;
	}
	public int getSubServiceTypeId() {
		return subServiceTypeId;
	}
	public void setSubServiceTypeId(int subServiceTypeId) {
		this.subServiceTypeId = subServiceTypeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getServiceTypeId() {
		return ServiceTypeId;
	}
	public void setServiceTypeId(int serviceTypeId) {
		ServiceTypeId = serviceTypeId;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "SubServiceType [subServiceTypeId=" + subServiceTypeId + ", name=" + name + ", ServiceTypeId="
				+ ServiceTypeId + ", active=" + active + ", Icon=" + Icon + ", subServiceType=" + subServiceType + "]";
	}
}