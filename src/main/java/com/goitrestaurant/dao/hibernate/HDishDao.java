package com.goitrestaurant.dao.hibernate;

import com.goitrestaurant.dao.DishDao;
import com.goitrestaurant.model.Category;
import com.goitrestaurant.model.Dish;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.List;

public class HDishDao implements DishDao {

    private static final String ID = "id";
    private static final String DISH_TITLE = "dishTitle";
    private static final String CATEGORY = "category";
    private static final String PRICE = "price";
    private static final String WEIGHT = "weight";
    private static final String INGREDIENTS = "ingredients";
    private static final String PICTURE = "picture";
    private static final String DESCRIPTION = "description";

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
    public void create(Dish dish) {
        sessionFactory.getCurrentSession().saveOrUpdate(dish);
    }

    @Transactional
    public Dish findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Dish> criteriaQuery = criteriaBuilder.createQuery(Dish.class);
        Root<Dish> root = criteriaQuery.from(Dish.class);
        Predicate condition = criteriaBuilder.equal(root.get(ID), id);
        criteriaQuery.where(condition);
        return session.createQuery(criteriaQuery).getSingleResult();
    }

    @Transactional
    public List<Dish> findByTitle(String dishTitle) {
        return findDishByField(DISH_TITLE, dishTitle);
    }

    private List<Dish> findDishByField(String fieldTitle, Object value) {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = getCriteriaBuilder(session);

        CriteriaQuery<Dish> criteriaQuery = criteriaBuilder.createQuery(Dish.class);
        Root<Dish> root = criteriaQuery.from(Dish.class);
        Predicate condition = criteriaBuilder.equal(root.get(fieldTitle), value);
        criteriaQuery.where(condition);

        return session.createQuery(criteriaQuery).getResultList();
    }

    @Transactional
    public List<Dish> getAll() {
        Session session = getSession();

        CriteriaQuery<Dish> criteriaQuery = getCriteriaBuilder(session).createQuery(Dish.class);
        Root<Dish> root = criteriaQuery.from(Dish.class);
        criteriaQuery.select(root);

        return session.createQuery(criteriaQuery).getResultList();
    }

    @Transactional
    public void delete(int id) {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = getCriteriaBuilder(session);

        CriteriaDelete<Dish> delete = criteriaBuilder.createCriteriaDelete(Dish.class);
        Root root = delete.from(Dish.class);
        delete.where(criteriaBuilder.equal(root.get(ID), id));

        session.createQuery(delete).executeUpdate();
    }

    @Transactional
    public void updateTitle(int id, String newDishTitle) {
        updateDishFieldById(id, DISH_TITLE, newDishTitle);
    }

    private void updateDishFieldById(int id, String fieldTitle, Object newValue) {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = getCriteriaBuilder(session);

        CriteriaUpdate<Dish> update = criteriaBuilder.createCriteriaUpdate(Dish.class);
        Root employeeRoot = update.from(Dish.class);
        Predicate condition = criteriaBuilder.equal(employeeRoot.get(ID), id);
        update.set(fieldTitle, newValue);
        update.where(condition);

        session.createQuery(update).executeUpdate();
    }

    @Transactional
    public void updateDishCategory(int id, Category newCategory) {
        updateDishFieldById(id, CATEGORY, newCategory);
    }

    @Transactional
    public void updateDishPrice(int id, float newDishPrice) {
        updateDishFieldById(id, PRICE, newDishPrice);
    }

    @Transactional
    public void updateDishWeight(int id, float newDishWeight) {
        updateDishFieldById(id, WEIGHT, newDishWeight);
    }

    @Transactional
    public void updateDishPicture(int id, byte[] newPicture) {
        updateDishFieldById(id, PICTURE, newPicture);
    }

    @Transactional
    public void updateDishDescription(int id, String newDescription) {
        updateDishFieldById(id, DESCRIPTION, newDescription);
    }

}
