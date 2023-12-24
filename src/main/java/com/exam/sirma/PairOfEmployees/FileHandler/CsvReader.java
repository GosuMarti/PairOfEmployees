package com.exam.sirma.PairOfEmployees.FileHandler;

import com.exam.sirma.PairOfEmployees.Model.Employee;
import org.springframework.core.io.Resource;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {
    public static List<Employee> read(Resource resource) {
        List<Employee> employees = new ArrayList<>();
        try (InputStream inputStream = resource.getInputStream();
             InputStreamReader reader = new InputStreamReader(inputStream);
             BufferedReader br = new BufferedReader((reader))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                try {
                    long id = Long.parseLong(values[0]);
                    int projectId = Integer.parseInt(values[1]);
                    LocalDate dateFrom = LocalDate.parse(values[2], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    LocalDate dateTo;
                    if (values.length == 2) {
                        dateTo = LocalDate.now();
                    } else {
                        try {
                            dateTo = LocalDate.parse(values[3], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        } catch (Exception exception) {
                            dateTo = LocalDate.now();
                        }
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
