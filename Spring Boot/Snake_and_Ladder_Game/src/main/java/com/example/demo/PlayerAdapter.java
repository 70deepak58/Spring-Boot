package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerAdapter extends MongoRepository<Player, Integer>{

}
