package com.jaydenxiao.common.commonwidget.citypickerview.model;

import java.util.List;

public class ProvinceModel {
	private String name;
	private List<CityModel> sub;
	
	public ProvinceModel() {
		super();
	}

	public ProvinceModel(String name, List<CityModel> sub) {
		super();
		this.name = name;
		this.sub = sub;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CityModel> getSub() {
		return sub;
	}

	public void setSub(List<CityModel> sub) {
		this.sub = sub;
	}

	@Override
	public String toString() {
		return "ProvinceModel [name=" + name + ", cityList=" + sub + "]";
	}
	
}
