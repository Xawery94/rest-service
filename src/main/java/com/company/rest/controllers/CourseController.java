package com.company.rest.controllers;

import com.company.rest.entity.Course;
import com.company.rest.entity.Grade;
import com.company.rest.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/courses")
public class CourseController {

    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewCourse(@RequestBody Course course) {
        courseService.createNewCourse(course);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{name}")
    public Course getCurrentCourse(@PathVariable String name) {
        return courseService.getCourse(name);
    }

   /* @RequestMapping(method = RequestMethod.PUT, value = "/{name}/grades")
    public void insertGrade(@PathVariable String name, @RequestBody Grade grade) {
        courseService.addNewGrade(name, grade);
    }*/
}
