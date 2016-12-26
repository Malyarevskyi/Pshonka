package com.goitrestaurant.web.admin.validators;

import com.goitrestaurant.model.Orders;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class OrdersValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Orders.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Orders orders = (Orders) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orderDate", null, "OrderDate is empty");

        /*if (orders.getDishesInOrder().size() <= 0) {
            errors.rejectValue("dishesInOrder", "", "DishesInOrder is not correct");
        }*/

        if (orders.getDesk().getDeskTitle().equals("NONE") ) {
            errors.rejectValue("desk", "", "Desk is not valid");
        }
    }
}
