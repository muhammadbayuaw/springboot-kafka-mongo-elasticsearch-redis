package com.project.dummy.elasticsearch.repository;

import com.project.dummy.elasticsearch.model.EsProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EsProductRepository extends ElasticsearchRepository<EsProduct, String>{
	Page<EsProduct> findAll();
}
