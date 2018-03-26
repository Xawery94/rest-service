package com.company.rest.entity;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Student {

    @Id
    private String index;

    private String name;
    private String lastName;
    private Date birthday;

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
}