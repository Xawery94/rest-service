package com.company.rest.controllers;

import com.company.rest.entity.Course;
import com.company.rest.entity.Grade;
import com.company.rest.entity.Student;
import com.company.rest.services.CourseService;
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
    private CourseService courseService;

    @Autowired
    public StudentController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
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

    @RequestMapping(method = RequestMethod.PUT, value = "/{index}/courses")
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
    public Student deleteCourse(@PathVariable String index,
                                @PathVariable String courseName) {
        return studentService.deleteCourse(index, courseName);
    }

    // TODO Finish endpoint for grades

//    @RequestMapping(method = RequestMethod.GET, value = "/{index}/grades")
//    public List<Grade> retrieveAllGradesForStudents(@PathVariable String index) {
//        return studentService.retrieveGradeForStudent(index);
//    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "/{index}/grades/{value}")
//    public List<Grade> retrieveAllGradesForStudents(@PathVariable String index, @PathVariable double value) {
//        return studentService.retrieveOneTypeGradeForStudent(index, value);
//    }

    @RequestMapping(method = RequestMethod.GET, value = "/{index}/courses/{courseName}/grades")
    public List<Grade> retrieveAllGradeForCourse(@PathVariable String index,
                                                 @PathVariable String courseName) {
        return studentService.retrieveGrade(index, courseName);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{index}/courses/{courseName}/grades")
    public ResponseEntity<Grade> createGradeForCourse(@PathVariable String index,
                                                      @PathVariable String courseName,
                                                      @RequestBody Grade gradeBody) {

        Grade grade = studentService.addNewGrade(index, courseName, gradeBody);

        if (grade == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{value}")
                .buildAndExpand(grade.getValue())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{index}/courses/{courseId}/grades/{value}")
    public List<Grade> retrieveGradeForCourse(@PathVariable String index,
                                              @PathVariable String courseId,
                                              @PathVariable double value) {
        return studentService.getOneGrade(index, courseId, value);
    }
}
