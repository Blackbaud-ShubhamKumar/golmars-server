package com.marse.martian.dto;

public class WalletData {

	private String walletType;

	private Float amount;

	private String transType;

	private String transStatus;

	private String clearBalancePopDate;
	
	private String mainBalancePopDate;

	public String getWalletType() {
		return walletType;
	}

	public void setWalletType(String walletType) {
		this.walletType = walletType;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getTransStatus() {
		return transStatus;
	}

	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}

	public String getDate() {
		return clearBalancePopDate;
	}

	public String getClearBalancePopDate() {
		return clearBalancePopDate;
	}

	public void setClearBalancePopDate(String clearBalancePopDate) {
		this.clearBalancePopDate = clearBalancePopDate;
	}

	public String getMainBalancePopDate() {
		return mainBalancePopDate;
	}

	public void setMainBalancePopDate(String mainBalancePopDate) {
		this.mainBalancePopDate = mainBalancePopDate;
	}
 }
