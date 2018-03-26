package com.company.rest.entity;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class Grade {

    @Id
    @NotNull
    private int id;
    private double mark;
    private Date date;
    private Student student;

    public Grade() {
    }

    public Grade(int id, double mark, Date date, Student student) {
        this.id = id;
        this.mark = mark;
        this.date = date;
        this.student = student;
    }

    public int getId() {
        return id;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Student getStudent() {
        return student;
    }
}
