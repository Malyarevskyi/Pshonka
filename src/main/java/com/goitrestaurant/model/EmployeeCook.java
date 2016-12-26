package com.goitrestaurant.model;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

public class EmployeeCook extends Employee {

    @OneToMany
    @JoinColumn(name = "employeeId")
    private List<DishesPreparation> dishesPreparations;

    public List<DishesPreparation> getDishesPreparations() {
        return dishesPreparations;
    }

    public void setDishesPreparations(List<DishesPreparation> dishesPreparations) {
        this.dishesPreparations = dishesPreparations;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EmployeeCook {\n");
        sb.append("   id = ").append(getId()).append("\n");
        sb.append("   lastName = ").append(getLastName()).append("\n");
        sb.append("   firstName = ").append(getFirstName()).append("\n");
        sb.append("   birthday = ").append(getBirthday()).append("\n");
        sb.append("   phone = ").append(getPhone()).append("\n");
        sb.append("   position = ").append(getPosition().getPositionTitle()).append("\n");
        sb.append("   salary = ").append(getSalary()).append("\n");
        sb.append("         dishes = {\n");
        dishesPreparations.forEach(dish -> sb.append("    ").append(dish).append("\n"));
        sb.append("    }\n");
        sb.append(" }\n");

        return sb.toString();
    }
}
