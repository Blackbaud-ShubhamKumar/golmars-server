package com.marse.martian.dto;

import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProfileData {

	private String guestName;

	private String fatherHushName;

	private String gender;

	private Calendar dob;

	private String occupation;

	private String city;

	private String pinCode;

	private String aadharNumber;

	private String email;

	private String phone;

	private String address;

	@JsonProperty("accHolderFirstName")
	public String getGuestName() {
		return guestName;
	}

	@JsonProperty("accHolderFirstName")
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	@JsonProperty("gaurdianName")
	public String getFatherHushName() {
		return fatherHushName;
	}

	@JsonProperty("gaurdianName")
	public void setFatherHushName(String fatherHushName) {
		this.fatherHushName = fatherHushName;
	}

	@JsonProperty("gender")
	public String getGender() {
		return gender;
	}

	@JsonProperty("gender")
	public void setGender(String gender) {
		this.gender = gender;
	}

	@JsonProperty("dob")
	public Calendar getDob() {
		return dob;
	}

	@JsonProperty("dob")
	public void setDob(Calendar dob) {
		this.dob = dob;
	}

	@JsonProperty("occupation")
	public String getOccupation() {
		return occupation;
	}

	@JsonProperty("occupation")
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	@JsonProperty("city")
	public String getCity() {
		return city;
	}

	@JsonProperty("city")
	public void setCity(String city) {
		this.city = city;
	}

	@JsonProperty("zip")
	public String getPinCode() {
		return pinCode;
	}

	@JsonProperty("zip")
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	@JsonProperty("aadharCard")
	public String getAadharNumber() {
		return aadharNumber;
	}

	@JsonProperty("aadharCard")
	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	@JsonProperty("emailId")
	public String getEmail() {
		return email;
	}

	@JsonProperty("emailId")
	public void setEmail(String email) {
		this.email = email;
	}

	@JsonProperty("mobileNumber")
	public String getPhone() {
		return phone;
	}

	@JsonProperty("mobileNumber")
	public void setPhone(String phone) {
		this.phone = phone;
	}

	@JsonProperty("address")
	public String getAddress() {
		return address;
	}

	@JsonProperty("address")
	public void setAddress(String address) {
		this.address = address;
	}

}
