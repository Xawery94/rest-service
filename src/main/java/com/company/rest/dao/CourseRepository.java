package com.company.rest.dao;

import com.company.rest.entity.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CourseRepository extends MongoRepository<Course, String> {

    Course findFirstByName(String name);

    Course findFirstById(int id);

    void deleteById(int id);

    List<Course> findAllByTeacher(String name);

    Course findById(int id);
}
