package com.company.rest.services.impl;

import com.company.rest.dao.CourseRepository;
import com.company.rest.entity.Course;
import com.company.rest.services.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private static final Logger log = LoggerFactory.getLogger(CourseServiceImpl.class);

    private CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course createNewCourse(Course course) {
        courseRepository.save(course);
        return course;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> getAllCoursesForTeacher(String name) {
        return courseRepository.findAllByTeacher(name);
    }

    @Override
    public Course getCourse(String name) {
        return courseRepository.findFirstByName(name);
    }

    @Override
    public Course updateCourse(String courseName, Course newCourse) {
        Course course = courseRepository.findFirstByName(courseName);
        course.setName(newCourse.getName());
        course.setTeacher(newCourse.getTeacher());

        courseRepository.save(course);
        return course;
    }

    @Override
    public void deleteCourse(String courseName) {
        courseRepository.deleteByName(courseName);
    }

}
