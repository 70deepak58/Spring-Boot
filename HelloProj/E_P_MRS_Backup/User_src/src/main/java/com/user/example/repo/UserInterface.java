package com.user.example.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.user.example.entity.User;

public interface UserInterface extends MongoRepository<User, String>{


}
