package com.project.dummy.mongo.repository;

import com.project.dummy.mongo.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Muhammad Bayu Agusto
 *
 */
@Repository
public interface ProductRepository extends MongoRepository<Product, String>{

}
