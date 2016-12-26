package com.goitrestaurant.service;

import com.goitrestaurant.dao.WarehouseDao;
import com.goitrestaurant.model.Ingredient;
import com.goitrestaurant.model.Warehouse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class WarehouseService {

    private WarehouseDao warehouseDao;

    public void setWarehouseDao(WarehouseDao warehouseDao) {
        this.warehouseDao = warehouseDao;
    }

    @Transactional
    public void create(Warehouse warehouse) {
        warehouseDao.create(warehouse);
    }

    @Transactional
    public Warehouse findById(int id) {
        return warehouseDao.findById(id);
    }

    @Transactional
    public List<Warehouse> getAll() {
        return warehouseDao.getAll();
    }

    @Transactional
    public void delete(int id) {
        warehouseDao.delete(id);
    }

    @Transactional
    public void updateWarehouseIngredient(int id, Ingredient newWarehouseIngredient) {
        warehouseDao.updateWarehouseIngredient(id, newWarehouseIngredient);
    }

    @Transactional
    public void updateWarehouseAmount(int id, float newWarehouseAmount) {
        warehouseDao.updateWarehouseAmount(id, newWarehouseAmount);
    }
}
