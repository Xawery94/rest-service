package com.company.rest.entity;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class Grade {

    @NotNull
    private double value;
    private Date date;
    private String courseName;

    public Grade() {
    }

    public Grade(double value, Date date, String courseName) {
        this.value = value;
        this.date = date;
        this.courseName = courseName;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
