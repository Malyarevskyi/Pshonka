package com.goitrestaurant.dao.hibernate;

import com.goitrestaurant.dao.WarehouseDao;
import com.goitrestaurant.model.Ingredient;
import com.goitrestaurant.model.Warehouse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.List;

public class HWarehouseDao implements WarehouseDao {

    private static final String ID = "id";
    private static final String INGREDIENTS = "ingredient";
    private static final String AMOUNT = "amount";

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
    public void create(Warehouse warehouse) {
        sessionFactory.getCurrentSession().saveOrUpdate(warehouse);
    }

    @Transactional
    public Warehouse findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Warehouse> criteriaQuery = criteriaBuilder.createQuery(Warehouse.class);
        Root<Warehouse> root = criteriaQuery.from(Warehouse.class);
        Predicate condition = criteriaBuilder.equal(root.get(ID), id);
        criteriaQuery.where(condition);
        return session.createQuery(criteriaQuery).getSingleResult();
    }

    @Transactional
    public List<Warehouse> getAll() {
        Session session = getSession();

        CriteriaQuery<Warehouse> criteriaQuery = getCriteriaBuilder(session).createQuery(Warehouse.class);
        Root<Warehouse> root = criteriaQuery.from(Warehouse.class);
        criteriaQuery.select(root);

        return session.createQuery(criteriaQuery).getResultList();
    }

    @Transactional
    public void delete(int id) {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = getCriteriaBuilder(session);

        CriteriaDelete<Warehouse> delete = criteriaBuilder.createCriteriaDelete(Warehouse.class);
        Root root = delete.from(Warehouse.class);
        delete.where(criteriaBuilder.equal(root.get(ID), id));

        session.createQuery(delete).executeUpdate();
    }

    @Transactional
    public void updateWarehouseIngredient(int id, Ingredient newWarehouseIngredient) {
        updateWarehouseFieldById(id, INGREDIENTS, newWarehouseIngredient);
    }

    @Transactional
    public void updateWarehouseAmount(int id, float newWarehouseAmount) {
        updateWarehouseFieldById(id, AMOUNT, newWarehouseAmount);
    }

    private void updateWarehouseFieldById(int id, String fieldTitle, Object newValue) {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = getCriteriaBuilder(session);

        CriteriaUpdate<Warehouse> update = criteriaBuilder.createCriteriaUpdate(Warehouse.class);
        Root employeeRoot = update.from(Warehouse.class);
        Predicate condition = criteriaBuilder.equal(employeeRoot.get(ID), id);
        update.set(fieldTitle, newValue);
        update.where(condition);

        session.createQuery(update).executeUpdate();
    }

}
