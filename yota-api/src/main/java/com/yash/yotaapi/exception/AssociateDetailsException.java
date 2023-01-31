package com.yash.yotaapi.exception;

public class AssociateDetailsException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public AssociateDetailsException(String errorMsg) {
		super(errorMsg);
	}
	
	public AssociateDetailsException() {
		super();
	}
}
