package com.company.rest.entity;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

public class Student {

    @Id
    @NotNull(message = "User's index must not be null")
    @Size(min = 6)
    private String index;
    private String name;
    private String lastName;
    private Date birthday;
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

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}