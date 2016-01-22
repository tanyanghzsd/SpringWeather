package com.example.springweather.model;

import java.io.Serializable;

public class City implements Serializable{
	private String city;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		
		return city;
	}
	
	
}
