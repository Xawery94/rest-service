package com.company.rest.dao;

import com.company.rest.entity.Grade;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GradeRepository extends MongoRepository<Grade, Integer> {
}
