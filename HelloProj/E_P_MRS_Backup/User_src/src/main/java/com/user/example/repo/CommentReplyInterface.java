package com.user.example.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.user.example.entity.CommentReply;

public interface CommentReplyInterface extends MongoRepository<CommentReply, String> {

}
