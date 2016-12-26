package com.goitrestaurant.dao.hibernate;

import com.goitrestaurant.dao.MenuDao;
import com.goitrestaurant.model.Dish;
import com.goitrestaurant.model.Menu;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.List;

public class HMenuDao implements MenuDao {

    private static final String ID = "id";
    private static final String MENU_TITLE = "menuTitle";
    private static final String DISHES_IN_MENU = "dishesInMenu";

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
    public void create(Menu menu) {
        sessionFactory.getCurrentSession().saveOrUpdate(menu);
    }

    @Transactional
    public Menu findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Menu> criteriaQuery = criteriaBuilder.createQuery(Menu.class);
        Root<Menu> root = criteriaQuery.from(Menu.class);
        Predicate condition = criteriaBuilder.equal(root.get(ID), id);
        criteriaQuery.where(condition);
        return session.createQuery(criteriaQuery).getSingleResult();
    }

    @Transactional
    public List<Menu> findByTitle(String menuTitle) {
        return findMenuByField(MENU_TITLE, menuTitle);
    }

    private List<Menu> findMenuByField(String fieldTitle, Object value) {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = getCriteriaBuilder(session);

        CriteriaQuery<Menu> criteriaQuery = criteriaBuilder.createQuery(Menu.class);
        Root<Menu> root = criteriaQuery.from(Menu.class);
        Predicate condition = criteriaBuilder.equal(root.get(fieldTitle), value);
        criteriaQuery.where(condition);

        return session.createQuery(criteriaQuery).getResultList();
    }

    @Transactional
    public List<Menu> getAll() {
        Session session = getSession();

        CriteriaQuery<Menu> criteriaQuery = getCriteriaBuilder(session).createQuery(Menu.class);
        Root<Menu> root = criteriaQuery.from(Menu.class);
        criteriaQuery.select(root);

        return session.createQuery(criteriaQuery).getResultList();
    }

    @Transactional
    public void delete(int id) {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = getCriteriaBuilder(session);

        CriteriaDelete<Menu> delete = criteriaBuilder.createCriteriaDelete(Menu.class);
        Root root = delete.from(Menu.class);
        delete.where(criteriaBuilder.equal(root.get(ID), id));

        session.createQuery(delete).executeUpdate();
    }

    @Transactional
    public void updateMenuDishes(int id, List<Dish> newDishes) {
        updateMenuFieldById(id, DISHES_IN_MENU, newDishes);
    }

    @Transactional
    public void updateTitle(int id, String newMenuTitle) {
        updateMenuFieldById(id, MENU_TITLE, newMenuTitle);
    }

    private void updateMenuFieldById(int id, String fieldTitle, Object newValue) {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = getCriteriaBuilder(session);

        CriteriaUpdate<Menu> update = criteriaBuilder.createCriteriaUpdate(Menu.class);
        Root employeeRoot = update.from(Menu.class);
        Predicate condition = criteriaBuilder.equal(employeeRoot.get(ID), id);
        update.set(fieldTitle, newValue);
        update.where(condition);

        session.createQuery(update).executeUpdate();
    }
}
