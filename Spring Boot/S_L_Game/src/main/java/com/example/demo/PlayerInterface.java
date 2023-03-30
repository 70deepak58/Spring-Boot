package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerInterface extends MongoRepository<Player, Integer>{

}
