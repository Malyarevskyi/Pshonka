package com.goitrestaurant.dao.hibernate;

import com.goitrestaurant.dao.CategoryDao;
import com.goitrestaurant.model.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@ContextConfiguration(locations = {"classpath:application-context-test.xml", "classpath:hibernate-context-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class HCategoryDaoTest {

    private CategoryDao categoryDao;

    @Autowired
    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Test
    @Transactional
    @Rollback
    public void testCreateDeleteCategory() throws Exception {
        Category category = new Category("testCategory");
        categoryDao.create(category);
        List<Category> categories = categoryDao.getAll();
        assertEquals(category.getCategoryTitle(), categories.get(0).getCategoryTitle());
        assertTrue(categories.size() == 1);

        categoryDao.delete(categories.get(0).getId());
        categories = categoryDao.getAll();
        assertTrue(categories.size() == 0);
    }
}