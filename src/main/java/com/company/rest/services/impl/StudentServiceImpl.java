package com.company.rest.services.impl;

import com.company.rest.dao.StudentRepository;
import com.company.rest.entity.Course;
import com.company.rest.entity.Grade;
import com.company.rest.entity.Student;
import com.company.rest.exceptions.AddNewGradeException;
import com.company.rest.exceptions.StudentDataExceptions;
import com.company.rest.exceptions.StudentDeleteException;
import com.company.rest.services.CourseService;
import com.company.rest.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
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
    public Student addStudent(Student body) {
        if (body.getIndex() == null || body.getName() == null || body.getLastName() == null || body.getBirthday().toString() == null) {
            throw new StudentDataExceptions();
        }

        studentRepository.save(body);
        return body;
    }

    @Override
    public Student updateStudent(String index, Student student) {
        Student currentStudent = studentRepository.findOneByIndex(index);

        currentStudent.setName(student.getName());
        currentStudent.setLastName(student.getLastName());
        currentStudent.setBirthday(student.getBirthday());

        studentRepository.save(currentStudent);
        return currentStudent;
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
    public List<Course> retrieveAllCourses(String index) {
        if (index != null) {
            Student student = studentRepository.findOneByIndex(index);
            return student.getCourses();
        } else {
            return null;
        }
    }

    @Override
    public Course retrieveCourse(String index, String courseName) {
        Student student = studentRepository.findOneByIndex(index);

        if (student == null) {
            return null;
        }

        for (Course course : student.getCourses()) {
            if (course.getName().equals(courseName)) {
                return course;
            }
        }

        return null;
    }

    @Override
    public List<Grade> retrieveGrade(String index, String courseName) {
        Student student = studentRepository.findOneByIndex(index);

        if (student.getGrades() == null) {
            return null;
        }

        return student.getGrades().stream()
                .filter(x -> x.getCourseName().equals(courseName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Grade> getOneGrade(String index, String courseName, double value) {
        List<Grade> grades = retrieveGrade(index, courseName);
        List<Grade> gradesToReturn = new ArrayList<>();

        for (Grade grade : grades) {

            if (grade.getValue() == value) {
                gradesToReturn.add(grade);
            }
        }

        return gradesToReturn;
    }

    @Override
    public Student deleteCourse(String index, String courseName) {
        Student student = studentRepository.findOneByIndex(index);
        if (student == null) {
            throw new StudentDeleteException();
        }

        List<Course> courses = student.getCourses();
        courses.removeIf(o -> o.getName().equals(courseName));
        student.setCourses(courses);
        studentRepository.save(student);

        return student;
    }

    @Override
    public Grade addNewGrade(String index, String courseName, Grade gradeBody) {
        if (index != null) {
            Student student = studentRepository.findOneByIndex(index);
            Grade grade = new Grade(gradeBody.getValue(), gradeBody.getCourseName());
            List<Grade> gradeList = student.getGrades();

            double gradeValue = grade.getValue();

            if (gradeValue < 2.0 || gradeValue > 5.0) {
                throw new AddNewGradeException();
            }

            if (gradeList == null) {
                student.setGrades(Collections.singletonList(grade));
            } else {
                gradeList.add(grade);
            }

            studentRepository.save(student);
        }

        return null;
    }

    @Override
    public Course addNewCourse(String index, Course course) {
        if (index != null) {
            Student student = studentRepository.findOneByIndex(index);

            List<Course> courseList = student.getCourses();

            if (courseList == null) {
                student.setCourses(Collections.singletonList(course));
            } else {
                if (containsCourse(student.getCourses(), course.getName())) {
                    return null;
                } else {
                    student.getCourses().add(course);
                }
            }

            studentRepository.save(student);
        }
        return null;
    }

    private boolean containsCourse(final List<Course> list, String name) {
        return list.stream().anyMatch(c -> c.getName().equals(name));
    }

//    @Override
//    public Optional<Course> getCourseForStudent(String index, String name) {
//        if (index != null || name != null) {
//            Student student = studentRepository.findOneByIndex(index);
//
//            final Optional<Course> foundCourse = student.getCourses().stream()
//                    .filter(course -> course.getName().equals(name))
//                    .findFirst();
//
//            if (!foundCourse.isPresent()) {
//                throw new StudentCourseFoundException();
//            } else {
//                return foundCourse;
//            }
//        } else {
//            throw new StudentDataExceptions();
//        }
//
//    }
//
//    @Override
//    public void addNewGradeaaa(String index, String name, Grade grade) {
//        *//*if (index != null) {
//            Course course = courseService.getCourse(name);
//            Grade gradeObj = new Grade(grade.getValue(), index);
//
//            List<Grade> gradeList = course.getGrade();
//
//            if (gradeList == null) {
//                List<Grade> gradeList2 = new ArrayList<>();
//                gradeList2.add(gradeObj);
//                course.setGrade(gradeList2);
//                courseService.saveGradeInCourse(course);
//            } else {
//                gradeList.add(gradeObj);
//                course.setGrade(gradeList);
//                courseService.saveGradeInCourse(course);
//            }
//        } else {
//            throw new StudentDataExceptions();
//        }*//*
//    }*/
//
}
