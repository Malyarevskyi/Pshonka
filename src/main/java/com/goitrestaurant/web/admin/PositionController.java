package com.goitrestaurant.web.admin;

import com.goitrestaurant.model.Position;
import com.goitrestaurant.service.PositionService;
import com.goitrestaurant.web.admin.validators.PositionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PositionController {

    private PositionService positionService;

    @Autowired
    private PositionValidator positionValidator;

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        binder.addValidators(positionValidator);
    }

    @RequestMapping(value = "/admin/positions", method = RequestMethod.GET)
    public String showAllPositions(Model model) {
        model.addAttribute("positions", positionService.getAll());
        return "admin/positions/list_positions";
    }

    @RequestMapping(value = "/admin/positions/{id}", method = RequestMethod.GET)
    public String showPosition(@PathVariable("id") int id, Model model) {
        Position position = positionService.findById(id);

        if (position == null) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "Position not found");
        }
        model.addAttribute("position", position);
        return "admin/positions/show";
    }

    @RequestMapping(value = "/admin/positions/{id}/delete", method = RequestMethod.GET)
    public String deletePosition(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {
        positionService.delete(id);

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Position is deleted!");

        return "redirect:/admin/positions";
    }

    @RequestMapping(value = "/admin/positions/{id}/update", method = RequestMethod.GET)
    public String showUpdatePositionForm(@PathVariable("id") int id, Model model) {
        Position position = positionService.findById(id);
        model.addAttribute("position_form", position);

        return "/admin/positions/position_form";
    }

    @RequestMapping(value = "/admin/positions/create", method = RequestMethod.GET)
    public String showCreatePositionForm(Model model) {
        Position position = new Position();
        model.addAttribute("position_form", position);

        return "admin/positions/position_form";
    }

    @RequestMapping(value = "/admin/positions", method = RequestMethod.POST)
    public String saveOrUpdatePosition(@ModelAttribute("position_form") @Validated Position position,
                                       BindingResult result, final RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "admin/positions/position_form";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
            if(position.isNew()){
                redirectAttributes.addFlashAttribute("msg", "Position added successfully!");
                positionService.create(position);
            }else{
                redirectAttributes.addFlashAttribute("msg", "Position updated successfully!");
                positionService.updateTitle(position.getId(), position.getPositionTitle());
            }

            return "redirect:/admin/positions/" + position.getId();
        }
    }

    @Autowired
    public void setPositionService(PositionService positionService) {
        this.positionService = positionService;
    }
}
