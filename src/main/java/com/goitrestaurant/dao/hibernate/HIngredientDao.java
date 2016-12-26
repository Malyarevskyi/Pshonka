package com.goitrestaurant.dao.hibernate;

import com.goitrestaurant.dao.IngredientDao;
import com.goitrestaurant.model.Ingredient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.List;

public class HIngredientDao implements IngredientDao {

    private static final String ID = "id";
    private static final String INGREDIENT_TITLE = "ingredientTitle";

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
    public void create(Ingredient ingredient) {
        sessionFactory.getCurrentSession().saveOrUpdate(ingredient);
    }

    @Transactional
    public Ingredient findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Ingredient> criteriaQuery = criteriaBuilder.createQuery(Ingredient.class);
        Root<Ingredient> root = criteriaQuery.from(Ingredient.class);
        Predicate condition = criteriaBuilder.equal(root.get(ID), id);
        criteriaQuery.where(condition);
        return session.createQuery(criteriaQuery).getSingleResult();
    }

    @Transactional
    public List<Ingredient> findByTitle(String ingredientTitle) {
        return findIngredientByField(INGREDIENT_TITLE, ingredientTitle);
    }

    private List<Ingredient> findIngredientByField(String fieldTitle, Object value) {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = getCriteriaBuilder(session);

        CriteriaQuery<Ingredient> criteriaQuery = criteriaBuilder.createQuery(Ingredient.class);
        Root<Ingredient> root = criteriaQuery.from(Ingredient.class);
        Predicate condition = criteriaBuilder.equal(root.get(fieldTitle), value);
        criteriaQuery.where(condition);

        return session.createQuery(criteriaQuery).getResultList();
    }

    @Transactional
    public List<Ingredient> getAll() {
        Session session = getSession();

        CriteriaQuery<Ingredient> criteriaQuery = getCriteriaBuilder(session).createQuery(Ingredient.class);
        Root<Ingredient> root = criteriaQuery.from(Ingredient.class);
        criteriaQuery.select(root);

        return session.createQuery(criteriaQuery).getResultList();
    }

    @Transactional
    public void delete(int id) {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = getCriteriaBuilder(session);

        CriteriaDelete<Ingredient> delete = criteriaBuilder.createCriteriaDelete(Ingredient.class);
        Root root = delete.from(Ingredient.class);
        delete.where(criteriaBuilder.equal(root.get(ID), id));

        session.createQuery(delete).executeUpdate();
    }

    @Transactional
    public void updateTitle(int id, String newTitle) {
        updateIngredientFieldById(id, INGREDIENT_TITLE, newTitle);
    }

    private void updateIngredientFieldById(int id, String fieldTitle, Object newValue) {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = getCriteriaBuilder(session);

        CriteriaUpdate<Ingredient> update = criteriaBuilder.createCriteriaUpdate(Ingredient.class);
        Root employeeRoot = update.from(Ingredient.class);
        Predicate condition = criteriaBuilder.equal(employeeRoot.get(ID), id);
        update.set(fieldTitle, newValue);
        update.where(condition);

        session.createQuery(update).executeUpdate();
    }
}
