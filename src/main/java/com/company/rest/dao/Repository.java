package com.company.rest.dao;

import com.company.rest.entity.Course;
import com.company.rest.entity.Grade;
import com.company.rest.entity.Student;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class Repository {

    public List<Student> student = new ArrayList<>();
    private List<Course> course = new ArrayList<>();
    private List<Grade> grade;
    SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy");

    public Repository() {
        this.initRepository();
    }

    public void initRepository() {
        try {
        student.add(new Student("123456", "Ala", "Kot", sdf.parse("10 20 2012")));
        student.add(new Student("534554", "Ola", "Puszka", sdf.parse("11 21 2013")));
        student.add(new Student("222222", "Pawel", "Wrona", sdf.parse("12 22 2014")));
        student.add(new Student("866456", "Adam", "Paczka", sdf.parse("13 23 2015")));
        } catch (ParseException exc) {
            System.err.println(exc.getMessage());
        }
    }

    public List<Student> getAllStudents() {
        return student;
    }

    public Student getStudent(String index) {
        for (Student student : student)
            if (student.getIndex().contentEquals(index)) {
                return student;
            }

        return null;
    }

    public void addStudent(Student body) {
        student.add(new Student(body.getIndex(), body.getName(), body.getLastName(), body.getBirthday()));
    }
}
