package com.exam.sirma.PairOfEmployees.FileHandler;

import com.exam.sirma.PairOfEmployees.Model.Employee;
import org.springframework.core.io.Resource;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import static com.exam.sirma.PairOfEmployees.Service.DateFormatParser.parseDate;

public class CsvReader {
    public static List<Employee> read(Resource resource) {
        List<Employee> employees = new ArrayList<>();
        try (InputStream inputStream = resource.getInputStream();
             InputStreamReader reader = new InputStreamReader(inputStream);
             BufferedReader br = new BufferedReader((reader))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\\s*,\\s*");
                try {
                    long id = Long.parseLong(values[0]);
                    int projectId = Integer.parseInt(values[1]);
                    LocalDate dateFrom = parseDate(values[2]);
                    LocalDate dateTo = LocalDate.now();
                    if (values.length > 3) {
                        try {
                            dateTo = parseDate(values[3]);
                        } catch (DateTimeParseException exception) {
                            System.err.println("NULL format date isn't an exception, but check csv file still.");
                        }
                    }

                    employees.add(new Employee(id, projectId, dateFrom, dateTo));
                } catch (NumberFormatException e) {
                    System.err.println("Invalid numeric format. Check csv file.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
