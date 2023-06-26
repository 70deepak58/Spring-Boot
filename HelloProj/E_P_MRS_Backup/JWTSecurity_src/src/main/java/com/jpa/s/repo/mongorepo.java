package com.jpa.s.repo;



import org.springframework.data.mongodb.repository.MongoRepository;


import com.jpa.s.entity.UserInfo;

public interface mongorepo extends MongoRepository<UserInfo, String> {

	

}
