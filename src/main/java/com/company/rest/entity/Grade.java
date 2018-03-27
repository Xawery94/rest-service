package com.company.rest.entity;

import java.io.Serializable;
import java.util.Date;

public class Grade implements Serializable {

    private double mark;
    private Date date;
    private Student student;

    public Grade() {
    }

    public Grade(double mark, Date date, Student student) {
        this.mark = mark;
        this.date = date;
        this.student = student;
    }

    public double getMark() {
        return mark;
    }

    public Date getDate() {
        return date;
    }

    public Student getStudent() {
        return student;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
