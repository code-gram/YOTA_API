package com.yash.yotaapi.util;

import javax.validation.ConstraintValidator;

import javax.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
import com.yash.yotaapi.constraints.CompareDate;
import com.yash.yotaapi.domain.Training;

@Component
public class CompareDateValidator implements ConstraintValidator<CompareDate, Training> {

	@Override
	public void initialize(CompareDate compareDate) {
	}

	@Override
	public boolean isValid(Training training, ConstraintValidatorContext context) {
		if (training.getStartDate().compareTo(training.getEndDate()) < 0) {
			return true;
		}
		return false;
	}
}