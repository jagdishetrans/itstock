package com.etrans.itstock.model;

public class FieldType {

	private String name;
	private String subType;

	public FieldType() {
	}

	public FieldType(String name, String subType) {
		this.name = name;
		this.subType = subType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

}
