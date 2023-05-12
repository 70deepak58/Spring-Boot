package com.user.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.user.entity.MyOTP;

public interface MyOTPRepo extends MongoRepository<MyOTP, String>{

}
