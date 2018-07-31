package com.marse.martian.entities;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "wallet")
public class Wallet extends AbstractEntity{
	
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "node")
	private Node node;
	
	@Column(name = "type")
    @Enumerated(EnumType.STRING)
    private WalletType walletType;
	
	@Column(name="detail")
	private String detail;
	
	@Column(name="amount")
	private Float amount;
	
	@Column(name="trans_type")
	@Enumerated(EnumType.STRING)
	private TransType transType;
	
	@Column(name="trans_date")
	private Calendar transDate;
	
	@Column(name="trans_status")
	@Enumerated(EnumType.STRING)
	private TransStatus status;

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public WalletType getWalletType() {
		return walletType;
	}

	public void setWalletType(WalletType walletType) {
		this.walletType = walletType;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public TransType getTransType() {
		return transType;
	}

	public void setTransType(TransType transType) {
		this.transType = transType;
	}

	public Calendar getTransDate() {
		return transDate;
	}

	public void setTransDate(Calendar transDate) {
		this.transDate = transDate;
	}

	public TransStatus getStatus() {
		return status;
	}

	public void setStatus(TransStatus status) {
		this.status = status;
	}
	
}
