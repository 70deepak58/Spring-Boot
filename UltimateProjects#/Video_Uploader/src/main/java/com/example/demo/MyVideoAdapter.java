package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MyVideoAdapter extends MongoRepository<MyVideo, Long>{

}
