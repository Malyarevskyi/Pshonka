package com.goitrestaurant.service;

import com.goitrestaurant.dao.DishDao;
import com.goitrestaurant.model.Category;
import com.goitrestaurant.model.Dish;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class DishService {

    private DishDao dishDao;

    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }

    @Transactional
    public void create(Dish dish) {
        dishDao.create(dish);
    }

    @Transactional
    public Dish findById(int id) {
        return dishDao.findById(id);
    }

    @Transactional
    public List<Dish> findByTitle(String dishTitle) {
        return dishDao.findByTitle(dishTitle);
    }

    @Transactional
    public List<Dish> getAll() {
        return dishDao.getAll();
    }

    @Transactional
    public void delete(int id) {
        dishDao.delete(id);
    }

    @Transactional
    public void updateTitle(int id, String newDishTitle) {
        dishDao.updateTitle(id, newDishTitle);
    }

    @Transactional
    public void updateDishCategory(int id, Category newCategory) {
        dishDao.updateDishCategory(id, newCategory);
    }

    @Transactional
    public void updateDishPrice(int id, float newDishPrice) {
        dishDao.updateDishPrice(id, newDishPrice);
    }

    @Transactional
    public void updateDishWeight(int id, float newDishWeight) {
        dishDao.updateDishWeight(id, newDishWeight);
    }

    @Transactional
    public void updateDishPicture(int id, byte[] newPicture) {
        dishDao.updateDishPicture(id, newPicture);
    }

    @Transactional
    public void updateDishDescription(int id, String newDescription) {
        dishDao.updateDishDescription(id, newDescription);
    }

}
