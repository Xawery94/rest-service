package com.company.rest.controllers;

import com.company.rest.entity.Student;
import com.company.rest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/students")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{index}")
    @ResponseStatus(HttpStatus.OK)
    public Student getStudent(@PathVariable String index){
        return studentService.getStudent(index);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addStudent(@RequestBody Student body) {
        studentService.addStudent(body);
    }

}
