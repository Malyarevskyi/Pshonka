package com.goitrestaurant.web.admin.validators;

import com.goitrestaurant.model.Ingredient;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class IngredientValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Ingredient.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Ingredient ingredient = (Ingredient) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ingredientTitle", "", "IngredientTitle is empty");
    }
}
