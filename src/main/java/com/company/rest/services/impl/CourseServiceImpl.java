package com.company.rest.services.impl;

import com.company.rest.dao.CourseRepository;
import com.company.rest.entity.Course;
import com.company.rest.entity.Grade;
import com.company.rest.entity.Student;
import com.company.rest.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public void createNewCourse(Course course) {
        courseRepository.save(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourse(String name) {
        return courseRepository.findFirstByName(name);
    }

    /*@Override
    public void addNewGrade(String index, String name, Grade grade) {
        Course course = courseRepository.findFirstByName(name);
        List<Grade> gradeList = course.getGrade();

       // Student student =

        if (gradeList != null) {
            gradeList.add(grade);
        }

        course.setGrade(gradeList);
    }*/

    @Override
    public void saveGradeInCourse(Course course) {
        courseRepository.save(course);
    }
}
