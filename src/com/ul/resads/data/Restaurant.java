package com.ul.resads.data;

public class Restaurant {
	private String name;
	private String address;
	private String logoPath;
	
	
	public Restaurant(String name, String address, String logoPath) {
		super();
		this.name = name;
		this.address = address;
		this.logoPath = logoPath;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLogoPath() {
		return logoPath;
	}
	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}
	

}
