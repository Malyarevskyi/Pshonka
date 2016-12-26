package com.goitrestaurant.service;

import com.goitrestaurant.dao.DeskDao;
import com.goitrestaurant.model.Desk;
import com.goitrestaurant.model.DeskStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class DeskService {

    private DeskDao deskDao;

    public void setDeskDao(DeskDao deskDao) {
        this.deskDao = deskDao;
    }

    @Transactional
    public void create(Desk desk) {
        deskDao.create(desk);
    }

    @Transactional
    public Desk findById(int id) {
        return deskDao.findById(id);
    }

    @Transactional
    public List<Desk> findByTitle(String deskTitle) {
        return deskDao.findByTitle(deskTitle);
    }

    @Transactional
    public List<Desk> getAll() {
        return deskDao.getAll();
    }

    @Transactional
    public void delete(int id) {
        deskDao.delete(id);
    }

    @Transactional
    public void updateTitle(int id, String newTitle) {
        deskDao.updateTitle(id, newTitle);
    }

    @Transactional
    public List<Desk> getAllFreeDesk() {
        return deskDao.getAllFreeDesk();
    }

    @Transactional
    public void updateStatus(int id, DeskStatus deskStatus) {
        deskDao.updateStatus(id, deskStatus);
    }

}
