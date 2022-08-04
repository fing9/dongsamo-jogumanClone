package com.dongsamo.jogumanClone.component;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@Component
public class ValidateHandler {

    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            if(error.getDefaultMessage().contains("java.lang.NumberFormatException"))
                validatorResult.put(validKeyName, "정수를 입력해 주세요.");
            else
                validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }
}
