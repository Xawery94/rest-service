package com.company.rest.dao;

import com.company.rest.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, Student> {

    Student findOneByIndex(String index);

}
