package com.goitrestaurant.dao.hibernate;


import com.goitrestaurant.Common;
import com.goitrestaurant.dao.EmployeeDao;
import com.goitrestaurant.dao.PositionDao;
import com.goitrestaurant.model.Employee;
import com.goitrestaurant.model.Position;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@ContextConfiguration(locations = {"classpath:application-context-test.xml", "classpath:hibernate-context-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class HEmployeeDaoTest {

    private PositionDao positionDao;
    private EmployeeDao employeeDao;

    @Autowired
    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Autowired
    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    @Test
    @Transactional
    @Rollback
    public void testCreateDeleteEmployee() throws Exception {
        String lastName = "lastName";
        String firstName = "firstName";
        String phone = "1234567890";
        String positionTitle = "testPosition";
        Date birthday = Common.stringToDate("1978-06-19");
        positionDao.create(new Position(positionTitle));
        float salary = 10000.0F;

        Employee currentEmployee = new Employee();
        currentEmployee.setLastName(lastName);
        currentEmployee.setFirstName(firstName);
        currentEmployee.setBirthday(birthday);
        currentEmployee.setPhone(phone);
        currentEmployee.setPosition(positionDao.findByTitle(positionTitle).get(0));
        currentEmployee.setSalary(salary);

        employeeDao.create(currentEmployee);

        Employee employee = employeeDao.getAll().get(0);
        assertEquals(employee.getLastName(), lastName);
        assertEquals(employee.getFirstName(), firstName);
        assertEquals(employee.getPhone(), phone);
        assertEquals(employee.getSalary(), salary, 0.01);
        assertEquals(employee.getPosition().getPositionTitle(), positionTitle);
        assertEquals(employee.getBirthday(), birthday);

        employeeDao.delete(employee.getId());
        assertTrue(employeeDao.getAll().size() == 0);
    }

}