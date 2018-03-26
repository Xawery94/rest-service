package com.company.rest.entity;

public class Course {

    private String name;
    private String teacher;
    private Grade[] grade;

    public Course() {
    }

    public Course(String name, String teacher, Grade[] grade) {
        this.name = name;
        this.teacher = teacher;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public String getTeacher() {
        return teacher;
    }

    public Grade[] getGrade() {
        return grade;
    }
}
