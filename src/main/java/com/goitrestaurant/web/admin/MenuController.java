package com.goitrestaurant.web.admin;

import com.goitrestaurant.dao.DishDao;
import com.goitrestaurant.model.Dish;
import com.goitrestaurant.model.Menu;
import com.goitrestaurant.service.MenuService;
import com.goitrestaurant.web.admin.validators.MenuValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.beans.PropertyEditorSupport;

@Controller
public class MenuController {

    private MenuService menuService;
    private DishDao dishDao;

    @Autowired
    private MenuValidator menuValidator;

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        binder.addValidators(menuValidator);

        binder.registerCustomEditor(Dish.class, "dishesInMenu", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(dishDao.findByTitle(text));
            }
        });
    }

    @RequestMapping(value = "/admin/menus", method = RequestMethod.GET)
    public String showAllMenus(Model model) {
        model.addAttribute("menus", menuService.getAll());
        return "/admin/menus/list_menus";
    }

    @RequestMapping(value = "/admin/menus/{id}", method = RequestMethod.GET)
    public String showMenus(@PathVariable("id") int id, Model model) {
        Menu menu = menuService.findById(id);

        if (menu == null) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "Menu not found");
        }
        model.addAttribute("menu", menu);
        return "/admin/menus/show";
    }


    @RequestMapping(value = "/admin/menus/{id}/delete", method = RequestMethod.GET)
    public String deleteMenu(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {
        menuService.delete(id);

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Menu is deleted!");

        return "redirect:/admin/menus";
    }

    @RequestMapping(value = "/admin/menus/{id}/update", method = RequestMethod.GET)
    public String showUpdateMenuForm(@PathVariable("id") int id, Model model) {
        Menu menu = menuService.findById(id);
        model.addAttribute("menu_form", menu);
        model.addAttribute("dishesAll", dishDao.getAll());

        return "/admin/menus/menu_form";
    }

    @RequestMapping(value = "/admin/menus/create", method = RequestMethod.GET)
    public String showCreateMenuForm(Model model) {
        Menu menu = new Menu();
        model.addAttribute("menu_form", menu);
        model.addAttribute("dishesAll", dishDao.getAll());

        return "/admin/menus/menu_form";
    }

    @RequestMapping(value = "/admin/menus", method = RequestMethod.POST)
    public String saveOrUpdateMenu(@ModelAttribute("menu_form") @Validated Menu menu,
                                   BindingResult result, final RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "/admin/menus/menu_form";
        } else {

            redirectAttributes.addFlashAttribute("css", "success");
            if (menu.isNew()){
                redirectAttributes.addFlashAttribute("msg", "Menu added successfully!");
                menuService.create(menu);

            } else {
                redirectAttributes.addFlashAttribute("msg", "Menu updated successfully!");
                menuService.updateTitle(menu.getId(), menu.getMenuTitle());
                //menuService.updateMenuDishes(menu.getId(), menu.getDishesInMenu());
            }

            return "redirect:/admin/menus/" + menu.getId();
        }

    }

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @Autowired
    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }
}
