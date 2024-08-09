package com.yash.yotaapi.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.HashMap;
import java.util.Map;

@Component
public class ValidateRequestUtility implements Validator {
    private final SpringValidatorAdapter validator;

    public ValidateRequestUtility() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = new SpringValidatorAdapter(validatorFactory.getValidator());
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public void validate(Object target, Errors errors) {
        this.validator.validate(target, errors);
    }

    public Map<String, String> validateRequest(Object request) {
        Errors errors = new BeanPropertyBindingResult(request, request.getClass().getName());
        validate(request, errors);
        Map<String, String> errorMap = new HashMap<>();
        errors.getFieldErrors().forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
        return errorMap;
    }
}
