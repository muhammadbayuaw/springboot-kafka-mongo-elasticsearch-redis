package com.bagayugu.dummy.redis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bagayugu.dummy.message.ProductDto;

@Repository
public interface RedisProductRepository extends CrudRepository<ProductDto, String>{

}
