package com.goitrestaurant.dao.hibernate;

import com.goitrestaurant.dao.OrderDao;
import com.goitrestaurant.model.Desk;
import com.goitrestaurant.model.Employee;
import com.goitrestaurant.model.Orders;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.sql.Date;
import java.util.List;

public class HOrderDao implements OrderDao {

    private static final String ID = "id";
    private static final String WAITER = "waiter";
    private static final String DESK = "desk";
    private static final String ORDER_DATE = "orderDate";
    private static final String DISHES_IN_ORDER = "dishesInOrder";

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
    public void create(Orders orders) {
        sessionFactory.getCurrentSession().saveOrUpdate(orders);
    }

    @Transactional
    public Orders findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Orders> criteriaQuery = criteriaBuilder.createQuery(Orders.class);
        Root<Orders> root = criteriaQuery.from(Orders.class);
        Predicate condition = criteriaBuilder.equal(root.get(ID), id);
        criteriaQuery.where(condition);
        return session.createQuery(criteriaQuery).getSingleResult();
    }

    @Transactional
    public List<Orders> getAll() {
        Session session = getSession();

        CriteriaQuery<Orders> criteriaQuery = getCriteriaBuilder(session).createQuery(Orders.class);
        Root<Orders> root = criteriaQuery.from(Orders.class);
        criteriaQuery.select(root);

        return session.createQuery(criteriaQuery).getResultList();
    }

    @Transactional
    public void delete(int id) {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = getCriteriaBuilder(session);

        CriteriaDelete<Orders> delete = criteriaBuilder.createCriteriaDelete(Orders.class);
        Root root = delete.from(Orders.class);
        delete.where(criteriaBuilder.equal(root.get(ID), id));

        session.createQuery(delete).executeUpdate();
    }

    @Transactional
    public void updateOrderWaiter(int id, Employee newWaiter) {
        updateOrdersFieldById(id, WAITER, newWaiter);
    }

    @Transactional
    public void updateOrderDesk(int id, Desk newDesk) {
        updateOrdersFieldById(id, DESK, newDesk);
    }

    @Transactional
    public void updateOrderDate(int id, Date newOrderDate) {
        updateOrdersFieldById(id, ORDER_DATE, newOrderDate);
    }

    /*@Transactional
    public void updateOrderDishes(int id, List<Dish> newDishes) {
        updateOrdersFieldById(id, DISHES_IN_ORDER, newDishes);
    }*/

    private void updateOrdersFieldById(int id, String fieldTitle, Object newValue) {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = getCriteriaBuilder(session);

        CriteriaUpdate<Orders> update = criteriaBuilder.createCriteriaUpdate(Orders.class);
        Root employeeRoot = update.from(Orders.class);
        Predicate condition = criteriaBuilder.equal(employeeRoot.get(ID), id);
        update.set(fieldTitle, newValue);
        update.where(condition);

        session.createQuery(update).executeUpdate();
    }
}
