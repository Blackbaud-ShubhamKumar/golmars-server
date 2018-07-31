package com.marse.martian.dto;

import java.util.Calendar;

public class PaymentFileData {

	public static final Float DEVIDER = 900F;

	private Long userRegId;

	private Float amount;

	private Calendar paymentDate;

	private String paymentMode;

	private String transNumber;

	public Long getUserRegId() {
		return userRegId;
	}

	public void setUserRegId(Long userRegId) {
		this.userRegId = userRegId;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Calendar getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Calendar paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getTransNumber() {
		return transNumber;
	}

	public void setTransNumber(String transNumber) {
		this.transNumber = transNumber;
	}

}
