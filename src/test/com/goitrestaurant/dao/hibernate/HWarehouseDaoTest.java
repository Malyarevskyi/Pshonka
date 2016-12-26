package com.goitrestaurant.dao.hibernate;

import com.goitrestaurant.dao.IngredientDao;
import com.goitrestaurant.dao.WarehouseDao;
import com.goitrestaurant.model.Ingredient;
import com.goitrestaurant.model.Warehouse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@ContextConfiguration(locations = {"classpath:application-context-test.xml", "classpath:hibernate-context-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class HWarehouseDaoTest {

    private WarehouseDao warehouseDao;
    private IngredientDao ingredientDao;

    @Autowired
    public void setWarehouseDao(WarehouseDao warehouseDao) {
        this.warehouseDao = warehouseDao;
    }

    @Autowired
    public void setIngredientDao(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }

    @Test
    @Transactional
    @Rollback
    public void testCreateDeleteWarehouse() throws Exception {
        String ingredientTitle = "testIngredient";
        ingredientDao.create(new Ingredient(ingredientTitle));
        float amount = 134.0F;
        warehouseDao.create(new Warehouse(ingredientDao.findByTitle(ingredientTitle).get(0), amount));

        Warehouse warehouse = warehouseDao.getAll().get(0);
        assertEquals(warehouse.getIngredient(), ingredientDao.findByTitle(ingredientTitle).get(0));
        assertEquals(warehouse.getAmount(), amount, 0.01);

        warehouseDao.delete(warehouse.getId());
        assertTrue(warehouseDao.getAll().size() == 0);
    }

}