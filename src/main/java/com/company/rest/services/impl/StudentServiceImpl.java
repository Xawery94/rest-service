package com.company.rest.services.impl;

import com.company.rest.dao.CourseRepository;
import com.company.rest.dao.StudentRepository;
import com.company.rest.entity.Course;
import com.company.rest.entity.Grade;
import com.company.rest.entity.Student;
import com.company.rest.exceptions.StudentCourseFoundException;
import com.company.rest.exceptions.StudentDataExceptions;
import com.company.rest.exceptions.StudentDeleteException;
import com.company.rest.services.CourseService;
import com.company.rest.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    private CourseRepository courseRepository;
    private CourseService courseService;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, CourseService courseService) {
        this.studentRepository = studentRepository;
        this.courseService = courseService;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudent(String index) {
        return studentRepository.findOneByIndex(index);
    }

    @Override
    public void addStudent(Student body) {
        if (body.getIndex() == null || body.getName() == null || body.getLastName() == null || body.getBirthday().toString() == null) {
            throw new StudentDataExceptions();
        }

        //List<Course> courseList = courseService.getAllCourses();
        //body.setCourses(courseList);
        studentRepository.save(body);
    }

    @Override
    public void deleteStudent(String index) {
        if (index != null) {
            studentRepository.deleteByIndex(index);
        } else {
            throw new StudentDeleteException();
        }
    }

    @Override
    public void addNewCourse(String index, List<Course> course) {
        if (index != null) {
            Student student = studentRepository.findOneByIndex(index);
            List<Course> courseList = student.getCourses();

            if (courseList == null) {
                student.setCourses(course);
                studentRepository.save(student);
            } else {
                courseList.addAll(course);
                student.setCourses(courseList);
                studentRepository.save(student);
            }
        } else {
            throw new StudentDataExceptions();
        }
    }

    @Override
    public Optional<Course> getCourseForStudent(String index, String name) {
        if (index != null || name != null) {
            Student student = studentRepository.findOneByIndex(index);

            final Optional<Course> foundCourse = student.getCourses().stream()
                    .filter(course -> course.getName().equals(name))
                    .findFirst();

            if (!foundCourse.isPresent()) {
                throw new StudentCourseFoundException();
            } else {
                return foundCourse;
            }
        } else {
            throw new StudentDataExceptions();
        }

    }

    @Override
    public void addNewGrade(String index, String name, Grade grade) {
        if (index != null) {
            Course course = courseService.getCourse(name);
            Grade gradeObj = new Grade(grade.getMark(), index);

            List<Grade> gradeList = course.getGrade();

            if (gradeList == null) {
                List<Grade> gradeList2 = new ArrayList<>();
                gradeList2.add(gradeObj);
                course.setGrade(gradeList2);
                courseService.saveGradeInCourse(course);
            } else {
                gradeList.add(gradeObj);
                course.setGrade(gradeList);
                courseService.saveGradeInCourse(course);
            }
        } else {
            throw new StudentDataExceptions();
        }

    }
}
