package com.exam.sirma.PairOfEmployees.Service;

import com.exam.sirma.PairOfEmployees.FileHandler.CsvReader;
import com.exam.sirma.PairOfEmployees.Model.Employee;
import com.exam.sirma.PairOfEmployees.Repository.EmployeeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CsvHandler {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ResourceLoader resourceLoader;
    @Value("classpath:employeeData.csv")
    private Resource csvResource;

    @PostConstruct
    public void readCsvOnStartup(){
        List<Employee> employeesList = CsvReader.read(csvResource);
        for (Employee employee : employeesList) {
            employeeRepository.save(employee);
        }
    }
}
