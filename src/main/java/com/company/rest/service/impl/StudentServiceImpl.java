package com.company.rest.service.impl;

import com.company.rest.entity.Student;
import com.company.rest.repository.Repository;
import com.company.rest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private Repository repository;

    @Autowired
    public StudentServiceImpl(Repository repository) {
        this.repository = repository;
    }

    @Override
    public List<Student> getAllStudents() {
        return repository.getAllStudents();
    }

    @Override
    public Student getStudent(String index) {
        return repository.getStudent(index);
    }

    @Override
    public void addStudent(Student body) {
        repository.addStudent(body);
    }
}
