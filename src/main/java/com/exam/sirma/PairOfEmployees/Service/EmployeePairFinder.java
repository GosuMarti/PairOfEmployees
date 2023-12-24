package com.exam.sirma.PairOfEmployees.Service;

import com.exam.sirma.PairOfEmployees.Model.Employee;
import com.exam.sirma.PairOfEmployees.Model.EmployeePair;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class EmployeePairFinder {
    public EmployeePair findPairWithMostTimeWorked(List<Employee> employees) {
        EmployeePair mostTimeWorkedPair = null;
        long maxDuration = 0;

        for (int i = 0; i < employees.size(); i++) {
            for (int j = i + 1; j < employees.size(); j++) {
                Employee employee1 = employees.get(i);
                Employee employee2 = employees.get(j);

                if (employee1.getProjectID() == employee2.getProjectID()) {
                    long duration = calculateOverlapDuration(employee1, employee2);

                    if (duration > maxDuration) {
                        maxDuration = duration;
                        mostTimeWorkedPair = new EmployeePair(employee1, employee2, duration);
                    }
                }
            }
        }

        return mostTimeWorkedPair;
    }

    private long calculateOverlapDuration(Employee employee1, Employee employee2) {
        LocalDate maxStartDate = maxStartDate(employee1.getStartDate(), employee2.getStartDate());
        LocalDate minFinishDate = minFinishDate(employee1.getFinishDate(), employee2.getFinishDate());

        if (maxStartDate.isAfter(minFinishDate)) {
            return 0;
        }

        // Check for continuous overlap
        LocalDate startDate = maxStartDate;
        LocalDate finishDate = startDate.plusDays(1);
        long maxContinuousOverlap = 0;
        while (finishDate.isBefore(minFinishDate) || finishDate.isEqual(minFinishDate)) {
            if (startDate.isBefore(employee1.getFinishDate()) && finishDate.isAfter(employee1.getStartDate()) &&
                    startDate.isBefore(employee2.getFinishDate()) && finishDate.isAfter(employee2.getStartDate())) {
                // There is an overlap on this day
                maxContinuousOverlap = Math.max(maxContinuousOverlap, ChronoUnit.DAYS.between(startDate, finishDate));
            } else {
                // No overlap on this day, reset start date
                startDate = finishDate;
            }
            finishDate = finishDate.plusDays(1);
        }

        return maxContinuousOverlap;
    }

    private LocalDate maxStartDate(LocalDate startDate1, LocalDate startDate2) {
        return startDate1.isAfter(startDate2) ? startDate1 : startDate2;
    }

    private LocalDate minFinishDate(LocalDate finishDate1, LocalDate finishDate2) {
        return finishDate1.isBefore(finishDate2) ? finishDate1 : finishDate2;
    }
}
