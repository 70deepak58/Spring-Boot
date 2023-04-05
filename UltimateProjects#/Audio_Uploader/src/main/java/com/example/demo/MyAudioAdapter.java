package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MyAudioAdapter extends MongoRepository<MyAudio, Long>{

}
