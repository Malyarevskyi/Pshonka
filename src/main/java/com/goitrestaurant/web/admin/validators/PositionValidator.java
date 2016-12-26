package com.goitrestaurant.web.admin.validators;

import com.goitrestaurant.model.Position;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PositionValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Position.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Position position = (Position) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "positionTitle", "", "PositionTitle is empty");
    }
}
