package com.goitrestaurant.web.admin;

import com.goitrestaurant.dao.PositionDao;
import com.goitrestaurant.model.Employee;
import com.goitrestaurant.service.EmployeeService;
import com.goitrestaurant.web.admin.validators.EmployeeValidator;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class EmployeeController {
    private EmployeeService employeeService;
    private PositionDao positionDao;

    @Autowired
    private EmployeeValidator employeeValidator;

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        binder.addValidators(employeeValidator);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, "birthday", new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/admin/", method = RequestMethod.GET)
    public String main(Model model) {
        return "admin/main";
    }

    @RequestMapping(value = "/admin/employees", method = RequestMethod.GET)
    public String showAllEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAll());

        return "/admin/employees/list_employees";
    }

    @RequestMapping(value = "/admin/employees/search", method = RequestMethod.GET)
    public String findEmployeeByLastName(@RequestParam("lastName")String lastName, Model model) {
        model.addAttribute("employees", employeeService.findByTitle(lastName));
        return "/admin/employees/list_employees";
    }

    @RequestMapping(value = "/admin/employees/{id}", method = RequestMethod.GET)
    public ModelAndView showEmployee(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        Employee employee = employeeService.findById(id);
        modelAndView.addObject("employee", employee);

        if (employee.getImage() != null) {
            byte[] encoded = Base64.encodeBase64(employeeService.findById(id).getImage());
            try {
                String encodedString = new String(encoded, "UTF-8");
                modelAndView.addObject("photo", encodedString);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("Can't display image!");
            }
        }

        modelAndView.setViewName("/admin/employees/show");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/employees/{id}/delete", method = RequestMethod.GET)
    public String deleteEmployee(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {
        employeeService.delete(id);

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Employee is deleted!");

        return "redirect:/admin/employees";
    }

    @RequestMapping(value = "/admin/employees/{id}/update", method = RequestMethod.GET)
    public String showUpdateEmployeeForm(@PathVariable("id") int id, Model model) {
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee_form", employee);
        model.addAttribute("positionList", positionDao.getAll());

        return "/admin/employees/employee_form";
    }

    @RequestMapping(value = "/admin/employees/create", method = RequestMethod.GET)
    public String showCreateEmployeeForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee_form", employee);
        model.addAttribute("positionList", positionDao.getAll());

        return "/admin/employees/employee_form";
    }

    @RequestMapping(value = "/admin/employees/addOrUpdateEmployee", method = RequestMethod.POST)
    public String saveOrUpdateEmployee(@RequestParam("file") MultipartFile file,
                                       @ModelAttribute("employee_form") @Validated Employee employee,
                                       BindingResult result,
                                       final RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "/admin/employees/employee_form";
        } else {

            redirectAttributes.addFlashAttribute("css", "success");
            if(employee.isNew()){
                try {
                    if(file.getBytes().length != 0) {
                        employee.setImage(file.getBytes());
                    }
                } catch (IOException e) {
                    throw new RuntimeException("Cannot save employees image!");
                }
                redirectAttributes.addFlashAttribute("msg", "Employee added successfully!");

                employee.setPosition(positionDao.findByTitle(employee.getPosition().getPositionTitle()).get(0));

                //System.out.println(employee);

                employeeService.create(employee);

            }else{
                redirectAttributes.addFlashAttribute("msg", "Employee updated successfully!");
                try {
                    if (file.getBytes().length != 0) {
                        employeeService.updateEmployeePhoto(employee.getId(), file.getBytes());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                employeeService.updateTitle(employee.getId(), employee.getLastName());
                employeeService.updateEmployeeFirstName(employee.getId(), employee.getFirstName());
                employeeService.updateEmployeeBirthday(employee.getId(), employee.getBirthday());
                employeeService.updateEmployeePhone(employee.getId(), employee.getPhone());
                employeeService.updateEmployeePositionId(employee.getId(),
                        positionDao.findByTitle(employee.getPosition().getPositionTitle()).get(0));
                employeeService.updateEmployeeSalary(employee.getId(), employee.getSalary());
            }

            return "redirect:/admin/employees/" + employee.getId();
        }

    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }
}
