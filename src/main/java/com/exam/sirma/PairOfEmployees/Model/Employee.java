package com.exam.sirma.PairOfEmployees.Model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    private int projectID;
    private LocalDate DateFrom;
    private LocalDate DateTo;

    public Employee(){}

    public Employee(long id, int projectID, LocalDate dateFrom, LocalDate dateTo) {
        this.ID = id;
        this.projectID = projectID;
        this.DateFrom = dateFrom;
        this.DateTo = dateTo;
    }

    public long getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public LocalDate getDateFrom() {
        return DateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        DateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return DateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        DateTo = dateTo;
    }
}
