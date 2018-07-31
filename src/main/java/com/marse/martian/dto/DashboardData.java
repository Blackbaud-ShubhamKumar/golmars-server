package com.marse.martian.dto;

public class DashboardData {

	private String userId;
	
	private String userName;
	
	private String joiningDate;
	
	private String promoter;
	
	private String nodeCount;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getPromoter() {
		return promoter;
	}

	public void setPromoter(String promoter) {
		this.promoter = promoter;
	}

	public String getNodeCount() {
		return nodeCount;
	}

	public void setNodeCount(String nodeCount) {
		this.nodeCount = nodeCount;
	}
	
}
