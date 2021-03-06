package com.company.rest.smallService;

import java.util.Date;
import java.util.List;

public class SmallStudent {

    private String index;
    private String name;
    private String lastName;
    private Date birthday;
    private List<SmallGrade> grades;
    private List<SmallCourse> courses;

    public SmallStudent() {
    }

    public SmallStudent(String index, String name, String lastName, Date birthday) {
        this.index = index;
        this.name = name;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SmallGrade> getGrades() {
        return grades;
    }

    public void setGrades(List<SmallGrade> grades) {
        this.grades = grades;
    }

    public List<SmallCourse> getCourses() {
        return courses;
    }

    public void setCourses(List<SmallCourse> courses) {
        this.courses = courses;
    }
}
