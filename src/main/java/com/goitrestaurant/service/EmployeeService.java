package com.goitrestaurant.service;

import com.goitrestaurant.dao.EmployeeDao;
import com.goitrestaurant.model.Employee;
import com.goitrestaurant.model.Position;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

public class EmployeeService {

    private EmployeeDao employeeDao;

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Transactional
    public void create(Employee employee) {
        employeeDao.create(employee);
    }

    @Transactional
    public Employee findById(int id) {
        return employeeDao.findById(id);
    }

    @Transactional
    public List<Employee> findByTitle(String lastName) {
        return employeeDao.findByTitle(lastName);
    }

    @Transactional
    public List<Employee> getAll() {
        return employeeDao.getAll();
    }

    @Transactional
    public void delete(int id) {
        employeeDao.delete(id);
    }

    @Transactional
    public List<Employee> findEmployeeByFullName(String lastName, String firstName) {
        return employeeDao.findEmployeeByFullName(lastName, firstName);
    }

    @Transactional
    public void updateTitle(int id, String newEmployeeFirstName) {
        employeeDao.updateTitle(id, newEmployeeFirstName);
    }

    @Transactional
    public List<Employee> findEmployeeByFirstName(String firstName) {
        return employeeDao.findEmployeeByFirstName(firstName);
    }

    @Transactional
    public List<Employee> getAllEmployeesByPosition(Position position) {
        return employeeDao.getAllEmployeesByPosition(position);
    }

    @Transactional
    public void updateEmployeeFirstName(int id, String newEmployeeFirstName) {
        employeeDao.updateEmployeeFirstName(id, newEmployeeFirstName);
    }

    @Transactional
    public void updateEmployeeBirthday(int id, Date newEmployeeBirthday) {
        employeeDao.updateEmployeeBirthday(id, newEmployeeBirthday);
    }

    @Transactional
    public void updateEmployeePhone(int id, String newEmployeePhone) {
        employeeDao.updateEmployeePhone(id, newEmployeePhone);
    }

    @Transactional
    public void updateEmployeePositionId(int id, Position newPosition) {
        employeeDao.updateEmployeePosition(id, newPosition);
    }

    @Transactional
    public void updateEmployeeSalary(int id, float newEmployeeSalary) {
        employeeDao.updateEmployeeSalary(id, newEmployeeSalary);
    }

    @Transactional
    public void updateEmployeePhoto(int id, byte[] newPhoto) {
        employeeDao.updateEmployeePhoto(id, newPhoto);
    }

}
