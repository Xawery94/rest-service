package com.company.rest.entity;

import org.springframework.data.annotation.Id;

import java.math.BigInteger;
import java.util.List;

public class Course {

    @Id
    private BigInteger id;
    private String name;
    private String teacher;
    private List<Grade> grade;

    public Course() {
    }

    public Course(BigInteger id, String name, String teacher) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getTeacher() {
        return teacher;
    }

    public List<Grade> getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setGrade(List<Grade> grade) {
        this.grade = grade;
    }
}
