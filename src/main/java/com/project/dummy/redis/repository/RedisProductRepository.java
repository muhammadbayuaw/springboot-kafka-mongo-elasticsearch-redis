package com.project.dummy.redis.repository;

import com.project.dummy.message.ProductDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisProductRepository extends CrudRepository<ProductDto, String>{

}
