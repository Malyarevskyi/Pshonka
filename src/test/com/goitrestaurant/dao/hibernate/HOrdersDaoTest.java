package com.goitrestaurant.dao.hibernate;

import com.goitrestaurant.Common;
import com.goitrestaurant.dao.*;
import com.goitrestaurant.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@ContextConfiguration(locations = {"classpath:application-context-test.xml", "classpath:hibernate-context-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class HOrdersDaoTest {

    private OrderDao orderDao;
    private EmployeeDao employeeDao;
    private PositionDao positionDao;
    private DeskDao deskDao;
    private CategoryDao categoryDao;
    private IngredientDao ingredientDao;
    private DishDao dishDao;

    @Autowired
    public void setOrdersDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Autowired
    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Autowired
    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    @Autowired
    public void setDeskDao(DeskDao deskDao) {
        this.deskDao = deskDao;
    }

    @Autowired
    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Autowired
    public void setIngredientDao(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }

    @Autowired
    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }

    @Test
    @Transactional
    @Rollback
    public void testCreateDeleteOrders() throws Exception {
        /* create waiter */
        String lastName = "lastName";
        String firstName = "firstName";
        String phone = "1234567890";
        String positionTitle = "testWaiter";
        Date birthday = Common.stringToDate("1978-06-19");
        positionDao.create(new Position(positionTitle));
        float salary = 10000.0F;

        Employee currentWaiter = new Employee();
        currentWaiter.setLastName(lastName);
        currentWaiter.setFirstName(firstName);
        currentWaiter.setBirthday(birthday);
        currentWaiter.setPhone(phone);
        currentWaiter.setPosition(positionDao.findByTitle(positionTitle).get(0));
        currentWaiter.setSalary(salary);

        employeeDao.create(currentWaiter);

        /* create desk */
        String title = "testDesk";
        Desk desk = new Desk(title);
        deskDao.create(desk);

        /* create date */
        Date orderDate = Common.stringToDate("2016-03-01");

        /* create dishList */
        String titleDish = "testDist";
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
        currentDish.setDishTitle(titleDish);
        currentDish.setCategory(categoryDao.findByTitle(category.getCategoryTitle()).get(0));
        currentDish.setPrice(price);
        currentDish.setWeight(weight);
        currentDish.setIngredients(ingredients);

        dishDao.create(currentDish);

        List<Dish> dishes = new ArrayList<>();
        dishes.add(dishDao.findByTitle(titleDish).get(0));

        /* create order */
        Orders currentOrders = new Orders();
        currentOrders.setWaiter(employeeDao.getAllEmployeesByPosition(positionDao.findByTitle(positionTitle).get(0)).get(0));
        currentOrders.setDesk(deskDao.findById(1));
        currentOrders.setOrderDate(orderDate);
        //currentOrders.setDishesInOrder(dishes);
        orderDao.create(currentOrders);

        Orders orders = orderDao.getAll().get(0);

        System.out.println(orders);
        assertEquals(orders.getWaiter(), currentWaiter);
        assertEquals(orders.getDesk(), desk);
        assertEquals(orders.getOrderDate(), orderDate);
        //assertArrayEquals(orders.getDishesInOrder().toArray(), dishes.toArray());
        assertTrue(orderDao.getAll().size() == 1);

        orderDao.delete(orders.getId());
        assertTrue(orderDao.getAll().size() == 0);
    }
}