package com.goitrestaurant.web.admin.validators;

import com.goitrestaurant.model.DishesPreparation;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DishesPreparationValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return DishesPreparation.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        DishesPreparation dishesPreparation = (DishesPreparation) target;
    }
}
