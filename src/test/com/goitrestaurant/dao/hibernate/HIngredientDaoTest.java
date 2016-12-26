package com.goitrestaurant.dao.hibernate;

import com.goitrestaurant.dao.IngredientDao;
import com.goitrestaurant.model.Ingredient;
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
public class HIngredientDaoTest {

    private IngredientDao ingredientDao;

    @Autowired
    public void setIngredientDao(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }

    @Test
    @Transactional
    @Rollback
    public void testCreateDeleteIngredient() throws Exception {
        Ingredient ingredient = new Ingredient("testIngredient");
        ingredientDao.create(ingredient);
        List<Ingredient> ingredients = ingredientDao.getAll();
        assertEquals(ingredient.getIngredientTitle(), ingredients.get(0).getIngredientTitle());
        assertTrue(ingredients.size() == 1);

        ingredientDao.delete(ingredients.get(0).getId());
        ingredients = ingredientDao.getAll();
        assertTrue(ingredients.size() == 0);
    }

}