package com.marse.martian.dto;

public class DashboardResponse extends MartianResponse {

	private DashboardData data;

	public DashboardResponse() {

	}

	public DashboardResponse(DashboardData data, String code, String message) {
		super(code, message);
		this.data = data;
	}

	public DashboardData getData() {
		return data;
	}

	public void setData(DashboardData data) {
		this.data = data;
	}

}
