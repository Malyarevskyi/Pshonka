package com.goitrestaurant.dao;

import com.goitrestaurant.model.Dish;
import com.goitrestaurant.model.Menu;

import java.util.List;

public interface MenuDao extends SimpleDao<Menu> {

    Menu findById(int id);

    void delete(int id);

    void updateMenuDishes(int id, List<Dish> newDishes);

}
