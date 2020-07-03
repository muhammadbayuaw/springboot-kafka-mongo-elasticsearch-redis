package com.bagayugu.dummy.elasticsearch.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.bagayugu.dummy.elasticsearch.model.EsProduct;

@Repository
public interface EsProductRepository extends ElasticsearchRepository<EsProduct, String>{
	Page<EsProduct> findAll();
}
