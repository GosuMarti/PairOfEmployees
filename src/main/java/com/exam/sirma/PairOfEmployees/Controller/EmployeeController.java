package com.exam.sirma.PairOfEmployees.Controller;

import com.exam.sirma.PairOfEmployees.Model.Employee;
import com.exam.sirma.PairOfEmployees.Model.EmployeePair;
import com.exam.sirma.PairOfEmployees.Repository.EmployeeRepository;
import com.exam.sirma.PairOfEmployees.Service.EmployeePairFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired(required = true)
    private EmployeeRepository employeeRepository;
    @Autowired(required = true)
    private EmployeePairFinder employeePairFinder;

    @GetMapping({"/"})
    public ModelAndView showEmployees(){
        ModelAndView modelAndView = new ModelAndView("list-employees");
        List<Employee> empList = employeeRepository.findAll();
        modelAndView.addObject("employees", empList);
        return modelAndView;
    }

    @GetMapping("/pairOfEmployees")
    public ModelAndView pairOfEmployees(){
        ModelAndView modelAndView = new ModelAndView("pair-of-employees");
        List<Employee> empList = employeeRepository.findAll();
        EmployeePair mostTimeWorkedPair = employeePairFinder.findPairWithMostTimeWorked(empList);
        modelAndView.addObject("employees", empList);
        modelAndView.addObject("mostTimeWorkedPair", mostTimeWorkedPair);
        return modelAndView;
    }


}
