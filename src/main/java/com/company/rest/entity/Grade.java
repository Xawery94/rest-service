package com.company.rest.entity;

import java.util.Date;

public class Grade {

    private double mark;
    private Date date;
    private int studentIndex;

    public Grade(double mark, Date date, int studentIndex) {
        this.mark = mark;
        this.date = date;
        this.studentIndex = studentIndex;
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

    public int getStudentIndex() {
        return studentIndex;
    }

    public void setStudentIndex(int studentIndex) {
        this.studentIndex = studentIndex;
    }
}
