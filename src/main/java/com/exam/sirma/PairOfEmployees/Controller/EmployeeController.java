package com.exam.sirma.PairOfEmployees.Controller;

import com.exam.sirma.PairOfEmployees.Model.Employee;
import com.exam.sirma.PairOfEmployees.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired(required = true)
    private EmployeeRepository employeeRepository;

    @GetMapping({"/", "/showEmployees"})
    public ModelAndView showEmployees(){
        ModelAndView modelAndView = new ModelAndView("list-employees");
        List<Employee> empList = employeeRepository.findAll();
        modelAndView.addObject("employees", empList);
        return modelAndView;
    }

    @GetMapping("/pairOfEmployees")
    public ModelAndView pairOfEmployees(){
        ModelAndView modelAndView = new ModelAndView("pair-of-employees");
        return modelAndView;
    }


}
