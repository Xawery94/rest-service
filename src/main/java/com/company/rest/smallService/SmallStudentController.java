package com.company.rest.smallService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/students")
public class SmallStudentController {

    private SmallStudentService studentService;

    @Autowired
    public SmallStudentController(SmallStudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<SmallStudent> retrieveAllStudents() {
        return studentService.retrieveAllStudents();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{index}/courses")
    public List<SmallCourse> retrieveCoursesForStudent(@PathVariable String index) {
        return studentService.retrieveCourses(index);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{index}/courses")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> registerStudentForCourse(
            @PathVariable String index, @RequestBody SmallCourse newCourse) {

        SmallCourse course = studentService.addCourse(index, newCourse);

        if (course == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
                "/{id}").buildAndExpand(course.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{index}/courses/{courseId}")
    public SmallCourse retrieveDetailsForCourse(@PathVariable String index,
                                                @PathVariable String courseId) {
        return studentService.retrieveCourse(index, courseId);
    }

}
