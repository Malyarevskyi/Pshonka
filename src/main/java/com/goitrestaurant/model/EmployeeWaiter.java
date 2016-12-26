package com.goitrestaurant.model;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

public class EmployeeWaiter extends Employee {

    @OneToMany
    @JoinColumn(name = "employeeId")
    private List<Orders> orders;

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EmployeeWaiter {\n");
        sb.append("   id = ").append(getId()).append("\n");
        sb.append("   lastName = ").append(getLastName()).append("\n");
        sb.append("   firstName = ").append(getFirstName()).append("\n");
        sb.append("   birthday = ").append(getBirthday()).append("\n");
        sb.append("   phone = ").append(getPhone()).append("\n");
        sb.append("   position = ").append(getPosition().getPositionTitle()).append("\n");
        sb.append("   salary = ").append(getSalary()).append("\n");
        sb.append("         orders = {\n");
        orders.forEach(order -> sb.append("    ").append(order).append("\n"));
        sb.append("    }\n");
        sb.append(" }\n");

        return sb.toString();
    }
}
