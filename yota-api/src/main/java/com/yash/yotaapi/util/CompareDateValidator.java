package com.yash.yotaapi.util;

import java.lang.reflect.Field;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.yash.yotaapi.constraints.CompareDate;
import com.yash.yotaapi.domain.Batch;
import com.yash.yotaapi.exception.DateInValidException;

/*CompareDateValidator validation api use to check custom validation for start date and end date*/
@Component
public class CompareDateValidator implements ConstraintValidator<CompareDate, Batch> {

	@Autowired
	Batch batch;

	@Override
	public void initialize(CompareDate compareDate) {

	}

	@Override
	public boolean isValid(Batch batch, ConstraintValidatorContext context) {

		if (batch.getStartDate().compareTo(batch.getEndDate()) < 0) {

			return true;
		}

		return false;
	}

}
