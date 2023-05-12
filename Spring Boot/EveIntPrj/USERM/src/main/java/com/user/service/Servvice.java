package com.user.service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.entity.Product;
import com.user.entity.UserInfo;
import com.user.repo.UserInfoRepo;
import jakarta.annotation.PostConstruct;

@Service
public class Servvice {
	
	@Autowired
	private UserInfoRepo userInfoRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	Product p1=new Product("ex",1,110);

	Product p2=new Product("force",1,50);
	Product p3=new Product("Viru",2,67);
	Product p4=new Product("jeet",3,247);
	
	List<Product>list=Arrays.asList(p1,p2,p3,p4);
//   repos.sava(list);




	
	
	public List<Product> getProduct()
	{
		return list;
	}

	public String addUser(UserInfo userInfo) {
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		userInfoRepo.save(userInfo);
		return "user added to DB";
	}

	public List<UserInfo> getuser() {
		
		return userInfoRepo.findAll();
	}

	public String del() {
	
		userInfoRepo.deleteAll();
		return "deleted";
	}

	
	
}
