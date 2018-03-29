package com.company.rest.smallService;

import java.util.List;

public class SmallCourse {

    private String id;
    private String name;
    private String teacher;
    ///private List<SmallGrade> grade;

    public SmallCourse() {
    }

    public SmallCourse(String id, String name, String teacher) {
        super();
        this.id = id;
        this.name = name;
        this.teacher = teacher;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    /*public List<SmallGrade> getGrade() {
        return grade;
    }

    public void setGrade(List<SmallGrade> grade) {
        this.grade = grade;
    }*/
}