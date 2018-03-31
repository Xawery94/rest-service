package com.company.rest.services.impl;

import com.company.rest.dao.StudentRepository;
import com.company.rest.entity.Student;
import com.company.rest.services.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceImplTest {

    private StudentService studentService = mock(StudentService.class);

    @Mock
    private StudentRepository studentRepository;

    private static final String index = "123456";
    private static final List<Student> expectedStudentList = new ArrayList<>();
    private static final Student expectedStudent = new Student();

    @Before
    public void setUp() {
        when(studentRepository.findOneByIndex(index)).thenReturn(expectedStudent);
        when(studentRepository.findAll()).thenReturn(expectedStudentList);
//        when(studentRepository.deleteByIndex(index)).thenReturn(any());
    }

    @Test
    public void getStudent() {
        Student student = studentRepository.findOneByIndex(index);

        assertEquals(expectedStudent, student);
    }

    @Test
    public void getAllStudents() {
        List<Student> studentList = studentRepository.findAll();

        assertEquals(expectedStudentList, studentList);
    }

    @Test
    public void addNewStudent() {
        studentRepository.save(expectedStudent);

        verify(studentRepository, times(1)).save(any());
    }

    @Test
    public void updateStudent(){
        Student student = studentRepository.findOneByIndex(index);

        student.setName("aaa");
        student.setBirthday(new Date());

        studentRepository.save(student);

        verify(studentRepository, times(1)).save(any());
    }

    @Test
    public void deleteStudent(){
        studentRepository.deleteByIndex(index);

        verify(studentRepository, times(1)).deleteByIndex(any());
    }
}