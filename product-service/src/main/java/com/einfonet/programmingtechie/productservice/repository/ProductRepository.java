package com.einfonet.programmingtechie.productservice.repository;

import com.einfonet.programmingtechie.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

}
