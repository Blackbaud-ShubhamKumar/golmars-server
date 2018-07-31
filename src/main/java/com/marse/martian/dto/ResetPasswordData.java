package com.marse.martian.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResetPasswordData {

	private String oldPassword;
	
	private String newPassword;
	
	private String repeatPassword;

	@JsonProperty("oldPassword")
	public String getOldPassword() {
		return oldPassword;
	}

	@JsonProperty("oldPassword")
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	@JsonProperty("newPassword")
	public String getNewPassword() {
		return newPassword;
	}

	@JsonProperty("newPassword")
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@JsonProperty("confPassword")
	public String getRepeatPassword() {
		return repeatPassword;
	}

	@JsonProperty("confPassword")
	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}
	
	
}
