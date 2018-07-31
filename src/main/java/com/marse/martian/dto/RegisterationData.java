package com.marse.martian.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterationData {

	private String sponsorId;

	private String accountNo;

	private String position;

	private String firstName;

	private String lastName;

	private String bank;

	private String ifscCode;

	private String emailId;

	private String phoneNumber;

	private String aadharNumber;

	private String password;
	
	private String panCard;

	private String image;

	private boolean acceptedTnc;

	@JsonProperty("sponcorId")
	public String getSponsorId() {
		return sponsorId;
	}

	@JsonProperty("sponcorId")
	public void setSponsorId(String sponsorId) {
		this.sponsorId = sponsorId;
	}

	@JsonProperty("accountNo")
	public String getAccountNo() {
		return accountNo;
	}

	@JsonProperty("accountNo")
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	@JsonProperty("nodePos")
	public String getPosition() {
		return position;
	}

	@JsonProperty("nodePos")
	public void setPosition(String position) {
		this.position = position;
	}

	@JsonProperty("accHolderFirstName")
	public String getFirstName() {
		return firstName;
	}

	@JsonProperty("accHolderFirstName")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@JsonProperty("accHolderLastName")
	public String getLastName() {
		return lastName;
	}

	@JsonProperty("accHolderLastName")
	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	@JsonProperty("emailId")
	public String getEmailId() {
		return emailId;
	}

	@JsonProperty("emailId")
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@JsonProperty("mobileNumber")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	@JsonProperty("mobileNumber")
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@JsonProperty("aadharCard")
	public String getAadharNumber() {
		return aadharNumber;
	}

	@JsonProperty("aadharCard")
	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	@JsonProperty("password")
	public String getPassword() {
		return password;
	}

	@JsonProperty("password")
	public void setPassword(String password) {
		this.password = password;
	}

	@JsonProperty("userImage")
	public String getImage() {
		/*
		 * Path path = Paths.get(image.getPath()); try { return
		 * Files.readAllBytes(path); } catch (IOException e) { e.printStackTrace(); }
		 * return null;
		 */
		return image;
	}

	@JsonProperty("userImage")
	public void setImage(String image) {
		this.image = image;
	}

	@JsonProperty("acceptTnc")
	public boolean isAcceptedTnc() {
		return acceptedTnc;
	}

	@JsonProperty("acceptTnc")
	public void setAcceptedTnc(boolean acceptedTnc) {
		this.acceptedTnc = acceptedTnc;
	}

	@JsonProperty("panCard")
	public String getPanCard() {
		return panCard;
	}

	@JsonProperty("panCard")
	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}
	
	

}
