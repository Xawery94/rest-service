package com.company.rest.repository;

import com.company.rest.entity.Course;
import com.company.rest.entity.Grade;
import com.company.rest.entity.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class Repository {

    public List<Student> student = new ArrayList<>();
    private List<Course> course;
    private List<Grade> grade;

    public Repository() {
        this.initRepository();
    }

    public void initRepository() {
        student.add(new Student("123456", "Ala", "Kot", new Date(1269540245)));
        student.add(new Student("534554", "Ola", "Puszka", new Date(2001, 02, 03)));
        student.add(new Student("222222", "Pawel", "Wrona", new Date(2001, 02, 03)));
        student.add(new Student("866456", "Adam", "Paczka", new Date(2001, 02, 03)));
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
