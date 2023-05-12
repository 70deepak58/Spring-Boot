package com.product.repo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.product.entity.Product;

public interface ProductRepo  extends MongoRepository<Product, ObjectId>{

}
