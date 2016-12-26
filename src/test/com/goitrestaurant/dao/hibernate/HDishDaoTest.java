package com.goitrestaurant.dao.hibernate;

import com.goitrestaurant.dao.CategoryDao;
import com.goitrestaurant.dao.DishDao;
import com.goitrestaurant.dao.IngredientDao;
import com.goitrestaurant.model.Category;
import com.goitrestaurant.model.Dish;
import com.goitrestaurant.model.Ingredient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@ContextConfiguration(locations = {"classpath:application-context-test.xml", "classpath:hibernate-context-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class HDishDaoTest {

    private DishDao dishDao;
    private CategoryDao categoryDao;
    private IngredientDao ingredientDao;

    @Autowired
    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }

    @Autowired
    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Autowired
    public void setIngredientDao(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }

    @Test
    @Transactional
    @Rollback
    public void testCreateDeleteDish() throws Exception {
        String title = "testDist";
        String categoryTitle = "testCategory";
        Category category = new Category(categoryTitle);
        categoryDao.create(category);
        float price = 111.0F;
        float weight = 222.0F;

        ingredientDao.create(new Ingredient("duck"));
        ingredientDao.create(new Ingredient("apple"));
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredientDao.findByTitle("duck").get(0));
        ingredients.add(ingredientDao.findByTitle("apple").get(0));

        Dish currentDish = new Dish();
        currentDish.setDishTitle(title);
        currentDish.setCategory(categoryDao.findByTitle(category.getCategoryTitle()).get(0));
        currentDish.setPrice(price);
        currentDish.setWeight(weight);
        currentDish.setIngredients(ingredients);

        dishDao.create(currentDish);

        Dish dish = dishDao.getAll().get(0);
        assertEquals(dish.getDishTitle(), title);
        assertEquals(dish.getCategory(), category);
        assertEquals(dish.getPrice(), price, 0.01);
        assertEquals(dish.getWeight(), weight, 0.01);
        assertArrayEquals(dish.getIngredients().toArray(), ingredients.toArray());

        dishDao.delete(dish.getId());
        assertTrue(dishDao.getAll().size() == 0);

    }
}