package com.goitrestaurant.dao.hibernate;

import com.goitrestaurant.dao.CategoryDao;
import com.goitrestaurant.model.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.List;

public class HCategoryDao implements CategoryDao {

    private static final String ID = "id";
    private static final String CATEGORY_TITLE = "categoryTitle";

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
    public void create(Category category) {
        sessionFactory.getCurrentSession().saveOrUpdate(category);
    }

    @Transactional
    public Category findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Category> criteriaQuery = criteriaBuilder.createQuery(Category.class);
        Root<Category> root = criteriaQuery.from(Category.class);
        Predicate condition = criteriaBuilder.equal(root.get(ID), id);
        criteriaQuery.where(condition);
        return session.createQuery(criteriaQuery).getSingleResult();
    }

    @Transactional
    public List<Category> findByTitle(String categoryTitle) {
        return findCategoryByField(CATEGORY_TITLE, categoryTitle);
    }

    private List<Category> findCategoryByField(String fieldTitle, Object value) {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = getCriteriaBuilder(session);

        CriteriaQuery<Category> criteriaQuery = criteriaBuilder.createQuery(Category.class);
        Root<Category> root = criteriaQuery.from(Category.class);
        Predicate condition = criteriaBuilder.equal(root.get(fieldTitle), value);
        criteriaQuery.where(condition);

        return session.createQuery(criteriaQuery).getResultList();
    }

    @Transactional
    public List<Category> getAll() {
        Session session = getSession();

        CriteriaQuery<Category> criteriaQuery = getCriteriaBuilder(session).createQuery(Category.class);
        Root<Category> root = criteriaQuery.from(Category.class);
        criteriaQuery.select(root);

        return session.createQuery(criteriaQuery).getResultList();
    }

    @Transactional
    public void delete(int id) {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = getCriteriaBuilder(session);

        CriteriaDelete<Category> delete = criteriaBuilder.createCriteriaDelete(Category.class);
        Root root = delete.from(Category.class);
        delete.where(criteriaBuilder.equal(root.get(ID), id));

        session.createQuery(delete).executeUpdate();
    }

    @Transactional
    public void updateTitle(int id, String newTitle) {
        updateCategoryFieldById(id, CATEGORY_TITLE, newTitle);
    }

    private void updateCategoryFieldById(int id, String fieldTitle, Object newValue) {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = getCriteriaBuilder(session);

        CriteriaUpdate<Category> update = criteriaBuilder.createCriteriaUpdate(Category .class);
        Root employeeRoot = update.from(Category.class);
        Predicate condition = criteriaBuilder.equal(employeeRoot.get(ID), id);
        update.set(fieldTitle, newValue);
        update.where(condition);

        session.createQuery(update).executeUpdate();
    }

}
