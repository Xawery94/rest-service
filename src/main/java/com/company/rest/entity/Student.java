package com.company.rest.entity;

import java.util.Date;

public class Student {

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

    public void setIndex(String index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
