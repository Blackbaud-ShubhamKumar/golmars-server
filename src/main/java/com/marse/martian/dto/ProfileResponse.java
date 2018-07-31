package com.marse.martian.dto;

public class ProfileResponse extends MartianResponse {
	
	public ProfileData profile;
	
	public ProfileResponse() {
		super();
	}

	public ProfileResponse(ProfileData profile,String code,String message) {
		super(code,message);
		this.profile = profile;
		
	}
}
