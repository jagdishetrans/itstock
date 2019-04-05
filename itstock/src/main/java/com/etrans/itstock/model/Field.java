package com.etrans.itstock.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Field {

	private String name;
	private Object type;
	private boolean isMandatory;
	private Object value;

	public Field() {

	}

	public Field(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	public Field(String name, Object type, boolean isMandatory) {
		super();
		this.name = name;
		this.type = type;
		this.isMandatory = isMandatory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getType() {
		return type;
	}

	public void setType(Object type) {
		this.type = type;
	}

	public boolean getIsMandatory() {
		return isMandatory;
	}

	public void setIsMandatory(boolean isMandatory) {
		this.isMandatory = isMandatory;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
