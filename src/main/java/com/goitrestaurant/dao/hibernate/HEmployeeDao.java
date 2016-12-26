package com.goitrestaurant.dao.hibernate;

import com.goitrestaurant.dao.EmployeeDao;
import com.goitrestaurant.model.Employee;
import com.goitrestaurant.model.Position;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.sql.Date;
import java.util.List;

public class HEmployeeDao implements EmployeeDao {

    private static final String ID = "id";
    private static final String POSITION = "position";
    private static final String LAST_NAME = "lastName";
    private static final String FIRST_NAME = "firstName";
    private static final String BIRTHDAY = "birthday";
    private static final String PHONE = "phone";
    private static final String SALARY = "salary";
    private static final String IMAGE = "image";

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    private CriteriaBuilder getCriteriaBuilder(Session session) {
        return session.getCriteriaBuilder();
    }

    @Transactional
    public void create(Employee employee) {
        sessionFactory.getCurrentSession().saveOrUpdate(employee);
    }

    @Transactional
    public Employee findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        Predicate condition = criteriaBuilder.equal(root.get(ID), id);
        criteriaQuery.where(condition);
        return session.createQuery(criteriaQuery).getSingleResult();
    }

    @Transactional
    public List<Employee> findByTitle(String lastName) {
        return findEmployeeByField(LAST_NAME, lastName);
    }

    @Transactional
    public List<Employee> getAll() {
        Session session = getSession();

        CriteriaQuery<Employee> criteriaQuery = getCriteriaBuilder(session).createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery.select(root);

        return session.createQuery(criteriaQuery).getResultList();
    }

    @Transactional
    public void delete(int id) {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = getCriteriaBuilder(session);

        CriteriaDelete<Employee> delete = criteriaBuilder.createCriteriaDelete(Employee.class);
        Root root = delete.from(Employee.class);
        delete.where(criteriaBuilder.equal(root.get(ID), id));

        session.createQuery(delete).executeUpdate();
    }

    private List<Employee> findEmployeeByField(String fieldTitle, Object value) {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = getCriteriaBuilder(session);

        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        Predicate condition = criteriaBuilder.equal(root.get(fieldTitle), value);
        criteriaQuery.where(condition);

        return session.createQuery(criteriaQuery).getResultList();
    }

    private void updateEmployeeFieldById(int id, String fieldTitle, Object newValue) {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = getCriteriaBuilder(session);

        CriteriaUpdate<Employee> update = criteriaBuilder.createCriteriaUpdate(Employee.class);
        Root employeeRoot = update.from(Employee.class);
        Predicate condition = criteriaBuilder.equal(employeeRoot.get(ID), id);
        update.set(fieldTitle, newValue);
        update.where(condition);

        session.createQuery(update).executeUpdate();
    }

    @Transactional
    public List<Employee> findEmployeeByFullName(String lastName, String firstName) {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = getCriteriaBuilder(session);

        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);
        Predicate conditionLastName = criteriaBuilder.equal(employeeRoot.get(LAST_NAME), lastName);
        Predicate conditionFirstName = criteriaBuilder.equal(employeeRoot.get(FIRST_NAME), firstName);
        Predicate mainCondition = criteriaBuilder.and(conditionLastName, conditionFirstName);
        criteriaQuery.where(mainCondition);

        return session.createQuery(criteriaQuery).getResultList();
    }

    @Transactional
    public void updateTitle(int id, String newEmployeeFirstName) {
        updateEmployeeFieldById(id, FIRST_NAME, newEmployeeFirstName);
    }

    @Transactional
    public List<Employee> findEmployeeByFirstName(String firstName) {
        return findEmployeeByField(FIRST_NAME, firstName);
    }

    @Transactional
    public List<Employee> getAllEmployeesByPosition(Position position) {
        return findEmployeeByField(POSITION, position);
    }

    @Transactional
    public void updateEmployeeFirstName(int id, String newEmployeeFirstName) {
        updateEmployeeFieldById(id, FIRST_NAME, newEmployeeFirstName);
    }

    @Transactional
    public void updateEmployeeBirthday(int id, Date newEmployeeBirthday) {
        updateEmployeeFieldById(id, BIRTHDAY, newEmployeeBirthday);
    }

    @Transactional
    public void updateEmployeePhone(int id, String newEmployeePhone) {
        updateEmployeeFieldById(id, PHONE, newEmployeePhone);
    }

    @Transactional
    public void updateEmployeePosition(int id, Position newPosition) {
        updateEmployeeFieldById(id, POSITION, newPosition);
    }

    @Transactional
    public void updateEmployeeSalary(int id, float newEmployeeSalary) {
        updateEmployeeFieldById(id, SALARY, newEmployeeSalary);
    }

    @Transactional
    public void updateEmployeePhoto(int id, byte[] newPhoto) {
        updateEmployeeFieldById(id, IMAGE, newPhoto);
    }

}
