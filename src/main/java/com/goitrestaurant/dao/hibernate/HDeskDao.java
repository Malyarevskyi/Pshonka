package com.goitrestaurant.dao.hibernate;

import com.goitrestaurant.dao.DeskDao;
import com.goitrestaurant.model.Desk;
import com.goitrestaurant.model.DeskStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.List;

public class HDeskDao implements DeskDao{

    private static final String ID = "id";
    private static final String DESK_TITLE = "deskTitle";
    private static final String DESK_STATUS = "deskStatus";

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
    public void create(Desk desk) {
        sessionFactory.getCurrentSession().saveOrUpdate(desk);
    }

    @Transactional
    public Desk findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Desk> criteriaQuery = criteriaBuilder.createQuery(Desk.class);
        Root<Desk> root = criteriaQuery.from(Desk.class);
        Predicate condition = criteriaBuilder.equal(root.get(ID), id);
        criteriaQuery.where(condition);
        return session.createQuery(criteriaQuery).getSingleResult();
    }

    @Transactional
    public List<Desk> findByTitle(String deskTitle) {
        return findDeskByField(DESK_TITLE, deskTitle);
    }

    @Transactional
    public List<Desk> getAll() {
        Session session = getSession();

        CriteriaQuery<Desk> criteriaQuery = getCriteriaBuilder(session).createQuery(Desk.class);
        Root<Desk> root = criteriaQuery.from(Desk.class);
        criteriaQuery.select(root);

        return session.createQuery(criteriaQuery).getResultList();
    }

    @Transactional
    public void delete(int id) {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = getCriteriaBuilder(session);

        CriteriaDelete<Desk> delete = criteriaBuilder.createCriteriaDelete(Desk.class);
        Root root = delete.from(Desk.class);
        delete.where(criteriaBuilder.equal(root.get(ID), id));

        session.createQuery(delete).executeUpdate();
    }

    @Transactional
    public void updateTitle(int id, String newTitle) {
        updateDeskFieldById(id, DESK_TITLE, newTitle);
    }

    @Transactional
    public List<Desk> getAllFreeDesk() {
        return findDeskByField(DESK_STATUS, DeskStatus.FREE);
    }

    @Transactional
    public void updateStatus(int id, DeskStatus deskStatus) {
        updateDeskFieldById(id, DESK_STATUS, deskStatus);
    }

    private List<Desk> findDeskByField(String fieldTitle, Object value) {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = getCriteriaBuilder(session);

        CriteriaQuery<Desk> criteriaQuery = criteriaBuilder.createQuery(Desk.class);
        Root<Desk> root = criteriaQuery.from(Desk.class);
        Predicate condition = criteriaBuilder.equal(root.get(fieldTitle), value);
        criteriaQuery.where(condition);

        return session.createQuery(criteriaQuery).getResultList();
    }

    private void updateDeskFieldById(int id, String fieldTitle, Object newValue) {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = getCriteriaBuilder(session);

        CriteriaUpdate<Desk> update = criteriaBuilder.createCriteriaUpdate(Desk.class);
        Root employeeRoot = update.from(Desk.class);
        Predicate condition = criteriaBuilder.equal(employeeRoot.get(ID), id);
        update.set(fieldTitle, newValue);
        update.where(condition);

        session.createQuery(update).executeUpdate();
    }

}
