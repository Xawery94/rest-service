package com.company.rest.services.impl;

import com.company.rest.dao.StudentRepository;
import com.company.rest.entity.Course;
import com.company.rest.entity.Grade;
import com.company.rest.entity.Student;
import com.company.rest.exceptions.AddNewGradeException;
import com.company.rest.exceptions.StudentDataExceptions;
import com.company.rest.exceptions.StudentDeleteException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    private static final String index = "123456";
    private static final List<Student> expectedStudentList = new ArrayList<>();
    private static final Student expectedStudent = new Student();
    private static final List<Course> courseTemplate = new ArrayList<>(Collections.singleton(new Course(1, "Spring", "Arnold")));
    private static final List<Grade> gradeTemplate = new ArrayList<>(Collections.singleton(new Grade(1, 2.0, new Date(), "Spring")));
    private static final List<Course> expectedCourseList = new ArrayList<>(courseTemplate);
    private static final List<Grade> expectedGradeList = new ArrayList<>(gradeTemplate);
    private static final Date date = new Date();

    @Before
    public void setUp() {
        expectedStudent.setIndex(index);
        expectedStudent.setName("Jan");
        expectedStudent.setLastName("Kac");
        expectedStudent.setBirthday(new Date());
        expectedStudent.setCourses(courseTemplate);
        expectedStudent.setGrades(gradeTemplate);

        when(studentRepository.findOneByIndex(index)).thenReturn(expectedStudent);
        when(studentRepository.findAll()).thenReturn(expectedStudentList);
    }

    @Test
    public void shouldReturnOeStudent() {
        Student student = studentService.getStudent(index);

        assertEquals(expectedStudent, student);
    }

    @Test
    public void shouldReturnAllStudents() {
        List<Student> studentList = studentService.getAllStudents();

        assertEquals(expectedStudentList, studentList);
    }

    @Test
    public void shouldAddNewStudent() {
        Student newStudent = new Student("122112", "Adam", "Kot", new Date());
        studentService.addStudent(newStudent);

        verify(studentRepository, times(1)).save(any());
    }

    @Test(expected = StudentDataExceptions.class)
    public void ShouldThrowExceptionOnAddNewStudent() {
        studentService.addStudent(new Student());
    }

    @Test
    public void shouldUpdateStudent() {
        Student student = studentRepository.findOneByIndex(index);

        student.setName("aaa");
        student.setBirthday(new Date());

        studentRepository.save(student);

        verify(studentRepository, times(1)).save(any());
    }

    @Test
    public void shouldDeleteStudent() {
        studentService.deleteStudent(index);

        verify(studentRepository, times(1)).deleteByIndex(any());
    }

    @Test
    public void shouldReturnCourseList() {
        List<Course> courseList = studentService.retrieveAllCourses(index);

        assertEquals(expectedCourseList, courseList);
    }

    @Test
    public void shouldReturnGradeList() {
        List<Grade> gradeList = studentService.retrieveGrade(index, "Spring");

        assertEquals(expectedGradeList, gradeList);
    }

    @Test(expected = StudentDeleteException.class)
    public void shouldThrowExceptionOnDeleteStudent() {
        studentService.deleteCourse("111", "AAA");
    }

    @Test(expected = AddNewGradeException.class)
    public void shouldThrowExceptionOnAddingNewGradeBelowLimit() {
        Grade grade = new Grade(1, 1.0, date, "AAA");
        studentService.addNewGrade(index, "AAA", grade);
    }

    @Test(expected = AddNewGradeException.class)
    public void shouldThrowExceptionOnAddingNewGradeAboveLimit() {
        Grade grade = new Grade(1, 5.5, date, "AAA");
        studentService.addNewGrade(index, "AAA", grade);
    }

    @Test
    public void shouldAddNewGradeMin() {
        Grade grade = new Grade(1, 2.0, date, "AAA");
        studentService.addNewGrade(index, "Spring", grade);

        verify(studentRepository, times(1)).save(any());
    }

    @Test
    public void shouldAddNewGradeMax() {
        Grade grade = new Grade(1, 5.0, date, "AAA");
        studentService.addNewGrade(index, "Spring", grade);

        verify(studentRepository, times(1)).save(any());
    }

    @Test(expected = StudentDataExceptions.class)
    public void shouldThrowExceptionOnDeleteGrade() {
        studentService.deleteGrade("111", "AAA", 1);
    }

    @Test
    public void shouldTDeleteGrade() {
        studentService.deleteGrade(index, "Spring", 1);

        verify(studentRepository, times(1)).save(any());
    }
}