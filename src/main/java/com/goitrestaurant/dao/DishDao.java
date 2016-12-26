package com.goitrestaurant.dao;

import com.goitrestaurant.model.Category;
import com.goitrestaurant.model.Dish;

public interface DishDao extends SimpleDao<Dish>{

    void updateDishCategory(int id, Category newCategory);

    void updateDishPrice(int id, float newDishPrice);

    void updateDishWeight(int id, float newDishWeight);

    void updateDishPicture(int id, byte[] newPicture);

    void updateDishDescription(int id, String newDescription);

}
