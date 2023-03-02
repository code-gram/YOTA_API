package com.yash.yotaapi.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

/**
 * This FieldErrorValidationUtillity Component will help to map the Field Error in errorMap. 
 * This can be used with any model error mapping. 
 * @author pratik.kurbet

 */
@Component
public class FieldErrorValidationUtillity {
	
	/**
	 * @param result
	 * @return It returns the field name with its validation error
	 */
	public ResponseEntity<?> validationError(BindingResult result)
	{
		if (result.hasErrors())
		{
			System.out.println(result.hasErrors());
			Map<String, String> errorMap=new HashMap<>();
			
			for (FieldError error:result.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<Map<String, String>>(errorMap,HttpStatus.BAD_REQUEST);
		}
		return null;
	}
	
	
}
