package com.bagayugu.dummy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bagayugu.dummy.elasticsearch.model.EsProduct;
import com.bagayugu.dummy.elasticsearch.repository.EsProductRepository;
import com.bagayugu.dummy.response.ListProductResponse;
import com.bagayugu.dummy.util.ResponseUtil;
import com.bagayugu.dummy.util.UrlUtil;

@RestController
@RequestMapping(path = UrlUtil.V1.PRODUCTS)
public class ElasticProductController {

	@Autowired
	private EsProductRepository repository;
	
	@GetMapping
	public ResponseEntity<ListProductResponse> findAllViaElastic(){
		List<EsProduct> products = repository.findAll().getContent();
		
		return new ResponseEntity<ListProductResponse>(
				new ListProductResponse(products, ResponseUtil.Message.SUCCESS, ResponseUtil.Code.SUCCESS),
				HttpStatus.OK
			);
	}
	
}
