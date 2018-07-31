package com.marse.martian.entities;

public enum TransStatus {

	PAID("PAID"), PENDING("PENDING");

	private String value;

	private TransStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}


}
