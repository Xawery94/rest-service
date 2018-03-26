package com.company.rest.dao;

import com.company.rest.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {

    Student findOneByIndex(String index);

    void deleteByIndex(String index);

}
