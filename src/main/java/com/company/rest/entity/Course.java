package com.company.rest.entity;

import java.util.List;

public class Course {

    private String name;
    private String teacher;
    private List<Grade> grade;

    public Course() {
    }

    public Course(String name, String teacher) {
        this.name = name;
        this.teacher = teacher;
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
