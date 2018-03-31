package com.company.rest.services;

import com.company.rest.entity.Course;
import com.company.rest.entity.Grade;
import com.company.rest.entity.Student;
import com.company.rest.smallService.SmallStudent;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    List<Student> getAllStudents();

    Student getStudent(String index);

    Student addStudent(Student body);

    void deleteStudent(String index);

    Course addNewCourse(String index, Course course);

    Student updateStudent(String index, Student student);

    List<Course> retrieveAllCourses(String index);

    Course retrieveCourse(String index, String courseName);

    List<Grade> retrieveGrade(String index, String courseName);

    List<Grade> getOneGrade(String index, String courseId, double value);

    Student deleteCourse(String index, String courseName);

    Grade addNewGrade(String index, String courseName, Grade grade);
}
