package com.goitrestaurant.dao.hibernate;

import com.goitrestaurant.dao.PositionDao;
import com.goitrestaurant.model.Position;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.List;

public class HPositionDao implements PositionDao {

    private static final String ID = "id";
    private static final String POSITION_TITLE = "positionTitle";

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
    public void create(Position position) {
        sessionFactory.getCurrentSession().saveOrUpdate(position);
    }

    @Transactional
    public Position findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Position> criteriaQuery = criteriaBuilder.createQuery(Position.class);
        Root<Position> root = criteriaQuery.from(Position.class);
        Predicate condition = criteriaBuilder.equal(root.get(ID), id);
        criteriaQuery.where(condition);
        return session.createQuery(criteriaQuery).getSingleResult();
    }

    @Transactional
    public List<Position> findByTitle(String positionTitle) {
        return findPositionByField(POSITION_TITLE, positionTitle);
    }

    private List<Position> findPositionByField(String fieldTitle, Object value) {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = getCriteriaBuilder(session);

        CriteriaQuery<Position> criteriaQuery = criteriaBuilder.createQuery(Position.class);
        Root<Position> root = criteriaQuery.from(Position.class);
        Predicate condition = criteriaBuilder.equal(root.get(fieldTitle), value);
        criteriaQuery.where(condition);

        return session.createQuery(criteriaQuery).getResultList();
    }

    @Transactional
    public List<Position> getAll() {
        Session session = getSession();

        CriteriaQuery<Position> criteriaQuery = getCriteriaBuilder(session).createQuery(Position.class);
        Root<Position> root = criteriaQuery.from(Position.class);
        criteriaQuery.select(root);

        return session.createQuery(criteriaQuery).getResultList();
    }

    @Transactional
    public void delete(int id) {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = getCriteriaBuilder(session);

        CriteriaDelete<Position> delete = criteriaBuilder.createCriteriaDelete(Position.class);
        Root root = delete.from(Position.class);
        delete.where(criteriaBuilder.equal(root.get(ID), id));

        session.createQuery(delete).executeUpdate();
    }

    @Transactional
    public void updateTitle(int id, String newTitle) {
        updatePositionFieldById(id, POSITION_TITLE, newTitle);
    }

    private void updatePositionFieldById(int id, String fieldTitle, Object newValue) {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = getCriteriaBuilder(session);

        CriteriaUpdate<Position> update = criteriaBuilder.createCriteriaUpdate(Position.class);
        Root employeeRoot = update.from(Position.class);
        Predicate condition = criteriaBuilder.equal(employeeRoot.get(ID), id);
        update.set(fieldTitle, newValue);
        update.where(condition);

        session.createQuery(update).executeUpdate();
    }

}
