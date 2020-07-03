package com.bagayugu.dummy.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bagayugu.dummy.mongo.model.Product;

/**
 * @author Muhammad Bayu Agusto
 *
 */
@Repository
public interface ProductRepository extends MongoRepository<Product, String>{

}
