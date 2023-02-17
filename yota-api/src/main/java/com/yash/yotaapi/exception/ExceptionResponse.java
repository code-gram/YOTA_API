package com.yash.yotaapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * 
 * @author pankaj.ssharma
*/
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExceptionResponse {
	
	/**
	 * This errorMessage field is used to customize the exception response.
	 */
	private String errorMessage;

}
