package com.company.rest.services;

import com.company.rest.entity.Course;

import java.util.List;

public interface CourseService {

    Course createNewCourse(Course course);

    List<Course> getAllCourses();

    Course getCourse(String name);

    Course updateCourse(String courseName, Course newCourse);

    void deleteCourse(String courseName);

    List<Course> getAllCoursesForTeacher(String name);
}
