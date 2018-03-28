package com.company.rest.entity;

import java.util.Date;

public class Grade {

    private double mark;
    private Date date;
    private String index;

    public Grade() {
    }

    public Grade(double mark, String index) {
        this.mark = mark;
        this.date = new Date();
        this.index = index;
    }

    public double getMark() {
        return mark;
    }

    public Date getDate() {
        return date;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
