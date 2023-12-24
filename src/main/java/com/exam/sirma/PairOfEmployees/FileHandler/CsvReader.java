package com.exam.sirma.PairOfEmployees.FileHandler;

import com.exam.sirma.PairOfEmployees.Model.Employee;
import com.exam.sirma.PairOfEmployees.Repository.EmployeeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvReader {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostConstruct
    public void readCsvOnStartup(){
        List<Employee> employeesList = read("employeeData.csv");
        for (Employee employee : employeesList) {
            employeeRepository.save(employee);
        }
    }
    public List<Employee> read(String filename){
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader br =  new BufferedReader(new FileReader(filename))){
            String line;
            while ((line = br.readLine()) != null){
                String[] values = line.split(",");
                try {
                    long id = Long.parseLong(values[0]);
                    int projectId = Integer.parseInt(values[1]);
                    LocalDate dateFrom = LocalDate.parse(values[2], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    LocalDate dateTo = LocalDate.parse(values[3], DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                    if (values[1].isEmpty() || values[2].isEmpty() || values[3].isEmpty()) {
                        System.err.println("Invalid data format.");
                        continue;
                    }

                    employees.add(new Employee(id, projectId, dateFrom, dateTo));
                } catch (NumberFormatException e) {
                    System.err.println("Invalid numeric format.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
