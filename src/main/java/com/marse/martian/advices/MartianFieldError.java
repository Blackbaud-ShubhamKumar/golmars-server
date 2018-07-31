package com.marse.martian.advices;

public class MartianFieldError {
	private String field;
	private String code;
	private Object rejectedValue;

	public MartianFieldError() {
	}

	public MartianFieldError(String field, String code, Object rejectedValue) {
		super();
		this.field = field;
		this.code = code;
		this.rejectedValue = rejectedValue;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getRejectedValue() {
		return rejectedValue;
	}

	public void setRejectedValue(Object rejectedValue) {
		this.rejectedValue = rejectedValue;
	}

}
