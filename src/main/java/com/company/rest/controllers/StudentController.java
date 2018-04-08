package com.company.rest.controllers;

import com.company.rest.entity.Course;
import com.company.rest.entity.Grade;
import com.company.rest.entity.Student;
import com.company.rest.exceptions.GradeValidationException;
import com.company.rest.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public List<Student> retrieveAllStudents() {
        return studentService.getAllStudents();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> addNewStudent(@RequestBody Student studentBody) {
        Student student = studentService.addStudent(studentBody);

        if (student == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{index}")
                .buildAndExpand(student.getIndex())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{index}")
    public Student retrieveStudent(@PathVariable String index) {
        return studentService.getStudent(index);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{index}")
    @ResponseStatus(HttpStatus.OK)
    public Student updateStudent(@PathVariable String index, @RequestBody Student student) {
        studentService.updateStudent(index, student);
        return student;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{index}")
    public void deleteStudent(@PathVariable String index) {
        studentService.deleteStudent(index);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{index}/courses")
    public List<Course> retrieveCoursesForStudent(@PathVariable String index) {
        return studentService.retrieveAllCourses(index);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{index}/courses")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Course> registerStudentForCourse(@PathVariable String index,
                                                           @RequestBody Course newCourse) {
        Course course = studentService.addNewCourse(index, newCourse);

        if (course == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{courseName}")
                .buildAndExpand(course.getName())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{index}/courses/{courseName}")
    public Course retrieveDetailsForCourse(@PathVariable String index,
                                           @PathVariable String courseName) {
        return studentService.retrieveCourse(index, courseName);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{index}/courses/{courseName}")
    public List<Course> deleteCourse(@PathVariable String index,
                                @PathVariable String courseName) {
        return studentService.deleteCourse(index, courseName);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{index}/courses/{courseName}")
    @ResponseStatus(HttpStatus.OK)
    public Course updateCourseForStudent(@PathVariable String index,
                                         @PathVariable String courseName,
                                         @RequestBody Course newCourse) {
        return studentService.updateCourse(index, courseName, newCourse);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{index}/courses/{courseName}/grades")
    public List<Grade> retrieveAllGradeForCourse(@PathVariable String index,
                                                 @PathVariable String courseName) {
        return studentService.retrieveGrade(index, courseName);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{index}/courses/{courseName}/grades")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Grade> createGradeForCourse(@PathVariable String index,
                                                      @PathVariable String courseName,
                                                      @RequestBody Grade gradeBody) {

        if (gradeBody.getValue() < 2.0 || gradeBody.getValue() > 5.0) {
            throw new GradeValidationException();
        }

        Grade grade = studentService.addNewGrade(index, courseName, gradeBody);

        if (grade == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{value}")
                .buildAndExpand(grade.getValue())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{index}/courses/{courseName}/grades/{value}")
    public List<Grade> retrieveGradeForCourse(@PathVariable String index,
                                              @PathVariable String courseName,
                                              @PathVariable double value) {
        return studentService.getOneGrade(index, courseName, value);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{index}/courses/{courseName}/grades/{value}")
    @ResponseStatus(HttpStatus.OK)
    public Grade updateGradeForCourse(@PathVariable String index,
                                      @PathVariable String courseName,
                                      @PathVariable double value,
                                      @RequestBody Grade newGrade) {

        if (newGrade.getValue() < 2.0 || newGrade.getValue() > 5.0) {
            throw new GradeValidationException();
        }
        return studentService.updateGrade(index, courseName, value, newGrade);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{index}/courses/{courseName}/grades/{value}")
    @ResponseStatus(HttpStatus.OK)
    public List<Grade> deleteGradeForCourse(@PathVariable String index,
                                            @PathVariable String courseName,
                                            @PathVariable double value) {
        return studentService.deleteGrade(index, courseName, value);
    }
}
