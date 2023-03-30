package com.example.demo.repos;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	

}
