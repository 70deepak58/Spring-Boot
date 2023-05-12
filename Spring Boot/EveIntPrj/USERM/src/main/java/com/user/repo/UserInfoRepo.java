package com.user.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.user.entity.UserInfo;

public interface UserInfoRepo extends MongoRepository<UserInfo, String>{
	Optional<UserInfo> findByName(String id);
}
