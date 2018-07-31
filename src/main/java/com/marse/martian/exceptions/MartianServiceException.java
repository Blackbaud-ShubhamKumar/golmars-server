package com.marse.martian.exceptions;

public class MartianServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MartianServiceException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MartianServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public MartianServiceException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public MartianServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public MartianServiceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
