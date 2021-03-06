package com.company.rest.services;

import com.company.rest.entity.Course;

import java.util.List;

public interface CourseService {

    Course createNewCourse(Course course);

    List<Course> getAllCourses(String name);

    Course getCourse(String name);

    Course updateCourse(int id, Course newCourse);

    void deleteCourse(int id);

}
