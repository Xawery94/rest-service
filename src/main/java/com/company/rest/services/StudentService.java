package com.company.rest.services;

import com.company.rest.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudents();

    Student getStudent(String index);

    void addStudent(Student body);

    void deleteStudent(String index);
}
