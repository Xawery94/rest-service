package com.company.rest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

public class Student {

    @Id
    @NotNull(message = "User's index must not be null")
    @Size(min = 6, max = 10)
    private String index;
    private String name;
    private String lastName;
    private Date birthday;

    @JsonIgnore
    private List<Grade> grades;
    @JsonIgnore
    private List<Course> courses;

    public Student() {
    }

    public Student(String index, String name, String lastName, Date birthday) {
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

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

}