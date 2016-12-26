package com.goitrestaurant.service;

import com.goitrestaurant.dao.DishesPreparationDao;
import com.goitrestaurant.model.Dish;
import com.goitrestaurant.model.DishesPreparation;
import com.goitrestaurant.model.Employee;
import com.goitrestaurant.model.Orders;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

public class DishesPreparationService {

    private DishesPreparationDao dishesPreparationDao;

    public void setDishesPreparationDao(DishesPreparationDao dishesPreparationDao) {
        this.dishesPreparationDao = dishesPreparationDao;
    }

    @Transactional
    public void create(DishesPreparation dishesPreparation) {
        dishesPreparationDao.create(dishesPreparation);
    }

    @Transactional
    public DishesPreparation findById(int id) {
        return dishesPreparationDao.findById(id);
    }

    @Transactional
    public List<DishesPreparation> getAll() {
        return dishesPreparationDao.getAll();
    }

    @Transactional
    public void delete(int id) {
        dishesPreparationDao.delete(id);
    }


    @Transactional
    public void updateDishesPreparationCook(int id, Employee newCook) {
        dishesPreparationDao.updateDishesPreparationCook(id, newCook);
    }

    @Transactional
    public void updateDishesPreparationDate(int id, Date newDate) {
        dishesPreparationDao.updateDishesPreparationDate(id, newDate);
    }

    @Transactional
    public void updateDishesPreparationOrders(int id, Orders newOrders) {
        dishesPreparationDao.updateDishesPreparationOrders(id, newOrders);
    }

    @Transactional
    public void updateDishesPreparationDish(int id, Dish newDish) {
        dishesPreparationDao.updateDishesPreparationDish(id, newDish);
    }

}
