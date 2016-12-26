package com.goitrestaurant.web;

import com.goitrestaurant.model.Dish;
import com.goitrestaurant.model.Employee;
import com.goitrestaurant.model.Menu;
import com.goitrestaurant.service.DishService;
import com.goitrestaurant.service.EmployeeService;
import com.goitrestaurant.service.MenuService;
import com.goitrestaurant.service.PositionService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ClientController {

    private EmployeeService employeeService;
    private PositionService positionService;
    private MenuService menuService;
    private DishService dishService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setPositionService(PositionService positionService) {
        this.positionService = positionService;
    }

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @Autowired
    public void setDishService(DishService dishService) {
        this.dishService = dishService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(Model model) {
        return "client/main";
    }

    @RequestMapping(value = "/client/search", method = RequestMethod.GET)
    public String findDishByTitle(@RequestParam("dishTitle") String dishTitle, Model model) {
        List<Dish> dishList = dishService.findByTitle(dishTitle);
        model.addAttribute("dishesMap", getDishesMap(dishList));
        return "client/main";
    }

    @RequestMapping(value = "client/booking", method = RequestMethod.GET)
    public String showBooking(Model model) {
        return "client/show_booking";
    }

    @RequestMapping(value = "client/dishes", method = RequestMethod.GET)
    public String showDishes(Model model) {
        List<Dish> dishList = dishService.getAll();
        model.addAttribute("dishesMap", getDishesMap(dishList));
        ;return "client/show_dishes";
    }

    @RequestMapping(value = "client/personal", method = RequestMethod.GET)
    public String show_waiters(Model model) {
        List<Employee> waiters =
                employeeService.getAllEmployeesByPosition(positionService.findByTitle("waiter").get(0));
        Map<Employee, String> waitersMap = new HashMap<>();

        for (Employee employee : waiters) {
            if (employee.getImage() != null) {
                byte[] encoded = Base64.encodeBase64(employee.getImage());
                try {
                    String encodedString = new String(encoded, "UTF-8");
                    waitersMap.put(employee, encodedString);
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException("Can't display image!");
                }
            } else {
                waitersMap.put(employee, null);
            }
        }

        model.addAttribute("waitersMap", waitersMap);

        return "client/show_waiters";
    }

    @RequestMapping(value = "client/contacts", method = RequestMethod.GET)
    public String showContact(Model model) {
        return "client/show_contact";
    }

    @RequestMapping(value = "client/menu", method = RequestMethod.GET)
    public String showAllMenus(Model model) {
        model.addAttribute("menus", menuService.getAll());
        return "client/list_menus";
    }

    @RequestMapping(value = "client/menu/{id}", method = RequestMethod.GET)
    public String showMenuById(@PathVariable("id") int id, Model model) {
        Menu menu = menuService.findById(id);
        model.addAttribute("dishesMap", getDishesMap(menu.getDishesInMenu()));
        model.addAttribute("menu", menu);

        return "client/show_menu";
    }

    private Map<Dish, String> getDishesMap(List<Dish> dishList) {
        Map<Dish, String> dishesMap = new HashMap<>();

        for (Dish dish : dishList) {
            if (dish.getPicture() != null) {
                byte[] encoded = Base64.encodeBase64(dish.getPicture());
                try {
                    String encodedString = new String(encoded, "UTF-8");
                    dishesMap.put(dish, encodedString);
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException("Can't display picture!");
                }
            } else {
                dishesMap.put(dish, null);
            }
        }

        return dishesMap;
    }

}
