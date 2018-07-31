package com.marse.martian.dto;

public class RegisterartionResponse extends MartianResponse {

	private String myId;

	public RegisterartionResponse() {
		super();
	}

	public RegisterartionResponse(String myId, String code, String message) {
		super(code, message);
		this.myId = myId;
	}

	public String getMyId() {
		return myId;
	}

	public void setMyId(String myId) {
		this.myId = myId;
	}

}
