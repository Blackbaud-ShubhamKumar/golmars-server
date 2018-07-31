package com.marse.martian.advices;

import java.util.List;

public class MartianErrorsView {
	private List<MartianFieldError> fieldErrors;
	private List<MartianGlobalError> globalErrors;

	public MartianErrorsView() {

	}

	public MartianErrorsView(List<MartianFieldError> fieldErrors, List<MartianGlobalError> globalErrors) {
		super();
		this.fieldErrors = fieldErrors;
		this.globalErrors = globalErrors;
	}

	public List<MartianFieldError> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(List<MartianFieldError> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

	public List<MartianGlobalError> getGlobalErrors() {
		return globalErrors;
	}

	public void setGlobalErrors(List<MartianGlobalError> globalErrors) {
		this.globalErrors = globalErrors;
	}

}
