package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MyImageAdapter extends MongoRepository<MyImage, Long>{

}
