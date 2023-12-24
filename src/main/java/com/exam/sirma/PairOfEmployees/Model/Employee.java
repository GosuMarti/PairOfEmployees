package com.exam.sirma.PairOfEmployees.Model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    private long ID;
    private int projectID;
    private LocalDate startDate;
    private LocalDate finishDate;

    public Employee(){}

    public Employee(long id, int projectID, LocalDate startDate, LocalDate finishDate) {
        this.ID = id;
        this.projectID = projectID;
        this.startDate = startDate;
        this.finishDate = finishDate;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }
}
