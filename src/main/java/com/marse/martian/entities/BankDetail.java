package com.marse.martian.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bank_detail")
public class BankDetail extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "account_number")
	private String accountNumber;

	@Column(name = "bank_name")
	private String bankName;

	@Column(name = "ifsc_code")
	private String ifscCode;

	@Column(name = "image")
	private String passImageName;

	@Column(name = "image_data")
	private byte[] passImageData;
	
	@Column(name="pan")
	private String panNumber;
	
	@Column(name="bank_address") 
	private String bankAddress;

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getPassImageName() {
		return passImageName;
	}

	public void setPassImageName(String passImageName) {
		this.passImageName = passImageName;
	}

	public byte[] getPassImageData() {
		return passImageData;
	}

	public void setPassImageData(byte[] passImageData) {
		this.passImageData = passImageData;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getBankAddress() {
		return bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}
}
