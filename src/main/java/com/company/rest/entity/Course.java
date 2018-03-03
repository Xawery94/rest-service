package com.company.rest.entity;

public class Course {

    private String name;
    private String teacher;
    private Grade[] grade;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Grade[] getGrade() {
        return grade;
    }

    public void setGrade(Grade[] grade) {
        this.grade = grade;
    }
}
