package com.exam.sirma.PairOfEmployees.Service;

import com.exam.sirma.PairOfEmployees.Model.Employee;
import com.exam.sirma.PairOfEmployees.Model.EmployeePair;
import org.springframework.stereotype.Service;

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
                    long duration = ChronoUnit.DAYS.between(employee1.getStartDate(), employee1.getFinishDate()) +
                            ChronoUnit.DAYS.between(employee2.getStartDate(), employee2.getFinishDate());

                    if (duration > maxDuration) {
                        maxDuration = duration;
                        mostTimeWorkedPair = new EmployeePair(employee1, employee2, duration);
                    }
                }
            }
        }

        return mostTimeWorkedPair;
    }
}
