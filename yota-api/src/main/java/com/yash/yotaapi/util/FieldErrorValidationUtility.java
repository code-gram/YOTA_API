package com.yash.yotaapi.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * This ValidationError Component will help to map the Field Error in errorMap. 
 * This can be used with any model error mapping. 
 * @author nitin.chougale
 */
@Service
public class FieldErrorValidationUtility{
	
	/**
	 * @param result
	 * @return It returns the field name with its validation error
	 */
	public ResponseEntity<?> validationError(BindingResult result)
	{
		if (result.hasErrors()) {
			Map<String, String> errorMap=new HashMap<>();
			for (FieldError error:result.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<Map<String, String>>(errorMap,HttpStatus.BAD_REQUEST);
		}
		return null;
	}
}
