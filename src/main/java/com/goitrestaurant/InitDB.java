package com.goitrestaurant;

import com.goitrestaurant.dao.*;
import com.goitrestaurant.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class InitDB {

    @Autowired
    private PositionDao positionDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private IngredientDao ingredientDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private WarehouseDao warehouseDao;

    @Autowired
    private DeskDao deskDao;

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private DishDao dishDao;

    @PostConstruct
    public void init() throws IOException {
        positionDao.create(new Position("waiter"));
        positionDao.create(new Position("cook"));
        positionDao.create(new Position("manager"));
        positionDao.create(new Position("bartender"));
        positionDao.create(new Position("security guard"));

        Employee employee1 = new Employee();
        employee1.setFirstName("Ivan");
        employee1.setLastName("Ivanov");
        employee1.setBirthday(Common.stringToDate("1988-08-18"));
        employee1.setPhone("1234567890");
        employee1.setPosition(positionDao.findByTitle("waiter").get(0));
        employee1.setSalary(10000.0F);
        employeeDao.create(employee1);

        Employee employee2 = new Employee();
        employee2.setFirstName("Petr");
        employee2.setLastName("Petrov");
        employee2.setBirthday(Common.stringToDate("1989-09-07"));
        employee2.setPhone("1234567890");
        employee2.setPosition(positionDao.findByTitle("waiter").get(0));
        employee2.setSalary(11000.0F);
        employeeDao.create(employee2);

        Employee employee3 = new Employee();
        employee3.setFirstName("Sidor");
        employee3.setLastName("Sidorov");
        employee3.setBirthday(Common.stringToDate("1990-05-05"));
        employee3.setPhone("9876543210");
        employee3.setPosition(positionDao.findByTitle("cook").get(0));
        employee3.setSalary(13000.0F);
        employeeDao.create(employee3);

        Employee employee4 = new Employee();
        employee4.setFirstName("Valera");
        employee4.setLastName("Valerov");
        employee4.setBirthday(Common.stringToDate("1991-01-05"));
        employee4.setPhone("9876543210");
        employee4.setPosition(positionDao.findByTitle("cook").get(0));
        employee4.setSalary(23000.0F);
        employeeDao.create(employee4);

        Employee employee5 = new Employee();
        employee5.setFirstName("Alex");
        employee5.setLastName("Alexsandrov");
        employee5.setBirthday(Common.stringToDate("1985-09-13"));
        employee5.setPhone("0987777777777");
        employee5.setPosition(positionDao.findByTitle("manager").get(0));
        employee5.setSalary(23000.0F);
        employeeDao.create(employee5);

        Employee employee6 = new Employee();
        employee6.setFirstName("Rita");
        employee6.setLastName("Rita");
        employee6.setBirthday(Common.stringToDate("1989-06-19"));
        employee6.setPhone("05088888888");
        employee6.setPosition(positionDao.findByTitle("bartender").get(0));
        employee6.setSalary(35000.0F);
        employeeDao.create(employee6);

        Employee employee7 = new Employee();
        employee7.setFirstName("Ira");
        employee7.setLastName("Petrova");
        employee7.setBirthday(Common.stringToDate("1993-07-08"));
        employee7.setPhone("9988777666");
        employee7.setPosition(positionDao.findByTitle("bartender").get(0));
        employee7.setSalary(29060.0F);
        employeeDao.create(employee1);

        Employee employee8 = new Employee();
        employee8.setFirstName("Vaselisa");
        employee8.setLastName("Ivnova");
        employee8.setBirthday(Common.stringToDate("1996-03-21"));
        employee8.setPhone("067333333333");
        employee8.setPosition(positionDao.findByTitle("security guard").get(0));
        employee8.setSalary(29000.0F);
        employeeDao.create(employee1);

        ingredientDao.create(new Ingredient("pshenka"));
        ingredientDao.create(new Ingredient("potato"));
        ingredientDao.create(new Ingredient("fish"));
        ingredientDao.create(new Ingredient("pork"));
        ingredientDao.create(new Ingredient("beef"));
        ingredientDao.create(new Ingredient("eggs"));
        ingredientDao.create(new Ingredient("cheese"));
        ingredientDao.create(new Ingredient("tomatoes"));
        ingredientDao.create(new Ingredient("union"));
        ingredientDao.create(new Ingredient("duck"));
        ingredientDao.create(new Ingredient("apple"));
        ingredientDao.create(new Ingredient("carrot"));
        ingredientDao.create(new Ingredient("sausage"));
        ingredientDao.create(new Ingredient("cucumbers"));
        ingredientDao.create(new Ingredient("olive"));

        categoryDao.create(new Category("meat"));
        categoryDao.create(new Category("fruit"));
        categoryDao.create(new Category("salads"));
        categoryDao.create(new Category("juices"));
        categoryDao.create(new Category("alcohol"));

        warehouseDao.create(new Warehouse(ingredientDao.findByTitle("pshenka").get(0), 1400.0F));
        warehouseDao.create(new Warehouse(ingredientDao.findByTitle("potato").get(0), 400.0F));
        warehouseDao.create(new Warehouse(ingredientDao.findByTitle("fish").get(0), 20.0F));
        warehouseDao.create(new Warehouse(ingredientDao.findByTitle("pork").get(0), 30.0F));
        warehouseDao.create(new Warehouse(ingredientDao.findByTitle("beef").get(0), 47.0F));
        warehouseDao.create(new Warehouse(ingredientDao.findByTitle("eggs").get(0), 200.0F));
        warehouseDao.create(new Warehouse(ingredientDao.findByTitle("cheese").get(0), 48.0F));
        warehouseDao.create(new Warehouse(ingredientDao.findByTitle("tomatoes").get(0), 100.0F));
        warehouseDao.create(new Warehouse(ingredientDao.findByTitle("union").get(0), 15.0F));
        warehouseDao.create(new Warehouse(ingredientDao.findByTitle("duck").get(0), 42.0F));
        warehouseDao.create(new Warehouse(ingredientDao.findByTitle("apple").get(0), 190.0F));
        warehouseDao.create(new Warehouse(ingredientDao.findByTitle("carrot").get(0), 200.0F));
        warehouseDao.create(new Warehouse(ingredientDao.findByTitle("sausage").get(0), 50.0F));
        warehouseDao.create(new Warehouse(ingredientDao.findByTitle("cucumbers").get(0), 45.0F));
        warehouseDao.create(new Warehouse(ingredientDao.findByTitle("olive").get(0), 36.0F));

        deskDao.create(new Desk("First"));
        deskDao.create(new Desk("Second"));
        deskDao.create(new Desk("Third"));
        deskDao.create(new Desk("Fourth"));
        deskDao.create(new Desk("Fifth"));

        List<Ingredient> olivieIngredient = new ArrayList<>();
        olivieIngredient.add(ingredientDao.findByTitle("potato").get(0));
        olivieIngredient.add(ingredientDao.findByTitle("eggs").get(0));
        olivieIngredient.add(ingredientDao.findByTitle("union").get(0));
        olivieIngredient.add(ingredientDao.findByTitle("carrot").get(0));
        olivieIngredient.add(ingredientDao.findByTitle("sausage").get(0));
        Dish dish1 = new Dish();
        dish1.setDishTitle("Olivie");
        dish1.setCategory(categoryDao.findByTitle("salads").get(0));
        dish1.setPrice(35.0F);
        dish1.setWeight(200.0F);
        dish1.setIngredients(olivieIngredient);
        dish1.setDescription("Olivier salad is a traditional salad dish in Russian cuisine. It is usually made with diced boiled potatoes, carrots, eggs, celeriac, onions, diced boiled chicken, dressed with mayonnaise.");
        dishDao.create(dish1);

        List<Ingredient> duckWithApplesIngredients = new ArrayList<>();
        duckWithApplesIngredients.add(ingredientDao.findByTitle("duck").get(0));
        duckWithApplesIngredients.add(ingredientDao.findByTitle("apple").get(0));
        Dish dish2 = new Dish();
        dish2.setDishTitle("Duck with apples");
        dish2.setCategory(categoryDao.findByTitle("meat").get(0));
        dish2.setPrice(70.0F);
        dish2.setWeight(430.0F);
        dish2.setIngredients(duckWithApplesIngredients);
        dish2.setDescription("Duck with apples is definitely elegant, beautiful and satisfying dish.");
        dishDao.create(dish2);

        List<Ingredient> greekSaladIngredients = new ArrayList<>();
        greekSaladIngredients.add(ingredientDao.findByTitle("tomatoes").get(0));
        greekSaladIngredients.add(ingredientDao.findByTitle("cucumbers").get(0));
        greekSaladIngredients.add(ingredientDao.findByTitle("olive").get(0));
        Dish dish3 = new Dish();
        dish3.setDishTitle("Greek salad");
        dish3.setCategory(categoryDao.findByTitle("salads").get(0));
        dish3.setPrice(30.0F);
        dish3.setWeight(180.0F);
        dish3.setIngredients(greekSaladIngredients);
        dish3.setDescription("Greek salad is a salad in Greek cuisine. Greek salad is made with pieces of tomatoes, sliced cucumbers, onion, feta cheese, and olives");
        dishDao.create(dish3);


        List<Dish> businessDishes = new ArrayList<>();
        businessDishes.add(dishDao.findByTitle("Olivie").get(0));
        businessDishes.add(dishDao.findByTitle("Duck with apples").get(0));
        Menu menu1 = new Menu();
        menu1.setMenuTitle("First Menu");
        menu1.setDishesInMenu(businessDishes);
        menuDao.create(menu1);

        List<Dish> buffetDishes = new ArrayList<>();
        buffetDishes.add(dishDao.findByTitle("Greek salad").get(0));
        buffetDishes.add(dishDao.findByTitle("Duck with apples").get(0));
        Menu menu2 = new Menu();
        menu2.setMenuTitle("Second Menu");
        menu2.setDishesInMenu(buffetDishes);
        menuDao.create(menu2);

        List<Dish> tableDishes = new ArrayList<>();
        tableDishes.add(dishDao.findByTitle("Greek salad").get(0));
        Menu menu3 = new Menu();
        menu3.setMenuTitle("Third Menu");
        menu3.setDishesInMenu(tableDishes);
        menuDao.create(menu3);

        List<Dish> carteDishes = new ArrayList<>();
        carteDishes.add(dishDao.findByTitle("Duck with apples").get(0));
        Menu menu4 = new Menu();
        menu4.setMenuTitle("Fourth Menu");
        menu4.setDishesInMenu(carteDishes);
        menuDao.create(menu4);

    }

    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public void setIngredientDao(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public void setWarehouseDao(WarehouseDao warehouseDao) {
        this.warehouseDao = warehouseDao;
    }

    public void setDeskDao(DeskDao deskDao) {
        this.deskDao = deskDao;
    }

    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }

}
