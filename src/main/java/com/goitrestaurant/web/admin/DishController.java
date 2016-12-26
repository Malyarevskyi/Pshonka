package com.goitrestaurant.web.admin;

import com.goitrestaurant.dao.CategoryDao;
import com.goitrestaurant.dao.IngredientDao;
import com.goitrestaurant.model.Dish;
import com.goitrestaurant.model.Ingredient;
import com.goitrestaurant.service.DishService;
import com.goitrestaurant.web.admin.validators.DishValidator;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DishController {

    private DishService dishService;
    private IngredientDao ingredientDao;
    private CategoryDao categoryDao;

    @Autowired
    private DishValidator dishValidator;

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        binder.addValidators(dishValidator);
    }


    @RequestMapping(value = "/admin/dishes", method = RequestMethod.GET)
    public String showAllDishes(Model model) {
        model.addAttribute("dishes", dishService.getAll());
        return "admin/dishes/list_dishes";
    }

    @RequestMapping(value = "/admin/dishes/{id}", method = RequestMethod.GET)
    public ModelAndView showDishes(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        Dish dish = dishService.findById(id);
        modelAndView.addObject("dish", dish);

        if (dish.getPicture() != null) {
            byte[] encoded = Base64.encodeBase64(dishService.findById(id).getPicture());
            try {
                String encodedString = new String(encoded, "UTF-8");
                modelAndView.addObject("picture", encodedString);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("Can't display picture!");
            }
        }

        modelAndView.setViewName("/admin/dishes/show");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/dishes/{id}/delete", method = RequestMethod.GET)
    public String deleteDish(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {
        dishService.delete(id);

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Dish is deleted!");

        return "redirect:/admin/dishes";
    }

    @RequestMapping(value = "/admin/dishes/{id}/update", method = RequestMethod.GET)
    public String showUpdateDishForm(@PathVariable("id") int id, Model model) {
        Dish dish = dishService.findById(id);
        model.addAttribute("dish_form", dish);
        model.addAttribute("ingredientList", ingredientDao.getAll());
        model.addAttribute("categoryList", categoryDao.getAll());

        return "admin/dishes/dish_form";
    }

    @RequestMapping(value = "/admin/dishes/create", method = RequestMethod.GET)
    public String showCreateDishForm(Model model) {
        Dish dish = new Dish();
        model.addAttribute("dish_form", dish);
        model.addAttribute("ingredientList", ingredientDao.getAll());
        model.addAttribute("categoryList", categoryDao.getAll());

        return "admin/dishes/dish_form";
    }

    @RequestMapping(value = "/admin/dishes/addOrUpdateDish", method = RequestMethod.POST)
    public String saveOrUpdateDish(@RequestParam("file") MultipartFile file,
                                       @ModelAttribute("dish_form") @Validated Dish dish,
                                       BindingResult result,
                                       final RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "admin/dishes/dish_form";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
            if (dish.isNew()){

                try {
                    if(file.getBytes().length != 0) {
                        dish.setPicture(file.getBytes());
                    }
                } catch (IOException e) {
                    throw new RuntimeException("Cannot save dish picture!");
                }

                redirectAttributes.addFlashAttribute("msg", "Dish added successfully!");
                dish.setCategory(categoryDao.findByTitle(dish.getCategory().getCategoryTitle()).get(0));
                dish.setIngredients(fixedSelectedIngredients(dish.getIngredients()));
                dishService.create(dish);

            } else {
                redirectAttributes.addFlashAttribute("msg", "Dish updated successfully!");
                try {
                    if (file.getBytes().length != 0) {
                        dishService.updateDishPicture(dish.getId(), file.getBytes());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                dishService.updateTitle(dish.getId(), dish.getDishTitle());
                dishService.updateDishCategory(dish.getId(),
                        categoryDao.findByTitle(dish.getCategory().getCategoryTitle()).get(0));
                dishService.updateDishPrice(dish.getId(), dish.getPrice());
                dishService.updateDishWeight(dish.getId(), dish.getWeight());
                dishService.updateDishDescription(dish.getId(), dish.getDescription());

                //dishService.updateDistIngredients(dish.getId(), fixedSelectedIngredients(dish.getIngredients()));
            }

            return "redirect:/admin/dishes/" + dish.getId();
        }

    }

    @Autowired
    public void setDishService(DishService dishService) {
        this.dishService = dishService;
    }

    @Autowired
    public void setIngredientDao(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }

    @Autowired
    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    private List<Ingredient> fixedSelectedIngredients(List<Ingredient> ingredients) {
        List<Ingredient> ingredientsList = new ArrayList<>();
        for (Ingredient currentIngredient : ingredients) {
            ingredientsList.add(ingredientDao.findByTitle(currentIngredient.getIngredientTitle()).get(0));
        }
        return ingredientsList;
    }
}