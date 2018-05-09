package com.wise.WorkForce.dto;

public class Address {
	private int userId;
	private String doorNumber;
	private String street;
	private String city;
	private String state;
	private int postalCode;
	private String country;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getDoorNo() {
		return doorNumber;
	}
	public void setDoorNo(String doorNo) {
		this.doorNumber = doorNo;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Override
	public String toString() {
		return "Address [userId=" + userId + ", doorNumber=" + doorNumber + ", street=" + street + ", city=" + city
				+ ", state=" + state + ", postalCode=" + postalCode + ", country=" + country + ", getUserId()="
				+ getUserId() + ", getDoorNo()=" + getDoorNo() + ", getStreet()=" + getStreet() + ", getCity()="
				+ getCity() + ", getState()=" + getState() + ", getPostalCode()=" + getPostalCode() + ", getCountry()="
				+ getCountry() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
}
