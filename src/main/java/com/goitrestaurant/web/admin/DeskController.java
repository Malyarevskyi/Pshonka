package com.goitrestaurant.web.admin;

import com.goitrestaurant.model.Desk;
import com.goitrestaurant.model.DeskStatus;
import com.goitrestaurant.service.DeskService;
import com.goitrestaurant.web.admin.validators.DeskValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;

@Controller
public class DeskController {

    private DeskService deskService;

    @Autowired
    private DeskValidator deskValidator;

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        binder.addValidators(deskValidator);
    }

    @RequestMapping(value = "/admin/desks", method = RequestMethod.GET)
    public String showAllDesks(Model model) {
        model.addAttribute("desks", deskService.getAll());
        return "admin/desks/list_desks";
    }

    @RequestMapping(value = "/admin/desks/{id}", method = RequestMethod.GET)
    public String showDesk(@PathVariable("id") int id, Model model) {
        Desk desk = deskService.findById(id);
        if (desk == null) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "Desk not found");
        }
        model.addAttribute("desk", desk);
        return "admin/desks/show";
    }

    @RequestMapping(value = "/admin/desks/{id}/delete", method = RequestMethod.GET)
    public String deleteDesk(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {
        deskService.delete(id);
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Desk is deleted!");

        return "redirect:/admin/desks";
    }

    @RequestMapping(value = "/admin/desks/{id}/update", method = RequestMethod.GET)
    public String showUpdateDeskForm(@PathVariable("id") int id, Model model) {
        Desk desk = deskService.findById(id);
        model.addAttribute("desk_form", desk);
        model.addAttribute("statusList", Arrays.asList(DeskStatus.values()));

        return "admin/desks/desk_form";
    }

    @RequestMapping(value = "/admin/desks/create", method = RequestMethod.GET)
    public String showCreateDeskForm(Model model) {
        Desk desk = new Desk();
        model.addAttribute("desk_form", desk);
        model.addAttribute("statusList", Arrays.asList(DeskStatus.values()));

        return "admin/desks/desk_form";
    }

    @RequestMapping(value = "/admin/desks", method = RequestMethod.POST)
    public String saveOrUpdateDesk(@ModelAttribute("desk_form") @Validated Desk desk,
                                   BindingResult result, final RedirectAttributes redirectAttributes) {

        System.out.println(desk);

        if (result.hasErrors()) {
            return "admin/desks/desk_form";
        } else {

            redirectAttributes.addFlashAttribute("css", "success");
            if(desk.isNew()){
                redirectAttributes.addFlashAttribute("msg", "Desk added successfully!");
                System.out.println(desk);

                desk.setDeskStatus(DeskStatus.FREE);

                System.out.println(desk);
                deskService.create(desk);

            }else{
                redirectAttributes.addFlashAttribute("msg", "Desk updated successfully!");
                deskService.updateTitle(desk.getId(), desk.getDeskTitle());
                deskService.updateStatus(desk.getId(), desk.getDeskStatus());
            }

            return "redirect:/admin/desks/" + desk.getId();
        }

    }

    @Autowired
    public void setDeskService(DeskService deskService) {
        this.deskService = deskService;
    }

}
