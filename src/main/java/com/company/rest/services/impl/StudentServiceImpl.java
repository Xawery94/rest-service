package com.company.rest.services.impl;

import com.company.rest.dao.StudentRepository;
import com.company.rest.entity.Student;
import com.company.rest.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
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
        studentRepository.save(body);
    }
}
