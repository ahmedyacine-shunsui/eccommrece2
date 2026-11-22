package com.example.ecommerce.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.ecommerce.models.Client;


public interface CilentRpository extends MongoRepository<Client, String> {

}
