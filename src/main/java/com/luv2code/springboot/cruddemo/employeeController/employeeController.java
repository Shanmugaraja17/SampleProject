package com.luv2code.springboot.cruddemo.employeeController;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class employeeController {

    private EmployeeService employeeService;

    public employeeController(EmployeeService theEmployeeService){

        employeeService = theEmployeeService;

    }

    //add mapping to list

    @GetMapping("/list")
    public String listEmployees(Model theModel){

        // get list from the db
        List<Employee> theEmployee = employeeService.findAll();

        theModel.addAttribute("employees", theEmployee);

        return "employee/list-employee";
    }

    @GetMapping("/showAddForm")
    public String showAddForm(Model themodel){

        //create model attribute to bind form data
        Employee theEmployee = new Employee();

        themodel.addAttribute("employees", theEmployee);

        return "employee/employee-form";
    }

    @GetMapping("/showUpdateForm")
    public String showUpdateForm(@RequestParam("employeeId") int theID, Model theModel){

        //get the Employee id from the employee service
        Employee theEmployee = employeeService.findById(theID);

        // set the employee to model to prepopulate
        theModel.addAttribute("employees", theEmployee);

        //send over to our form
         return "employee/employee-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int theID){
        employeeService.deleteById(theID);

        return "redirect:/employees/list";
    }
    @PostMapping("/save")
    public String SaveForm(@ModelAttribute("employees") Employee theEmployee){

        employeeService.save(theEmployee);

        return "redirect:/employees/list";

    }






}
