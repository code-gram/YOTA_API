package com.yash.yotaapi.util;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.yash.yotaapi.exception.DateInValidException;
/**
 * Utility class to validate start date is less than end date
 */
@Component
public class DateValidationUtility {

	public void validateDateRange(Date startDate, Date endDate) {
		// Check if start date is before end date
		if (startDate.after(endDate)) {
		
			 throw new DateInValidException("Start date should be less than end date");
		}
		else if (startDate.equals(endDate)) {
			throw new DateInValidException("Start date and end date cannot be same");
		}

	}
}
