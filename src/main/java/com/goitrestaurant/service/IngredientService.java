package com.goitrestaurant.service;

import com.goitrestaurant.dao.IngredientDao;
import com.goitrestaurant.model.Ingredient;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class IngredientService {

    private IngredientDao ingredientDao;

    public void setIngredientDao(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }

    @Transactional
    public void create(Ingredient ingredient) {
        ingredientDao.create(ingredient);
    }

    @Transactional
    public Ingredient findById(int id) {
        return ingredientDao.findById(id);
    }

    @Transactional
    public List<Ingredient> findByTitle(String ingredientTitle) {
        return ingredientDao.findByTitle(ingredientTitle);
    }

    @Transactional
    public List<Ingredient> getAll() {
        return ingredientDao.getAll();
    }

    @Transactional
    public void delete(int id) {
        ingredientDao.delete(id);
    }

    @Transactional
    public void updateTitle(int id, String newTitle) {
        ingredientDao.updateTitle(id, newTitle);
    }

}
