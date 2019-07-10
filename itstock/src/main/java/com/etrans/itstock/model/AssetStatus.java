package com.etrans.itstock.model;

public enum AssetStatus {

	INSTOCK(0), ASSIGNED(1), DEPRECATED(2), SOLD(3);

	public final int value;

	AssetStatus(int value) {
		this.value = value;
	}

	public int valueOf() {
		return value;
	}
}
