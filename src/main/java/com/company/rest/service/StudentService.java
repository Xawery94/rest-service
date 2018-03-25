package com.company.rest.service;

import com.company.rest.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

    List<Student> getAllStudents();

    Student getStudent(String index);

    void addStudent(Student body);
}
