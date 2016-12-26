package com.goitrestaurant.web.admin.validators;

import com.goitrestaurant.model.Dish;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class DishValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Dish.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Dish dish = (Dish) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dishTitle", "", "DishTitle is empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "", "Description is empty");

        if (dish.getIngredients().size() <= 0) {
            errors.rejectValue("ingredients", "", "Ingredients is not valid");
        }

        if (dish.getCategory().getCategoryTitle().equals("NONE")) {
            errors.rejectValue("category", "", "Category is not valid");
        }

        if (dish.getPrice() <=0) {
            errors.rejectValue("price","", "Price is not correct");
        }

        if (dish.getWeight() <=0) {
            errors.rejectValue("weight","", "Weight is not correct");
        }
    }
}
