package com.goitrestaurant.web.admin.validators;

import com.goitrestaurant.model.Warehouse;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class WarehouseValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Warehouse.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Warehouse warehouse = (Warehouse) target;

        if (warehouse.getIngredient().getIngredientTitle().equals("NONE")) {
            errors.rejectValue("ingredient", "", "Ingredient is not valid");
        }

        if (warehouse.getAmount() <=0) {
            errors.rejectValue("amount","", "Amount is not correct");
        }
    }
}
