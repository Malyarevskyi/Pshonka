package com.goitrestaurant.service;

import com.goitrestaurant.dao.PositionDao;
import com.goitrestaurant.model.Position;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class PositionService {

    private PositionDao positionDao;

    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    @Transactional
    public void create(Position position) {
        positionDao.create(position);
    }

    @Transactional
    public Position findById(int id) {
        return positionDao.findById(id);
    }

    @Transactional
    public List<Position> findByTitle(String positionTitle) {
        return positionDao.findByTitle(positionTitle);
    }

    @Transactional
    public List<Position> getAll() {
        return positionDao.getAll();
    }

    @Transactional
    public void delete(int id) {
        positionDao.delete(id);
    }

    @Transactional
    public void updateTitle(int id, String newTitle) {
        positionDao.updateTitle(id, newTitle);
    }

}
