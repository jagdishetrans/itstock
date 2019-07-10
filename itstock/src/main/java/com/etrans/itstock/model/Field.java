package com.etrans.itstock.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Document
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Field {

	@Id
	private String key;
	private String name;
	// private FieldType type;
	private String type;
	private String subType;
	private Object value;
	private Object defaultValue;
	private boolean mandatory;
	private String placeholder;
	private Object options;

	public Field() {

	}

	public Field(String name, Object value) {
		this.name = name;
		this.value = value;
	}

//	public Field(String name, FieldType type, boolean mandatory) {
//		this.name = name;
//		this.type = type;
//		this.mandatory = mandatory;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public FieldType getType() {
//		return type;
//	}
//
//	public void setType(FieldType type) {
//		this.type = type;
//	}

	public boolean isMandatory() {
		return mandatory;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(Object defaultValue) {
		this.defaultValue = defaultValue;
	}

	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	public Object getOptions() {
		return options;
	}

	public void setOptions(Object options) {
		this.options = options;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public void setFieldsForAsset(Field field) {
		mandatory = field.isMandatory();
		name = field.getName();
		placeholder = field.getPlaceholder();
		options = field.options;
		type = field.type;
		defaultValue = field.defaultValue;
		subType = field.subType;
	}

	public void setFieldsForAssetType(Field field) {
		options = field.options;
		type = field.type;
		name = field.name;
		subType = field.subType;
	}

	@Override
	public boolean equals(Object obj) {
		if ((null == obj) || (obj.getClass() != UUID.class))
			return false;
		Field field = (Field) obj;
		return UUID.fromString(key).equals(UUID.fromString(field.key));
	}

}
