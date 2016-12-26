package com.goitrestaurant.web.admin;

import com.goitrestaurant.dao.IngredientDao;
import com.goitrestaurant.model.Warehouse;
import com.goitrestaurant.service.WarehouseService;
import com.goitrestaurant.web.admin.validators.WarehouseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WarehouseController {

    private WarehouseService warehouseService;
    private IngredientDao ingredientDao;

    @Autowired
    private WarehouseValidator warehouseValidator;

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        binder.addValidators(warehouseValidator);
    }

    @RequestMapping(value = "/admin/warehouses", method = RequestMethod.GET)
    public String showAllWarehouses(Model model) {
        model.addAttribute("warehouses", warehouseService.getAll());
        return "/admin/warehouses/list_warehouses";
    }

    @RequestMapping(value = "/admin/warehouses/{id}", method = RequestMethod.GET)
    public String showWarehouse(@PathVariable("id") int id, Model model) {
        Warehouse warehouse = warehouseService.findById(id);
        if (warehouse == null) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "Warehouse not found");
        }
        model.addAttribute("warehouse", warehouse);
        return "/admin/warehouses/show";
    }

    @RequestMapping(value = "/admin/warehouses/{id}/delete", method = RequestMethod.GET)
    public String deleteWarehouse(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {
        warehouseService.delete(id);

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Warehouse is deleted!");

        return "redirect:/admin/warehouses";
    }

    @RequestMapping(value = "/admin/warehouses/{id}/update", method = RequestMethod.GET)
    public String showUpdateWarehouseForm(@PathVariable("id") int id, Model model) {
        Warehouse warehouse = warehouseService.findById(id);
        model.addAttribute("warehouse_form", warehouse);
        model.addAttribute("ingredientList", ingredientDao.getAll());

        return "/admin/warehouses/warehouse_form";
    }

    @RequestMapping(value = "/admin/warehouses/create", method = RequestMethod.GET)
    public String showCreateWarehouseForm(Model model) {
        Warehouse warehouse = new Warehouse();
        model.addAttribute("warehouse_form", warehouse);
        model.addAttribute("ingredientList", ingredientDao.getAll());

        return "/admin/warehouses/warehouse_form";
    }

    @RequestMapping(value = "/admin/warehouses", method = RequestMethod.POST)
    public String saveOrUpdateWarehouse(@ModelAttribute("warehouse_form") @Validated Warehouse warehouse,
                                        BindingResult result, final RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "/admin/warehouses/warehouse_form";
        } else {

            redirectAttributes.addFlashAttribute("css", "success");
            if (warehouse.isNew()) {
                redirectAttributes.addFlashAttribute("msg", "Warehouse added successfully!");
                warehouse.setIngredient(ingredientDao.findByTitle(warehouse.getIngredient().getIngredientTitle()).get(0));
                warehouseService.create(warehouse);
            } else {
                redirectAttributes.addFlashAttribute("msg", "Warehouse updated successfully!");
                warehouseService.updateWarehouseIngredient(warehouse.getId(),
                        ingredientDao.findByTitle(warehouse.getIngredient().getIngredientTitle()).get(0));
                warehouseService.updateWarehouseAmount(warehouse.getId(), warehouse.getAmount());
            }

            return "redirect:/admin/warehouses/" + warehouse.getId();
        }
    }

    @Autowired
    public void setWarehouseService(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @Autowired
    public void setIngredientDao(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }

}