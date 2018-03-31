package com.company.rest.entity;

import com.company.rest.smallService.SmallCourse;
import com.company.rest.smallService.SmallGrade;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Student {

    @Id
    @NotNull(message = "User's index must not be null")
    @Size(min = 6)
    private String index;
    private String name;
    private String lastName;
    private Date birthday;
    private List<Grade> grades;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(index, student.index) &&
                Objects.equals(name, student.name) &&
                Objects.equals(lastName, student.lastName) &&
                Objects.equals(birthday, student.birthday) &&
                Objects.equals(grades, student.grades) &&
                Objects.equals(courses, student.courses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, name, lastName, birthday, grades, courses);
    }


}