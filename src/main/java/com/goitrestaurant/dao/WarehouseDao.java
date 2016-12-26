package com.goitrestaurant.dao;

import com.goitrestaurant.model.Ingredient;
import com.goitrestaurant.model.Warehouse;

public interface WarehouseDao extends Dao<Warehouse> {

    void updateWarehouseIngredient(int id, Ingredient newWarehouseIngredient);

    void updateWarehouseAmount(int id, float newWarehouseAmount);

}
