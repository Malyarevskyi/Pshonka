package com.goitrestaurant.web.admin.validators;

import com.goitrestaurant.model.Employee;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class EmployeeValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Employee.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Employee employee = (Employee) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "", "LastName is empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "", "FirstName is empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthday", null, "Birthday is empty");

        if (employee.getPosition().getPositionTitle().equals("NONE")) {
            errors.rejectValue("position", "", "Position is not valid");
        }

        if (employee.getPhone().equals("") || !isCorrectPhoneFormat(employee.getPhone())) {
            errors.rejectValue("phone", "", "Phone is not correct");
        }

        if (employee.getSalary() <=0) {
            errors.rejectValue("salary","", "Salary is not correct");
        }
    }

    private boolean isCorrectPhoneFormat(String phone) {
        char[] charArray = phone.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            if (!isPhoneSymbols(charArray[i])) {
                return false;
            }
        }

        return true;
    }

    private boolean isPhoneSymbols(final char c) {
        return "0123456789+-()".indexOf(c) != -1;
    }
}
