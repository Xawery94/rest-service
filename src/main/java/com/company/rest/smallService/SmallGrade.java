package com.company.rest.smallService;

import java.util.Date;

public class SmallGrade {

    private double mark;
    private Date date;
    private String courseName;

    public SmallGrade() {
    }

    public SmallGrade(double mark, String courseName) {
        this.mark = mark;
        this.date = new Date();
        this.courseName = courseName;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
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
