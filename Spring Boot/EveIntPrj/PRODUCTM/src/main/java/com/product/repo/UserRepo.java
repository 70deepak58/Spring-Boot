package com.product.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.product.entity.User;

public interface UserRepo extends MongoRepository<User, String>{

}
