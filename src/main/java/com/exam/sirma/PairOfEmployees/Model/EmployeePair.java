package com.exam.sirma.PairOfEmployees.Model;
public class EployeePair {
    private final Employee employee1;
    private final Employee employee2;
    private final long totalDurationInDays;

    public EployeePair(Employee employee1, Employee employee2, long totalDurationInDays) {
        this.employee1 = employee1;
        this.employee2 = employee2;
        this.totalDurationInDays = totalDurationInDays;
    }

    public Employee getEmployee1() {
        return employee1;
    }

    public Employee getEmployee2() {
        return employee2;
    }

    public long getTotalDurationInDays() {
        return totalDurationInDays;
    }
}
