package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserAdapter extends MongoRepository<User, Integer>{

}
