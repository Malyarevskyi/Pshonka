package com.goitrestaurant.dao.hibernate;

import com.goitrestaurant.dao.CategoryDao;
import com.goitrestaurant.dao.DishDao;
import com.goitrestaurant.dao.IngredientDao;
import com.goitrestaurant.dao.MenuDao;
import com.goitrestaurant.model.Category;
import com.goitrestaurant.model.Dish;
import com.goitrestaurant.model.Ingredient;
import com.goitrestaurant.model.Menu;
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
public class HMenuDaoTest {

    private MenuDao menuDao;
    private DishDao dishDao;
    private IngredientDao ingredientDao;
    private CategoryDao categoryDao;

    @Autowired
    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    @Autowired
    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }

    @Autowired
    public void setIngredientDao(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }

    @Autowired
    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Test
    @Transactional
    @Rollback
    public void testCreateDeleteMenu() throws Exception {
        Category category = new Category("testCategory");
        categoryDao.create(category);

        List<Ingredient> duckWithApplesIngredients = new ArrayList<>();
        Ingredient duck = new Ingredient("duck");
        ingredientDao.create(duck);
        Ingredient apple = new Ingredient("apple");
        ingredientDao.create(apple);
        duckWithApplesIngredients.add(ingredientDao.findByTitle(duck.getIngredientTitle()).get(0));
        duckWithApplesIngredients.add(ingredientDao.findByTitle(apple.getIngredientTitle()).get(0));
        Dish dish = new Dish();
        dish.setDishTitle("Duck with apples");
        dish.setCategory(categoryDao.findByTitle(category.getCategoryTitle()).get(0));
        dish.setPrice(70.0F);
        dish.setWeight(430.0F);
        dish.setIngredients(duckWithApplesIngredients);
        dishDao.create(dish);


        List<Dish> testDishes = new ArrayList<>();
        testDishes.add(dishDao.findByTitle("Duck with apples").get(0));

        String title = "testMenu";
        Menu menuOne = new Menu();
        menuOne.setMenuTitle(title);
        menuOne.setDishesInMenu(testDishes);
        menuDao.create(menuOne);

        Menu menu = menuDao.getAll().get(0);
        assertEquals(menu.getMenuTitle(), title);
        assertArrayEquals(menu.getDishesInMenu().toArray(), testDishes.toArray());

        menuDao.delete(menu.getId());
        assertTrue(menuDao.getAll().size() == 0);

    }
}