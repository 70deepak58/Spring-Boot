package com.user.example.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.user.example.entity.Comment;

public interface CommentInterface extends MongoRepository<Comment, String>{

}
