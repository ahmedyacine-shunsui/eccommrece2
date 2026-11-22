package com.example.ecommerce.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.ecommerce.models.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}
