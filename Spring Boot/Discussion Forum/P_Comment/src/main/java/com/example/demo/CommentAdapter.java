package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentAdapter extends MongoRepository<Comment, Integer>{

}
