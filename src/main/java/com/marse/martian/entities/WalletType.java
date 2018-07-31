package com.marse.martian.entities;

public enum WalletType {

	MAIN("MAIN"), BINARY("BINARY"), FRENCHISE("FRENCHISE");
	private String value;

	private WalletType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
