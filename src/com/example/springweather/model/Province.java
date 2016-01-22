package com.example.springweather.model;

import java.io.Serializable;
import java.util.List;



public class Province implements Serializable{

	private String provinceName;

	private List<Cities> cities;

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public List<Cities> getCitySet() {
		return cities;
	}

	public void setCitySet(List<Cities> cities) {
		this.cities = cities;
	}

	@Override
	public String toString() {
		return provinceName;
	}




}
