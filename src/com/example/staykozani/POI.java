package com.example.staykozani;

import android.content.res.TypedArray;

public class POI{
	
	private String name;
	private int type;
	private double latitude;
	private double longitude;
	private String address;
	private String description;
	private String detailedDescription;
	private TypedArray icon;
	private int distance;
	


	public POI(String name, int type, double latitude, double longitude,
			String address, String description, String detailedDescription, TypedArray icon) {
		
		super();
		this.name = name;
		this.type = type;
		this.latitude = latitude;
		this.longitude = longitude;
		this.address = address;
		this.description = description;
		this.detailedDescription = detailedDescription;
		this.icon = icon;
				
	}
	
	public String getName() {
		return name;		
	}
	
	public void setName(String name) {
		this.name = name;		
	}
	
	public int getType() {
		return type;		
	}
	
	public void setType(int type) {
		this.type = type;		
	}
	
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public String getAddress() {
		return address;		
	}
	
	public void setAddress(String address) {
		this.address = address;		
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDetailedDescription() {
		return detailedDescription;
	}

	public void setDetailedDescription(String detailedDescription) {
		this.detailedDescription = detailedDescription;
	}


	public TypedArray getIcon() {
		return icon;
	}

	public void setIcon(TypedArray icon) {
		this.icon = icon;
	}
	
	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

}