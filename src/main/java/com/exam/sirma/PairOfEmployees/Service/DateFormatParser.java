package com.exam.sirma.PairOfEmployees.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class DateFormatParser {
    public static LocalDate parseDate(String dateString) {
        List<String> dateFormats = Arrays.asList("dd/MM/yyyy", "yyyy-MM-dd", "MM/dd/yyyy", "yyyy/MM/dd", "yyyy/dd/MM");

        for (String format : dateFormats) {
            try {
                return LocalDate.parse(dateString, DateTimeFormatter.ofPattern(format));
            } catch (DateTimeParseException e) {
                // Try the next format
            }
        }

        System.err.println("Unable to parse date: " + dateString + ". Using default value.");
        return LocalDate.now();
    }
}
