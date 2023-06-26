package com.user.example.repo;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.user.example.entity.Product;

public interface ProductInterface extends MongoRepository<Product, String> {
	
	
}
