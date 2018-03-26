package com.company.rest.entity;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.util.List;

public class Course {

    @Id
    @NotNull
    private int id;
    private String name;
    private String teacher;
    private List<Grade> grade;

    public Course() {
    }

    public Course(int id, String name, String teacher, List<Grade> grade) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.grade = grade;
    }

    public int getId() {
        return id;
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
}
