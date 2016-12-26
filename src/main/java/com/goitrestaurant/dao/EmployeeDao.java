package com.goitrestaurant.dao;

import com.goitrestaurant.model.Employee;
import com.goitrestaurant.model.Position;

import java.sql.Date;
import java.util.List;

public interface EmployeeDao extends SimpleDao<Employee> {

    List<Employee> findEmployeeByFirstName(String firstName);

    List<Employee> findEmployeeByFullName(String lastName, String firstName);

    List<Employee> getAllEmployeesByPosition(Position position);

    void updateEmployeeFirstName(int id, String newEmployeeFirstName);

    void updateEmployeeBirthday(int id, Date newEmployeeBirthday);

    void updateEmployeePhone(int id, String newEmployeePhone);

    void updateEmployeePosition(int id, Position newPosition);

    void updateEmployeeSalary(int id, float newEmployeeSalary);

    void updateEmployeePhoto(int id, byte[] newPhoto);

}
