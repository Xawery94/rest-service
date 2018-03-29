package com.company.rest.smallService;



import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class SmallGrade {

    private double mark;
    private Date date;

//    @JsonIgnore
    private String student;
    private String courseName;

    public SmallGrade() {
    }

    public SmallGrade(double mark, Date date, String student, String courseName) {
        this.mark = mark;
        this.date = date;
        this.student = student;
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

    public void setStudent(String student) {
        this.student = student;
    }

    public String getStudent() {
        return student;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
