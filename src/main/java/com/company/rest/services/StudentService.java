package com.company.rest.services;

import com.company.rest.entity.Course;
import com.company.rest.entity.Grade;
import com.company.rest.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    List<Student> getAllStudents();

    Student getStudent(String index);

    void addStudent(Student body);

    void deleteStudent(String index);

    void addNewCourse(String index, List<Course> course);

    Optional<Course> getCourseForStudent(String index, String name);

    void addNewGrade(String index, String name, Grade grade);
}
