package com.jpa.s.service;

import java.util.Arrays;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jpa.s.entity.Product;
import com.jpa.s.entity.UserInfo;
import com.jpa.s.repo.mongorepo;



@Service
public class Servvice {
	
	@Autowired
	private mongorepo repo;
	

	
	public ResponseEntity<?> addUser(UserInfo userInfo) {
//		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		repo.save(userInfo);
		 return ResponseEntity.ok().build();
	}
	


	


	
	
}
