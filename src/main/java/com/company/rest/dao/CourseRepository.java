package com.company.rest.dao;

import com.company.rest.entity.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepository extends MongoRepository<Course, String> {

    Course findFirstByName(String name);
}
