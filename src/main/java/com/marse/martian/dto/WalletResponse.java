package com.marse.martian.dto;

import java.util.List;

public class WalletResponse extends MartianResponse {

	public List<WalletData> wallets;

	public Float totalRecieved;
	
	public Float totalExpenseOrSent;
	
	public Float totalBalance;
	
	public WalletResponse() {
		super();
	}

	public WalletResponse(List<WalletData> wallets, String code, String message) {
		super(code, message);
		this.wallets = wallets;
	}

	public List<WalletData> getWallets() {
		return wallets;
	}

	public void setWallets(List<WalletData> wallets) {
		this.wallets = wallets;
	}

	public Float getTotalRecieved() {
		return totalRecieved;
	}

	public void setTotalRecieved(Float totalRecieved) {
		this.totalRecieved = totalRecieved;
	}

	public Float getTotalExpenseOrSent() {
		return totalExpenseOrSent;
	}

	public void setTotalExpenseOrSent(Float totalExpenseOrSent) {
		this.totalExpenseOrSent = totalExpenseOrSent;
	}

	public Float getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(Float totalBalance) {
		this.totalBalance = totalBalance;
	}

}
