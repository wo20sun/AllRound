package com.jaydenxiao.common.commonwidget.citypickerview.model;

import java.util.List;

public class CityModel {
	private String name;
	private String zipcode;
	private List<String> sub;

	public CityModel() {
		super();
	}

	public CityModel(String name, List<String> districtList) {
		super();
		this.name = name;
		this.sub = districtList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public List<String> getSub() {
		return sub;
	}

	public void setSub(List<String> sub) {
		this.sub = sub;
	}

	@Override
	public String toString() {
		return "CityModel [name=" + name + ", districtList=" + sub
				+ "]";
	}
	
}
