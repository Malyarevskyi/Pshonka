package com.goitrestaurant.dao.hibernate;

import com.goitrestaurant.dao.DishesPreparationDao;
import com.goitrestaurant.model.Dish;
import com.goitrestaurant.model.DishesPreparation;
import com.goitrestaurant.model.Employee;
import com.goitrestaurant.model.Orders;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.sql.Date;
import java.util.List;

public class HDishesPreparationDao implements DishesPreparationDao {

    private static final String ID = "id";
    private static final String DISH = "dish";
    private static final String COOK = "cook";
    private static final String DATE_PREPARATION = "datePreparation";
    private static final String ORDER = "order";

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
    public void create(DishesPreparation dishesPreparation) {
        sessionFactory.getCurrentSession().saveOrUpdate(dishesPreparation);
    }

    @Transactional
    public DishesPreparation findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<DishesPreparation> criteriaQuery = criteriaBuilder.createQuery(DishesPreparation.class);
        Root<DishesPreparation> root = criteriaQuery.from(DishesPreparation.class);
        Predicate condition = criteriaBuilder.equal(root.get(ID), id);
        criteriaQuery.where(condition);
        return session.createQuery(criteriaQuery).getSingleResult();
    }

    @Transactional
    public List<DishesPreparation> getAll() {
        Session session = getSession();

        CriteriaQuery<DishesPreparation> criteriaQuery = getCriteriaBuilder(session).createQuery(DishesPreparation.class);
        Root<DishesPreparation> root = criteriaQuery.from(DishesPreparation.class);
        criteriaQuery.select(root);

        return session.createQuery(criteriaQuery).getResultList();
    }

    @Transactional
    public void delete(int id) {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = getCriteriaBuilder(session);

        CriteriaDelete<DishesPreparation> delete = criteriaBuilder.createCriteriaDelete(DishesPreparation.class);
        Root root = delete.from(DishesPreparation.class);
        delete.where(criteriaBuilder.equal(root.get(ID), id));

        session.createQuery(delete).executeUpdate();
    }


    @Transactional
    public void updateDishesPreparationCook(int id, Employee newCook) {
        updateDishesPreparationFieldById(id, COOK, newCook);
    }

    @Transactional
    public void updateDishesPreparationDate(int id, Date newDate) {
        updateDishesPreparationFieldById(id, DATE_PREPARATION, newDate);
    }

    @Transactional
    public void updateDishesPreparationOrders(int id, Orders newOrders) {
        updateDishesPreparationFieldById(id, ORDER, newOrders);
    }

    @Transactional
    public void updateDishesPreparationDish(int id, Dish newDish) {
        updateDishesPreparationFieldById(id, DISH, newDish);
    }

    private void updateDishesPreparationFieldById(int id, String fieldTitle, Object newValue) {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = getCriteriaBuilder(session);

        CriteriaUpdate<DishesPreparation> update = criteriaBuilder.createCriteriaUpdate(DishesPreparation.class);
        Root Root = update.from(DishesPreparation.class);
        Predicate condition = criteriaBuilder.equal(Root.get(ID), id);
        update.set(fieldTitle, newValue);
        update.where(condition);

        session.createQuery(update).executeUpdate();
    }
}
