package com.company.rest.services;

import com.company.rest.entity.Course;
import com.company.rest.entity.Grade;

import java.util.List;

public interface CourseService {

    void createNewCourse(Course course);

    List<Course> getAllCourses();

    Course getCourse(String name);

    void saveGradeInCourse(Course course);
}
