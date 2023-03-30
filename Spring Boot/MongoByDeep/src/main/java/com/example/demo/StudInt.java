package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudInt extends MongoRepository<Student, Integer> {

}
