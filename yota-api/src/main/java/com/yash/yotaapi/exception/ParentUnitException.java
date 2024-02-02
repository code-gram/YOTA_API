package com.yash.yotaapi.exception;

public class ParentUnitException extends RuntimeException{
private static final long serialVersionUID = 1L;
	
	public ParentUnitException() {
		super();
	}
	
	public ParentUnitException(String errorMsg) {
		super(errorMsg);
	}
	
}
