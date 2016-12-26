package com.goitrestaurant.web.admin;

import com.goitrestaurant.model.Category;
import com.goitrestaurant.service.CategoryService;
import com.goitrestaurant.web.admin.validators.CategoryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    private CategoryValidator categoryValidator;

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        binder.addValidators(categoryValidator);
    }

    @RequestMapping(value = "/admin/categories", method = RequestMethod.GET)
    public String showAllCategories(Model model) {
        model.addAttribute("categories", categoryService.getAll());
        return "/admin/categories/list_categories";
    }

    @RequestMapping(value = "/admin/categories/{id}", method = RequestMethod.GET)
    public String showCategory(@PathVariable("id") int id, Model model) {
        Category category = categoryService.findById(id);
        if (category == null) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "Category not found");
        }
        model.addAttribute("category", category);
        return "/admin/categories/show";
    }

    @RequestMapping(value = "/admin/categories/{id}/delete", method = RequestMethod.GET)
    public String deleteCategory(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {
        categoryService.delete(id);

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Category is deleted!");

        return "redirect:/admin/categories";
    }

    @RequestMapping(value = "/admin/categories/{id}/update", method = RequestMethod.GET)
    public String showUpdateCategoryForm(@PathVariable("id") int id, Model model) {
        Category category = categoryService.findById(id);
        model.addAttribute("category_form", category);

        return "/admin/categories/category_form";
    }

    @RequestMapping(value = "/admin/categories/create", method = RequestMethod.GET)
    public String showCreateCategoryForm(Model model) {
        Category category = new Category();
        model.addAttribute("category_form", category);

        return "/admin/categories/category_form";
    }

    @RequestMapping(value = "/admin/categories", method = RequestMethod.POST)
    public String saveOrUpdateCategory(@ModelAttribute("category_form") @Validated Category category,
                                       BindingResult result, final RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "/admin/categories/category_form";
        } else {

            redirectAttributes.addFlashAttribute("css", "success");
            if(category.isNew()){
                redirectAttributes.addFlashAttribute("msg", "Category added successfully!");

                System.out.println(category);
                categoryService.create(category);
            }else{
                redirectAttributes.addFlashAttribute("msg", "Category updated successfully!");
                categoryService.updateTitle(category.getId(), category.getCategoryTitle());
            }

            return "redirect:/admin/categories/" + category.getId();
        }

    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}