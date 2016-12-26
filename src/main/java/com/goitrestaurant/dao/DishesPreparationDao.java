package com.goitrestaurant.dao;

import com.goitrestaurant.model.Dish;
import com.goitrestaurant.model.DishesPreparation;
import com.goitrestaurant.model.Employee;
import com.goitrestaurant.model.Orders;

import java.sql.Date;

public interface DishesPreparationDao extends Dao<DishesPreparation> {

    void updateDishesPreparationCook(int id, Employee newCook);

    void updateDishesPreparationDate(int id, Date newDate);

    void updateDishesPreparationOrders(int id, Orders newOrders);

    void updateDishesPreparationDish(int id, Dish newDish);

}
