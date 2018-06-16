package com.company.rest.services.impl;

import com.company.rest.dao.CourseRepository;
import com.company.rest.entity.Course;
import com.company.rest.exceptions.CourseDataException;
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
        if (course.getId() == null || course.getName() == null || course.getTeacher() == null) {
            throw new CourseDataException();
        }

        Course existingCourse = courseRepository.findById(course.getId());

        if (existingCourse != null) {
            log.warn("Trying add duplicate course with index {}", course.getName());
            return null;
        }

        courseRepository.save(course);
        return course;
    }

    @Override
    public List<Course> getAllCourses(String teacher) {
        if (teacher != null) {
            return courseRepository.findAllByTeacher(teacher);
        } else {
            return courseRepository.findAll();
        }
    }

    @Override
    public Course getCourse(String name) {
        return courseRepository.findFirstByName(name);
    }

    @Override
    public Course updateCourse(int id, Course newCourse) {
        Course course = courseRepository.findFirstById(id);
        course.setName(newCourse.getName());
        course.setTeacher(newCourse.getTeacher());

        courseRepository.save(course);
        return course;
    }

    @Override
    public void deleteCourse(int id) {
        courseRepository.deleteById(id);
    }

}
