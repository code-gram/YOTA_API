package com.yash.yotaapi.exception;

public class ParentUnitNotFoundException extends RuntimeException{
private static final long serialVersionUID = 1L;
	
	public ParentUnitNotFoundException() {
		super();
	}
	
	public ParentUnitNotFoundException(String errorMsg) {
		super(errorMsg);
	}
	
}
