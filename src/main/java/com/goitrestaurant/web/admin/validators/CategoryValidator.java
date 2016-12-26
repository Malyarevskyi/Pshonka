package com.goitrestaurant.web.admin.validators;

import com.goitrestaurant.model.Category;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CategoryValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Category.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Category category = (Category) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "categoryTitle", "", "CategoryTitle is empty");
    }
}
