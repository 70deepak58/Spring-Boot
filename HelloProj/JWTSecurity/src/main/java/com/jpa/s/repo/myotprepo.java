package com.jpa.s.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jpa.s.entity.MyOTP;

public interface myotprepo extends MongoRepository<MyOTP, String>{

}
