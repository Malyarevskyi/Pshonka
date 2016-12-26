package com.goitrestaurant.web.admin;

import com.goitrestaurant.dao.DeskDao;
import com.goitrestaurant.dao.EmployeeDao;
import com.goitrestaurant.dao.PositionDao;
import com.goitrestaurant.model.DeskStatus;
import com.goitrestaurant.model.Employee;
import com.goitrestaurant.model.Orders;
import com.goitrestaurant.service.OrdersService;
import com.goitrestaurant.web.admin.validators.OrdersValidator;
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
public class OrderController {

    private OrdersService orderService;
    private EmployeeDao employeeDao;
    private PositionDao positionDao;
    private DeskDao deskDao;
    //private DishDao dishDao;

    @Autowired
    private OrdersValidator ordersValidator;

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        binder.addValidators(ordersValidator);

        binder.registerCustomEditor(Employee.class, "waiter", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(employeeDao.findById(Integer.valueOf(text)));
            }
        });

        /*binder.registerCustomEditor(Dish.class, "dishesInOrder", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(dishDao.findByTitle(text));
            }
        });*/

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, "orderDate", new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/admin/orders", method = RequestMethod.GET)
    public String showAllOrders(Model model) {
        model.addAttribute("orders", orderService.getAll());
        return "admin/orders/list_orders";
    }

    @RequestMapping(value = "/admin/orders/{id}", method = RequestMethod.GET)
    public String showOrder(@PathVariable("id") int id, Model model) {
        Orders order = orderService.findById(id);
        if (order == null) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "Order not found");
        }

        model.addAttribute("order", order);
        return "admin/orders/show";
    }

    @RequestMapping(value = "/admin/orders/{id}/delete", method = RequestMethod.GET)
    public String deleteOrder(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {
        orderService.delete(id);
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Order is deleted!");

        return "redirect:/admin/orders";
    }

    @RequestMapping(value = "/admin/orders/{id}/update", method = RequestMethod.GET)
    public String showUpdateOrderForm(@PathVariable("id") int id, Model model) {
        Orders order = orderService.findById(id);
        model.addAttribute("order_form", order);

        model.addAttribute("waiterList", employeeDao.getAllEmployeesByPosition(positionDao.findByTitle("waiter").get(0)));
        model.addAttribute("deskList", deskDao.getAllFreeDesk());
        //model.addAttribute("dishesAll", dishDao.getAll());

        return "admin/orders/order_form";
    }

    @RequestMapping(value = "/admin/orders/create", method = RequestMethod.GET)
    public String showCreateOrderForm(Model model) {
        Orders order = new Orders();
        model.addAttribute("order_form", order);
        model.addAttribute("waiterList", employeeDao.getAllEmployeesByPosition(positionDao.findByTitle("waiter").get(0)));
        model.addAttribute("deskList", deskDao.getAllFreeDesk());
        //model.addAttribute("dishesAll", dishDao.getAll());

        return "admin/orders/order_form";
    }

    @RequestMapping(value = "/admin/orders/addOrUpdateOrders", method = RequestMethod.POST)
    public String saveOrUpdateOrders(@ModelAttribute("order_form") @Validated Orders orders,
                                     BindingResult result, final RedirectAttributes redirectAttributes) {

        System.out.println("start: " + orders);

        if (result.hasErrors()) {
            System.out.println(result.toString());
            return "admin/orders/order_form";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
            if(orders.isNew()){
                redirectAttributes.addFlashAttribute("msg", "Orders added successfully!");
                orders.setDesk(deskDao.findByTitle(orders.getDesk().getDeskTitle()).get(0));

                deskDao.updateStatus(orders.getDesk().getId(), DeskStatus.ORDERED);

                orderService.create(orders);

            }else{
                redirectAttributes.addFlashAttribute("msg", "Orders updated successfully!");

                orderService.updateOrderWaiter(orders.getId(), orders.getWaiter());
                orderService.updateOrderDate(orders.getId(), orders.getOrderDate());
                //orderService.updateOrderDesk(orders.getId(), orders.getDesk());

                /*employeeService.updateEmployeeLastName(employee.getId(), employee.getLastName());
                employeeService.updateEmployeeFirstName(employee.getId(), employee.getFirstName());
                employeeService.updateEmployeeBirthday(employee.getId(), employee.getBirthday());
                employeeService.updateEmployeePhone(employee.getId(), employee.getPhone());
                employeeService.updateEmployeePositionId(employee.getId(),
                        positionService.findPositionByTitle(employee.getPosition().getPositionTitle()));
                employeeService.updateEmployeeSalary(employee.getId(), employee.getSalary());*/
            }

            return "redirect:/admin/orders/" + orders.getId();
        }

    }

    @Autowired
    public void setOrderService(OrdersService orderService) {
        this.orderService = orderService;
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

    /*@Autowired
    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }*/
}