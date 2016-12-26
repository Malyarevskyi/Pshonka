package com.goitrestaurant.service;

import com.goitrestaurant.dao.CategoryDao;
import com.goitrestaurant.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class CategoryService {

    private CategoryDao categoryDao;

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Transactional
    public void create(Category category) {
        categoryDao.create(category);
    }

    @Transactional
    public Category findById(int id) {
        return categoryDao.findById(id);
    }

    @Transactional
    public List<Category> findByTitle(String categoryTitle) {
        return categoryDao.findByTitle(categoryTitle);
    }

    @Transactional
    public List<Category> getAll() {
        return categoryDao.getAll();
    }

    @Transactional
    public void delete(int id) {
        categoryDao.delete(id);
    }

    @Transactional
    public void updateTitle(int id, String newTitle) {
        categoryDao.updateTitle(id, newTitle);
    }
}
