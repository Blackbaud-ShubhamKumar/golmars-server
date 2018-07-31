package com.marse.martian.entities;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "payment")
public class Payment extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "mode")
	private String paymentMode;

	@Column(name = "date")
	private Calendar paymentDate;

	@Column(name = "trans_no")
	private String transactionNo;

	@Column(name = "other")
	private String otherDetail;

	@Column(name = "image")
	private String slipImageName;

	@Column(name = "image_data")
	private byte[] slipImageData;

	@ManyToOne
	@JoinColumn(name = "node")
	private Node node;

	@Column(name = "amount")
	private Float amount;

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public Calendar getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Calendar paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getTransactionNo() {
		return transactionNo;
	}

	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}

	public String getOtherDetail() {
		return otherDetail;
	}

	public void setOtherDetail(String otherDetail) {
		this.otherDetail = otherDetail;
	}

	public String getSlipImageName() {
		return slipImageName;
	}

	public void setSlipImageName(String slipImageName) {
		this.slipImageName = slipImageName;
	}

	public byte[] getSlipImageData() {
		return slipImageData;
	}

	public void setSlipImageData(byte[] slipImageData) {
		this.slipImageData = slipImageData;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

}
