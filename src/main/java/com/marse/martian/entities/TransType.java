package com.marse.martian.entities;

public enum TransType {

	CREDIT("CREDIT"), DEBIT("DEBIT");
	
	private String value;

	private TransType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}


}
