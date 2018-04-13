package com.company.rest.controllers;

import com.company.rest.entity.Course;
import com.company.rest.entity.Grade;
import com.company.rest.entity.Student;
import com.company.rest.exceptions.AddNewGradeException;
import com.company.rest.exceptions.GradeValidationException;
import com.company.rest.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
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
    public List<Student> retrieveAllStudentsByNameAndLastName(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "lastName", required = false) String lastName) {
        return studentService.getStudentsByNameAndLastName(name, lastName);
    }

    @RequestMapping(method = RequestMethod.GET, params = "birthday")
    public List<Student> retrieveAllStudentsByBirthday(
            @RequestParam("birthday") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return studentService.getStudentsByBirthday(date);
    }

    @RequestMapping(method = RequestMethod.GET, params = "birthdayBefore")
    public List<Student> retrieveAllStudentsByBirthdayBefore(
            @RequestParam("birthdayBefore") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return studentService.getStudentsByBirthdayBefore(date);
    }

    @RequestMapping(method = RequestMethod.GET, params = "birthdayAfter")
    public List<Student> retrieveAllStudentsByBirthdayAfter(
            @RequestParam("birthdayAfter") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return studentService.getStudentsByBirthdayAfter(date);
    }

    @RequestMapping(method = RequestMethod.POST)
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
    public ResponseEntity<Student> retrieveStudent(@PathVariable String index) {
        Student student = studentService.getStudent(index);

        if (student != null) {
            return new ResponseEntity<>(student, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
    public ResponseEntity<Course> retrieveDetailsForCourse(@PathVariable String index,
                                                           @PathVariable String courseName) {
        Course course = studentService.retrieveCourse(index, courseName);

        if (course != null) {
            return new ResponseEntity<>(course, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{index}/courses/{courseName}")
    public void deleteCourse(@PathVariable String index,
                             @PathVariable String courseName) {
        studentService.deleteCourse(index, courseName);
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

    @RequestMapping(value = "/{index}/courses/{courseName}/grades", method = RequestMethod.GET, params = "valueGrater")
    public List<Grade> retrieveAllGradeForCourseGraterThan(@PathVariable String index,
                                                           @PathVariable String courseName,
                                                           @RequestParam("valueGrater") double value) {
        return studentService.retrieveGradeGraterThan(index, courseName, value);
    }

    @RequestMapping(value = "/{index}/courses/{courseName}/grades", method = RequestMethod.GET, params = "valueLess")
    public List<Grade> retrieveAllGradeForCourseLessThan(@PathVariable String index,
                                                         @PathVariable String courseName,
                                                         @RequestParam("valueLess") double value) {
        return studentService.retrieveGradeLessThan(index, courseName, value);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{index}/courses/{courseName}/grades")
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

    @RequestMapping(method = RequestMethod.GET, value = "/{index}/courses/{courseName}/grades/{id}")
    public ResponseEntity<Grade> retrieveGradeForCourse(@PathVariable String index,
                                                        @PathVariable String courseName,
                                                        @PathVariable Integer id) {
        Grade grade = studentService.getOneGrade(index, courseName, id);

        if (grade != null) {
            return new ResponseEntity<>(grade, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{index}/courses/{courseName}/grades/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Grade updateGradeForCourse(@PathVariable String index,
                                      @PathVariable String courseName,
                                      @PathVariable Integer id,
                                      @RequestBody Grade newGrade) {

        if (newGrade.getValue() < 2.0 || newGrade.getValue() > 5.0) {
            throw new AddNewGradeException();
        }
        return studentService.updateGrade(index, courseName, id, newGrade);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{index}/courses/{courseName}/grades/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteGradeForCourse(@PathVariable String index,
                                     @PathVariable String courseName,
                                     @PathVariable Integer id) {
        studentService.deleteGrade(index, courseName, id);
    }
}
