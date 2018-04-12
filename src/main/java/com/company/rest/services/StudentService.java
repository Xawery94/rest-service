package com.company.rest.services;

import com.company.rest.entity.Course;
import com.company.rest.entity.Grade;
import com.company.rest.entity.Student;

import java.util.Date;
import java.util.List;

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

    Grade getOneGrade(String index, String courseName, Integer id);

    void deleteCourse(String index, String courseName);

    Grade addNewGrade(String index, String courseName, Grade grade);

    Course updateCourse(String index, String courseName, Course newCourse);

    Grade updateGrade(String index, String courseName, Integer id, Grade newGrade);

    List<Grade> deleteGrade(String index, String courseName, Integer id);

    List<Student> getStudentsByName(String name);

    List<Student> getStudentsByLastName(String lastName);

    List<Student> getStudentsByNameAndLastName(String name, String lastName);

    List<Student> getStudentsByBirthday(Date date);

    List<Student> getStudentsByBirthdayBefore(Date date);

    List<Student> getStudentsByBirthdayAfter(Date date);

    List<Grade> retrieveGradeGraterThan(String index, String courseName, double value);

    List<Grade> retrieveGradeLessThan(String index, String courseName, double value);
}
