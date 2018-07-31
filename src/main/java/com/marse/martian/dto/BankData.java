package com.marse.martian.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BankData {
	
	private String guestName;
	
	private String accountNumber;
	
	private String bank;
	
	private String ifscCode;
	
	private String bankAddress;
	
	private String panNumber;
	
	private String passbook;

	@JsonProperty("accHolderFirstName")
	public String getGuestName() {
		return guestName;
	}

	@JsonProperty("accHolderFirstName")
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	@JsonProperty("accountNo")
	public String getAccountNumber() {
		return accountNumber;
	}

	@JsonProperty("accountNo")
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@JsonProperty("bankName")
	public String getBank() {
		return bank;
	}

	@JsonProperty("bankName")
	public void setBank(String bank) {
		this.bank = bank;
	}

	@JsonProperty("ifscCode")
	public String getIfscCode() {
		return ifscCode;
	}

	@JsonProperty("ifscCode")
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	@JsonProperty("bankAddress")
	public String getBankAddress() {
		return bankAddress;
	}

	@JsonProperty("bankAddress")
	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	@JsonProperty("panCard")
	public String getPanNumber() {
		return panNumber;
	}

	@JsonProperty("panCard")
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	@JsonProperty("bankCheckPassbook")
	public String getPassbook() {
		return passbook;
	}

	@JsonProperty("bankCheckPassbook")
	public void setPassbook(String passbook) {
		this.passbook = passbook;
	}

}
