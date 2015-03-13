package com.skessler.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.skessler.model.Acsorders;

@Component
public class OrderValidator implements Validator {

    private final static String PROCESSED = "processed";

    @Override
    public boolean supports(Class<?> clazz) {
        return Acsorders.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Acsorders acsorders = (Acsorders) target;

        byte processed = acsorders.getProcessed();

        ValidationUtils.rejectIfEmpty(errors, "orderdata", "acsorders.orderdata.empty");
        ValidationUtils.rejectIfEmpty(errors, PROCESSED, "acsorders.processed.empty");

        if (processed < 1)
            errors.rejectValue(PROCESSED, "acsorders.processed.lessThenOne");

    }

}
