package com.goitrestaurant.web.admin;

import com.goitrestaurant.model.Ingredient;
import com.goitrestaurant.service.IngredientService;
import com.goitrestaurant.web.admin.validators.IngredientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class IngredientController {

    private IngredientService ingredientService;

    @Autowired
    private IngredientValidator ingredientValidator;

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        binder.addValidators(ingredientValidator);
    }

    @Autowired
    public void setIngredientService(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @RequestMapping(value = "/admin/ingredients", method = RequestMethod.GET)
    public String showAllIngredients(Model model) {
        model.addAttribute("ingredients", ingredientService.getAll());
        return "admin/ingredients/list_ingredients";
    }

    @RequestMapping(value = "/admin/ingredients/{id}", method = RequestMethod.GET)
    public String showIngredient(@PathVariable("id") int id, Model model) {
        Ingredient ingredient = ingredientService.findById(id);
        if (ingredient == null) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "Ingredient not found");
        }
        model.addAttribute("ingredient", ingredient);
        return "admin/ingredients/show";
    }

    @RequestMapping(value = "/admin/ingredients/{id}/delete", method = RequestMethod.GET)
    public String deleteIngredient(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {
        ingredientService.delete(id);

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Ingredient is deleted!");

        return "redirect:/admin/ingredients";
    }

    @RequestMapping(value = "/admin/ingredients/{id}/update", method = RequestMethod.GET)
    public String showUpdateIngredientForm(@PathVariable("id") int id, Model model) {
        Ingredient ingredient = ingredientService.findById(id);
        model.addAttribute("ingredient_form", ingredient);

        return "admin/ingredients/ingredient_form";
    }

    @RequestMapping(value = "/admin/ingredients/create", method = RequestMethod.GET)
    public String showCreateIngredientForm(Model model) {
        Ingredient ingredient = new Ingredient();
        model.addAttribute("ingredient_form", ingredient);

        return "admin/ingredients/ingredient_form";
    }

    @RequestMapping(value = "/admin/ingredients", method = RequestMethod.POST)
    public String saveOrUpdateIngredient(@ModelAttribute("ingredient_form") @Validated Ingredient ingredient,
                                         BindingResult result, final RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "admin/ingredients/ingredient_form";
        } else {

            redirectAttributes.addFlashAttribute("css", "success");
            if(ingredient.isNew()){
                redirectAttributes.addFlashAttribute("msg", "Ingredient added successfully!");
                ingredientService.create(ingredient);
            }else{
                redirectAttributes.addFlashAttribute("msg", "Ingredient updated successfully!");
                ingredientService.updateTitle(ingredient.getId(), ingredient.getIngredientTitle());
            }

            return "redirect:/admin/ingredients/" + ingredient.getId();
        }

    }
}