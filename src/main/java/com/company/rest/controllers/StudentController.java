package com.company.rest.controllers;

import com.company.rest.entity.Course;
import com.company.rest.entity.Grade;
import com.company.rest.entity.Student;
import com.company.rest.services.CourseService;
import com.company.rest.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/students")
public class StudentController {

    private StudentService studentService;
    private CourseService courseService;

    @Autowired
    public StudentController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{index}")
    @ResponseStatus(HttpStatus.OK)
    public Student getStudent(@PathVariable String index) {
        return studentService.getStudent(index);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addStudent(@RequestBody Student body) {
        studentService.addStudent(body);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{index}")
    public void deleteStudent(@PathVariable String index) {
        studentService.deleteStudent(index);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{index}/courses")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewCourse(@PathVariable String index, @RequestBody List<Course> courses) {
        studentService.addNewCourse(index, courses);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{index}/courses/{name}")
    public Optional<Course> getCourseForStudent(@PathVariable String index, @PathVariable String name) {
        return studentService.getCourseForStudent(index, name);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{index}/courses/{name}/grades")
    public void insertGrade(@PathVariable String index, @PathVariable String name, @RequestBody Grade grade) {
        studentService.addNewGrade(index, name, grade);
    }
}
