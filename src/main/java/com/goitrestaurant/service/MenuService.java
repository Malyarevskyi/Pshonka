package com.goitrestaurant.service;

import com.goitrestaurant.dao.MenuDao;
import com.goitrestaurant.model.Dish;
import com.goitrestaurant.model.Menu;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class MenuService {

    private MenuDao menuDao;

    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    @Transactional
    public void create(Menu menu) {
        menuDao.create(menu);
    }

    @Transactional
    public Menu findById(int id) {
        return menuDao.findById(id);
    }

    @Transactional
    public List<Menu> findByTitle(String menuTitle) {
        return menuDao.findByTitle(menuTitle);
    }

    @Transactional
    public List<Menu> getAll() {
        return menuDao.getAll();
    }

    @Transactional
    public void delete(int id) {
        menuDao.delete(id);
    }

    @Transactional
    public void updateTitle(int id, String newMenuTitle) {
        menuDao.updateTitle(id, newMenuTitle);
    }

    @Transactional
    public void updateMenuDishes(int id, List<Dish> newDishes) {
        menuDao.updateMenuDishes(id, newDishes);
    }

}
