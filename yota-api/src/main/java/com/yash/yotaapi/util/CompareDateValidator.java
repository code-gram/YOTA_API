package com.yash.yotaapi.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yash.yotaapi.constraints.CompareDate;
import com.yash.yotaapi.domain.Batch;
//@Component
public class CompareDateValidator implements ConstraintValidator<CompareDate, Object> {

	private String beforeEndDate;
	private String afterEndDate;
	
	@Autowired
	Batch batch;

	@Override
	public void initialize(CompareDate compareDate) {

		this.beforeEndDate = compareDate.before();
		this.afterEndDate = compareDate.after();

	}

	@Override
	public boolean isValid(Object date, ConstraintValidatorContext context) {

		try {

			/*
			 * final Field beforeFieldDate =
			 * date.getClass().getDeclaredField(beforeEndDate);
			 * //beforeFieldDate.setAccessible(true);
			 * 
			 * final Field afterFieldDate = date.getClass().getDeclaredField(afterEndDate);
			 * //afterFieldDate.setAccessible(true);
			 * 
			 * final Date beforeDate = (Date) beforeFieldDate.get(date); final Date
			 * afterDate = (Date) afterFieldDate.get(date);
			 * 
			 * return ((beforeDate == null && afterDate == null) || (afterDate != null &&
			 * afterDate.before(beforeDate)));
			 */
			
			

		} catch (final Exception e) {

			e.printStackTrace();
		}
		return false;
	}

}
