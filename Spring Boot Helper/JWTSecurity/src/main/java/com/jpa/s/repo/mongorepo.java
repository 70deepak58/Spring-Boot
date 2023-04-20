package com.jpa.s.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

//import com.jpa.s.entity.Product;
import com.jpa.s.entity.UserInfo;

public interface mongorepo extends MongoRepository<UserInfo, Integer> {

	Optional<UserInfo> findByName(String id);

}
