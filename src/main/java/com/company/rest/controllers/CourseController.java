package com.company.rest.controllers;

import com.company.rest.entity.Course;
import com.company.rest.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/courses")
public class CourseController {

    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Course> getAllCourses(@RequestParam(value = "teacher", required = false) String name) {
        return courseService.getAllCourses(name);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Course> addNewCourse(@RequestBody Course newCourse) {

        Course course = courseService.createNewCourse(newCourse);

        if (course == null)
            return ResponseEntity.badRequest().build();

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{name}")
                .buildAndExpand(course.getName())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{name}")
    public ResponseEntity<Course> getCurrentCourse(@PathVariable String name) {
        Course course = courseService.getCourse(name);

        if (course != null) {
            return new ResponseEntity<>(course, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Course updateCourse(@PathVariable int id, @RequestBody Course newCourse) {
        return courseService.updateCourse(id, newCourse);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCourse(@PathVariable int id) {
        courseService.deleteCourse(id);
    }
}
