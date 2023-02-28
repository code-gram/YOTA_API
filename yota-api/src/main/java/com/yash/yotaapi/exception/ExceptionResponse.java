package com.yash.yotaapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Used to store the exception error message from exception handler.
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ExceptionResponse {
	
	private String errorMsg;
}
