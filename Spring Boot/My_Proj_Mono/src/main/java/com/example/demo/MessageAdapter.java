package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageAdapter extends MongoRepository<Message, Integer>{

}
