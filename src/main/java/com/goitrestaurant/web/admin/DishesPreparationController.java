package com.goitrestaurant.web.admin;

import com.goitrestaurant.dao.DishDao;
import com.goitrestaurant.dao.EmployeeDao;
import com.goitrestaurant.dao.OrderDao;
import com.goitrestaurant.dao.PositionDao;
import com.goitrestaurant.model.Dish;
import com.goitrestaurant.model.DishesPreparation;
import com.goitrestaurant.model.Employee;
import com.goitrestaurant.model.Orders;
import com.goitrestaurant.service.DishesPreparationService;
import com.goitrestaurant.web.admin.validators.DishesPreparationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.beans.PropertyEditorSupport;
import java.sql.Date;
import java.text.SimpleDateFormat;

@Controller
public class DishesPreparationController {

    private DishesPreparationService dishesPreparationService;
    private EmployeeDao employeeDao;
    private PositionDao positionDao;
    private DishDao dishDao;
    private OrderDao orderDao;

    @Autowired
    private DishesPreparationValidator dishesPreparationValidator;

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        binder.addValidators(dishesPreparationValidator);

        binder.registerCustomEditor(Employee.class, "cook", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(employeeDao.findById(Integer.valueOf(text)));
            }
        });

        binder.registerCustomEditor(Dish.class, "dish", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(dishDao.findById(Integer.valueOf(text)));
            }
        });

        binder.registerCustomEditor(Orders.class, "order", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(orderDao.findById(Integer.valueOf(text)));
            }
        });

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, "datePreparation", new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/admin/dishes_preparations", method = RequestMethod.GET)
    public String showAllDishesPreparation(Model model) {
        model.addAttribute("dishes_preparations", dishesPreparationService.getAll());
        return "/admin/dishes_preparations/list_dishes_preparations";
    }

    @RequestMapping(value = "/admin/dishes_preparations/{id}", method = RequestMethod.GET)
    public String showDishesPreparation(@PathVariable("id") int id, Model model) {
        DishesPreparation dishesPreparation = dishesPreparationService.findById(id);
        if (dishesPreparation == null) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "DishesPreparation not found");
        }
        model.addAttribute("dishesPreparation", dishesPreparation);
        return "/admin/dishes_preparations/show";
    }

    @RequestMapping(value = "/admin/dishes_preparations/{id}/delete", method = RequestMethod.GET)
    public String deleteDishesPreparation(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {
        dishesPreparationService.delete(id);

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "DishesPreparation is deleted!");

        return "redirect:/admin/dishes_preparations";
    }

    @RequestMapping(value = "/admin/dishes_preparations/{id}/update", method = RequestMethod.GET)
    public String showUpdateDishesPreparationForm(@PathVariable("id") int id, Model model) {
        DishesPreparation dishesPreparation = dishesPreparationService.findById(id);
        model.addAttribute("dishes_preparation_form", dishesPreparation);
        model.addAttribute("cookList", employeeDao.getAllEmployeesByPosition(positionDao.findByTitle("cook").get(0)));
        model.addAttribute("dishList", dishDao.getAll());
        model.addAttribute("orderList", orderDao.getAll());

        return "/admin/dishes_preparations/dishes_preparation_form";
    }

    @RequestMapping(value = "/admin/dishes_preparations/create", method = RequestMethod.GET)
    public String showCreateDishesPreparationForm(Model model) {
        DishesPreparation dishesPreparation = new DishesPreparation();
        model.addAttribute("dishes_preparation_form", dishesPreparation);
        model.addAttribute("cookList", employeeDao.getAllEmployeesByPosition(positionDao.findByTitle("cook").get(0)));
        model.addAttribute("dishList", dishDao.getAll());
        model.addAttribute("orderList", orderDao.getAll());

        return "/admin/dishes_preparations/dishes_preparation_form";
    }

    @RequestMapping(value = "/admin/dishes_preparations", method = RequestMethod.POST)
    public String saveOrUpdateDishesPreparation(@ModelAttribute("dishes_preparation_form") @Validated DishesPreparation dishesPreparation,
                                                BindingResult result, final RedirectAttributes redirectAttributes) {

        System.out.println("dishesPreparation: " + dishesPreparation);

        if (result.hasErrors()) {
            System.out.println("result.hasErrors()!!!");
            return "admin/dishes_preparations/dishes_preparation_form";
        } else {

            redirectAttributes.addFlashAttribute("css", "success");
            if(dishesPreparation.isNew()){
                redirectAttributes.addFlashAttribute("msg", "DishesPreparation added successfully!");
                dishesPreparationService.create(dishesPreparation);

            }else{
                /*redirectAttributes.addFlashAttribute("msg", "DishesPreparation updated successfully!");
                dishesPreparationService.updateEmployeeLastName(employee.getId(), employee.getLastName());
                dishesPreparationService.updateEmployeeFirstName(employee.getId(), employee.getFirstName());
                dishesPreparationService.updateEmployeeBirthday(employee.getId(), employee.getBirthday());
                dishesPreparationService.updateEmployeePhone(employee.getId(), employee.getPhone());
                dishesPreparationService.updateEmployeePositionId(employee.getId(),
                        positionService.findPositionByTitle(employee.getPosition().getPositionTitle()));
                dishesPreparationService.updateEmployeeSalary(employee.getId(), employee.getSalary());*/
            }

            return "redirect:/admin/dishes_preparations/" + dishesPreparation.getId();
        }

    }

    @Autowired
    public void setDishesPreparationService(DishesPreparationService dishesPreparationService) {
        this.dishesPreparationService = dishesPreparationService;
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
    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }

    @Autowired
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

}