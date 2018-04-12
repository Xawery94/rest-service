package com.company.rest.dao;

import com.company.rest.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface StudentRepository extends MongoRepository<Student, String> {

    Student findOneByIndex(String index);

    void deleteByIndex(String index);

    List<Student> findAllByName(String name);

    List<Student> findAllByLastName(String lastName);

    List<Student> findAllByBirthday(Date birthday);

}
